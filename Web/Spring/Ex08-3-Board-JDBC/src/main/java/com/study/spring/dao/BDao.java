package com.study.spring.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.study.spring.dto.BDto;
import com.study.spring.util.Constant;

public class BDao {

	private static BDao instance = new BDao();
	
	JdbcTemplate template = null;
	DataSource dataSource = null;
	int listCount = 10;
	int pageCount = 10;

	public BDao() {
		template = Constant.template;
	}	
	
	public static BDao getInstance() {
		return instance;
	}
	
	public void write(final String bName, final String bTitle,final String bContent) {
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "insert into mvc_board"+
						   "(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) "+
						   "values "+
						   "(mvc_board_seq.nextval,?,?,?, 0, mvc_board_seq.currval, 0, 0)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				return pstmt;
			}
		});
	}

	public ArrayList<BDto> list(){	
		String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent "
					+ "from mvc_board order by bGroup desc, bStep asc ";
		return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}


	public BDto contentView(String strID){
		upHit(strID);
		String query = "select * from mvc_board where bId = "+ strID;	
			
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public void modify(final String bId,final String bName,final String bTitle,final String bContent) {
		String query = "update mvc_board "+
					   "set bName = ?, "+
					   "	bTitle = ?, "+
					   "	bContent = ? "+
					   "where bId = ?";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setString(4, bId);
			}
		});

	}
	
	public void upHit(final String bId){
		String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, bId);
			}
		});
	}
	
	public void delete(final String bId){
		String query = "delete from mvc_board where bId = ?";	
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setString(1, bId);
			}
		});
	}
	
	public BDto reply_view(String strID){
		upHit(strID);

		String query = "select * from mvc_board where bId = "+strID;	
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public void reply(final String bId,final String bName,final String bTitle,final String bContent,
			final String bGroup,final String bStep,final String bIndent){
		
		replyShape(bGroup,bStep);
		String query = "insert into mvc_board "+
				   " (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) "+
				   " values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setInt(4, Integer.parseInt(bGroup));
				pstmt.setInt(5, Integer.parseInt(bStep)+1);
				pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			}
		});
	}
	
	public void replyShape(final String strGroup,final String strStep){

		String query = "update mvc_board "+
				   "set bStep = bStep + 1 "+
				   "where bGroup = ? and bStep > ?";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setInt(1, Integer.parseInt(strGroup));
				pstmt.setInt(2, Integer.parseInt(strStep));
			}
		});
	}	
}
