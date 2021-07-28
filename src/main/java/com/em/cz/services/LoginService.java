package com.em.cz.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.em.cz.daos.LoginDAO;
import com.em.cz.databeans.UserRegistrationDataBean;

@Service
public class LoginService {
	
	@Autowired LoginDAO loginDAO;
	
	public List<Map<String,Object>> getUserByEmail(String emailId){
		List<Map<String,Object>> userDetails=loginDAO.getUserByEmail(emailId);
		if(userDetails!=null && !userDetails.isEmpty()) {
			return userDetails;
		}else {
			return loginDAO.getOrgByEmail(emailId);
		}
	}

	public UserRegistrationDataBean getUserOrgDtlsByEmail(String email) {
		 List<Map<String,Object>> userDtlList=loginDAO.getUserDtlsByEmail(email);
		
		
	
		List<Map<String,Object>> orgDtlList=loginDAO.getOrgDtlsByEmail(email);
	
		if(userDtlList!=null && !userDtlList.isEmpty()) {
			Map<String,Object> userDtlMap=userDtlList.get(0);
			UserRegistrationDataBean userDB=new UserRegistrationDataBean();
			userDB.setUserTypeId(Integer.parseInt(userDtlMap.get("usertypeid").toString()));
			userDB.setFirstName(userDtlMap.get("firstname").toString());
			userDB.setLastName(userDtlMap.get("lastname").toString());
			userDB.setUserId(Integer.parseInt(userDtlMap.get("userid").toString()));
			userDB.setMobileNo(userDtlMap.get("mobileno").toString());
			userDB.setCountry(userDtlMap.get("countryid").toString());
			userDB.setInterest(userDtlMap.get("interestid").toString());
			userDB.setEmail(userDtlMap.get("emailid").toString());
	
			return userDB;
		}else if(orgDtlList!=null && !orgDtlList.isEmpty()) {
			Map<String,Object> orgDtlMap=orgDtlList.get(0);
			UserRegistrationDataBean userDB=new UserRegistrationDataBean();
			userDB.setUserTypeId(Integer.parseInt(orgDtlMap.get("usertypeid").toString()));
			userDB.setAddress(orgDtlMap.get("address").toString());
			userDB.setOrgName(orgDtlMap.get("orgname").toString());
			userDB.setOrgRegId(Integer.parseInt(orgDtlMap.get("orgregid").toString()));
			userDB.setMobileNo(orgDtlMap.get("mobileno").toString());
			userDB.setCountry(orgDtlMap.get("countryid").toString());
			userDB.setState(orgDtlMap.get("stateid").toString());
			userDB.setEmail(orgDtlMap.get("emailid").toString());
			
			return userDB;
		}

		return null;
	}

	
}
