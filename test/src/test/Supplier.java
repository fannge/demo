package test;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
 
class Car {
    //Supplier��jdk1.8�Ľӿڣ������lamdaһ��ʹ����
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
    
    public static void main(String[] args) {
    	final Car car = Car.create( Car::new );
    	car.repair();
	}
}