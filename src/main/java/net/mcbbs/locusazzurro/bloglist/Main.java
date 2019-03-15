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
		
		Path excelFilePath = FileUtilities.inputPath();
		Path outputFile = FileUtilities.outputPath();
		Components.initializeTableNames();

		ParsedWorkBook workbook = new ParsedWorkBook(excelFilePath, 0);
		TableWriter writer = new TableWriter(workbook, outputFile);
		writer.blogTableWrite();
	}
}
