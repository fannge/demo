package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Java8Tester {
	public static void main(String args[]) {
		test3();
	}
	
	private static void test3() {
		System.out.println("ʹ�� Java 7: ");
        
	      // ������ַ���
	      List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
	      System.out.println("�б�: " +strings);
	      long count = getCountEmptyStringUsingJava7(strings);
	        
	      System.out.println("���ַ�����Ϊ: " + count);
	      count = getCountLength3UsingJava7(strings);
	        
	      System.out.println("�ַ�������Ϊ 3 ������Ϊ: " + count);
	        
	      // ɾ�����ַ���
	      List<String> filtered = deleteEmptyStringsUsingJava7(strings);
	      System.out.println("ɸѡ����б�: " + filtered);
	        
	      // ɾ�����ַ�������ʹ�ö��Ű����Ǻϲ�����
	      String mergedString = getMergedStringUsingJava7(strings,", ");
	      System.out.println("�ϲ��ַ���: " + mergedString);
	      List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
	        
	      // ��ȡ�б�Ԫ��ƽ����
	      List<Integer> squaresList = getSquares(numbers);
	      System.out.println("ƽ�����б�: " + squaresList);
	      List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
	        
	      System.out.println("�б�: " +integers);
	      System.out.println("�б��������� : " + getMax(integers));
	      System.out.println("�б�����С���� : " + getMin(integers));
	      System.out.println("������֮�� : " + getSum(integers));
	      System.out.println("ƽ���� : " + getAverage(integers));
	      System.out.println("�����: ");
	        
	      // ���10�������
	      Random random = new Random();
	        
	      for(int i=0; i < 10; i++){
	         System.out.println(random.nextInt());
	      }
	        
	      System.out.println("ʹ�� Java 8: ");
	      System.out.println("�б�: " +strings);
	        
	      count = strings.stream().filter(string->string.isEmpty()).count();
	      System.out.println("���ַ�������Ϊ: " + count);
	        
	      count = strings.stream().filter(string -> string.length() == 3).count();
	      System.out.println("�ַ�������Ϊ 3 ������Ϊ: " + count);
	        
	      filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
	      System.out.println("ɸѡ����б�: " + filtered);
	        
	      mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
	      System.out.println("�ϲ��ַ���: " + mergedString);
	        
	      squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
	      System.out.println("Squares List: " + squaresList);
	      System.out.println("�б�: " +integers);
	        
	      IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();
	        
	      System.out.println("�б��������� : " + stats.getMax());
	      System.out.println("�б�����С���� : " + stats.getMin());
	      System.out.println("������֮�� : " + stats.getSum());
	      System.out.println("ƽ���� : " + stats.getAverage());
	      System.out.println("�����: ");
	        
	      random.ints().limit(10).sorted().forEach(System.out::println);
	        
	      // ���д���
	      count = strings.parallelStream().filter(string -> string.isEmpty()).count();
	      System.out.println("���ַ���������Ϊ: " + count);
	}

	private static void test2() {
		List names = new ArrayList();

		names.add("Google");
		names.add("Runoob");
		names.add("Taobao");
		names.add("Baidu");
		names.add("Sina");

		names.forEach(System.out::println);
	}
	
	private static int getCountEmptyStringUsingJava7(List<String> strings){
	      int count = 0;
	        
	      for(String string: strings){
	        
	         if(string.isEmpty()){
	            count++;
	         }
	      }
	      return count;
	   }
	    
	   private static int getCountLength3UsingJava7(List<String> strings){
	      int count = 0;
	        
	      for(String string: strings){
	        
	         if(string.length() == 3){
	            count++;
	         }
	      }
	      return count;
	   }
	    
	   private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
	      List<String> filteredList = new ArrayList<String>();
	        
	      for(String string: strings){
	        
	         if(!string.isEmpty()){
	             filteredList.add(string);
	         }
	      }
	      return filteredList;
	   }
	    
	   private static String getMergedStringUsingJava7(List<String> strings, String separator){
	      StringBuilder stringBuilder = new StringBuilder();
	        
	      for(String string: strings){
	        
	         if(!string.isEmpty()){
	            stringBuilder.append(string);
	            stringBuilder.append(separator);
	         }
	      }
	      String mergedString = stringBuilder.toString();
	      return mergedString.substring(0, mergedString.length()-2);
	   }
	    
	   private static List<Integer> getSquares(List<Integer> numbers){
	      List<Integer> squaresList = new ArrayList<Integer>();
	        
	      for(Integer number: numbers){
	         Integer square = new Integer(number.intValue() * number.intValue());
	            
	         if(!squaresList.contains(square)){
	            squaresList.add(square);
	         }
	      }
	      return squaresList;
	   }
	    
	   private static int getMax(List<Integer> numbers){
	      int max = numbers.get(0);
	        
	      for(int i=1;i < numbers.size();i++){
	        
	         Integer number = numbers.get(i);
	            
	         if(number.intValue() > max){
	            max = number.intValue();
	         }
	      }
	      return max;
	   }
	    
	   private static int getMin(List<Integer> numbers){
	      int min = numbers.get(0);
	        
	      for(int i=1;i < numbers.size();i++){
	         Integer number = numbers.get(i);
	        
	         if(number.intValue() < min){
	            min = number.intValue();
	         }
	      }
	      return min;
	   }
	    
	   private static int getSum(List numbers){
	      int sum = (int)(numbers.get(0));
	        
	      for(int i=1;i < numbers.size();i++){
	         sum += (int)numbers.get(i);
	      }
	      return sum;
	   }
	    
	   private static int getAverage(List<Integer> numbers){
	      return getSum(numbers) / numbers.size();
	   }

	private static void test1() {
		Java8Tester tester = new Java8Tester();

		// ��������
		MathOperation addition = (int a, int b) -> a + b;

		// ������������
		MathOperation subtraction = (a, b) -> a - b;

		// �������еķ������
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// û�д����ż��������
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + tester.operate(10, 5, division));

		// ��������
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		// ������
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Runoob");
		greetService2.sayMessage("Google");
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}