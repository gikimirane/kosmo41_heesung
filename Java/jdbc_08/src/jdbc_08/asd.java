package jdbc_08;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class asd {

public static void main(String[] args) {
	qweqwe a = new qweqwe();
	//String[] b = a.qwe();
	for(int i=0;i<a.qwe().length;i++) {
//		if(a.qwe[i]==null) {
//			continue;
//		}
		System.out.println(a.qwe[i]);
		
		
	}
	
	}
}
class qweqwe{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = null;
	boolean commit = false;
	PreparedStatement pstmt = null;
	String[] qwe = new String[4];
	void zxc() {
		
	}
	
	String[] qwe() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			pstmt = con.prepareStatement("select*from roomlist");
			rs = pstmt.executeQuery();
			int 	i=0;
			while(rs.next()) {
			this.qwe[i++] = rs.getString(1);
			//System.out.println(qwe[i]);
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
		//		if(pstmt!=null)pstmt.close();
			//	if(con!=null)cp.releaseConnection(con);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return qwe;
		
	}
	
}



