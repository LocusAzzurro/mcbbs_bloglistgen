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
	static final String[] TITLES = {"本周方块<已完结>","背包盘点","遇见生物","业界讯息","周边产品","社区文化"};
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		//Input acquisition
		Scanner input = new Scanner(System.in);
		System.out.println("Insert file name.");
		String fileName = input.nextLine();
		//System.out.println("Insert sheet number.");
		//int sheetNum = input.nextInt();
		input.close();
		
		//input and output path
		File jarLoc = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		String excelFilePath = jarLoc.getParent() + File.separator + fileName;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String outputFile = "output-" + df.format(new Date()) + ".txt";
		
		try
		(Workbook workbook = new XSSFWorkbook(excelFilePath);
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true)))
			{
			
				
				System.out.println("Input File: " + excelFilePath);
				
				for (int i=0;i<6;i++)
				{
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println("------------------");
				System.out.println("Table: " + sheet.getSheetName());
			 	System.out.println("Rows: "+ sheet.getLastRowNum());
			 	int lastRow = getLastValidRow(sheet);
			 	System.out.println("Valid Rows: " + lastRow);
			 	System.out.println("Commencing List Write...");
			 	blogTableWriter(sheet, writer, lastRow, TITLES[i]);
				}
			 	writer.flush();
			 	System.out.println("Write Complete: \n" + outputFile);
				
			}
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
			{
				author = Double.toString(nextRow.getCell(5).getNumericCellValue());
			}
			else 
			{
				author = nextRow.getCell(5).getStringCellValue();
			}
			
			String tableLine = 
					Components.BLOG_EL(postDate,originalLink,originalTitle,translationLink,translationTitle,author,parity);
			writer.write(tableLine);
			writer.write("\r\n");
		}
		writer.write(Components.BLOG_ED);
	}
	public static int getLastValidRow(Sheet sheet)
	{
		int count = 0;
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext())
		{
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			if(nextRow.getCell(0).getNumericCellValue() > 1) count++; //time stamp check
		}
		return count;
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
