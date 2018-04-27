package org.foxconn.springbootStart.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.TokenStream;

public class TestMyAnalyzer {
	
	public static void main(String[] args) throws IOException {
		String msg ="aaa like bbb,ccc ddd";
		Analyzer an = new MyAnalyzer();
		TokenStream  token = an.tokenStream("aa", msg);
		while(token.incrementToken()){
			MyAttribute myAttribute = token.getAttribute(MyAttribute.class);
			System.out.println(myAttribute.getText());
		}
		
		token.end();
	}
}
