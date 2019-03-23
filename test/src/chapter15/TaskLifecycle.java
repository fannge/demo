package chapter15;

public interface TaskLifecycle<T> {
	void onStart(Thread thread);
	
	void onRunning(Thread thread);
	
	void onFinish(Thread thread, T result);
	
	void onError(Thread thread, Exception e);
	
	class EmptyLifecycle<T> implements TaskLifecycle<T> {
		@Override
		public void onStart(Thread thread) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRunning(Thread thread) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFinish(Thread thread, T result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onError(Thread thread, Exception e) {
			// TODO Auto-generated method stub
			
		}
	}
}
