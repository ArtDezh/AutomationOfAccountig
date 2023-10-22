import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Accouting {
    public void startApp() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            short inDigit = scanner.nextShort();
            switch (inDigit) {
                case 1:
                    for (int i = 1; i <=3; i++) {
                        String fileName = "FilesOfReports\\Monthly\\m.20210" + i + ".csv";
                        readFileContentsOrNull(fileName);
                    }
                    break;
                case 2: String fileName = "FilesOfReports\\Yearly\\y.2021.csv";
                        readFileContentsOrNull(fileName);
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

    public void printMenu() {
        System.out.println("1. Считать все месячные отчеты");
        System.out.println("2. Считать годовой отчет");
        System.out.println("3. Сверить отчеты");
        System.out.println("4. Вывести информацию о всех месячных отчетах");
        System.out.println("5. Вывести информацию о всех месячных отчетах");
        System.out.println("6. Выход из приложения");

    }
    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчетом. Возможно, файла не существует.");
            return null;
        }
    }
}
