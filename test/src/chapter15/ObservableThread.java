package chapter15;

import chapter15.Observable.Cycle;

public class ObservableThread<T> extends Thread {
	private final TaskLifecycle<T> lifecycle;
	private final Task<T> task;
	private Cycle cycle;

	public ObservableThread(Task<T> task)
	{
		this(new TaskLifecycle.EmptyLifecycle<>(), task);
	}
	
	public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task)
	{
		super();
		if (task == null) {
			throw new IllegalArgumentException("The task is required.");
		}
		this.lifecycle = lifecycle;
		this.task = task;
	}
	
	@Override
	public void run() {
		this.update(Cycle.STARTED, null, null);
		
		try {
			this.update(Cycle.RUNNING, null, null);
			
			T result = this.task.call();
			
			this.update(Cycle.DONE, result, null);
		} catch (Exception e) {
			this.update(Cycle.ERROR, null, e);
		}
	}
	
	private void update(Cycle cycle, T result, Exception e) {
		this.cycle = cycle;
		if (lifecycle == null) return;
		switch (cycle) {
		case STARTED:
			this.lifecycle.onStart(currentThread());
			break;
		case RUNNING:
			this.lifecycle.onRunning(currentThread());
			break;
		case DONE:
			this.lifecycle.onFinish(currentThread(), result);
			break;
		case ERROR:
			this.lifecycle.onError(currentThread(), e);
			break;
		}
	}
	
	public Cycle getCycle() {
		return cycle;
	}
}
