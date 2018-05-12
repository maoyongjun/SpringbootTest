package org.foxconn.springbootStart.lucene;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexWriterTest {
	
//	public static void main(String[] args) throws IOException, URISyntaxException{
//
//	}
	
	private void test() throws IOException{
//		URI uri = new URI("file:///d:/index");
//		Path path =Paths.get(uri);
		Path path =new File("d:/index").toPath();
		Directory d =FSDirectory.open(path);
		MyAnalyzer analyzer = new MyAnalyzer();
//		IKAnalyzer4Lucene7 analyzer = new IKAnalyzer4Lucene7();
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		conf.setOpenMode(OpenMode.CREATE);
		IndexWriter iw = new IndexWriter(d, conf);
		FieldType type = new FieldType();// new FieldType();
		type.setStored(true);
		type.setDocValuesType(DocValuesType.SORTED_SET);
		type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
		type.setTokenized(true);
		Field field = new TextField("name", "this is a test Value is", Store.YES);
//		Field field = new Field("name", "this is a test Value is".getBytes(), type);
//		Field field = new Field("name", "this is a test Value is", type);
		Document doc = new Document();
		doc.add(field);
//		type.freeze();
		iw.addDocument(doc);
		System.out.println(iw.getDirectory());
		System.out.println(iw.getFieldNames());
		iw.close();
		IndexReader reader = StandardDirectoryReader.open(d);
		Document readDoc = reader.document(0);
		System.out.println(readDoc);
		IndexableField filed1 = readDoc.getField("name");
		System.out.println(filed1.stringValue());
//		System.out.println(filed1.binaryValue());
		Long totalTeams = reader.getSumTotalTermFreq("name");
		System.out.println(totalTeams);
//		TokenStream ts =null; 
//		ts = filed1.tokenStream(analyzer, ts);
//		MyAttribute att = ts.getAttribute(MyAttribute.class);
//		ts.reset();
//		while(ts.incrementToken()){
//			System.out.println(att.getText());
//		}
		
	}
	
	
	
}
