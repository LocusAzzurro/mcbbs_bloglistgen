package net.mcbbs.locusazzurro.bloglist;

import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BlogTableParser {

    private final ArticleList articles;
    private final FileWriter writer;

    public BlogTableParser(Path inputFilePath, Path outputFilePath) throws IOException {
        String json = new String(Files.readAllBytes(inputFilePath));
        this.articles = new Gson().fromJson(json, ArticleList.class);
        this.writer = new FileWriter(outputFilePath.toString());
    }

    public void parseBlogTable() throws ParseException, IOException {
        List<ParsedArticle> parsedArticles = new ArrayList<ParsedArticle>();
        System.out.println("Parsing JSON...");
        for (ArticleList.Article a : articles.articleGrid) {
            ParsedArticle pa = new ParsedArticle(a.publishDate,a.defaultTile.title,a.articleURL,a.primaryCategory);
            parsedArticles.add(pa);
            System.out.println(pa);
        }
        parsedArticles.sort(null);
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        parsedArticles.forEach(a -> {try {
            printer.printRecord(a.getDate(),a.getURL(),a.getTitle(),a.getCategory());
        } catch (IOException e) {e.printStackTrace();}
        });
        printer.flush();
        writer.flush();
        printer.close();
        writer.close();
        System.out.println("Parsing Complete.");
    }

}
