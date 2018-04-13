package org.foxconn.springbootStart.datasource;

public class DataSourceHolder {
	private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

	public static String getDatasources() {
		return (String)dataSources.get();
	}

	public static void setDatasources(String type) {
		dataSources.set(type);
	}
	public static void clearDataSource(){
		dataSources.remove();
	}
	
}
