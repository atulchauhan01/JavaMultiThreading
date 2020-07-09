package playground;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor1 implements Runnable {

	CountDownLatch latch;

	public Processor1(CountDownLatch latch) {
		this.latch = latch;

	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		System.out.println(latch.getCount());

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		System.out.println("After sleep" + latch.getCount());
		
		latch.countDown();
		System.out.println("after countDown:: "+latch.getCount());

	}

}

public class CountDownLatch1 {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);

		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 3; i++) {
			System.out.println("Starting...." + i);
			executor.submit(new Processor1(latch));
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Completed");

	}

}
