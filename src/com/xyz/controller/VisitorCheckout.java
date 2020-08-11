package com.infodart.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.infodart.service.BaseService;
import com.infodart.service.BaseServiceImpl;


@WebServlet("/VisitorCheckout")
public class VisitorCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final BaseService baseService = new BaseServiceImpl();    
  
    public VisitorCheckout() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
		 PrintWriter out = response.getWriter();

		String id = request.getParameter("ID");	
		int result = Integer.parseInt(id);	
		 
		 baseService.VisitorCheckout(result);
		 

			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Visitor Checkout added successfully')");
			out.println("location='Checkout.jsp';");
			
			out.println("</script>");
		
			
		 

}
}
