package net.mcbbs.locusazzurro.bloglist;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class TableWriter {
	private ParsedWorkBook workbook;
	private BufferedWriter writer;
	
	static final long UNIX_DIFF = 2208988800000L; //Time protocol RFC 868
	static final long TIME_OFFSET = 86400000L * 2; //2 days in millisecs shift for xlsx date format
	
	public TableWriter(ParsedWorkBook workbook,String outputFilePath) throws IOException
	{
		this.workbook = workbook;
		this.writer = new BufferedWriter(new FileWriter(outputFilePath,true));
	}
	
	public void blogTableWrite() throws IOException
	{
		boolean parity = true;
		String postDate,originalLink,originalTitle,translationLink,translationTitle;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String author = "-";
		
		for (int i=0 ; i<workbook.getNumSheets() ; i++) //iterate sheets
		{
			Sheet sheet = workbook.getWorkBook().getSheetAt(i); //get num i sheet
			Iterator<Row> rowIterator = sheet.iterator();
			
			System.out.println("------------------"); //sheet info check
			System.out.println("Table: " + workbook.getNames()[i]);
		 	System.out.println("Rows: "+ workbook.getRows()[i]);
		 	
		 	writer.write(Components.BLOG_OP_1);
			if (Components.tableNames.containsKey(workbook.getNames()[i]))
				writer.write(Components.tableNames.get(workbook.getNames()[i]));
			else writer.write(workbook.getNames()[i]);
			writer.write(Components.BLOG_OP_2);
			

			for (int j=0 ; j<workbook.getRows()[i] ; j++) //iterate rows
			{
				Row nextRow = rowIterator.next();
				parity = !parity;
				
				postDate = df.format(new Date((long)nextRow.getCell(0).getNumericCellValue()*86400*1000 - UNIX_DIFF - TIME_OFFSET));
				originalLink = nextRow.getCell(1).getStringCellValue();
				originalTitle = nextRow.getCell(2).getStringCellValue();
				translationLink = nextRow.getCell(3).getStringCellValue();
				translationTitle = nextRow.getCell(4).getStringCellValue();
				if (nextRow.getCell(5).getCellTypeEnum().equals(CellType.NUMERIC))
					author = String.format("%.0f",nextRow.getCell(5).getNumericCellValue());
				else 
					author = nextRow.getCell(5).getStringCellValue();
				
				String tableLine = 
						Components.BLOG_EL(postDate,originalLink,originalTitle,translationLink,translationTitle,author,parity);
				writer.write(tableLine);
				writer.write("\r\n");
			}
			writer.write(Components.BLOG_ED);
		}
		
		writer.flush();
		writer.close();
		
	}
	
	public void bugTableWrite() throws IOException
	{
		int n=0;
		Sheet sheet = workbook.getWorkBook().getSheetAt(n);
		Iterator<Row> rowIterator = sheet.iterator();
		writer.write(Components.BUG_OP);
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			String[] values = new String[3];

			Cell nextCell = cellIterator.next();
			int number = (int) nextCell.getNumericCellValue();
			values[0] = String.valueOf(number);
			nextCell = cellIterator.next();
			values[1] = nextCell.getStringCellValue();
			nextCell = cellIterator.next();
			values[2] = nextCell.getStringCellValue();

			String tableLine = Components.BUGLIST_EL(values[0], values[1], values[2]);
			writer.write(tableLine);
		}
		writer.write(Components.BUG_ED);
		writer.flush();
		writer.close();
	}
}
