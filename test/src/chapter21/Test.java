package chapter21;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test {
	public static void main(String[] args) {
		ThreadLocal<Integer> tlocal = new ThreadLocal<>();
		
		IntStream.range(0, 10).forEach(i -> new Thread(() -> {
			tlocal.set(i);
			System.out.println(Thread.currentThread() + " set i " + tlocal.get());
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + " get i " + tlocal.get());
			
			}).start()
		);
	}
}
