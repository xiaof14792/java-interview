package o;

import java.util.concurrent.CountDownLatch;
/**
 * 倒计时门栓-CountDownLatch
 * 让主线程等待一组事件发生后继续执行
 * @author 14792
 *
 */
public class CountDownLatchDemo {
	public static void main(String[] args) {
		try {
			new CountDownLatchDemo().go();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void go() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		//依次创建3个线程，并启动
		new Thread(new Task(countDownLatch),"Thread-1").start();
		Thread.sleep(1000);
		new Thread(new Task(countDownLatch),"Thread-2").start();
		Thread.sleep(1000);
		new Thread(new Task(countDownLatch),"Thread-3").start();
		countDownLatch.await();
		
		//
		System.out.println("所有线程都已到达，主线程开始执行." + System.currentTimeMillis());
	}
	
	class Task implements Runnable{
		private CountDownLatch countDownLatch;
		
		
		public Task(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}


		@Override
		public void run() {
			System.out.println("线程" + Thread.currentThread().getName() + "已经到达：" + System.currentTimeMillis());
			countDownLatch.countDown();
		}
		
	}
}
 