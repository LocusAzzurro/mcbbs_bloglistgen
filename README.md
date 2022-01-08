# MCBBS识海漫谈版博文录列表维护工具

## 简介

本程序用于MCBBS [识海漫谈](https://www.mcbbs.net/forum-translation-1.html) 板块博文录的日常维护，主要功能为：
* 将特定格式的`xlsx`文件表格转换为论坛博文录使用的`bbcode`格式文本。
* 将Minecraft.net API提供的`json`格式博文列表转换为`csv`表格。

## 使用

* 得到`jar`文件后，将其放在与需要处理的文件的相同路径下，并在相同路径创建`bat`脚本。
```
java -jar <文件名>.jar
pause
```
* 执行脚本，并按顺序输入文件名和处理模式。（`1`为`json`转`csv`，`2`为`xlsx`转`bbcode`。）
* 以上参数可以直接在命令行中添加。
```
java -jar <文件名>.jar <源文件名> <模式>
pause
```

## 链接
*（不保证可用性与时效性）*
* [博文录](https://www.mcbbs.net/thread-823054-1-1.html)
* [Minecraft.net API](https://www.minecraft.net/content/minecraft-net/_jcr_content.articles.grid)