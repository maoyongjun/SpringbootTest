package org.foxconn.springbootStart.lucene.seach;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.DisjunctionMaxQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;
import org.foxconn.springbootStart.lucene.MyAnalyzer;

public class IndexSeachDemo {
	
//	public static void main(String[] args) throws IOException, ParseException {
//		IndexSeachDemo demo = new IndexSeachDemo();
//		demo.test();
//	}
	private void test() throws IOException, ParseException{
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
		doSeach3(is,null,null);
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
	
	private void doSeach3(IndexSearcher is,String queryFiled,String queryText) throws ParseException, IOException{
		//9大基础查询：临近词查询、模糊查询、通配符查询、范围查询、布尔查询
		
		System.out.println("doSeach3");
		Analyzer analyzer = new MyAnalyzer();
		QueryParser queryParser = new MultiFieldQueryParser(new String[]{"name"},analyzer);
		queryParser.setDefaultOperator(Operator.OR);
		Query query = queryParser.parse("value");
		TopDocs docs  = is.search(query, 1);
		System.out.println("hit:"+docs.totalHits+",score:"+(docs.scoreDocs.length>0?docs.scoreDocs[0].score:0));
		query = queryParser.parse("name:value and title:123");
		docs  = is.search(query, 1);
		System.out.println("hit:"+docs.totalHits+",score:"+(docs.scoreDocs.length>0?docs.scoreDocs[0].score:0));
		query = queryParser.parse("name:value title:abc");
		docs  = is.search(query, 1);
		System.out.println("hit:"+docs.totalHits+",score:"+(docs.scoreDocs.length>0?docs.scoreDocs[0].score:0));
		queryParser = new MultiFieldQueryParser(new String[]{"name","field"},analyzer);
		query = queryParser.parse("value");
		docs  = is.search(query, 1);
		System.out.println("hit:"+docs.totalHits+",score:"+(docs.scoreDocs.length>0?docs.scoreDocs[0].score:0));
	}
	
}
