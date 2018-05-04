package org.foxconn.springbootStart.lucene;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexWriterTest {
	
	public static void main(String[] args) throws IOException, URISyntaxException{
		URI uri = new URI("file:///d:/index");
		Path path =Paths.get(uri);
		
		Directory d =FSDirectory.open(path);
		
		MyAnalyzer analyzer = new MyAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
		IndexWriter iw = new IndexWriter(d, conf);
		FieldType type = new FieldType();
		type.setStored(true);
		type.setDocValuesType(DocValuesType.SORTED_SET);
		type.freeze();
		Field field = new StringField("name", "value", Store.YES);
		Document doc = new Document();
		doc.add(field);
		iw.addDocument(doc);
		System.out.println(iw.getDirectory());
		System.out.println(iw.getFieldNames());
		
		
	}
	
}
