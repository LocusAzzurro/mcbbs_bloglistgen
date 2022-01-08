package net.mcbbs.locusazzurro.bloglist;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class FileUtilities {

	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");

	public static Path inputPathFromString(String fileName) throws URISyntaxException
	{
			Path jarFilePath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			Path excelFilePath = jarFilePath.resolveSibling(fileName);
			return excelFilePath;
	}
	
	public static Path outputPath()
	{
		return outputPath(".txt");
	}

	public static Path outputPath(String format)
	{
		Path outputFile = Paths.get("output-" + df.format(new Date()) + format);
		return outputFile;
	}

	public static Path outputPath(int type)
	{
		Path outputFile;
		String timeStamp = df.format(new Date());
		if (type==1) outputFile = Paths.get("mcnet-" + timeStamp + ".csv");
		else if (type==2) outputFile = Paths.get("blog-" + timeStamp + ".txt");
		else outputFile = outputPath();
		return outputFile;
	}
}
