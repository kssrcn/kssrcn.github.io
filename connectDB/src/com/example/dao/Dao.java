package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.example.dto.BoardDto;
import com.example.dto.MemberDto;

public class Dao {
	Connection con;
	PreparedStatement pre;
	DataSource datasource;
	ResultSet rs;

	public Dao() {
		// TODO Auto-generated constructor stub
//		String user = "kim";
//		String password = "1612";
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection(url, user, password);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertData(HttpServletRequest request) {
		try {
			con = datasource.getConnection();
			String query = "insert into Member(id,pw) values (?,?)";
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			pre = con.prepareStatement(query);
			pre.setString(1, id);
			pre.setString(2, pw);
			pre.executeUpdate();
			request.setAttribute("id", id);
			request.setAttribute("pw", pw);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
	}

	public void getData(HttpServletRequest request) {
		try {
			con = datasource.getConnection();
			String query = "select * from Member";
			pre = con.prepareStatement(query);
			rs = pre.executeQuery();
			ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
			while (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				dtos.add(new MemberDto(id, pw));
			}
			request.setAttribute("memberList", dtos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
	}

	public void deleteData(HttpServletRequest request) {
		try {
			con = datasource.getConnection();
			String query = "delete from Member where id=?";
			pre = con.prepareStatement(query);
			pre.setString(1, request.getParameter("id"));
			pre.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
	}

	public void updateData(HttpServletRequest request) {
		try {
			con = datasource.getConnection();
			String query = "update member set pw=? where id=?";
			pre = con.prepareStatement(query);
			pre.setString(1, request.getParameter("pw"));
			pre.setString(2, request.getParameter("id"));
			pre.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
	}

	public MemberDto loginProcess(HttpServletRequest request) {
		try {
			con = datasource.getConnection();
			String query = "select id,pw from member where id=?";
			pre = con.prepareStatement(query);
			pre.setString(1, request.getParameter("id"));
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				return new MemberDto(id, pw);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	public ArrayList<BoardDto> loadData(HttpServletRequest request) {
		try {
			ArrayList<BoardDto> bdtos = new ArrayList<BoardDto>();
			con = datasource.getConnection();
			String query = "select wid,wtitle from board";
			pre = con.prepareStatement(query);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("wid");
				String title = rs.getString("wtitle");
				BoardDto bdto = new BoardDto();
				bdto.setwId(id);
				bdto.setwTitle(title);
				bdtos.add(bdto);
			}
			return bdtos;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
		
		return null;
	}
	
	public void writeData(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			con = datasource.getConnection();
			String query = "insert into board(wid,writer,wtitle,wcontent) values(board_seq.nextval,?,?,?)";
			pre = con.prepareStatement(query);
			pre.setString(1, (String) session.getAttribute("id"));
			pre.setString(2, request.getParameter("title"));
			pre.setString(3, request.getParameter("content"));
			pre.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
	}
	
	public BoardDto showData(HttpServletRequest request) {
		try {
			con = datasource.getConnection();
			String query = "select wid,writer,wtitle,wContent from board where wid=?";
			pre = con.prepareStatement(query);
			pre.setInt(1, Integer.parseInt(request.getParameter("wId")));
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("wid");
				String writer = rs.getString("writer");
				String title = rs.getString("wtitle");
				String content = rs.getString("wcontent");
				BoardDto bdto = new BoardDto();
				bdto.setwId(id);
				bdto.setWriter(writer);
				bdto.setwTitle(title);
				bdto.setwContent(content);
				return bdto;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
		
		return null;
	}
}
