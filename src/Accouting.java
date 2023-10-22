import java.util.Scanner;

public class Accouting {
    public static void startApp() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            short inDigit = scanner.nextShort();
            switch (inDigit) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6: System.exit(0);
                    break;
                default:
            }

        }

    }

    public static void printMenu() {
        System.out.println("1. Считать все месячные отчеты");
        System.out.println("2. Считать годовой отчет");
        System.out.println("3. Сверить отчеты");
        System.out.println("4. Вывести информацию о всех месячных отчетах");
        System.out.println("5. Вывести информацию о всех месячных отчетах");
        System.out.println("6. Выход из приложения");
    }
}
