package org.foxconn.springbootStart.lucene;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.IndexableFieldType;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexWriterTest {
	
	public static void main(String[] args) throws IOException, URISyntaxException{
		URI uri = new URI("file:///d:/index");
		Path path =Paths.get(uri);
		Directory d =FSDirectory.open(path);
		MyAnalyzer analyzer = new MyAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		conf.setOpenMode(OpenMode.CREATE);
		IndexWriter iw = new IndexWriter(d, conf);
		FieldType type = new FieldType();// new FieldType();
		type.setStored(true);
		type.setDocValuesType(DocValuesType.SORTED_SET);
		type.setTokenized(true);
//		Field field = new TextField("name", "this is a test Value is", Store.YES);
		Field field = new Field("name", "this is a test Value is".getBytes(), type);
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
//		System.out.println(filed1.stringValue());
		System.out.println(filed1.binaryValue());
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
