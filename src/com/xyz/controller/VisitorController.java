package com.infodart.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infodart.dao.BaseDao;
import com.infodart.dao.BaseDaoImpl;
import com.infodart.entity.Login;
import com.infodart.entity.Visitor;
import com.infodart.service.BaseService;
import com.infodart.service.BaseServiceImpl;

public class VisitorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BaseService baseService = new BaseServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisitorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		BaseDao base = new BaseDaoImpl();

	
		HttpSession session = request.getSession(false);
		String str = (String) session.getAttribute("name");
		System.out.println("staffsecurity : " + str);

		int ID = baseService.getId();
		System.out.println("id is" + ID);

		Login loguserid = new Login();
		loguserid.loginid(ID);

		String Name = request.getParameter("Name");
		String ContactNo = request.getParameter("mobile");

		long contact = Long.parseLong(ContactNo);
		String purposeOfVisit = request.getParameter("PurposeOfVisit");
		String contactPersonName = request.getParameter("ContactPersonName");
		String designation = request.getParameter("Designation");
		//String Email = request.getParameter("Email");
		String Email = (String) session.getAttribute("email");
		System.out.println("visitor email is"+ Email);
		Boolean verify = base.verifyEmployee(contactPersonName, designation);
		if (verify) {

			Visitor visitor = new Visitor(Name, contact, purposeOfVisit, contactPersonName, designation, Email);

			visitor.setDate(new Date(System.currentTimeMillis()));
			visitor.setCheckIntimestamp(null);
		
			visitor.setCreatedBy(str);
			visitor.setModifiedBy(str);
			visitor.setActiveflag(1);
			visitor.setCreatedTimestamp(null);
			visitor.setModifiedBy(null);
			visitor.setLoginid(loguserid);
			baseService.registerVisitor(visitor);

			System.out.println("Data Saved Successfully");

			base.sendMail();

			out.println("<script type=\"text/javascript\">");
			out.println("alert('data saved');");
			out.println("location='Registration.jsp';");
			out.println("</script>");
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter the valid details of Employee');");
			out.println("location='Registration.jsp';");
			out.println("</script>");
			System.out.println("emp not true");

		}

	}

}
