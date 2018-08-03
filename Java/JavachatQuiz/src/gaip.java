
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class gaip {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String test4 = null;
		boolean commit = false;
		Scanner s = new Scanner(System.in);

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@ec2-52-14-40-133.us-east-2.compute.amazonaws.com:1521:xe", "scott", "tiger");
			stmt = con.createStatement();
			con.setAutoCommit(false);

			System.out.println("회원가입을 시작하겠습니다");
			System.out.println("아이디를 입력하세요 10글자 미만");
			String a = s.nextLine();
			System.out.println("아이디가 "+a+" 맞습니까? [y/n]");
			String c = s.nextLine();
			if (c.equals("n")||c.equals("n")) {
				System.out.println("다시 입력해주세요");
				a=s.nextLine();
			}
			System.out.println("비밀번호를 입력하세요.");
			String b = s.nextLine();
//			System.out.println("비빌번호 확인차 한번더 입력해주세요");
//			String d = s.nextLine();
//			if(b != d)
//				System.out.println("일치하지않습니다.다시입력하세요");
			stmt.addBatch("INSERT INTO test4 " + "VALUES('" + a+ "','" + b  + "')");
				
			int[] updateCounts = stmt.executeBatch();
			commit = true;
			con.commit();

			rs = stmt.executeQuery("select*from test4");
			while (rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				System.out.println("id :" + id + ", password :" + password);
			}
		} catch (SQLException sqle) {
			System.out.println("중복된 아이디입니다.다시 가입해주세요");
		} finally {
			try {
				if (!commit)
					con.rollback();
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
