package ua.i.licit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Controller mc = new MenuController();
        Scanner sc = new Scanner(System.in);
        try {
            for (; ; ) {
                System.out.println("1: add a meal");
                System.out.println("2: select meals by price");
                System.out.println("3: select meals by discount");
                System.out.println("4: select meals weight not over one kilo");
                System.out.println("5: view meals");
                System.out.print("-> ");

                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        mc.addRow();
                        break;
                    case "2":
                        System.out.println(mc.selectByPrice());
                        break;
                    case "3":
                        System.out.println(mc.selectByDiscount());
                        break;
                    case "4":
                        System.out.println(mc.pickMealsKilogramm());
                        break;
                    case "5":
                        mc.viewMeals();
                        break;
                    default:
                        return;
                }
            }
        } finally {
            mc.closeConnection();
            sc.close();
        }
    }
}
