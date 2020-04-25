package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		// Лекция 1.7. Повторяем урок
		hello("world");
		hello("Adele");
		hello("Alexei");

		// Лекция 1.7. Повторяем урок
		double l = 5;
		System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

		double a = 4;
		double b = 5;
		System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));

		// Лекция 1.5. Повторяем урок
		//double l = 8;
		//double s = l * l;
		//System.out.println("Площадь квадрата со стороной " + l + " = " + s);
	}

	// Лекция 1.7. Повторяем урок
	public static void hello(String somebody) {
		System.out.println("Hello, " +  somebody + "!");
	}

	// Лекция 1.7. Повторяем урок
	public static double area(double len){
		return  len * len;
	}

	// Лекция 1.7. Повторяем урок
	public static double area(double a, double b){
		return  a * b;
	}
}