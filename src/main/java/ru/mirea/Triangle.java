package ru.mirea;

import java.util.Locale;
import java.util.Scanner;

public class Triangle {
    double side1, side2, side3;
    int angle12, angle23, angle31;

    // Инициализация по трём сторонам
    public Triangle(double a, double b, double c) {
        this.side1 = a;
        this.side2 = b;
        this.side3 = c;
        calculateAngles();
    }

    // Инициализация по двум сторонам и углу между ними
    public Triangle(double a, double b, int angle) {
        this.side1 = a;
        this.side2 = b;
        this.angle12 = angle;
        calculateThirdSide();
        calculateAngles();
    }

    // Инициализация по одной стороне и двум прилежащим углам
    public Triangle(double a, int angle1, int angle2) {
        this.side1 = a;
        this.angle23 = angle1;
        this.angle31 = angle2;
        calculateOtherSides();
        calculateAngles();
    }

    //Расчет углов треугольника по трем сторонам
    private void calculateAngles() {
        this.angle12 = calculateAngle(side1, side2, side3);
        this.angle23 = calculateAngle(side2, side3, side1);
        this.angle31 = calculateAngle(side3, side1, side2);
    }

    //Расчет 1 угла
    private int calculateAngle(double a, double b, double c) {
        return (int) Math.round (Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)) * 180 / Math.PI);
    }

    // Вычисление третьей стороны (закон косинусов)
    private void calculateThirdSide() {
        double angleRadians = Math.toRadians(angle12);
        this.side3 = Math.sqrt(side1 * side1 + side2 * side2 - 2 * side1 * side2 * Math.cos(angleRadians));
    }

    // Вычисление двух сторон (закон синусов)
    private void calculateOtherSides() {
        int totalAngle = 180 - angle23 - angle31;
        if (totalAngle <= 0) return;
        double angleRadians1 = Math.toRadians(angle23);
        double angleRadians2 = Math.toRadians(angle31);
        double totalAngleRadians = Math.toRadians(totalAngle);

        this.side2 = (side1 * Math.sin(angleRadians1)) / Math.sin(totalAngleRadians);
        this.side3 = (side1 * Math.sin(angleRadians2)) / Math.sin(totalAngleRadians);
    }

    // Проверка существования треугольника
    public boolean isValidTriangle() {
        return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
    }

    // Вычисление площади треугольника (формула Герона)
    public double calculateArea() {
        double p = (side1 + side2 + side3) / 2.0;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    // Определение типа треугольника
    public String determineType() {
        if (side1 == side2 && side2 == side3) {
            return "Равносторонний";
        } else if (side1 == side2 || side2 == side3 || side1 == side3) {
            return "Равнобедренный";
        } else if (angle12 == 90 || angle23 + angle31 == 90) {
            return "Прямоугольный";
        } else {
            return "Разносторонний";
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        // Ввод способа инициализации
        int method = scanner.nextInt();
        Triangle triangle = null;

        if (method == 1) {
            // Инициализация по трём сторонам
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            double c = scanner.nextDouble();
            triangle = new Triangle(a, b, c);
        } else if (method == 2) {
            // Инициализация по двум сторонам и углу
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            int angle = scanner.nextInt();
            triangle = new Triangle(a, b, angle);
        } else if (method == 3) {
            // Инициализация по одной стороне и двум углам
            double a = scanner.nextDouble();
            int angle1 = scanner.nextInt();
            int angle2 = scanner.nextInt();
            triangle = new Triangle(a, angle1, angle2);
        }

        if (triangle != null && triangle.isValidTriangle()) {
            double area = triangle.calculateArea();
            String type = triangle.determineType();
            System.out.printf("Площадь: %.2f%n", area);
            System.out.println("Тип: " + type);
        } else {
            System.out.println("Невозможно построить треугольник");
        }

        scanner.close();
    }
}

