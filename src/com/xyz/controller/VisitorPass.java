package com.infodart.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infodart.pdf.VisitorPassPDF;


@WebServlet("/print")
public class VisitorPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitorPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
         
		final ServletContext servletContext = request.getSession().getServletContext();
		final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		final String temperotyFilePath = tempDirectory.getAbsolutePath();

		String fileName = "visitorpass.pdf";
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);

		try {
			 
			System.out.println("New File created");
			VisitorPassPDF.createPDF(temperotyFilePath + "\\" + fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = convertPDFToByteArrayOutputStream(temperotyFilePath + "\\" + fileName);
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
		} catch (Exception e1) {
			e1.printStackTrace();

		}

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
		private static ByteArrayOutputStream 
	    convertPDFToByteArrayOutputStream(String fileName) {

	    InputStream inputStream = null;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try {

	        inputStream = new FileInputStream(fileName);

	        byte[] buffer = new byte[1024];
	        baos = new ByteArrayOutputStream();

	        int bytesRead;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            baos.write(buffer, 0, bytesRead);
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (inputStream != null) {
	            try {
	                inputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return baos;
	}
		
	}


