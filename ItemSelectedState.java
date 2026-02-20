public class ItemSelectedState implements VendingMachineState {
    
    @Override
    public void selectItem(VendingMachine machine, String itemId) {
        System.out.println("Cannot select another item. An item is already selected. Please proceed with payment or cancel.");
    }
    
    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid coin amount. Please insert a valid amount.");
            return;
        }
        
        machine.addBalance(amount);
        System.out.println("Coin of $" + amount + " inserted. Current balance: $" + machine.getBalance());
        
        String selectedItem = machine.getSelectedItem();
        double itemPrice = machine.getItemPrice(selectedItem);
        
        if (machine.getBalance() >= itemPrice) {
            System.out.println("Sufficient funds. Ready to dispense " + selectedItem);
        } else {
            System.out.println("Need $" + (itemPrice - machine.getBalance()) + 
                " more to dispense " + selectedItem);
        }
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        String selectedItem = machine.getSelectedItem();
        double itemPrice = machine.getItemPrice(selectedItem);
        
        if (machine.getBalance() >= itemPrice) {
            machine.setState(new DispensingState());
            machine.getState().dispenseItem(machine);
        } else {
            System.out.println("Insufficient funds. Need $" + (itemPrice - machine.getBalance()) + 
                " more to dispense " + selectedItem);
        }
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Setting machine to Out of Order state.");
        machine.setState(new OutOfOrderState());
    }
    
    @Override
    public String getStateName() {
        return "ItemSelected";
    }
}