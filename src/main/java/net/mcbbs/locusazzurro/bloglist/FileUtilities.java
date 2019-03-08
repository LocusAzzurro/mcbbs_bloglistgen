package net.mcbbs.locusazzurro.bloglist;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileUtilities {

	public FileUtilities() {}
	
	public static String inputPath() throws URISyntaxException
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Insert file name.");
		String fileName = input.nextLine();
		input.close();
		File jarLoc = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		String excelFilePath = jarLoc.getParent() + File.separator + fileName;
		return excelFilePath;
	}
	
	public static String outputPath()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String outputFile = "output-" + df.format(new Date()) + ".txt";
		return outputFile;
	}
}
