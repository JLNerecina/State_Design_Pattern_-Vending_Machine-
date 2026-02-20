public class OutOfOrderState implements VendingMachineState {
    
    @Override
    public void selectItem(VendingMachine machine, String itemId) {
        System.out.println("Machine is Out of Order. No operations allowed.");
    }
    
    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        System.out.println("Machine is Out of Order. No operations allowed.");
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Machine is Out of Order. No operations allowed.");
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Machine is already Out of Order.");
    }
    
    @Override
    public String getStateName() {
        return "OutOfOrder";
    }
}