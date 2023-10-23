public class MonthlyReport {
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private int sumOfOne;

    public MonthlyReport(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }

    public void setSumOfOne(int sumOfOne) {
        this.sumOfOne = sumOfOne;
    }

    @Override
    public String toString() {
        return getItemName() + " " + isExpense + " " + getQuantity() + " " + getSumOfOne();
    }
}
