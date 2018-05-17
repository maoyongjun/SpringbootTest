package org.foxconn.springbootStart;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.foxconn.springbootStart.solr.SolrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
* @author:myz
* @version 1.0 
* 创建时间：2018年5月17日 下午10:47:35
*/
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestSolrClient {
	@Test
	public void test() throws SolrServerException, IOException{
		SolrUtil util = new SolrUtil();
		util.uploadDocument();
	}
}
