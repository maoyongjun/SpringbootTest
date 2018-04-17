package org.foxconn.springbootStart.dao;

import org.foxconn.springbootStart.entity.EUSER;

public interface UserDao {
	public EUSER getUserByName(String name);
}
