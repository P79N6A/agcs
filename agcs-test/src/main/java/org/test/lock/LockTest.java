package org.test.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
	
	public static void main(String[] args) {
		
		MyCount myCount = new MyCount("121154545", 7000);
		ReadWriteLock lock = new ReentrantReadWriteLock(false);
		ExecutorService pool = Executors.newFixedThreadPool(2);
		User u1 = new User("张三", myCount, -4000, lock, false);  
        User u2 = new User("张三他爹", myCount, -6000, lock, false); 
        User u3 = new User("张三他弟", myCount, -8000, lock, false);  
        User u4 = new User("张三", myCount, 800, lock, false);  
        User u5 = new User("张三他爹", myCount, 0, lock, true);
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);  
        pool.execute(u4);  
        pool.execute(u5);
        pool.shutdown();
	}

}

class User implements Runnable{
	
	private String name;
	private MyCount myCount;
	private int iocash;
	private ReadWriteLock myLock;
	private boolean ischeck;
	
	User(String name, MyCount myCount, int iocash, ReadWriteLock myLock,
			boolean ischeck) {
		this.name = name;
		this.myCount = myCount;
		this.iocash = iocash;
		this.myLock = myLock;
		this.ischeck = ischeck;
	}

	@Override
	public void run() {
		if(ischeck){
			myLock.readLock().lock();
			System.out.println("读："+name+"正在查询"+myCount+"账户，当前余额为"+myCount.getCash());
			myLock.readLock().unlock();
		}else{
			myLock.writeLock().lock();
			System.out.println("写："+name+"正在操作"+myCount+"账户，金额为"+iocash+",当前余额为"+myCount.getCash());
			myCount.setCash(myCount.getCash()+iocash);
			System.out.println("写："+name+"操作"+myCount+"账户成功，金额为"+iocash+",当前余额为"+myCount.getCash());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myLock.writeLock().unlock();
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MyCount getMyCount() {
		return myCount;
	}

	public void setMyCount(MyCount myCount) {
		this.myCount = myCount;
	}

	public int getIocash() {
		return iocash;
	}

	public void setIocash(int iocash) {
		this.iocash = iocash;
	}

	public ReadWriteLock getMyLock() {
		return myLock;
	}

	public void setMyLock(ReadWriteLock myLock) {
		this.myLock = myLock;
	}

	public boolean isIscheck() {
		return ischeck;
	}

	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}
	
	
}

class MyCount{
	
	private String count;
	private int cash;
	
	MyCount(String count, int cash){
		this.count = count;
		this.cash = cash;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "MyCount{count='"+count+"', cash="+cash+"}";
	}
	
	
}
