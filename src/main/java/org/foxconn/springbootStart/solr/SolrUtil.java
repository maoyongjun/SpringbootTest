package org.foxconn.springbootStart.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;

/**
* @author:myz
* @version 1.0 
* 创建时间：2018年5月17日 下午9:21:13
*/
public class SolrUtil {
	
	public void uploadDocument() throws SolrServerException, IOException{
	
		//1.创建连接对象
		final SolrClient solrClient =  getClient();
        //2.创建一个文档对象
        SolrInputDocument inputDocument = new SolrInputDocument();
        //向文档中添加域以及对应的值,注意：所有的域必须在schema.xml中定义过,前面已经给出过我定义的域。
        inputDocument.addField("id", "1");
        inputDocument.addField("item_title", "sansung爆炸牌手机");
        inputDocument.addField("item_price", 666);
        inputDocument.addField("item_image", "www.boom.png");
        //3.将文档写入索引库中
        solrClient.add(inputDocument);
        //提交
        solrClient.commit();
	}
	
	public SolrClient getClient(){
		final String solrUrl="http://localhost:8983/solr/update";
		return new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(10000).build();
	}
}
