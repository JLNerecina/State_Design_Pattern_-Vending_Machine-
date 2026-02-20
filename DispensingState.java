public class DispensingState implements VendingMachineState {
    
    @Override
    public void selectItem(VendingMachine machine, String itemId) {
        System.out.println("Cannot select items while dispensing. Please wait.");
    }
    
    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        System.out.println("Cannot insert coins while dispensing. Please wait.");
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        String selectedItem = machine.getSelectedItem();
        double itemPrice = machine.getItemPrice(selectedItem);
        
        System.out.println("Dispensing " + selectedItem + "...");
        
        // Simulate dispensing process
        try {
            Thread.sleep(2000); // Simulate dispensing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Update inventory
        machine.getInventory().put(selectedItem, machine.getInventory().get(selectedItem) - 1);
        
        // Calculate change
        double change = machine.getBalance() - itemPrice;
        
        // Dispense change
        if (change > 0) {
            System.out.println(selectedItem + " dispensed successfully! Change: $" + 
                String.format("%.2f", change));
        } else {
            System.out.println(selectedItem + " dispensed successfully!");
        }
        
        // Reset machine state
        machine.setBalance(0);
        machine.setSelectedItem(null);
        System.out.println("Machine returning to Idle state.");
        machine.setState(new IdleState());
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Cannot set to Out of Order during dispensing. Please wait.");
    }
    
    @Override
    public String getStateName() {
        return "Dispensing";
    }
}