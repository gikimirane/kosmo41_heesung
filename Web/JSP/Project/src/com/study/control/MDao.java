package com.study.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MDao {
	
	DataSource dataSource = null;
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static MDao instance = new MDao();

	private MDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MDao getInstance() {
		return instance;
	}

	
	public int join(MDto dto) {
		int ri = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into mjoin values (?, ?, ?, ?, ?)";		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getHome());
			pstmt.setString(5, dto.getNick());
			pstmt.executeUpdate();
			ri = MDao.MEMBER_JOIN_SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ri;
	}

	public int confirmId(String id) {
		int ri=0;
		Connection con = null;
		ResultSet set = null;
		PreparedStatement pstmt = null;
		String query = "select id from mjoin where id = ?";		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = MDao.MEMBER_EXISTENT;
			}else {
				ri = MDao.MEMBER_NONEXISTENT;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ri;
	}
	
	

    public int userCheck(String id, String pw) {
    	int ri=0;
    	String dbPw;
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet set = null;
    	String query = "select pw from mjoin where id = ?";
    	
    	try {
    		con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					System.out.println("login ok");
					ri = MDao.MEMBER_LOGIN_SUCCESS;
				}else {
					System.out.println("login fail");
					ri = MDao.MEMBER_LOGIN_PW_NO_GOOD;
				}
			}else {
				System.out.println("login fail");
				ri = MDao.MEMBER_LOGIN_IS_NOT; //ID x
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    	return ri;
    }
    
    public MDto getMember(String id) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet set = null;
    	String query = "select * from mjoin where id = ?";
    	MDto dto = null;
    	
    	try {
    		con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new MDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.setHome(set.getString("home"));
				dto.setNick(set.getString("nick"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
    	return dto;
    }
    
    public int updateMember(MDto dto) {
    	int ri=0;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	String query = "update mjoin set pw=? where id=?";
    	
    	try {
    		con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getId());
			ri = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    	return ri;
    }
    
}
