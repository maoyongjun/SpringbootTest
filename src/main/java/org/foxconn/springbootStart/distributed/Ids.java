package org.foxconn.springbootStart.distributed;

public class Ids {
	private static int id;
	public static String getId(){
		return String.valueOf(id++);
	}
	public  String getNextId(){
		return String.valueOf(id++);
	}
}
