package chapter15;

import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		Task task = () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" finished done...");
			return "perfect";
		};
		
		TaskLifecycle<String> lifecycle = new TaskLifecycle<String>() {
			
			@Override
			public void onStart(Thread thread) {
				System.out.println(thread.getName() + " start...");
				
			}
			
			@Override
			public void onRunning(Thread thread) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinish(Thread thread, String result) {
				System.out.println("The result is " + result);
			}
			
			@Override
			public void onError(Thread thread, Exception e) {
				// TODO Auto-generated method stub
				
			}
		};
		ObservableThread t = new ObservableThread<>(lifecycle, task);
		t.start();
	}
}
