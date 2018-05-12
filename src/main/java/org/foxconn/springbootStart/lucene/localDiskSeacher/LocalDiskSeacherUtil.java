package org.foxconn.springbootStart.lucene.localDiskSeacher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;
import org.foxconn.springbootStart.lucene.MyAnalyzer;
import org.junit.Test;

public class LocalDiskSeacherUtil {
	private MyAnalyzer analyzer = new MyAnalyzer();
//	public static void main(String[] args) throws IOException {
//		LocalDiskSeacherUtil util = new LocalDiskSeacherUtil();
//		String fileDir ="d:/";
//		util.addDocToIndex(fileDir,"d:/index");
//	}
	
	
	/**
	 * 通过文件名搜索文档
	 * @param fileName
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public List<Doc> findDocByFileName(String fileName,int fromIndex,int toIndex){
		
		return null;
	}
	
	/**
	 * 通过文件内容搜索文档
	 * @param contentName
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public List<Doc> findDocByContent(String contentName,int fromIndex,int toIndex){
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private  List<Doc> findDocByContent(String indexPath,String queryText) throws IOException{
		Directory dir = FSDirectory.open(new File("d:/index").toPath());
		IndexReader ir = StandardDirectoryReader.open(dir);
		IndexSearcher is = new IndexSearcher(ir);
		String queryFiled ="name";
		Query query = new QueryBuilder(analyzer).createBooleanQuery(queryFiled, queryText);
		TopDocs docs =  is.search(query, 1);
		return null;
	}
	
	/**
	 * 将指定磁盘路径的文档加入到索引中
	 * @param fileDir
	 * @param indexPath 索引库的路径
	 * @throws IOException 
	 */
	public void addDocToIndex(String fileDir,String indexPath) throws IOException{
		List<Doc> docs =findAllDoc(fileDir);
		for(Doc doc : docs){
			addDocToIndex(doc,indexPath);
		}
	}
	/**
	 * 将文档加入到索引中
	 * @param doc
	 * @throws IOException 
	 */
	private void addDocToIndex(Doc doc,String indexPath) throws IOException{
		Path path =new File(indexPath).toPath();
		Directory d =FSDirectory.open(path);
		
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		conf.setOpenMode(OpenMode.CREATE);
		try(IndexWriter iw = new IndexWriter(d, conf);){
			Document document = new Document();
			iw.addDocument(putDocFiledIntoDocument(doc,document));
		}catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 将doc中的个属性加入到document中
	 * @param doc
	 * @param document
	 * @return
	 */
	private Document putDocFiledIntoDocument(Doc doc,Document document){
		Field fileDirField = new StringField("fileDir", doc.getFileDir(), Store.YES);
		Field fileContentField = new TextField("fileContent", doc.getSimpleContent(), Store.YES);
		Field fileSizeField = new DoublePoint("fileSize", doc.getFileSize());
		document.add(fileDirField);
		document.add(fileContentField);
		document.add(fileSizeField);
		return document;
	}
	
	
	
	/**
	 * 通过磁盘路径搜索所有的文档
	 * @param fileDir
	 * @return
	 */
	public List<Doc> findAllDoc(String fileDir){
		
		return null;
	}
	
	//通过文档解析器，解析文档的内容，封装成定义的对象
//	private Doc parseDoc(Doc doc){
//		
//	}
	/**
	 * 测试方法，测试添加一个doc文档到索引库
	 * 通过索引工具查看是否添加成功
	 * @throws IOException
	 */
	@Test
	public void testAddDoc() throws IOException{
		
		List<Doc> docs =new ArrayList<Doc>();
		Doc testDoc = new Doc();
		testDoc.setFileDir("d:/file/test.doc");
		testDoc.setFileName("test.doc");
		testDoc.setFileSize(1.2021);
		testDoc.setSimpleContent("this is a test doc document");
		docs.add(testDoc);
		for(Doc doc : docs){
			addDocToIndex(doc,"d:/index");
		}
	}
	
}
