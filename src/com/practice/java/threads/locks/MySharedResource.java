package com.practice.java.threads.locks;

public class MySharedResource {
	ReentrantLock resouceLock = new ReentrantLock();
	
	public void sharedMethod(){
		try {
			resouceLock.lock();
			new MyAnotherSharedResource().innerMethod();
			innerMethod();
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+" is running in locked mode.");
			}
			resouceLock.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void innerMethod(){
		try {
			resouceLock.lock();
			System.out.println("Inner method with same resource.");
			resouceLock.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
