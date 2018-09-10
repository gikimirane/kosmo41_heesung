package com.study.jsp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BDao {

	private static BDao instance = new BDao();
	DataSource dataSource = null;
	int listCount = 10;
	int pageCount = 10;

	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static BDao getInstance() {
		return instance;
	}
	
	public void write(String bName, String bTitle, String bContent,int table,String filename) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into mvc_board "+
					   "(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, tableNum,filename) "+
					   " values "+
					   "(mvc_board_seq.nextval,?,?,?, 0, mvc_board_seq.currval, 0, 0, ?,?)";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, table);
			pstmt.setString(5, filename);
			pstmt.executeUpdate();
			
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
	}

	public ArrayList<BDto> list(int nPage, int table,String search){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			
			int ePage = (nPage-1)*listCount+listCount;
			int sPage = (nPage-1)*listCount+1;
			con = dataSource.getConnection();
			
			if(table == 6) {
				String query = "select * "+
						   " from ( "+
						   " select rownum num, A.* "+
						   " from ( "+
						   " select * from mvc_board "+
						   " where bname like '%"+search+"%'"+
						   " order by bgroup desc, bstep asc ) A "+
						   " where rownum <= "+ePage+") B "+
						   " where B.num >= "+sPage+" ";

				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
			}else if(table == 7) {
				String query = "select * "+
						   " from ( "+
						   " select rownum num, A.* "+
						   " from ( "+
						   " select * from mvc_board "+
						   " where btitle like '%"+search+"%'"+
						   " order by bgroup desc, bstep asc ) A "+
						   " where rownum <= "+ePage+") B "+
						   " where B.num >= "+sPage+" ";

				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
			}else if(table == 8) {
				String query = "select * "+
						   " from ( "+
						   " select rownum num, A.* "+
						   " from ( "+
						   " select * from mvc_board "+
						   " where bcontent like '%"+search+"%'"+
						   " order by bgroup desc, bstep asc ) A "+
						   " where rownum <= "+ePage+") B "+
						   " where B.num >= "+sPage+" ";

				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
			}else {
				String query = "select * "+
						   " from ( "+
						   " select rownum num, A.* "+
						   " from ( "+
						   " select * from mvc_board "+
						   " where tableNum = "+table+" "+
						   " order by bgroup desc, bstep asc ) A "+
						   " where rownum <= "+ePage+") B "+
						   " where B.num >= "+sPage+" ";

				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();				
				
			}
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int tableNum = resultSet.getInt("tableNum");
				String filename = resultSet.getString("filename");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
								bHit, bGroup, bStep, bIndent, tableNum, filename);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}

	public BPageInfo articlePage(int curPage, int table,String search) {
		
		BPageInfo pinfo = new BPageInfo();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			if(table == 6) {
				String query = "select count(*) as total from mvc_board where bname like '%"+search+"%'";
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
			}else if(table == 7) {
				String query = "select count(*) as total from mvc_board where btitle like '%"+search+"%'";
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
			}else if(table == 8) {
				String query = "select count(*) as total from mvc_board where bcontent like '%"+search+"%'";
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
			}else {
				String query = "select count(*) as total from mvc_board where tableNum = "+table;
				pstmt = con.prepareStatement(query);
				resultSet = pstmt.executeQuery();
			}
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0) {
			totalPage++;
		}
		//현재 페이지 
		int myCurPage = curPage;
		if(myCurPage > totalPage) {
			myCurPage = totalPage;
		}
		if(myCurPage < 1) {
			myCurPage = 1;
		}
		
		//시작 페이지
		int startPage = ((myCurPage-1)/pageCount)*pageCount+1;
		
		
		//끝 페이지
		int endPage = startPage + pageCount-1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
			
					
		return pinfo;
	}

	public BDto contentView(String strID){
		upHit(strID);
		
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";	
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int tableNum = resultSet.getInt("tableNum");
				String filename = resultSet.getString("filename");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
								bHit, bGroup, bStep, bIndent, tableNum,filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public void modify(String bId,String bName, String bTitle, String bContent) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update mvc_board "+
					   "set bName = ?, "+
					   "	bTitle = ?, "+
					   "	bContent = ? "+
					   "where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
			pstmt.executeUpdate();
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
	}
	
	public void upHit(String bId){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";	
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String bId){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";	
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,Integer.parseInt(bId));
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public BDto reply_view(String strID){
		upHit(strID);
		
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";	
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int tableNum = resultSet.getInt("tableNum");
				String filename = resultSet.getString("filename");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
								bHit, bGroup, bStep, bIndent,tableNum,filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public void reply(String bId,String bName, String bTitle, String bContent,
						String bGroup, String bStep, String bIndent,int table){
		
		replyShape(bGroup,bStep);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board "+
						   " (bId, bName, bTitle, bContent, bGroup, bStep, bIndent, tableNum) "+
						   " values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			pstmt.setInt(7, table);
			pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void replyShape(String strGroup, String strStep){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSource.getConnection();
			String query = "update mvc_board "+
						   "set bStep = bStep + 1 "+
						   "where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	private Connection getConnection() {
//		
//		Context context = null;
//		DataSource dataSource = null;
//		Connection con = null;
//		try {
//			context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
//			con = dataSource.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return con;
//	}
	
	
	

}
