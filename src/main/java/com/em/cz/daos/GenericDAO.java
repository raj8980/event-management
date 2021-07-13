package com.em.cz.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class GenericDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> executeQueryForList(String query,Object ...params ){
		return jdbcTemplate.queryForList(query,params);
	}
	
	public Map<String,Object> executeQueryForMap(String query,Object ...params ){
		return jdbcTemplate.queryForMap(query,params);
	}
	
	
	public int executeQueryForInsertion(String query,Object ...params) {
		return jdbcTemplate.update(query,params);
	}
	
	public long executeQueryWithInsertId(String query,String colName,Object ...params) throws Exception {
		long out=0;
		if(colName!=null && colName.length()>0) {
			final String finalQuery=query;
			final Object[] finalParams=params;
			final String fetchColName=colName;
			
			KeyHolder keyHolder=new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps=con.prepareStatement(finalQuery,new String[] {fetchColName});
					if(params!=null && params.length>0) {
						for(int i=0;i<params.length;i++) {
							ps.setObject((i+1), params[i]);
						}
					}
					return ps;
					
				}
			},keyHolder);
			out=keyHolder.getKey().longValue();
		}else {
			throw new Exception("Column name is null or not found.");
		}
		return out;
	}
	
}
