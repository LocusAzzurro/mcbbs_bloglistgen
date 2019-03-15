package net.mcbbs.locusazzurro.bloglist;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileUtilities {

	public FileUtilities() {}
	
	public static Path inputPath() throws URISyntaxException
	{
		try (Scanner input = new Scanner(System.in))
		{
			System.out.println("Insert file name.");
			String fileName = input.nextLine();
			Path jarFilePath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			Path excelFilePath = jarFilePath.resolveSibling(fileName);
			return excelFilePath;
		}
	}
	
	public static Path outputPath()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Path outputFile = Paths.get("output-" + df.format(new Date()) + ".txt");
		return outputFile;
	}
}
