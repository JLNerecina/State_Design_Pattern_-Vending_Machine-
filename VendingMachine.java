import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    
    private VendingMachineState currentState;
    private Map<String, Integer> inventory;      // Item ID -> Quantity
    private Map<String, Double> itemPrices;      // Item ID -> Price
    private double balance;                        // Current balance
    private String selectedItem;                   // Currently selected item
    
    public VendingMachine() {
        this.currentState = new IdleState();
        this.inventory = new HashMap<>();
        this.itemPrices = new HashMap<>();
        this.balance = 0.0;
        this.selectedItem = null;
    }
    
    public void addItem(String itemId, int quantity, double price) {
        inventory.put(itemId, inventory.getOrDefault(itemId, 0) + quantity);
        itemPrices.put(itemId, price);
        System.out.println("Added " + quantity + " of item " + itemId + " at $" + price);
    }
    
    public void selectItem(String itemId) {
        currentState.selectItem(this, itemId);
    }
    
    public void insertCoin(double amount) {
        currentState.insertCoin(this, amount);
    }
    
    public void dispenseItem() {
        currentState.dispenseItem(this);
    }
    
    public void setOutOfOrder() {
        currentState.setOutOfOrder(this);
    }
    
    public VendingMachineState getState() {
        return currentState;
    }
    
    public void setState(VendingMachineState state) {
        System.out.println("State changed to: " + state.getStateName());
        this.currentState = state;
    }
    
    public Map<String, Integer> getInventory() {
        return inventory;
    }
    
    public double getBalance() {
        return balance;
    }
    

    public void addBalance(double amount) {
        this.balance += amount;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public String getSelectedItem() {
        return selectedItem;
    }
    
    public void setSelectedItem(String itemId) {
        this.selectedItem = itemId;
    }
    
    public double getItemPrice(String itemId) {
        return itemPrices.getOrDefault(itemId, 0.0);
    }
    
    public String getCurrentStateName() {
        return currentState.getStateName();
    }

    public void displayStatus() {
        System.out.println("\n=== Vending Machine Status ===");
        System.out.println("Current State: " + currentState.getStateName());
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
        System.out.println("Selected Item: " + (selectedItem != null ? selectedItem : "None"));
        System.out.println("Inventory: " + inventory);
        System.out.println("==============================\n");
    }
}