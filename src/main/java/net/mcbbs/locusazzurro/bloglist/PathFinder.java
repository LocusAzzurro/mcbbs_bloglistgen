package net.mcbbs.locusazzurro.bloglist;

public class PathFinder {

	public PathFinder()
	{
		
	}
	public static void main(String[] args)
	{
		PathFinder pf = new PathFinder();
		System.out.println(getClassPath(pf));
	}
	public static String getClassPath(Object obj)
	{
		return obj.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	}
}
