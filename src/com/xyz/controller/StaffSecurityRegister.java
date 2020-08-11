package com.infodart.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infodart.entity.Login;
import com.infodart.service.BaseService;
import com.infodart.service.BaseServiceImpl;

public class StaffSecurityRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BaseService baseService = new BaseServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StaffSecurityRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		String n = (String) session.getAttribute("name");
		String name = request.getParameter("Name");
		String uname = request.getParameter("userId");
		String Pass = request.getParameter("password");
		Login staff = new Login(name, uname, Pass);
		staff.setCreatedBy(n);
		staff.setModifiedBy(n);
		staff.setActiveflag(1);
		staff.setCreatedTimestamp(null);
		staff.setModifiedTimestamp(null);
		staff.setRole("S");
		baseService.register(staff);

		out.println("<script type=\"text/javascript\">");
		out.println("alert('Staff Registered Successfully');");
		out.println("location='register.jsp';");
		out.println("</script>");

	}

}