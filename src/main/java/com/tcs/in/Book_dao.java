package com.tcs.in;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class Book_dao {
	
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void get_book_data()
	{
		String sql = "select * from book_data.book;";
		
		template.query(sql, new ResultSetExtractor<List<book>>(){

			@Override
			public List<book> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				XSSFWorkbook book = new XSSFWorkbook();

				XSSFSheet sheet = book.createSheet("Books-Data");

				XSSFRow headerRow = sheet.createRow(0);

				headerRow.createCell(0).setCellValue("Id");
				headerRow.createCell(1).setCellValue("Name");
				headerRow.createCell(2).setCellValue("Price");
				
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream("C:\\Users\\gowtham\\OneDrive\\Desktop\\bookdata\\data.xlsx");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				   int i = 1;
				   
				while(rs.next())
				{
					int a = rs.getInt(1);
					String b = rs.getString(2);
					int c = rs.getInt(3);
					
					XSSFRow dataRow = sheet.createRow(i);
					
					dataRow.createCell(0).setCellValue(a);
					dataRow.createCell(1).setCellValue(b);
					dataRow.createCell(2).setCellValue(c);
					
					i++;
					
				}
				
				try {
					book.write(fos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					book.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					fos.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				return null;
			}
			
			
		});
	

	}
}
