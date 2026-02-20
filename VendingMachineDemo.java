public class VendingMachineDemo {
    
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        
        // Setup: Add items to the vending machine
        System.out.println("=== Setting up Vending Machine ===\n");
        machine.addItem("SODA", 10, 1.50);
        machine.addItem("CHIPS", 15, 0.75);
        machine.addItem("CANDY", 20, 1.00);
        
        machine.displayStatus();
        
        // Scenario 1: Successful purchase
        System.out.println("=== Scenario 1: Successful Purchase ===\n");
        machine.selectItem("SODA");
        machine.insertCoin(1.50);
        machine.displayStatus();
        machine.dispenseItem();
        machine.displayStatus();
        
        // Scenario 2: Insufficient funds
        System.out.println("=== Scenario 2: Insufficient Funds ===\n");
        machine.selectItem("CHIPS");
        machine.insertCoin(0.50);
        machine.displayStatus();
        machine.dispenseItem(); // Should fail
        machine.insertCoin(0.25); // Add more coins
        machine.displayStatus();
        machine.dispenseItem(); // Should succeed
        machine.displayStatus();
        
        // Scenario 3: Try to dispense in Idle state
        System.out.println("=== Scenario 3: Invalid Operations ===\n");
        machine.dispenseItem(); // Should fail - no item selected
        machine.insertCoin(1.00); // Should fail - no item selected
        
        // Scenario 4: Machine out of order
        System.out.println("=== Scenario 4: Machine Out of Order ===\n");
        machine.setOutOfOrder();
        machine.displayStatus();
        machine.selectItem("CANDY"); // Should fail
        machine.insertCoin(1.00); // Should fail
        
        // Scenario 5: Multiple transactions
        System.out.println("=== Scenario 5: Fresh Machine Ready ===\n");
        VendingMachine machine2 = new VendingMachine();
        machine2.addItem("COFFEE", 8, 2.00);
        machine2.addItem("WATER", 25, 0.50);
        
        machine2.selectItem("WATER");
        machine2.insertCoin(0.50);
        machine2.displayStatus();
        machine2.dispenseItem();
        
        machine2.selectItem("COFFEE");
        machine2.insertCoin(1.00);
        machine2.insertCoin(1.00);
        machine2.displayStatus();
        machine2.dispenseItem();
    }
}