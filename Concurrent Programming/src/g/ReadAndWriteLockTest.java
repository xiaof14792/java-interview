package g;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLockTest {
	double balance;
	
	
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	//抽取读锁和写锁
	private Lock readLock = rwl.readLock();
	private Lock writeLock = rwl.writeLock();
	
	
	//对所有的获取方法加读锁
	public double getBalance() {
		readLock.lock();
		try {
			return balance;
		} finally {
			readLock.unlock();
		}
	}
	
	//对所有的修改方法加写锁
	public void setBalance(double balance) {
		writeLock.lock();
		
		try {
			this.balance = balance;
		} finally {
			writeLock.unlock();
		}
	}
}
