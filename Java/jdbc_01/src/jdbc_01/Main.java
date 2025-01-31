package jdbc_01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Main {

	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try{
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			Statement stmt = con.createStatement();
			
			//------------------------------------------------------
			StringBuffer sb = new StringBuffer();
			sb.append("create table test1(");
			sb.append(" id varchar(10), ");
			sb.append(" age number ) ");
			
			int updateCount = stmt.executeUpdate(sb.toString());
			System.out.println("createCount : "+updateCount);
			
			
			//---------------------------------------------------------
			sb.setLength(0);
			sb.append("insert into test1 ");
			sb.append("values ('ȫ�浿',10)");
			updateCount = stmt.executeUpdate(sb.toString());
			System.out.println("insertCount : "+updateCount);
			
			//---------------------------------------------------------
			sb.setLength(0);
			sb.append("select*from test1");
			ResultSet rs = stmt.executeQuery(sb.toString());
			while(rs.next()) {
				System.out.println("id :"+rs.getString(1)+",");
				System.out.println("age : "+rs.getString("age"));
			}
			//---------------------------------------------------
			sb.setLength(0);
			sb.append("update test1");
			sb.append(" set id = '����ġ',");
			sb.append("  age=20");
			sb.append(" where id = 'ȫ�浿'");
			updateCount = stmt.executeUpdate(sb.toString());
			System.out.println("updateCount : "+updateCount);
			//------------------------------------------------------
			sb.setLength(0);
			sb.append("select*from test1");
			rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				System.out.println("id :" + rs.getString(1)+",");
				System.out.println("age : "+rs.getString("age"));
			}
			sb.setLength(0);
			sb.append("delete from test1");
			updateCount = stmt.executeUpdate(sb.toString());
			System.out.println("deleteCount : "+updateCount);
			//----------------------------------------------------
			sb.setLength(0);
			sb.append("drop table test1");
			updateCount = stmt.executeUpdate(sb.toString());
			System.out.println("dropCount : "+updateCount);
			//-----------------------------------------------------
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException sqle) {
			System.out.println("Connection Error");
			sqle.printStackTrace();
		}

	}

}
