package net.mcbbs.locusazzurro.bloglist;

import com.google.gson.annotations.SerializedName;

public class ArticleList{
	
	@SerializedName("article_grid")
	public Article[] articleGrid;
	
	@SerializedName("article_count")
	public int articleCount;
	
	public class Article{
		
		@SerializedName("default_tile")
		public Tile defaultTile;
		
		@SerializedName("preferred_tile")
		public Tile preferredTile;
		
		@SerializedName("primary_category")
		public String primaryCategory;
		
		@SerializedName("categories")
		public String[] categories;
		
		@SerializedName("article_url")
		public String articleURL;
		
		@SerializedName("publish_date")
		public String publishDate;
		
		@SerializedName("tags")
		public String[] tags;
		
		public class Tile{
			
			@SerializedName("sub_header")
			public String subHeader;
			
			@SerializedName("title")
			public String title;
		}
	}
	

}
