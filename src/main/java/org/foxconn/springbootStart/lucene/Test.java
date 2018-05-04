package org.foxconn.springbootStart.lucene;

public class Test {
	public static void main(String[] args) {
		String reg = "^[0-9a-zA-Z._]+$";
		String msg =".";
		System.out.println(msg.matches(reg));
		
	}
}
