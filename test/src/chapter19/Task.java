package chapter19;

public interface Task<IN, OUT> {
	OUT get(IN input);
}
