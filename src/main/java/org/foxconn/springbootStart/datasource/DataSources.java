package org.foxconn.springbootStart.datasource;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSources {
	private Logger logger = Logger.getLogger(DataSources.class);
	@Bean(name="db1")
	@ConfigurationProperties(prefix="spring.datasource.db1")
	public DataSource getDataSource(){
		logger.info(DataSourceBuilder.create().build());
		return DataSourceBuilder.create().build();
	}
//	@Bean(name="db2")
//	@ConfigurationProperties(prefix="spring.datasource.db2")
//	public DataSource getDataSource2(){
////		return DataSourceBuilder.create().build();
//		return null;
//	}
//	@Bean(name="db3")
//	@ConfigurationProperties(prefix="spring.datasource.db3")
//	public DataSource getDataSource3(){
////		return DataSourceBuilder.create().build();
//		return null;
//	}
}
