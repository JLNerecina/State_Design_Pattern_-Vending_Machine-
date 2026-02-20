public interface VendingMachineState {
    
    void selectItem(VendingMachine machine, String itemId);
    
    void insertCoin(VendingMachine machine, double amount);
    
    void dispenseItem(VendingMachine machine);
    
    void setOutOfOrder(VendingMachine machine);
    
    String getStateName();
}