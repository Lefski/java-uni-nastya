package ru.mirea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Интерфейс CalcSalesItem
interface CalcSalesItem {
    String reportTitle = "\n== Отчет о продажах ==";

    double calcSalesPrice(); // Рассчитать стоимость продаж
    double calcCost();       // Рассчитать себестоимость
    double calcProfit();     // Рассчитать прибыль
    String getName();        // Получить название товара

    static void printItemArray(CalcSalesItem[] items) {
        System.out.println(reportTitle);
        CalcSalesItem mostProfitable = null;

        for (CalcSalesItem item : items) {
            item.printItemReport();
            if (mostProfitable == null || item.calcProfit() > mostProfitable.calcProfit()) {
                mostProfitable = item;
            }
        }

        System.out.println("== Итоги ==");
        if (mostProfitable != null) {
            System.out.println("Самый прибыльный товар: " + mostProfitable.getName());
            System.out.println("Прибыль: " + mostProfitable.calcProfit());
        }
    }

    default void printItemReport() {
        System.out.println("-- Отчет о продажах: " + getName() + " --");
        System.out.println("Выручка: " + calcSalesPrice());
        System.out.println("Себестоимость: " + calcCost());
        System.out.println("Прибыль: " + calcProfit());
        System.out.println();
    }
}

// Класс Chocolate
class Chocolate implements CalcSalesItem {
    protected double salesPrice; // Цена за единицу
    protected double cost;       // Себестоимость за единицу
    protected long quantity;     // Количество товара
    protected String name;       // Название товара

    public Chocolate(double salesPrice, double cost, long quantity, String name) {
        this.salesPrice = salesPrice;
        this.cost = cost;
        this.quantity = quantity;
        this.name = name;
    }

    @Override
    public double calcSalesPrice() {
        return salesPrice * quantity;
    }

    @Override
    public double calcCost() {
        return cost * quantity;
    }

    @Override
    public double calcProfit() {
        return calcSalesPrice() - calcCost();
    }

    @Override
    public String getName() {
        return name + " шоколад";
    }
}

// Класс ChocolateWithAdditives
class ChocolateWithAdditives extends Chocolate {
    private String additive; // Название добавки

    public ChocolateWithAdditives(double salesPrice, double cost, long quantity, String additive) {
        super(salesPrice, cost, quantity, "Шоколад с добавками: " + additive);
        this.additive = additive;
    }

    @Override
    public String getName() {
        return "Шоколад с добавками: " + additive;
    }
}


public class TestSales {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<CalcSalesItem> items = new ArrayList<>();

        // Чтение данных из стандартного ввода
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break; // Завершить чтение, если строка пуста

            String[] parts = line.split(" ");
            String type = parts[0];
            String name = parts[1];
            double salesPrice = Double.parseDouble(parts[2]);
            double cost = Double.parseDouble(parts[3]);
            long quantity = Long.parseLong(parts[4]);

            if (type.equals("Шоколад")) {
                items.add(new Chocolate(salesPrice, cost, quantity, name));
            } else if (type.equals("ШоколадСДобавками")) {
                String additive = parts[5];
                items.add(new ChocolateWithAdditives(salesPrice, cost, quantity, additive));
            }
        }

        // Печать отчета
        CalcSalesItem.printItemArray(items.toArray(new CalcSalesItem[0]));
        scanner.close();
    }
}
