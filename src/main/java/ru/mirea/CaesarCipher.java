package ru.mirea;

import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Чтение входных данных
        System.out.println("Введите текст для шифрования:");
        String inputText = scanner.nextLine();

        System.out.println("Введите сдвиг:");
        int shift = scanner.nextInt();

        // Шифрование текста
        String encryptedText = encrypt(inputText, shift);

        // Вывод результата
        System.out.println("Зашифрованный текст:");
        System.out.println(encryptedText);
    }

    /**
     * Метод для шифрования текста с использованием шифра Цезаря
     *
     * @param text  текст для шифрования
     * @param shift величина сдвига
     * @return зашифрованный текст
     */
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        // Русский алфавит
        String upperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String lowerCase = upperCase.toLowerCase();

        // Перебор каждого символа текста
        for (char ch : text.toCharArray()) {
            if (upperCase.indexOf(ch) != -1) {
                // Сдвиг для заглавных букв
                result.append(shiftChar(ch, shift, upperCase));
            } else if (lowerCase.indexOf(ch) != -1) {
                // Сдвиг для строчных букв
                result.append(shiftChar(ch, shift, lowerCase));
            } else {
                // Оставить неизмененные символы
                result.append(ch);
            }
        }

        return result.toString();
    }

    /**
     * Метод для выполнения сдвига символа
     *
     * @param ch       символ для шифрования
     * @param shift    величина сдвига
     * @param alphabet алфавит
     * @return зашифрованный символ
     */
    private static char shiftChar(char ch, int shift, String alphabet) {
        int length = alphabet.length();
        int originalIndex = alphabet.indexOf(ch);
        int newIndex = (originalIndex + shift) % length;

        if (newIndex < 0) {
            newIndex += length; // Коррекция для отрицательного сдвига
        }

        return alphabet.charAt(newIndex);
    }
}
