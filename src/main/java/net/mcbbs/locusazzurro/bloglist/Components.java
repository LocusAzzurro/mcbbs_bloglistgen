package net.mcbbs.locusazzurro.bloglist;

import java.util.HashMap;
import java.util.Map;

public class Components {

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
public static final String BUG_OP = "[b][size=3]漏洞修复列表[/size][/b]\r\n[list]\r\n";
public static final String BUG_ED = "[/list]\r\n";

public static String BLOG_EL(String date,String originalURL,String originalTitle,
		String translationURL,String translationTitle,String author,boolean parity)
{
	String color;
	if (parity) color = "#eee0be";
	else color = "#eeecd7";
	String output = "[tr=" + color + "][td=15%]" + date + "[/td][td=30%]"
			+ "[url=" + originalURL + "]" + originalTitle + "[/url][/td][td=30%]"
			+ "[url=" + translationURL + "]" + translationTitle + "[/url][/td]"
			+ "[td=25%]" + author + "[/td][/tr]";
	return output;
}

public static String BUGLIST_EL(String bugNumber, String eng, String chn)
{
	String output = "[*][url=https://bugs.mojang.com/browse/MC-" + bugNumber
			+ "]MC-" + bugNumber + "[/url] - " + eng + "\r\n"
			+ "[*][url=https://bugs.mojang.com/browse/MC-" + bugNumber
			+ "]MC-" + bugNumber + "[/url] - " + chn + "\r\n";
	return output;
}

public static Map<String, String> tableNames = new HashMap<String, String>();

public static void initializeTableNames()
{
	tableNames.put("BLOCKS","本周方块");
	tableNames.put("ITEMS","背包清点");
	tableNames.put("MOBS","遇见生物");
	tableNames.put("INSIDER","内部资讯");
	tableNames.put("MERCH","周边产品");
	tableNames.put("CULTURE","社区文化");
}

public static void main(String args[])
{
	System.out.println(BLOG_OP_1 + "本周方块" + BLOG_OP_2);
	System.out.println(BLOG_EL("01/01/2018","LinkO","TitleO","LinkT","TitleT","-",true));
}
}
