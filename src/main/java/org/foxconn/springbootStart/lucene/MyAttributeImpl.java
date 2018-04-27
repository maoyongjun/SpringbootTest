package org.foxconn.springbootStart.lucene;

import org.apache.lucene.util.AttributeImpl;
import org.apache.lucene.util.AttributeReflector;

public class MyAttributeImpl extends AttributeImpl implements MyAttribute{
	private String text;
	@Override
	public void clear() {
		text="";
	}


	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		this.text = text;
	}


	@Override
	public void reflectWith(AttributeReflector reflector) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void copyTo(AttributeImpl target) {
		
	}

}
