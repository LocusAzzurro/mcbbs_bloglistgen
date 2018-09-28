package net.mcbbs.locusazzurro.bloglist;

public class Components {

public static final String BLOG_OP_1 =
"[table]\n[tr=#a87b3f][td=3,1,600][b][color=White][size=3]";
//Insert Catalog Type here when used
public static final String BLOG_OP_2 =
"[/size][/color][/b][/td][/tr]\r\n[/table][spoiler][table]\r\n"
+ "[tr=#d5b592][td=20%][b][color=DarkRed]日期[/color][/b][/td]"
+ "[td=40%][b][color=DarkRed]原文[/color][/b][/td][td=40%][b][color=DarkRed]译文[/color][/b][/td][/tr]\r\n";
public static final String BLOG_ED =
"[/table][/spoiler]\r\n";
public static final String BUG_OP = "[b][size=3]漏洞修复列表[/size][/b]\r\n[list]\r\n";
public static final String BUG_ED = "[/list]\r\n";

public static String BLOG_EL(String date,String originalURL,String originalTitle,
		String translationURL,String translationTitle,boolean parity)
{
	String color;
	if (parity) color = "#eee0be";
	else color = "#eeecd7";
	String output = "[tr=" + color + "][td=20%]" + date + "[/td][td=40%]"
			+ "[url=" + originalURL + "]" + originalTitle + "[/url][/td][td=40%]"
			+ "[url=" + translationURL + "]" + translationTitle + "[/url][/td][/tr]";
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

public static void main(String args[])
{
	System.out.println(BLOG_OP_1 + "本周方块" + BLOG_OP_2);
	System.out.println(BLOG_EL("01/01/2018","LinkO","TitleO","LinkT","TitleT",true));
}
}
