package org.foxconn.springbootStart.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.TokenStream;

public class TestMyAnalyzer {

	public static void main(String[] args) throws IOException {
//		String msg = "aaa like bbb,ccc ddd";
		String msg = "aaa";
		try (Analyzer an = new MyAnalyzer(); 
				TokenStream token = an.tokenStream("aa", msg);) {
			token.reset();// 赋值或者重置
			MyAttribute myAttribute = token.getAttribute(MyAttribute.class);// 同一个属性变量公用
			while (token.incrementToken()) {// 分词
				System.out.print(myAttribute.getText() + "|");// 分词结果
			}
			token.end();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
