package ru.mirea;

import java.util.Scanner;

public class StudentTestsResults {

    // Метод для расчёта процента успешных тестов
    public static int calculatePercentage(int passedTests, int totalTests) {
        return (passedTests * 100) / totalTests;
    }

    // Метод для расчёта итоговых баллов
    public static int calculateScore(int passedTests, int pointsPerTest, int totalTests, int attempt) {
        int score = passedTests * pointsPerTest; // Начальные баллы за успешные тесты

        // Если пройдены все тесты, добавить бонусные баллы
        if (passedTests == totalTests) {
            score += pointsPerTest;
        }

        // Учёт штрафа за попытку (начиная со второй)
        if (attempt > 1) {
            score -= (attempt - 1) * pointsPerTest / 2;
        }

        // Убедиться, что баллы не отрицательные
        return Math.max(score, 0);
    }

    // Метод для вывода результата
    public static void displayResult(int percentage, int score, int threshold) {
        if (percentage >= threshold) {
            System.out.printf("Порог пройден %d%% %d%n", percentage, score);
        } else {
            System.out.printf("Порог не пройден %d%% %d%n", percentage, score);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод данных
        System.out.println("Введите количество тестов, баллы за один тест, количество пройденных тестов и номер попытки:");
        int totalTests = scanner.nextInt(); // Количество тестов
        int pointsPerTest = scanner.nextInt(); // Баллы за один пройденный тест
        int passedTests = scanner.nextInt(); // Количество пройденных тестов
        int attempt = scanner.nextInt(); // Номер попытки

        // Порог успешности
        int threshold = 70;

        // Расчёт процента успешных тестов
        int percentage = calculatePercentage(passedTests, totalTests);

        // Расчёт итогового балла
        int score = calculateScore(passedTests, pointsPerTest, totalTests, attempt);

        // Вывод результата
        displayResult(percentage, score, threshold);

        scanner.close();
    }
}
