package com.infodart.pdf;

import java.io.FileNotFoundException;


import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.infodart.dao.BaseDao;
import com.infodart.dao.BaseDaoImpl;
import com.infodart.entity.Visitor;
import com.infodart.util.Myfactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class VisitorPassPDF {
	
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
	

	public static Document createPDF(String file) {

	
		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			addTitlePage(document);

			createTable(document);

			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;

	}

	private static void addMetaData(Document document) {
		document.addTitle("Generate PDF report");
		document.addSubject("Generate PDF report");
		document.addAuthor("Visitor Pass");

	}

	private static void addTitlePage(Document document) throws DocumentException {
		long millis = System.currentTimeMillis();
		Paragraph preface = new Paragraph();
		createEmptyLine(preface, 1);
		preface.add(new Paragraph("Visitor Pass", TIME_ROMAN));

		createEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		preface.add(new Paragraph("Report created on " + simpleDateFormat.format(new Date(millis)), TIME_ROMAN_SMALL));

		document.add(preface);

	}

	private static void createEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	@SuppressWarnings("unchecked")
	private static void createTable(Document document) throws DocumentException {

		Map<Object, List<Visitor>> map = null;

		List<Visitor> visit = null;
		Paragraph paragraph = new Paragraph();
		createEmptyLine(paragraph, 2);

		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100.0f);

		table.setSpacingBefore(10);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.ORANGE);
		cell.setPadding(5);
		
		
		
		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Purpose", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Date", font));
		table.addCell(cell);

		table.setHeaderRows(1);
		
	    
	 
		@SuppressWarnings("unused")
		SimpleDateFormat format;
		List<Object[]> visitor = null;

		try {

			EntityManager entity = Myfactory.getEntityManager();
			BaseDao base = new BaseDaoImpl();
			int lastid = base.getlastId();
			System.out.println("lastid" +lastid);
			String query = "select v.ID, v.Name, v.Purpose , v.Date from Visitor v where v.ID = :lastid";
			
			Query qry = entity.createQuery(query).setParameter("lastid", lastid);

			visitor = qry.getResultList();
			for (int i = 0; i < visitor.size(); i++) {

			

				for (int j = 0; j < visitor.get(i).length; j++) {
					map = new HashMap<>();
					visit = new ArrayList<>();

					map.put(visitor.get(i)[j], visit);

					for (Object listEntry : map.keySet()) {

						visit = map.get(listEntry);
						
						
						
						
						cell = new PdfPCell(new Phrase(listEntry.toString()));
						table.addCell(cell);

						for (Visitor values : map.get(listEntry)) {

							values = (Visitor) map.get(values);
							cell = new PdfPCell(new Phrase(visit.toString()));
							table.addCell(cell);
							cell = new PdfPCell(new Phrase(visit.toString()));
							table.addCell(cell);
							cell = new PdfPCell(new Phrase(visit.toString()));
							table.addCell(cell);
							
						}
					}
				}
			}

			entity.close();

			document.add(table);
			

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
