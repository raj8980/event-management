package com.em.cz.security.configs;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.UrlPathHelper;

public class AuthenticationFilter extends GenericFilter{

	private static final long serialVersionUID=1L;
	
	private AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager=authenticationManager;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		String resourcePath=new UrlPathHelper().getPathWithinApplication(httpServletRequest);
		try {
			System.out.println("rp:"+resourcePath);
			System.out.println("method:"+httpServletRequest.getMethod());
			if(("/login").equalsIgnoreCase(resourcePath) && httpServletRequest.getMethod().equals("POST")) {
				InputStream is=request.getInputStream();
				byte[] data=StreamUtils.copyToByteArray(is);
				ByteArrayInputStream bais=new ByteArrayInputStream(data);
				String body=IOUtils.toString(new BufferedReader(new InputStreamReader(bais)));
				JSONParser jsonparser=new JSONParser();
				JSONObject jsonObject=(JSONObject) jsonparser.parse(body);
				String emailId=jsonObject.get("email").toString();
				String password=null;
				UsernamePasswordAuthenticationToken userNamePassAuthentication=new UsernamePasswordAuthenticationToken(emailId,password);
				
				Authentication authentication=authenticationManager.authenticate(userNamePassAuthentication);
				System.out.println(authentication);
				if(authentication==null && !authentication.isAuthenticated()) {
					System.out.println("not authenticated");
					SecurityContextHolder.clearContext();
					
                    
					
				}else {
					System.out.println("here");
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
				
			}
			chain.doFilter(httpServletRequest, httpServletResponse);
		}catch(Exception e) {
			SecurityContextHolder.clearContext();
			e.printStackTrace();
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
		
	}
	

}
