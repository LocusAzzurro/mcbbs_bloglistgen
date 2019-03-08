package net.mcbbs.locusazzurro.bloglist;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParsedWorkBook {
	
	private XSSFWorkbook WorkBook = new XSSFWorkbook();
	private int numSheets;
	private int type;
	private int[] rows;
	private String[] names;
	
	public ParsedWorkBook(String filePath,int type) throws IOException
	{
		this.WorkBook = new XSSFWorkbook(filePath);
		this.numSheets = WorkBook.getNumberOfSheets();
		this.type = type;
		this.rows = getLastValidRows();
		this.names = getSheetNames();
	}
	public XSSFWorkbook getWorkBook()
	{
		return this.WorkBook;
	}
	public int getNumSheets()
	{
		return this.numSheets;
	}
	public int[] getRows()
	{
		return this.rows;
	}
	public String[] getNames()
	{
		return names;
	}
	public int getType()
	{
		return type;
	}
	private int[] getLastValidRows()
	{
		int[] rows = new int[this.numSheets];
		
		for (int i=0; i<this.numSheets; i++) //iterate through sheets
		{
		Sheet sheet = this.WorkBook.getSheetAt(i);
		rows[i] = 0;
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext())
		{
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellCheck = nextRow.cellIterator();
			if(cellCheck.hasNext()) {
				if(nextRow.getCell(0).getNumericCellValue() > 1) rows[i]++; } //time stamp check
		}
		}
		return rows;
	}
	private String[] getSheetNames()
	{
		String[] names = new String[this.numSheets];
		
		for (int i=0; i<this.numSheets; i++)
		{
		names[i] = this.WorkBook.getSheetName(i);
		}
		return names;
	}
	public void Main() {}
}
