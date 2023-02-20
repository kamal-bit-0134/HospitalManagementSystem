package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;import org.apache.catalina.connector.Response;

import com.entity.Specialist;

public class SpecialistDao {
	
	private Connection conn;

	public SpecialistDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addSpecialist(String specString) {
		boolean f = false;
		
		try {
			
			String sqlString = "insert into specialist(spec_name) values(?)";
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, specString);
		    int i = ps.executeUpdate();
		    if(i==1) {
		    	f=true;
		    }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return f;
	}
	
	public List<Specialist> getAllSpecialist(){
		
		List<Specialist> list  = new ArrayList<Specialist>();
		
		Specialist s= null;
		try {
			String sqlString = "select * from specialist";
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				s = new Specialist();
				s.setId(rs.getInt(1));
				s.setSpecialistName(rs.getString(2));
				list.add(s);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return list;
	}
	
}
