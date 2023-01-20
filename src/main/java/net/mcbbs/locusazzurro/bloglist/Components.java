package net.mcbbs.locusazzurro.bloglist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Components {

private static final SimpleDateFormat tableTimeFormat= new SimpleDateFormat("yyyy-MM-dd");
private static final String tableTime = tableTimeFormat.format(new Date());

public static final String BLOG_HEADER = "{:grass_side:} "
		+ "[size=5][color=DarkGreen][b][u]Minecraft.net 官方博文录[/u][/b][/color][/size]\n"
		+ "[table][tr=#a87b3f][td=300][b][color=White]最后更新时间："
		+ tableTime + "[/color][/b][/td][/tr][/table]\n";
public static final String BLOG_OP_1 =
"[table]\n[tr=#a87b3f][td=4,1,600][b][color=White][size=3]";
//Insert Catalog Type here when used
public static final String BLOG_OP_2 =
"[/size][/color][/b][/td][/tr]\r\n[/table][spoiler][table]\r\n"
+ "[tr=#d5b592][td=15%][b][color=DarkRed]日期[/color][/b][/td]"
+ "[td=30%][b][color=DarkRed]原文[/color][/b][/td][td=30%][b][color=DarkRed]译文[/color][/b][/td]"
+ "[td=25%][b][color=DarkRed]译文作者[/color][/b][/td][/tr]\r\n";
public static final String BLOG_ED =
"[/table][/spoiler]\r\n";
public static final String BLOG_LASTPOST_1 = "[table][tr=#a87b3f][td=300][b][color=White]原文最后收录：";
public static final String BLOG_LASTPOST_2 = "[/color][/b][/td][/tr][/table]\n";
public static final String BLOG_RULES_REF = "\n[list]\n" + 
		"[*][size=3][b][color=Green]官方博文相关建议与守则请参阅[url=https://www.mcbbs.net/thread-566428-1-1.html]版规[/url][/color][/b][/size]\n" + 
		"[*][size=3][b][color=Green]了解重收录相关流程请前往[url=https://www.mcbbs.net/thread-1259271-1-1.html]申请帖[/url][/color][/b][/size]\n" + 
		"[/list]";
public static final String NAVBAR = "\n[align=center][table=80%]\n" + 
		"[tr=#fff2d3][td][color=#3e2c00][b][size=3][align=center]识海漫谈 导航[/align][/size][/b][/color][/td][/tr]\n" + 
		"[tr=#ffe7ad][td][/td][/tr]\n" + 
		"[tr=#fff8e6][td][align=center][url=https://www.mcbbs.net/thread-566428-1-1.html][color=Sienna]版规[/color][/url]" +
		" • [url=https://www.mcbbs.net/thread-823054-1-1.html][color=Sienna]官方博文录[/color][/url]" +
		" • [url=https://www.mcbbs.net/thread-1259271-1-1.html][color=Sienna]重收录申请[/color][/url]" +
		" • [url=https://www.mcbbs.net/thread-1175676-1-1.html][color=Sienna]译名讨论指导[/color][/url]" +
		" • [url=https://www.mcbbs.net/thread-1046420-1-1.html][color=Sienna]中文MCWiki动员[/color][/url][/align][/td][/tr]\n" + 
		"[/table][/align]";

public static String BLOG_EL(String date,String originalURL,String originalTitle,
		String translationURL,String translationTitle,String author,boolean parity)
{
	String color;
	if (parity) color = "#eee0be";
	else color = "#eeecd7";
	return "[tr=" + color + "][td=15%]" + date + "[/td][td=30%]"
			+ "[url=" + originalURL + "]" + originalTitle + "[/url][/td][td=30%]"
			+ "[url=" + translationURL + "]" + translationTitle + "[/url][/td]"
			+ "[td=25%]" + author + "[/td][/tr]";
}

public static Map<String, String> tableNames = new HashMap<>();

static
{
	tableNames.put("BLOCKS","本周方块");
	tableNames.put("ITEMS","背包盘点");
	tableNames.put("MOBS","遇见生物");
	tableNames.put("BIOMES","群系漫游");
	tableNames.put("BUILD","以之搭建");
	tableNames.put("INSIDER","内部资讯");
	tableNames.put("DIVES","块海漫游");
	tableNames.put("GUIDES","游玩指南");
	tableNames.put("THINGS","十或不知");
	tableNames.put("DUNGEONS","地下城记");
	tableNames.put("LEGENDS","传奇故事");
	tableNames.put("MERCH","周边产品");
	tableNames.put("MARKET","市场推荐");
	tableNames.put("REALMS","领域新品");
	tableNames.put("MCBUILDS","社区建筑");
	tableNames.put("EVENTS","活动块讯");
	tableNames.put("SKINS","社区皮肤");
	tableNames.put("FANART","粉丝创作");
	tableNames.put("CULTURE","社区文化");
	tableNames.put("PLUGINS","插件资料");
	tableNames.put("MODS","模组资料");
	tableNames.put("DOCS","学术文章");
}

}
