package org.foxconn.springbootStart.lucene;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.lucene.analysis.Tokenizer;

public class MyTokenizer extends Tokenizer {
	MyAttribute attribute1 = addAttribute(MyAttribute.class);
	private String msg ;
	private int currentIndex;
	public MyTokenizer() {
		
	}

	@Override
	public boolean incrementToken() throws IOException {
		if(msg!=null){
			input.reset();
			BufferedReader br = new BufferedReader(input);
			msg = br.readLine();
		}
		clearAttributes();
		attribute1.setText(String.valueOf(RandomUtils.nextInt()));
//		if(msg.charAt(currentIndex)){}
//		 
//		if(msg.charAt(currentIndex)==(int)''){
//			
//		}
		
		return true;

	}

}
