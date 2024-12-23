package ru.mirea;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Tournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Чтение входных данных
        System.out.println("Введите дату рождения первого участника (старшего) в формате ДД.ММ.ГГ:");
        String inputDate1 = scanner.nextLine();

        System.out.println("Введите дату рождения второго участника (младшего) в формате ДД.ММ.ГГ:");
        String inputDate2 = scanner.nextLine();

        // Форматирование дат
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

        try {
            LocalDate date1 = LocalDate.parse(inputDate1, formatter);
            LocalDate date2 = LocalDate.parse(inputDate2, formatter);

            // Проверка диапазона дат
            LocalDate minDate = LocalDate.of(2005, 1, 1);
            LocalDate maxDate = LocalDate.of(2006, 12, 31);

            if (date1.isBefore(minDate) || date1.isAfter(maxDate) ||
                date2.isBefore(minDate) || date2.isAfter(maxDate)) {
                System.out.println("Ошибка: проверьте корректность данных.");
                return;
            }

            // Проверка порядка дат
            if (date2.isBefore(date1)) {
                System.out.println("Ошибка: проверьте корректность данных.");
                return;
            }

            // Вычисление разницы в днях (включительно)
            long daysDifference = date2.toEpochDay() - date1.toEpochDay() + 1;

            // Определение группы или неподходящих кандидатов
            if (daysDifference <= 50) {
                System.out.println("Группа 1: " + daysDifference);
            } else if (daysDifference > 500) {
                System.out.println("Группа 2: " + daysDifference);
            } else {
                System.out.println("Кандидаты не подходят");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: проверьте корректность данных.");
        }
    }
}