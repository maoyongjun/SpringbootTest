package org.foxconn.springbootStart.lucene;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexableFieldType;

public class MyField extends Field{
	
	  protected MyField(String name, IndexableFieldType type) {
		super(name, type);
	}

	/** Indexed, tokenized, not stored. */
	  public static final FieldType TYPE_NOT_STORED = new FieldType();

	  /** Indexed, tokenized, stored. */
	  public static final FieldType TYPE_STORED = new FieldType();

	  static {
	    TYPE_NOT_STORED.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
	    TYPE_NOT_STORED.setTokenized(true);

	    TYPE_STORED.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
	    TYPE_STORED.setTokenized(true);
	    TYPE_STORED.setStored(true);
	  }
}