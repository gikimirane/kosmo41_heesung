
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

			System.out.println("ȸ�������� �����ϰڽ��ϴ�");
			System.out.println("���̵� �Է��ϼ��� 10���� �̸�");
			String a = s.nextLine();
			System.out.println("���̵� "+a+" �½��ϱ�? [y/n]");
			String c = s.nextLine();
			if (c.equals("n")||c.equals("n")) {
				System.out.println("�ٽ� �Է����ּ���");
				a=s.nextLine();
			}
			System.out.println("��й�ȣ�� �Է��ϼ���.");
			String b = s.nextLine();
//			System.out.println("�����ȣ Ȯ���� �ѹ��� �Է����ּ���");
//			String d = s.nextLine();
//			if(b != d)
//				System.out.println("��ġ�����ʽ��ϴ�.�ٽ��Է��ϼ���");
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
			System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���");
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
