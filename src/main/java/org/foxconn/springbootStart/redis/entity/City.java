package org.foxconn.springbootStart.redis.entity;

import java.io.Serializable;

public class City  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cityname;
	
	public City(String cityname) {
		super();
		this.cityname = cityname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	
}
