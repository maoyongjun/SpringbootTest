package org.foxconn.springbootStart;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.unlock();
	}
}
