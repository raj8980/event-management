package com.em.cz.daos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {

	@Autowired GenericDAO genericDAO;
	
	public List<Map<String, Object>> getUserByEmail(String emailId) {
		String query=" SELECT emailId AS emailID, password AS password FROM user_register WHERE emailId=? ";
		return genericDAO.executeQueryForList(query,emailId);
	}

	public List<Map<String, Object>> getOrgByEmail(String emailId) {
		String query=" SELECT emailid AS emailID ,password  AS password FROM organization_register WHERE emailid=? ";
		return genericDAO.executeQueryForList(query,emailId);
	}

	public List<Map<String,Object>> getUserDtlsByEmail(String emailId){
		String query="SELECT userid,firstname,lastname,mobileno,countryid,interestid,emailid,usertypeid FROM user_register WHERE xstatus=1 AND emailid=?";
		return genericDAO.executeQueryForList(query,emailId);
	}

	public List<Map<String, Object>> getOrgDtlsByEmail(String email) {
		String query="SELECT orgregid,orgname,usertypeid,countryid,stateid,address,emailid,mobileno FROM organization_register WHERE xstatus=1 AND emailid=?";
		return genericDAO.executeQueryForList(query,email);
	}
}
