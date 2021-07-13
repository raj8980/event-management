package com.em.cz.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.em.cz.daos.UserRegistrationDAO;
import com.em.cz.databeans.UserRegistrationDataBean;

@Service
public class UserRegistrationService {

	@Autowired
	UserRegistrationDAO userRegistrationDAO;
	@Autowired
	PasswordEncoder becPasswordEncoder;

	private Path fileStorageLocation;
	private String extendedPath;
	

	public Map<String, Object> registerUser(UserRegistrationDataBean userDB) throws Exception {
		System.out.println(userDB.toString());
		Object[] params = new Object[] { userDB.getFirstName(), userDB.getLastName(), userDB.getEmail(),
				becPasswordEncoder.encode(userDB.getPassword()), userDB.getMobileNo(), userDB.getCountry(),
				userDB.getInterest(), 1 };
		long userId = userRegistrationDAO.registerUser(params);
		if (userId > 0) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("status", 1001);
			resultMap.put("userId", userId);
			return resultMap;
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", 1002);
		resultMap.put("userId", userId);
		return resultMap;
	}

	
	
	public long saveFileDtls(int userId, MultipartFile file,int fileTypeID) throws Exception {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		//System.out.println("extension:"+extension);
		String filePath = "/event-docs/" + 1 + "/" + userId;
		this.extendedPath=filePath;
		long fileUploadID = userRegistrationDAO.uploadFile(userId, file.getOriginalFilename(), filePath,fileTypeID);
		String fileName = fileUploadID + "." + extension;
		//System.out.println("fileName:" + fileName);
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
			}
			this.fileStorageLocation = Paths.get("C:\\Users\\RAJ\\Downloads"+File.separator+extendedPath).toAbsolutePath().normalize();

			try {
				Files.createDirectories(this.fileStorageLocation);
			} catch (Exception ex) {
				throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
			}
			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileUploadID;
		} catch (IOException ex) {
			throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
		}

	}



	public int removeUserProfile(Map<String,Object> fileUploadMap) {
		System.out.println(fileUploadMap);
		int fileUploadID=Integer.parseInt(fileUploadMap.get("fileUploadId").toString());
		int userID=Integer.parseInt(fileUploadMap.get("userId").toString());
		return userRegistrationDAO.removeUserProfile(fileUploadID,userID);
	}



	public Object orgRegister(UserRegistrationDataBean userDB) throws Exception {
		System.out.println(userDB.toString());
		Object[] params = new Object[] { userDB.getOrgName(), userDB.getAddress(), userDB.getEmail(),
				becPasswordEncoder.encode(userDB.getPassword()), userDB.getMobileNo(), userDB.getCountry(),
				userDB.getState(), 2 };
		long userId = userRegistrationDAO.orgRegistration(params);
		if (userId > 0) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("status", 1001);
			resultMap.put("userId", userId);
			return resultMap;
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", 1002);
		resultMap.put("userId", userId);
		return resultMap;
	}
}
