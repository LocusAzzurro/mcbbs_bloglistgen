/**
 * MCBBS Translation & Wiki Board 
 * Blog List Auto Writer
 * by LocusAzzurro
 */

package net.mcbbs.locusazzurro.bloglist;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.mcbbs.locusazzurro.bloglist.Components;

@SuppressWarnings("unused")
public class Main {
	
	static final long UNIX_DIFF = 2208988800000L; //Time protocol RFC 868
	static final long TIME_OFFSET = 86400000L * 2; //2 days in milliseconds
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		String excelFilePath = FileUtilities.inputPath();
		String outputFile = FileUtilities.outputPath();
		
		ParsedWorkBook workbook = new ParsedWorkBook(excelFilePath,0);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile,true));
		
		for (int i=0;i<workbook.getNumSheets();i++)
		{
			Sheet sheet = workbook.getWorkBook().getSheetAt(i);
			System.out.println("------------------");
			System.out.println("Table: " + workbook.getNames()[i]);
		 	System.out.println("Rows: "+ workbook.getRows()[i]);
		 	blogTableWriter(sheet,writer,workbook.getRows()[i],workbook.getNames()[i]);
		}
		writer.flush();
		writer.close();
	}

	public static void blogTableWriter(Sheet sheet, BufferedWriter writer, int numRows, String title) throws IOException {
		boolean parity = true;
		String postDate,originalLink,originalTitle,translationLink,translationTitle;
		Iterator<Row> rowIterator = sheet.iterator();

		writer.write(Components.BLOG_OP_1);
		writer.write(title);
		writer.write(Components.BLOG_OP_2);

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		for (int i = 0; i < numRows ; i++){
			Row nextRow = rowIterator.next();
			parity = !parity;
			
			postDate = df.format(new Date((long)nextRow.getCell(0).getNumericCellValue()*86400*1000 - UNIX_DIFF - TIME_OFFSET));
			originalLink = nextRow.getCell(1).getStringCellValue();
			originalTitle = nextRow.getCell(2).getStringCellValue();
			translationLink = nextRow.getCell(3).getStringCellValue();
			translationTitle = nextRow.getCell(4).getStringCellValue();
			String author = "-";
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
		
	//unused (for now)
	public static void bugTableWriter(Sheet sheet, BufferedWriter writer) throws IOException {
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

	}
}
