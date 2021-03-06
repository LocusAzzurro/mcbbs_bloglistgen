package net.mcbbs.locusazzurro.bloglist;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParsedWorkBook {
	
	private final XSSFWorkbook workBook;
	private final int numSheets;
	private final int[] rows;
	private final String[] names;
	
	public ParsedWorkBook(Path filePath) throws IOException
	{
		try
		{
			this.workBook = new XSSFWorkbook(filePath.toFile());
		}
		catch (InvalidFormatException e)
		{
			throw new IOException(e.toString(), e);
		}
		this.numSheets = workBook.getNumberOfSheets();
		this.rows = getLastValidRows();
		this.names = getSheetNames();
	}
	public XSSFWorkbook getWorkBook()
	{
		return this.workBook;
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
	private int[] getLastValidRows()
	{
		int[] rows = new int[this.numSheets];
		
		for (int i=0; i<this.numSheets; i++) //iterate through sheets
	{
		Sheet sheet = this.workBook.getSheetAt(i);
		rows[i] = 0;
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext())
		{
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellCheck = nextRow.cellIterator();
			if(cellCheck.hasNext()) 
			{
				//if(nextRow.getCell(0).getNumericCellValue() > 1) rows[i]++; } //time stamp check
				
				Cell cell = cellCheck.next();
				CellType type = cell.getCellTypeEnum();
				switch (type) 
				{
				case NUMERIC: //timestamp check
					if(cell.getNumericCellValue() > 1)
						rows[i]++;
						break;
				case STRING: //string content check
					if(!cell.getStringCellValue().replaceAll("\\s+", "").equals(""))
						rows[i]++;
						break;
				default: break;
				}
				
			}
				
		}
		
		
	}
		return rows;
	}
	private String[] getSheetNames()
	{
		String[] names = new String[this.numSheets];
		
		for (int i=0; i<this.numSheets; i++)
		{
		names[i] = this.workBook.getSheetName(i);
		}
		return names;
	}
	public void Main() {}
}
