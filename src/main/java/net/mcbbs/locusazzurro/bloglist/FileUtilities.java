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
	public static Path inputPath(String fileName) throws URISyntaxException
	{
			Path jarFilePath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			Path excelFilePath = jarFilePath.resolveSibling(fileName);
			return excelFilePath;
	}
	
	public static Path outputPath()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Path outputFile = Paths.get("output-" + df.format(new Date()) + ".txt");
		return outputFile;
	}
	
	@Deprecated // Use the general output
	public static Path outputPath(int type)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Path outputFile;
		String timeStamp = df.format(new Date());
		if (type==1) outputFile = Paths.get("lib-" + timeStamp + ".txt");
		else if (type==2) outputFile = Paths.get("blog-" + timeStamp + ".txt");
		else outputFile = Paths.get("output-" + timeStamp + ".txt");
		return outputFile;
	}
	
	@Deprecated
	public static int tableType()
	{
		try (Scanner input = new Scanner(System.in))
		{
			System.out.println("Insert file type.\n 1 for Library \n 2 for Blogs");
			int type = input.nextInt();
			return type;
		}
	}
}
