import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Accouting {
    HashMap<Integer, ArrayList<MonthlyReport>> listMonthObject = new HashMap<>();
    ArrayList<YearlyReport> listForMapYear = new ArrayList<>();


    public void startApp() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            short inDigit = scanner.nextShort();
            switch (inDigit) {
                case 1:
                    for (int i = 1; i <= 3; i++) {
                        String fileName = "FilesOfReports\\Monthly\\m.20210" + i + ".csv";
                        splitStrAndAddToHashMap(Objects.requireNonNull(readFileContentsOrNull(fileName)), i);
                    }
                    printMenu();
                    break;
                case 2:
                    byte flag = 4;// для определения чтения годового или месячного файла
                    String fileName = "FilesOfReports\\Yearly\\y.2021.csv";
                    splitStrAndAddToHashMap(Objects.requireNonNull(readFileContentsOrNull(fileName)), flag);
                    printMenu();
                    break;
                case 3:
                    compareReports();
                    printMenu();
                    break;
                case 4:
                    infoForMonth(listMonthObject);
                    System.out.println();
                    printMenu();
                    break;
                case 5:
                    getInfoForYear(listForMapYear);
                    printMenu();
                    break;
                case 6:
                    System.exit(0);
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
        System.out.println("5. Вывести информацию о годовом отчете");
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

    private void splitStrAndAddToHashMap(String str, int j) {
        ArrayList<MonthlyReport> listForMapMonth = new ArrayList<>();
        String[] lines = str.split("\r\n");
        String[] linesContents;
        if (j != 4) {
            for (int i = 1; i < lines.length; i++) {
                linesContents = lines[i].split(",");
                listForMapMonth.add(new MonthlyReport(linesContents[0], Boolean.parseBoolean(linesContents[1]), Integer.parseInt(linesContents[2]), Integer.parseInt(linesContents[3])));
            }
            listMonthObject.put(j, listForMapMonth);
        } else {
            for (int i = 1; i < lines.length; i++) {
                linesContents = lines[i].split(",");
                listForMapYear.add(new YearlyReport(linesContents[0], Integer.parseInt(linesContents[1]), Boolean.parseBoolean(linesContents[2])));
            }
        }
    }

    private void compareReports() {
        int i = 1;
        int flag = 0; // маркер для положительной проверки месячного и годового отчетов
        for (int j = 0; j < listForMapYear.size(); j = j + 2) {
            if ((incomeAndCostCount(listMonthObject, i)[0] == listForMapYear.get(j).getAmount()) && (!listForMapYear.get(j).isIs_expense()) && (i == Integer.parseInt(listForMapYear.get(j).getMonth()))) {
                flag++;
            } else System.out.println("Отчет по доходам за " + i + " месяц не совпадают с годовым отчетом.");
            if ((incomeAndCostCount(listMonthObject, i)[1] == listForMapYear.get(j + 1).getAmount()) && (listForMapYear.get(j + 1).isIs_expense()) && (i == Integer.parseInt(listForMapYear.get(j + 1).getMonth()))) {
                flag++;
            } else System.out.println("Отчет по расходам за " + i + " месяц не совпадают с годовым отчетом.");
            i++;
        }
        if (flag == listForMapYear.size()) System.out.println("Месячные и годовой отчеты совпадают.");
    }

    private int[] incomeAndCostCount(HashMap<Integer, ArrayList<MonthlyReport>> list, int numberOfMonth) {
        int[] incomeAndCosts = new int[2];// [0] - доходы  [1] - расходы
        ArrayList<MonthlyReport> values = list.get(numberOfMonth);
        for (MonthlyReport mr : values) {
            if (mr.isExpense()) {
                incomeAndCosts[1] = incomeAndCosts[1] + (mr.getSumOfOne() * mr.getQuantity());
            } else {
                incomeAndCosts[0] = incomeAndCosts[0] + (mr.getSumOfOne() * mr.getQuantity());
            }
        }
        return incomeAndCosts;
    }

    private void infoForMonth(HashMap<Integer, ArrayList<MonthlyReport>> map) {
        for (Map.Entry<Integer, ArrayList<MonthlyReport>> entry : map.entrySet()) {
            int key = entry.getKey();
            ArrayList<MonthlyReport> values = entry.getValue();

            String nameProductIncome = "";
            int priceProductIncome = 0;
            int maxValueProductIncome = 0;

            String nameProductCost = "";
            int priceProductCost = 0;
            int maxValueProductCost = 0;

            for (MonthlyReport mr : values) {
                int temp = mr.getQuantity() * mr.getSumOfOne();
                if (mr.isExpense() == false && maxValueProductIncome < temp) {
                    maxValueProductIncome = temp;
                    priceProductIncome = mr.getSumOfOne();
                    nameProductIncome = mr.getItemName();

                }

                if (mr.isExpense() == true && maxValueProductCost < temp) {
                    maxValueProductCost = temp;
                    priceProductCost = mr.getSumOfOne();
                    nameProductCost = mr.getItemName();

                }
            }
            System.out.println("В 0" + key + " месяце самый прибыльный товар - " + nameProductIncome + " с суммуой " + priceProductIncome);
            System.out.println("В 0" + key + " месяце самая большая трата - " + nameProductCost + " на сумму " + priceProductCost);
        }
    }

    private void getInfoForYear(ArrayList<YearlyReport> list) {
        int averageCostForAllMonths = 0;
        int averageIncomForAllMonths = 0;

        for (int i = 0; i < list.size(); i = i + 2) {
            int diff = list.get(i).getAmount() - list.get(i + 1).getAmount();
            averageIncomForAllMonths += list.get(i).getAmount();
            averageCostForAllMonths += list.get(i + 1).getAmount();
            System.out.println("Прибыль за " + list.get(i).getMonth() + " месяц составляет " + diff + "\n");
        }
        System.out.println("Средний расход за год составляет: " + averageCostForAllMonths / (list.size() / 2));
        System.out.println("Средний доход за год составляет: " + averageIncomForAllMonths / (list.size() / 2));
    }
}
