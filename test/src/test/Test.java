package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
	public static void main(String[] args) {
		test3();
	}
	
	static void test4 () {
//		IntStream.range(0, 5).forEach(System.out::println);
		
//		List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
//		System.out.println("筛选列表: " + filtered);
//		String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining("| "));
//		System.out.println("合并字符串: " + mergedString);
		
//		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//		List<Integer> squaresList = numbers.stream().distinct().map(i -> i*i).collect(Collectors.toList());
//		squaresList.forEach(System.out::println);
		
//		Random random = new Random();
//		random.ints().limit(5).forEach(System.out::println);
//		
//		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//		strings.forEach(System.out::println);
		
//		Runnable r = ()->System.out.println(Thread.currentThread().getName());
//		IntStream.range(5, 10).boxed().map(
//				i->new Thread(r)
//			).forEach(Thread::start);
		
//		IntStream.range(0, 5).mapToObj(Test::createThread).forEach(Thread::start);
		
		List<Thread> threads = IntStream.range(1, 3).mapToObj(Test::createThread2).collect(Collectors.toList());
		
		threads.forEach(Thread::start);
		
		try {
			threads.get(0).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		

		
		
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() +"#" + i);
			sleep();
		}
	}
	
	private static void sleep () {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Thread createThread2(final int intName)
	{
		return new Thread(
					()->{
						for (int i = 0; i < 10; i++) {
							System.out.println(Thread.currentThread().getName() +"#" + i);
							sleep();
						}
					} , "" + intName
				);
	}
	
	private static Thread createThread(final int intName)
	{
		return new Thread(
				()->System.out.println(Thread.currentThread().getName()), "ALEX-" + intName
				);
	}
	
	static void test3 () {
		IntStream.range(0, 5).boxed().map(
					i->new Thread(
								()->System.out.println(Thread.currentThread().getName())
							  )
				).forEach(Thread::start);
	}
	
	static void test () {
		try {
			byte[] bytes = Files.readAllBytes(Paths.get("d:/a.txt"));
			String context = new String(bytes);
			System.out.println(Arrays.asList(context.split("[\\P{L}]+")).size());
			
			long start = new Date().getTime();
			long count = Arrays.asList(context.split("[\\P{L}]+")).parallelStream().filter(w -> w.length() > 5).count();
			long end = new Date().getTime();
			System.out.println(end - start);
			System.out.println(count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void test2 () {
		List<String> list = Arrays.asList("aab", null, null, "bbb");
		list.stream().forEach(aa -> System.out.println(aa));
		list.forEach(System.out::println);
		
		list.stream().filter(aa -> aa != null).forEach(aa -> System.out.println(aa));
		long count = list.stream().filter(aa -> aa != null).count();
		System.out.println(count);
		
		list.forEach(System.out::println);
	}
	
	static void test1 () {
		// 1、数组
	    String[] arr = new String[]{"ab", "cd", "ef"};
	    Stream<String> arrStream = Arrays.stream(arr);
	    // 2、集合
	    List<String> list = Arrays.asList("aab", "ffd", "eef", "bbb");
	    Stream<String> colStream = list.stream();
	    // 3、值
	    Stream<String> stream = Stream.of("ab", "cd", "ef");
	    
	    // java 8 lambda
	    System.out.println("java 8 lambda");
	    System.out.println("排序前：");
	    colStream.forEach(aa -> System.out.println(aa));
	    
	    System.out.println("排序后：");
	    list.stream().sorted().forEach(aa -> System.out.println(aa));
	    
	    List<User> users = Arrays.asList(
	    	      new User("张三", 11),
	    	      new User("王五", 20),
	    	      new User("王五", 91),
	    	      new User("张三", 8),
	    	      new User("李四", 44),
	    	      new User("李四", 44),
	    	      new User("李四", 44)
	    	  );
	    System.out.println("排序前：");
	    users.stream().forEach(user -> System.out.println(user));
	    
	    System.out.println("排序后：");
	    users.stream().sorted(Comparator.comparing(User::getAge)).forEach(user -> System.out.println(user));
	    
	    System.out.println("过滤到40岁以后的：");
	    users.stream().filter(user -> user.getAge() < 40).forEach(user -> System.out.println(user));
	}
}

class User {
	private String name;
	private int age;
	
	public User() {
		super();
	}
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
}
