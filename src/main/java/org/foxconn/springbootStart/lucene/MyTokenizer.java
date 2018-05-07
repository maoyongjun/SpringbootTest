package org.foxconn.springbootStart.lucene;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class MyTokenizer extends Tokenizer {
	MyAttribute attribute1 = addAttribute(MyAttribute.class);
	private String msg ;
	private int currentIndex;
	private StringBuilder sb;
	
	private final CharTermAttribute termAtt=addAttribute(CharTermAttribute.class);//
	
	public MyTokenizer() {
		
	}

	@Override
	public boolean incrementToken() throws IOException {
		clearAttributes();
		int i=-1;
		int index=-1;
		sb=new StringBuilder("");
		while((i=input.read())!=-1){
			
			index++;
			if(Character.isWhitespace(i)){
				attribute1.setText(String.valueOf(sb.toString()));
				termAtt.append(String.valueOf(sb.toString()));
				return true;
			}else{
				sb.append((char)i);
			}
		}
		if(sb.length()>0){
			attribute1.setText(String.valueOf(sb.toString()));
			termAtt.append(String.valueOf(sb.toString()));
			return true;
		}
		return false;
		
		

	}

}
