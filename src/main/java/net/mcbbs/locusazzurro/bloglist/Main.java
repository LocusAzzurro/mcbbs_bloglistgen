/**
 * Minecraft.net Blog Table Serializer
 * @author LocusAzzurro
 */

package net.mcbbs.locusazzurro.bloglist;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException, ParseException {

		Path inputPath, outputPath;
		int type;

		if (args.length > 0){
			inputPath = FileUtilities.inputPathFromString(args[0]);
			type = Integer.parseInt(args[1]);
		}
		else {
			try (Scanner input = new Scanner(System.in))
			{
				System.out.println("Insert file name.");
				inputPath = FileUtilities.inputPathFromString(input.nextLine());
				System.out.println("Insert file type.\n 1 for json parsing \n 2 for sheet conversion");
				type = input.nextInt();
			}
		}
		outputPath = FileUtilities.outputPath(type);

		switch (type){
			case 1: {
				BlogTableParser parser = new BlogTableParser(inputPath, outputPath);
				parser.parseBlogTable();
				break;
			}
			case 2: {
				BlogTableWriter writer = new BlogTableWriter(inputPath, outputPath);
				writer.blogTableWrite();
				break;
			}
			default:{
				throw new IllegalArgumentException("Invalid type. Use 1 for json to csv parsing, 2 for sheet to bbcode conversion.");
			}
		}

		System.out.println("Output file created: " + outputPath);

	}
}
