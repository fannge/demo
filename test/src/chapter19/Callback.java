package chapter19;

@FunctionalInterface
public interface Callback<T> {
	void call(T t);
}
