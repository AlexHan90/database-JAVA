package day19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DB {
	Connection con;							//변수 생성
	PreparedStatement ps;
	ResultSet rs;
	public DB() {							//생성자 생성
		System.out.println("DB실행");
		
		try {
			//드라이브 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// localhost = 127.0.0.1 = 192.168.7.6
			// port : 2byte/ 65.535의 숫자로 표현된다
			// port : 프로그램 주소
			// - 프로그램을 구분하기 위한 값
			// ip : pc의 위치를 알려주는 값
			
			String url = "jdbc:oracle:thin:@ 192.168.7.6:1521:xe";
			//									ip:host:xe(java-version)
			String id="Alex", pwd="1234";
			//DB와 연결된 객체를 얻어온다
			con = DriverManager.getConnection(url, id, pwd); // (url, id, pwd) 입력
			//연결을 해준다
		} catch (Exception e) {
			e.printStackTrace();
		}       
	}
	public void selectOne(String inputId) {  //2번 
		 String sql = 
				 "select*from newst where id = '"+inputId+"'";
		 System.out.println("sql : "+sql);    //쿼리문
		 
		 
		// PreparedStatement ps = null;  // try밖에 변수를 만들어야한다
		 							// because try안에 지역변수는 try문안에서만 쓸수있기때문에
		 							// 최종적으로 ps.close 를 사용해야하기떄문에 try밖에 변수생성
		// PrepardStatement ps = con.prepardStatement(sql)
		 
		// ResultSet rs = null;
		 
		 try {
			//연결된 객체를 이용해서 전송객체를 얻어온다 (Ps사용)
			 ps =con.prepareStatement(sql);  //전송객체 얻어오는 작업
			rs = ps.executeQuery();      //명령어를 실행해주세요(위에서만든 명령어를)
			// System.out.println(rs.next());  // 데이터베이스에 값이있으면 true 없으면 false
			 if( rs.next()) {
				 System.out.println("id : "+rs.getString("id"));
				 System.out.println("name : "+rs.getString("name"));
				 System.out.println("age : "+rs.getInt("age")); //가로안에 컬럼명입력
				 
			 }else {
				 System.out.println("조회한 정보가 없습니다!!!");
			 }
			 
			 
			 
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {    //finally 마지막으로 꼭 실행되어야하는 문구
			try {
				if(rs != null)	//역순으로 닫아줘야해서 가장먼저 사용 rs.close
					rs.close();
				if(ps != null) // null point exception error 때문에 if문 사용
				ps.close();    // ps.close 처리할때는 if문 사용
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		 
	}
	public Member selectOne2(String inputId) {  //2번 
		 String sql = 
				 "select*from newst where id = '"+inputId+"'";
		 System.out.println("sql : "+sql);    //쿼리문
		 
		 
		// PreparedStatement ps = null;  // try밖에 변수를 만들어야한다
		 							// because try안에 지역변수는 try문안에서만 쓸수있기때문에
		 							// 최종적으로 ps.close 를 사용해야하기떄문에 try밖에 변수생성
		// PrepardStatement ps = con.prepardStatement(sql)
		 
		// ResultSet rs = null;
		 
		 try {
			//연결된 객체를 이용해서 전송객체를 얻어온다 (Ps사용)
			 ps =con.prepareStatement(sql);  //전송객체 얻어오는 작업
			rs = ps.executeQuery();      //명령어를 실행해주세요(위에서만든 명령어를)
			// System.out.println(rs.next());  // 데이터베이스에 값이있으면 true 없으면 false
			 if( rs.next()) {
				//System.out.println("id : "+rs.getString("id"));
				//System.out.println("name : "+rs.getString("name"));
				// System.out.println("age : "+rs.getInt("age")); //가로안에 컬럼명입력
				 
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
				
				
				/*
				 * System.out.println(rs.next()); System.out.println(rs.getString("id"));
				 * System.out.println(rs.getString("name"));
				 * System.out.println(rs.getInt("age")); System.out.println("=============");
				 * 
				 * System.out.println(rs.next()); System.out.println(rs.getString("id"));
				 * System.out.println(rs.getString("name"));
				 * System.out.println(rs.getInt("age")); System.out.println("=============");
				 * 
				 * System.out.println(rs.next()); System.out.println(rs.getString("id"));
				 * System.out.println(rs.getString("name"));
				 * System.out.println(rs.getInt("age")); System.out.println("=============");
				 */
				
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
				
			/*	Member m2 = new Member();
				rs.next();
				m2.setId(rs.getString("id"));
				m2.setName(rs.getString("name"));
				m2.setAge(rs.getString("age"));*/
			 

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
							if(rs != null)	//역순으로 닫아줘야해서 가장먼저 사용 rs.close
								rs.close();
							if(ps != null) // null point exception error 때문에 if문 사용
							ps.close();    // ps.close 처리할때는 if문 사용
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
					
							
					}
				 
				public int delete(String inputId) {
					String sql = "delete from newst where id= ?"; // ?나중에 값을 채워주겠다.
					int result = 0;
							
					try {
						ps = con.prepareStatement(sql);
						
						ps.setString(1, inputId);
						
						//rs = ps.executeQuery();
						result = ps.executeUpdate();  //delete나 다른기능을 쓸때는 update 사용
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						dbClose();	
					}
					
					return result;
					
		}
public int insert(String userId, String userName, int userAge) {
					
					
					int result = 0;
					String sql = "insert into newst values(?,?,?)";
					
					try {
						ps =con.prepareStatement(sql);
						ps.setString(1, userId);
						ps.setString(2, userName);
						ps.setInt(3, userAge);
						result = ps.executeUpdate(); //실행해라
					} catch (Exception e) {
						e.printStackTrace();
						//System.out.println("아이디가 중복 되었습니다");
					}
					
					return result;
				}
		 
}


			 




	/*
	 *1.최초로 드라이브 로드 =>무조건 먼저들어와야한다 이게 문제 없이 실행되면 오라클 관련된 기능 사용가능 
	 *2. 예외처리
	 *3.window탐색기 oracle exe 폴더 jdbc > lib > library
	 *4.project 오른쪽 누른후에 > properties > java build path > library> modul path > jar external add > oracle exe 타고들어가서 jdbc폴더 
	 *5. 오라클관련기능 사용 가능하다
	 *6. 만약 오류가 뜬다면 
	 */