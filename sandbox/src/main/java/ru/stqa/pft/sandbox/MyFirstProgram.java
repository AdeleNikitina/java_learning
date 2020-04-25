package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		// Лекция 1.7. Повторяем урок
		hello("world");
		hello("Adele");
		hello("Alexei");

		// Лекция 1.8. Повторяем урок
		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r =  new Rectangle(4,6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

		// Лекция 1.5. Повторяем урок
		//double l = 8;
		//double s = l * l;
		//System.out.println("Площадь квадрата со стороной " + l + " = " + s);
	}

	// Лекция 1.7. Повторяем урок
	public static void hello(String somebody) {
		System.out.println("Hello, " +  somebody + "!");
	}

}