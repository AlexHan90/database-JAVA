package day19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dbrv {
	Connection con;						
	PreparedStatement ps;
	ResultSet rs;
	public Dbrv() {						
		System.out.println("DB실행");
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			String url = "jdbc:oracle:thin:@ 192.168.7.6:1521:xe";
										
			String id="Alex", pwd="1234";
			
			con = DriverManager.getConnection(url, id, pwd); 
		
		} catch (Exception e) {
			e.printStackTrace();
		}       
	}
	public void selectOne(String inputId) {  
		 String sql = 
				 "select*from newst where id = '"+inputId+"'";
		 System.out.println("sql : "+sql);    
		 
		 
		 try {
			
			 ps =con.prepareStatement(sql);  
			rs = ps.executeQuery();    
		
			 if( rs.next()) {
				 System.out.println("id : "+rs.getString("id"));
				 System.out.println("name : "+rs.getString("name"));
				 System.out.println("age : "+rs.getInt("age")); 
				 
			 }else {
				 System.out.println("조회한 정보가 없습니다!!!");
			 }
			 
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {   
			try {
				if(rs != null)	
					rs.close();
				if(ps != null) 
				ps.close();   
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		 
	}
	public Member selectOne2(String inputId) {  
		 String sql = 
				 "select*from newst where id = '"+inputId+"'";
		 System.out.println("sql : "+sql);  
		 
		 try {
			
			 ps =con.prepareStatement(sql);  
			rs = ps.executeQuery();     
		
			 if( rs.next()) {
				
				 Member mem = new Member();
				 mem.setId(rs.getString("id"));
				 mem.setName(rs.getString("name"));
				 mem.setAge(rs.getInt("age"));
				 
			 }else {
				 System.out.println("조회한 정보가 없습니다!!!");
			 }
			  
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		 
		 return null;
	}
	
		 public void selectAll() {
			 String sql = "select * from newst";
			 try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()) {
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
				System.out.println("=============");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
				
			
			}
			 	  
	}
		 
		 public ArrayList<Member> selectAll2() {
			 String sql = "select * from newst";
			 ArrayList<Member> arr = new ArrayList<Member>();
			 try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
				while(rs.next()) {
				Member m1 = new Member();
				
			
				m1.setId(rs.getString("id"));
				m1.setName(rs.getString("name"));
				m1.setAge(rs.getInt("age"));
				arr.add(m1);
				}
				
		
			 }catch (Exception e) {
						e.printStackTrace();
			 }finally {
				 dbClose();
			 }
			 return arr;
					
		 }
				 public void dbClose() {
					 System.out.println("데이터베이스 닫음");
						try {
							if(rs != null)	
								rs.close();
							if(ps != null) 
							ps.close();    
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
				 }
		 
		 }


			 


