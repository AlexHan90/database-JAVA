package day19;


import java.util.ArrayList;
import java.util.Scanner;

public class MainClass01 {
	public static void main(String[] args) {
		
		DB db = new DB();
		
		int num, result=0;
		
		Scanner input = new Scanner(System.in);
		
		String inputId;
		
		String userId,userName;
		int userAge;
		
		while(true) {
			System.out.println("1.모든정보보기");
			System.out.println("2.개인정보보기");
			System.out.println("3.개인삭제");
			System.out.println("4.회원가입");
			System.out.println(">>>");
			num = input.nextInt();
			switch(num) {
			case 1: 
				//db.selectAll();	
				ArrayList<Member> list = db.selectAll2();
			if(list.size()== 0) {
				System.out.println("저장데이터 없음");
			}else {
				for(int i=0; i<list.size(); i++) {
					Member m = list.get(i);
					System.out.println(m.getId());
					System.out.println(m.getName());
					System.out.println(m.getAge());
					System.out.println("=========");
				}
			}
			
			
				break;
			
			case 2:
				System.out.println("검색할 아이디 입력");
				inputId = input.next();
				Member m = db.selectOne2(inputId);
				System.out.println("===main출력===");
				if(m == null) {
					System.out.println("찾는 회원 없음");
				}else {
					System.out.println("id : "+m.getId());
					System.out.println("name : "+m.getName());
					System.out.println("age : "+m.getAge());
				}
				
				break;
				
				case 3:
				System.out.println("삭제 아이디 입력");
				inputId = input.next();
				
				
				result = db.delete(inputId);
				if(result ==0)
					System.out.println("삭제실패");
				
				else 
					System.out.println("삭제 성공");
				break;
				
				case 4:
					
					System.out.println("가입 아이디입력");
					userId = input.next();
					System.out.println("가입 이름 입력");
					userName = input.next();
					System.out.println("가입 나이 입력");
					userAge = input.nextInt();
					
					db.insert(userId, userName, userAge);
					
					break;
					
	
			}
			}		
		
}
}



		
	




/*
1.driver road : oracle 관련 기능 사용

2.connection: oracle과 java와 연결시켜준다
 - 오라클의 위치 정보(ip, port)
 - 오라클의 계정 정보(id, pwd) 필요
 1.Connection ctrl+space > sql  선택
 2. try문 안에 con = DriverManager.getConnection 사용 (경로,아이디 사용)
 String url = 입력
 String id, pwd 입력
 
 localhost =  나의 pc에 대한 ip

3.PreparedStatement:명령어를 수행 하는 객체
 - sql문을 문자열로 만들어 줘야 한다.
 
4. ResultSet : 사용해도 되고 안해도 된다
  - 결과를 받아오는 객체
  - select를 사용하는 경우에만 사용.
  - 반복자 형태로 들어오게 된다
  
  
*/
