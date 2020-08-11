package com.infodart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infodart.dao.BaseDao;
import com.infodart.dao.BaseDaoImpl;

/**
 * Servlet implementation class VerifyOTP
 */
@WebServlet("/VerifyOTP")
public class VerifyOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyOTP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		String receiveotp = request.getParameter("otp");
		int rcvotp = Integer.parseInt(receiveotp);
		BaseDao base = new BaseDaoImpl();
		boolean flag = base.verifyotp(rcvotp);
		if(flag)
		{
		System.out.println("otp verified successfully");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('OTP Verified');");
		out.println("window.close();");
		out.println("</script>");
		}
		else
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('OTP not Verified');");
			out.println("location='otp.jsp';");
			out.println("</script>");	
		}
		
	}

}
