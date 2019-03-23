
package chapter19;

import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		test3();
	}
	
	public static void test3 () {
		FutureService<String, Integer> service = FutureService.newService();
		
		service.submit(input -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return input.length();
		}, "Hello", System.out::println);
	}
	
	public static void test2 () {
		FutureService<String, Integer> service = FutureService.newService();
		
		Future<Integer> future = service.submit(input -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return input.length();
		}, "Hello");
		
		
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	public static void test1 () {
		FutureService<Void, Void> service = FutureService.newService();
		
		Future<?> future = service.submit(() ->
		{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("I am finish done.");
		});
		
		
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
