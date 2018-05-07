package org.foxconn.springbootStart.lucene.seach;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.search.DisjunctionMaxQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;
import org.foxconn.springbootStart.lucene.MyAnalyzer;

public class IndexSeachDemo {
	
	public static void main(String[] args) throws IOException {
		IndexSeachDemo demo = new IndexSeachDemo();
		demo.test();
	}
	private void test() throws IOException{
		Directory dir = FSDirectory.open(new File("d:/index").toPath());
		IndexReader ir = StandardDirectoryReader.open(dir);
		IndexSearcher is = new IndexSearcher(ir);
		String queryFiled ="name";
		doSeach(is,queryFiled,"this is");
		doSeach(is,queryFiled,"this  is a test Value ");
		doSeach(is,queryFiled,"this is a test Value is");
		doSeach(is,queryFiled,"this");
		doSeach(is,queryFiled,"this test Value  Value Value a");
		doSeach2(is,queryFiled,"this is");
	}
	
	private void doSeach(IndexSearcher is,String queryFiled,String queryText) throws IOException{
		Analyzer analyzer = new MyAnalyzer();
		Query query = new QueryBuilder(analyzer).createBooleanQuery(queryFiled, queryText);
		TopDocs docs = is.search(query, 1);
		System.out.println("hit:"+docs.totalHits+",score:"+docs.scoreDocs[0].score);
	}
	private void doSeach2(IndexSearcher is,String queryFiled,String queryText) throws IOException{
		Analyzer analyzer = new MyAnalyzer();
		Query query = new QueryBuilder(analyzer).createBooleanQuery(queryFiled, queryText);
		Collection querys = new ArrayList<Query>();
		querys.add(query);
		query =new DisjunctionMaxQuery(querys, 1.0f);
		TopDocs docs = is.search(query, 1);
		System.out.println("hit:"+docs.totalHits+",score:"+docs.scoreDocs[0].score);
	}
}
