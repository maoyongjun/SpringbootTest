package org.foxconn.springbootStart.lucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

public class MyAnalyzer extends Analyzer{

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer source = new MyTokenizer();
		TokenStreamComponents components = new TokenStreamComponents(source, source);
		return  components;
	}

}
