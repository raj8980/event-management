package com.em.cz.security.configs;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.em.cz.daos.GenericDAO;
import com.em.cz.databeans.UserRegistrationDataBean;
//implements AuthenticationProvider 
public class CustomAuthenticationProvider {
//
//	@Autowired GenericDAO genericDAO;
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		
//		UserRegistrationDataBean userBean=new UserRegistrationDataBean();
//		String userPrincipal=null;
//		boolean isString=false;
//		if(authentication.getPrincipal() instanceof UserRegistrationDataBean) {
//			userBean=(UserRegistrationDataBean) authentication.getPrincipal();
//			return authentication;
//		}else {
//			userPrincipal=authentication.getName();
//			isString=true;
//			try {
//				
				//String userDtlsQuery=" SELECT userid,firstname,lastname,mobileno,countryid,interestid,emailid,usertypeid FROM user_register WHERE xstatus=1 AND emailid=?";
			//	System.out.println("here 2");
//					List<Map<String,Object>> userDtlList=genericDAO.executeQueryForList(userDtlsQuery,isString?userPrincipal:userBean.getEmail());
//					System.out.println("userDtlList:"+userDtlList.toString());
//					String orgDtlsQuery="SELECT orgregid,orgname,usertypeid,countryid,stateid,address,emailid,mobileno FROM organization_register WHERE xstatus=1 AND emailid=? ";
//					
//					System.out.println("here 3");
//					List<Map<String,Object>> orgDtlList=genericDAO.executeQueryForList(orgDtlsQuery,isString?userPrincipal:userBean.getEmail());
//					System.out.println("orgDtlsQuery:"+orgDtlList.toString());
//					if(userDtlList!=null && !userDtlList.isEmpty()) {
//						Map<String,Object> userDtlMap=userDtlList.get(0);
//						UserRegistrationDataBean userDB=new UserRegistrationDataBean();
//						userDB.setUserTypeId(Integer.parseInt(userDtlMap.get("usertypeid").toString()));
//						userDB.setFirstName(userDtlMap.get("firstname").toString());
//						userDB.setLastName(userDtlMap.get("lastname").toString());
//						userDB.setUserId(Integer.parseInt(userDtlMap.get("userid").toString()));
//						userDB.setMobileNo(userDtlMap.get("mobileno").toString());
//						userDB.setCountry(userDtlMap.get("countryid").toString());
//						userDB.setInterest(userDtlMap.get("interestid").toString());
//						userDB.setEmail(userDtlMap.get("emailid").toString());
//						System.out.println("here 1st");
//						return new UsernamePasswordAuthenticationToken(userDB,userDtlMap.get("emailid").toString());
//					}else if(orgDtlList!=null && !orgDtlList.isEmpty()) {
//						Map<String,Object> orgDtlMap=orgDtlList.get(0);
//						UserRegistrationDataBean userDB=new UserRegistrationDataBean();
//						userDB.setUserTypeId(Integer.parseInt(orgDtlMap.get("usertypeid").toString()));
//						userDB.setAddress(orgDtlMap.get("address").toString());
//						userDB.setOrgName(orgDtlMap.get("orgname").toString());
//						userDB.setOrgRegId(Integer.parseInt(orgDtlMap.get("orgregid").toString()));
//						userDB.setMobileNo(orgDtlMap.get("mobileno").toString());
//						userDB.setCountry(orgDtlMap.get("countryid").toString());
//						userDB.setState(orgDtlMap.get("stateid").toString());
//						userDB.setEmail(orgDtlMap.get("emailid").toString());
//						System.out.println("here 2nd");
//						return new UsernamePasswordAuthenticationToken(userDB,orgDtlMap.get("emailid").toString());
//					}
					
//			
//				
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		}
//		System.out.println("return null");
//		return authentication;
//				
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
//	}

}
