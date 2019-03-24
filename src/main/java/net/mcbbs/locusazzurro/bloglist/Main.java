/**
 * MCBBS Translation & Wiki Board 
 * Blog List Auto Writer
 * by LocusAzzurro
 */

package net.mcbbs.locusazzurro.bloglist;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import net.mcbbs.locusazzurro.bloglist.Components;

public class Main {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		String input = args[0];
		int type = Integer.parseInt(args[1]);
		if (type < 1 || type > 2) throw new IllegalArgumentException("Invalid table type.\nUse 1 for Library, 2 for Blogs");
		
		Path excelFilePath = FileUtilities.inputPath(input);
		Path outputFile = FileUtilities.outputPath();
		Components.initializeTableNames();
		
		ParsedWorkBook workbook = new ParsedWorkBook(excelFilePath);
		TableWriter writer = new TableWriter(workbook, outputFile);
		

		if (type == 1) writer.libraryTableWrite();
		if (type == 2) writer.blogTableWrite();
	}
}
