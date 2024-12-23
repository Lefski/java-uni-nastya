package ru.mirea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

class TasksTest extends AbstractTest {

    @ParameterizedTest
    @CsvSource({
        "'10\n5\n7\n1\n', 'Порог пройден 70% 35'",
        "'10\n10\n6\n2\n', 'Порог не пройден 60% 55'",
        "'8\n8\n8\n3\n', 'Порог пройден 100% 64'",
        "'5\n10\n2\n4\n', 'Порог не пройден 40% 5'",
        "'5\n10\n0\n1\n', 'Порог не пройден 0% 0'"
    })
    void testTask1(String input, String expectedOutput) {
        emulateInput(input);
        ByteArrayOutputStream outputStream = captureOutput();

        StudentTestsResults.main(new String[]{});

        String actualOutput = trimOutputStream(outputStream, 1);
        assertEquals(expectedOutput, actualOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "'1\n5.0\n5.0\n5.0\n', 'Площадь: 10.83\nТип: Равносторонний'",
        "'2\n5.5\n6.5\n90\n', 'Площадь: 17.87\nТип: Прямоугольный'",
        "'2\n5.0\n5.0\n50\n', 'Площадь: 9.58\nТип: Равнобедренный'",
        "'1\n3.0\n4.0\n5.0\n', 'Площадь: 6.00\nТип: Прямоугольный'",
        "'1\n1.0\n2.0\n3.5\n', 'Невозможно построить треугольник'",
        "'2\n5.0\n5.0\n180\n', 'Невозможно построить треугольник'"
    })
    void testTask2(String input, String expectedOutput) {
        emulateInput(input);
        ByteArrayOutputStream outputStream = captureOutput();
        expectedOutput = expectedOutput.replaceAll("\n", System.lineSeparator());

        Triangle.main(new String[]{});

        String actualOutput = trimOutputStream(outputStream, 0);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "'01.01.05\n15.06.06\n', 'Группа 2: 531'",
        "'01.01.05\n20.01.05\n', 'Группа 1: 20'",
        "'01.01.05\n01.05.06\n', 'Кандидаты не подходят'",
        "'31.12.04\n01.01.05\n', 'Ошибка: проверьте корректность данных.'",
        "'31.12.06\n01.01.07\n', 'Ошибка: проверьте корректность данных.'"
    })
    void testTask3(String input, String expectedOutput) {
        emulateInput(input);
        ByteArrayOutputStream outputStream = captureOutput();
        expectedOutput = expectedOutput.replaceAll("\n", System.lineSeparator());

        Tournament.main(new String[]{});

        String actualOutput = trimOutputStream(outputStream, 2);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "'R 1 1 0.01', 'Депозит: 1.00R,00 + 0.01% на 1 день:\n   1.00R,00 + 0.00R,00'",
        "'E 10000 1 100.0', 'Депозит: 10000.00E,00 + 100.00% на 1 день:\n   10000.00E,00 + 27.40E,00'",
        "'$ 1 1000 1', 'Депозит: 1.00$,00 + 1.00% на 1000 дней:\n   1.00$,00 + 0.03$,00'",
        "'R 10000 365 0.01', 'Депозит: 10000.00R,00 + 0.01% на 365 дней:\n   10000.00R,00 + 1.00R,00'",
        "'E 10 1 100.0', 'Депозит: 10.00E,00 + 100.00% на 1 день:\n   10.00E,00 + 0.03E,00'"
    })
    void testTask4(String input, String expectedOutput) {
        emulateInput(input);
        ByteArrayOutputStream outputStream = captureOutput();
        expectedOutput = expectedOutput.replaceAll("\n", System.lineSeparator());

        Task4.main(new String[]{});

        String actualOutput = trimOutputStream(outputStream, 1);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "'Привет, мир!\n3\n', 'Тулезх, плу!'",
        "'Программирование\n-5\n', 'Клйюлызздлйэыида'",
        "'Привет, мир!\n0\n', 'Привет, мир!'",
        "'Java разработка\n-10\n', 'Java жцюжцчеибц'"
    })
    void testTask5(String input, String expectedOutput) {
        emulateInput(input);
        ByteArrayOutputStream outputStream = captureOutput();
        expectedOutput = expectedOutput.replaceAll("\n", System.lineSeparator());

        CaesarCipher.main(new String[]{});

        String actualOutput = trimOutputStream(outputStream, 3);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @ParameterizedTest
    @CsvSource({
        """
            'Шоколад Молочный 4.0 6.0 10
            ', '== Отчет о продажах ==
            -- Отчет о продажах: Молочный шоколад --
            Выручка: 40.0
            Себестоимость: 60.0
            Прибыль: -20.0

            == Итоги ==
            Самый прибыльный товар: Молочный шоколад
            Прибыль: -20.0'""",

        """
            'Шоколад Молочный 6.0 5.0 10
            ШоколадСДобавками Ореховый 14.0 12.0 20 Орехи
            Шоколад Темный 8.0 6.0 15
            ШоколадСДобавками Карамельный 12.0 10.0 25 Карамель
            ', '== Отчет о продажах ==
            -- Отчет о продажах: Молочный шоколад --
            Выручка: 60.0
            Себестоимость: 50.0
            Прибыль: 10.0

            -- Отчет о продажах: Шоколад с добавками: Орехи --
            Выручка: 280.0
            Себестоимость: 240.0
            Прибыль: 40.0

            -- Отчет о продажах: Темный шоколад --
            Выручка: 120.0
            Себестоимость: 90.0
            Прибыль: 30.0

            -- Отчет о продажах: Шоколад с добавками: Карамель --
            Выручка: 300.0
            Себестоимость: 250.0
            Прибыль: 50.0

            == Итоги ==
            Самый прибыльный товар: Шоколад с добавками: Карамель
            Прибыль: 50.0'""",

        """
            'Шоколад Белый 10.0 8.0 1
            ШоколадСДобавками Ореховый 15.0 12.0 1 Орехи
            ', '== Отчет о продажах ==
            -- Отчет о продажах: Белый шоколад --
            Выручка: 10.0
            Себестоимость: 8.0
            Прибыль: 2.0

            -- Отчет о продажах: Шоколад с добавками: Орехи --
            Выручка: 15.0
            Себестоимость: 12.0
            Прибыль: 3.0

            == Итоги ==
            Самый прибыльный товар: Шоколад с добавками: Орехи
            Прибыль: 3.0'"""
    })
    void testTask6(String input, String expectedOutput) {
        emulateInput(input);
        ByteArrayOutputStream outputStream = captureOutput();
        expectedOutput = expectedOutput.replaceAll("\n", System.lineSeparator());

        TestSales.main(new String[]{});

        String actualOutput = trimOutputStream(outputStream, 0);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
