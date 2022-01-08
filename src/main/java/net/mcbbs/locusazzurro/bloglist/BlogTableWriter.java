package net.mcbbs.locusazzurro.bloglist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class BlogTableWriter {
	private final ParsedWorkBook workbook;
	private final BufferedWriter writer;
	
	static final long UNIX_DIFF = 2208988800000L; //Time protocol RFC 868
	static final long TIME_OFFSET = 86400000L * 2; //2 days in ms shift for xlsx date format
	
	public BlogTableWriter(Path inputFilePath, Path outputFilePath) throws IOException
	{
		this.workbook = new ParsedWorkBook(inputFilePath);
		try	{Files.createFile(outputFilePath);}
		catch (IOException e) {	e.printStackTrace();}
		this.writer = Files.newBufferedWriter(outputFilePath, StandardOpenOption.APPEND);
	}
	
	public void blogTableWrite() throws IOException
	{
		boolean parity = true;
		String postDate,originalLink,originalTitle,translationLink,translationTitle;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String author = "-";
		long lastPostDate = 0,postDateRaw = 0;

		System.out.println("Converting Sheet...");
	 	writer.write(Components.BLOG_HEADER);
		
		for (int i=0 ; i<workbook.getNumSheets() ; i++) //iterate sheets
		{
			Sheet sheet = workbook.getWorkBook().getSheetAt(i); //get num i sheet
			Iterator<Row> rowIterator = sheet.iterator();

			String tableName = workbook.getNames()[i];
			int rows = workbook.getRows()[i];

			System.out.println("Now Processing Table " + tableName + " (" + rows + " Entries)"); //sheet info check
		 	
		 	writer.write(Components.BLOG_OP_1);
			writer.write(Components.tableNames.getOrDefault(tableName, tableName));
			writer.write(Components.BLOG_OP_2);
			

			for (int j = 0; j< rows; j++) //iterate rows
			{
				Row nextRow = rowIterator.next();
				parity = !parity;
				
				postDate = df.format(new Date((long)nextRow.getCell(0).getNumericCellValue()*86400*1000 - UNIX_DIFF - TIME_OFFSET));
				postDateRaw = (long)nextRow.getCell(0).getNumericCellValue()*86400*1000 - UNIX_DIFF - TIME_OFFSET;
				originalLink = nextRow.getCell(1).getStringCellValue();
				originalTitle = nextRow.getCell(2).getStringCellValue();
				translationLink = nextRow.getCell(3).getStringCellValue();
				translationTitle = nextRow.getCell(4).getStringCellValue();
				author = authorParser(nextRow.getCell(5));
				String tableLine = 
						Components.BLOG_EL(postDate,originalLink,originalTitle,translationLink,translationTitle,author,parity);
				writer.write(tableLine + "\r\n");
				if (postDateRaw > lastPostDate) lastPostDate = postDateRaw;
			}
			writer.write(Components.BLOG_ED);

		}
		writer.write(Components.BLOG_LASTPOST_1);
		writer.write(df.format(new Date(lastPostDate)));
		writer.write(Components.BLOG_LASTPOST_2);
		writer.write(Components.BLOG_RULES_REF);
		writer.write(Components.NAVBAR);
		writer.flush();
		writer.close();
		System.out.println("Conversion Complete.");
	}
	
	private String authorParser(Cell cell)
	{
		String parsedAuthor;
		if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
			parsedAuthor = String.format("%.0f",cell.getNumericCellValue());
		else 
			parsedAuthor = cell.getStringCellValue();
		return parsedAuthor;
	}
}
