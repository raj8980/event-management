package com.em.cz.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.em.cz.databeans.UserRegistrationDataBean;

@Repository
public class UserRegistrationDAO {

	@Autowired GenericDAO genericDAO;
	
	public long registerUser(Object[] params) throws Exception {
		StringBuilder query=new StringBuilder();
		query.append(" INSERT INTO user_register (firstname,lastname,emailid,password,mobileno,countryid,interestid,usertypeid,xstatus,createdon) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,1,UTC_TIMESTAMP()) ");
		
		return genericDAO.executeQueryWithInsertId(query.toString(), "userid",params );
	}

	public long uploadFile(int userId, String originalFilename,String fileUploadPath,int fileTypeID) throws Exception {
		StringBuilder query=new StringBuilder();
		query.append(" INSERT INTO common_filedetails (filetypeid,filename,path,xstatus,objectid,createdby,createdon) ");
		query.append(" VALUES(?,?,?,1,?,?,UTC_TIMESTAMP()) ");
		Object[] params= new Object[] {fileTypeID,originalFilename,fileUploadPath,userId,userId};
		return genericDAO.executeQueryWithInsertId(query.toString(), "filedtlid",params);
	}

	public int removeUserProfile(int fileUploadID,int userID) {
		String query=" UPDATE common_filedetails SET xstatus=2, updatedon=UTC_TIMESTAMP(),updatedby=? WHERE filedtlid=? ";
		return genericDAO.executeQueryForInsertion(query.toString(), new Object[] {userID,fileUploadID});
		
	}

	public long orgRegistration(Object[] params) throws Exception {
		StringBuilder query=new StringBuilder();
		query.append(" INSERT INTO organization_register (orgname,address,emailid,password,mobileno,countryid,stateid,usertypeid,xstatus,createdon) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,1,UTC_TIMESTAMP()) ");
		
		return genericDAO.executeQueryWithInsertId(query.toString(), "orgregid",params );
	}

}
