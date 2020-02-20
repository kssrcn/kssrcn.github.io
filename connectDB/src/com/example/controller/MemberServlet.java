package com.example.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.Command;
import com.example.ListCommand;
import com.example.LogOutCommand;
import com.example.LoginCommand;
import com.example.MemberDeleteCommand;
import com.example.MemberSearchCommand;
import com.example.MemberUpdateCommand;
import com.example.ShowBoardCommand;
import com.example.SignUpCommand;
import com.example.WriteCommand;

/**
 * Servlet implementation class MemberInsertServlet
 */
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		String viewPage = null;
		Command command = null;
		response.setCharacterEncoding("EUC-KR");
		if (page.equals("/insert.do")) {
			command = new SignUpCommand();
			command.execute(request);
			viewPage = "signUpComplete.jsp";
		} else if (page.equals("/member_search.do")) {
			command = new MemberSearchCommand();
			command.execute(request);
			viewPage = "memberSearchList.jsp";
		} else if (page.equals("/delete.do")) {
			command = new MemberDeleteCommand();
			command.execute(request);
			viewPage = "member_search.do";
			response.sendRedirect(viewPage);
			return;
		} else if (page.equals("/update.do")) {
			if (!checkPassWord(request)) {
				viewPage = "updateFailedView.jsp";
			} else {
				command = new MemberUpdateCommand();
				command.execute(request);
				viewPage = "member_search.do";
				response.sendRedirect(viewPage);
				return;
			}
		} else if (page.equals("/login.do")) {
			HttpSession session = request.getSession();
			if (session.isNew()) {
				command = new LoginCommand();
				command.execute(request);
				if (request.getAttribute("success").equals("ok")) {
					viewPage = "list.do";
					response.sendRedirect(viewPage);
					return;
				} else {
					session.invalidate();
					viewPage = "loginFailedView.jsp";
				}
			} else {
				viewPage = "list.do";
				response.sendRedirect(viewPage);
				return;
			}
		} else if (page.equals("/list.do")) {
			command = new ListCommand();
			command.execute(request);
			viewPage = "boardList.jsp";

		} else if (page.equals("/write.do")) {
			command = new WriteCommand();
			command.execute(request);
			viewPage = "login.do";
			response.sendRedirect(viewPage);
			return;
		}else if (page.equals("/showBoard.do")) {
			command = new ShowBoardCommand();
			command.execute(request);
			viewPage = "showView.jsp";
		}else if (page.equals("/logOut.do")) {
			command = new LogOutCommand();
			command.execute(request);
			response.sendRedirect("login.html");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	private boolean checkPassWord(HttpServletRequest request) {
		return request.getParameter("pw").equals(request.getParameter("pwCheck"));
	}

}
