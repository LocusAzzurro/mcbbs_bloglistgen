package net.mcbbs.locusazzurro.bloglist;

import org.apache.commons.text.WordUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ParsedArticle implements Comparable<ParsedArticle>{
	
	private Date publishDate;
	private String parsedDate;
	private String title;
	private String URL;
	private String category;
	
	private static SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss z", Locale.ENGLISH);
	private static SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	static {
	inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	outputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	public ParsedArticle(String publishDate, String title, String URL, String category) throws ParseException {
		this.publishDate = inputDateFormat.parse(publishDate);
		this.parsedDate = outputDateFormat.format(this.publishDate);
		this.title = WordUtils.capitalizeFully(title);
		this.URL = "https://www.minecraft.net" + URL;
		this.category = category;
	}
	
	public String getDate() {return parsedDate;}
	public String getTitle() {return title;}
	public String getURL() {return URL;}
	public String getCategory() {return category;}

	@Override
	public int compareTo(ParsedArticle o) {
		return this.publishDate.compareTo(o.publishDate);
	}

	@Override
	public String toString(){
		return parsedDate + " - " + title;
	}
	
}
