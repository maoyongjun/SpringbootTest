package org.foxconn.springbootStart.lucene;

import org.apache.lucene.util.Attribute;

public interface MyAttribute extends Attribute{
	public String getText();
	public void setText(String text);
}
