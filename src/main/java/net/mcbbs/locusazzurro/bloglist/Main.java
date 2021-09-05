/**
 * Minecraft.net Blog Table Serializer
 * @author LocusAzzurro
 */

package net.mcbbs.locusazzurro.bloglist;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import net.mcbbs.locusazzurro.bloglist.Components;

public class Main {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		Path excelFilePath = (args.length > 0) ? FileUtilities.inputPath(args[0]) : FileUtilities.inputPath();
		Path outputFile = FileUtilities.outputPath();
		
		ParsedWorkBook workbook = new ParsedWorkBook(excelFilePath);
		TableWriter writer = new TableWriter(workbook, outputFile);
		
		writer.blogTableWrite();
	}
}
