public class IdleState implements VendingMachineState {
    
    @Override
    public void selectItem(VendingMachine machine, String itemId) {
        if (machine.getInventory().containsKey(itemId)) {
            int quantity = machine.getInventory().get(itemId);
            if (quantity > 0) {
                machine.setSelectedItem(itemId);
                System.out.println("Item " + itemId + " selected. Price: $" + 
                    machine.getItemPrice(itemId) + ". Current balance: $" + machine.getBalance());
                machine.setState(new ItemSelectedState());
            } else {
                System.out.println("Item " + itemId + " is out of stock.");
            }
        } else {
            System.out.println("Item " + itemId + " does not exist.");
        }
    }
    
    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        System.out.println("Cannot insert coins in Idle state. Please select an item first.");
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Cannot dispense in Idle state. Please select an item first.");
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Setting machine to Out of Order state.");
        machine.setState(new OutOfOrderState());
    }
    
    @Override
    public String getStateName() {
        return "Idle";
    }
}