# Vending Machine State Pattern Implementation

A Java implementation of the **State Design Pattern** demonstrating a flexible vending machine system with multiple operational states. This project showcases how to eliminate complex conditional logic and create maintainable, extensible code through proper design patterns.

## 🎯 Problem & Solution

### The Problem
Traditional vending machine implementations rely on multiple conditional statements scattered throughout the code, making it difficult to:
- Add new states without modifying existing code
- Understand state-specific behavior
- Test individual states in isolation
- Maintain and extend functionality

### The Solution
The **State Pattern** encapsulates each state's behavior in its own class, allowing the vending machine to change its behavior dynamically based on its current state.

## 📋 Project Overview

This project implements a vending machine that manages four distinct operational states:

| State | Description | Allowed Operations |
|-------|-------------|-------------------|
| **Idle** | Waiting for user input | Select Item |
| **ItemSelected** | Item chosen, awaiting payment | Insert Coin, Dispense Item |
| **Dispensing** | Currently dispensing an item | None (Automatic) |
| **OutOfOrder** | Machine is broken | None (All blocked) |

## ✨ Key Features

- 🔄 **State Encapsulation**: Each state has its own class with specific behavior
- 🎯 **Automatic State Transitions**: States transition based on user actions
- 💾 **Inventory Management**: Track item quantities and pricing
- 💰 **Balance Tracking**: Manage user deposits and change calculation
- 🔧 **Extensible Design**: Easy to add new states without modifying existing code
- 🚀 **No Conditionals**: Clean code without if-else chains
- 🧪 **Comprehensive Examples**: Includes demo scenarios for all use cases

## 📁 Project Structure

```
vending-machine-state-pattern/
├── VendingMachineState.java        # State interface definition
├── IdleState.java                  # Idle state implementation
├── ItemSelectedState.java          # ItemSelected state implementation
├── DispensingState.java            # Dispensing state implementation
├── OutOfOrderState.java            # OutOfOrder state implementation
├── VendingMachine.java             # Context class
├── VendingMachineDemo.java         # Demo and usage examples
└── README.md                       # Documentation
```

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Any IDE (IntelliJ IDEA, Eclipse, VS Code) or command line

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/JLNerecina/vending-machine-state-pattern.git
cd vending-machine-state-pattern
```

2. **Compile the Java files:**
```bash
javac *.java
```

3. **Run the demo:**
```bash
java VendingMachineDemo
```

## 📖 Usage Examples

### Basic Purchase

```java
VendingMachine machine = new VendingMachine();

// Add items to the machine
machine.addItem("SODA", 10, 1.50);
machine.addItem("CHIPS", 15, 0.75);

// Customer selects an item
machine.selectItem("SODA");

// Customer inserts coins
machine.insertCoin(1.50);

// Customer dispenses the item
machine.dispenseItem();
```

**Expected Output:**
```
State changed to: ItemSelected
Item SODA selected. Price: $1.5. Current balance: $0.0
Coin of $1.5 inserted. Current balance: $1.5
Sufficient funds. Ready to dispense SODA
State changed to: Dispensing
Dispensing SODA...
SODA dispensed successfully!
Machine returning to Idle state.
State changed to: Idle
```

### Insufficient Funds Scenario

```java
machine.selectItem("CHIPS");
machine.insertCoin(0.50);      // Only $0.50, need $0.75
machine.dispenseItem();         // Fails - insufficient funds
machine.insertCoin(0.25);      // Add more coins
machine.dispenseItem();         // Now succeeds
```

### Out of Order Scenario

```java
machine.setOutOfOrder();
machine.selectItem("SODA");    // ❌ Blocked
machine.insertCoin(1.00);      // ❌ Blocked
machine.dispenseItem();        // ❌ Blocked
```

## 🔄 State Transitions

```
Idle State
├─ selectItem() ───────────→ ItemSelected State
└─ setOutOfOrder() ────────→ OutOfOrder State

ItemSelected State
├─ insertCoin() ───────────→ ItemSelected State (stay)
├─ dispenseItem() ─────────→ Dispensing State
└─ setOutOfOrder() ────────→ OutOfOrder State

Dispensing State
├─ dispenseItem() ─────────→ Idle State (automatic)
└─ [All other operations blocked]

OutOfOrder State
└─ [All operations permanently blocked]
```

## 📊 State Behavior Matrix

| Operation | Idle | ItemSelected | Dispensing | OutOfOrder |
|-----------|:----:|:------------:|:----------:|:----------:|
| selectItem | ✅ | ❌ | ❌ | ❌ |
| insertCoin | ❌ | ✅ | ❌ | ❌ |
| dispenseItem | ❌ | ✅* | ✅ | ❌ |
| setOutOfOrder | ✅ | ✅ | ❌ | ℹ️ |

*Only if sufficient funds are available

## 🎓 Design Pattern Details

### What is the State Pattern?

The **State Pattern** is a behavioral design pattern that allows an object to change its behavior when its internal state changes. The object appears to change its class.

### How It Works

1. **Context (VendingMachine)**: Maintains a reference to a concrete state object
2. **State Interface (VendingMachineState)**: Defines methods for state-specific behavior
3. **Concrete States**: Each state implements the interface with its own behavior
4. **Delegation**: The context delegates operations to the current state

### Why Use the State Pattern?

- 🎯 **Single Responsibility**: Each state class has one reason to change
- 🔓 **Open/Closed Principle**: Open for extension, closed for modification
- 🧩 **Composition**: Uses composition instead of inheritance
- 🛡️ **Encapsulation**: State logic is hidden from the client
- ♻️ **Reusability**: States can be reused across different contexts

## 📝 Class Responsibilities

### VendingMachine (Context)
- Maintains current state reference
- Stores inventory, balance, and selected item
- Delegates all operations to the current state
- Provides public interface for state transitions

### VendingMachineState (Interface)
- Defines contract for all state implementations
- Declares methods: `selectItem()`, `insertCoin()`, `dispenseItem()`, `setOutOfOrder()`

### Concrete States
- **IdleState**: Allows item selection, blocks other operations
- **ItemSelectedState**: Allows coin insertion and dispensing, blocks item selection
- **DispensingState**: Allows only dispensing operation, auto-transitions to Idle
- **OutOfOrderState**: Blocks all operations

## 🧪 Demo Scenarios

The `VendingMachineDemo` class demonstrates real-world usage:

1. **Successful Purchase**: Complete transaction from selection to dispensing
2. **Insufficient Funds**: Adding coins incrementally to reach required amount
3. **Invalid Operations**: Attempting operations in invalid states
4. **Machine Maintenance**: Out of order state blocking all operations
5. **Multiple Transactions**: Sequential purchases in one session

Run the demo:
```bash
java VendingMachineDemo
```

## 🔧 Extending the System

### Adding a New State

To add a new state (e.g., `MaintenanceState`):

1. **Create the new state class implementing VendingMachineState:**
```java
public class MaintenanceState implements VendingMachineState {
    @Override
    public void selectItem(VendingMachine machine, String itemId) {
        System.out.println("Machine under maintenance.");
    }
    
    @Override
    public void insertCoin(VendingMachine machine, double amount) {
        System.out.println("Machine under maintenance.");
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Machine under maintenance.");
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("Machine is already under maintenance.");
    }
    
    @Override
    public String getStateName() {
        return "Maintenance";
    }
}
```

2. **Use it in the vending machine:**
```java
machine.setState(new MaintenanceState());
```

### Adding New Operations

To add a new operation (e.g., `cancelTransaction()`):

1. Add method to `VendingMachineState` interface:
```java
void cancelTransaction(VendingMachine machine);
```

2. Implement in all concrete state classes
3. Add delegating method to `VendingMachine` class:
```java
public void cancelTransaction() {
    currentState.cancelTransaction(this);
}
```

## 📈 Code Comparison

### Without State Pattern (Conditional Logic)
```java
public void selectItem(String itemId) {
    if (state.equals("IDLE")) {
        // Complex selection logic
        System.out.println("Item selected");
    } else if (state.equals("ITEM_SELECTED")) {
        System.out.println("Already selected");
    } else if (state.equals("DISPENSING")) {
        System.out.println("Currently dispensing");
    } else if (state.equals("OUT_OF_ORDER")) {
        System.out.println("Machine broken");
    }
    // More states = more conditions...
}
```

### With State Pattern (Clean Delegation)
```java
public void selectItem(String itemId) {
    currentState.selectItem(this, itemId);
}
```

## 🎯 Learning Outcomes

By studying this project, you'll understand:

- ✅ How to implement the State Design Pattern in Java
- ✅ Encapsulation of state-specific behavior
- ✅ Using composition over inheritance
- ✅ SOLID principles in practice
- ✅ Clean code principles and best practices
- ✅ Elimination of complex conditional logic
- ✅ Building extensible and maintainable systems
- ✅ State machine implementation

## 🔗 Related Design Patterns

- **Strategy Pattern**: Similar structure; used for selecting algorithms at runtime
- **Template Method Pattern**: Defines skeleton of algorithm; subclasses fill in details
- **Visitor Pattern**: Separates algorithms from objects they operate on

## 📚 Resources & References

- [State Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/state)
- [Design Patterns: Elements of Reusable Object-Oriented Software](https://en.wikipedia.org/wiki/Design_Patterns)
- [Java Design Patterns - Digital Ocean](https://www.digitalocean.com/community/tutorials/java-design-patterns)
- [SOLID Principles in Java](https://www.baeldung.com/solid-principles)

## 🚀 Future Enhancements

Consider these improvements for advanced implementation:

- [ ] **Thread Safety**: Use synchronized methods for concurrent access
- [ ] **Singleton Pattern**: Make state objects singletons to save memory
- [ ] **Logging**: Integrate SLF4J or Log4J for better debugging
- [ ] **Exception Handling**: Create custom exceptions for error cases
- [ ] **Undo/Redo**: Implement transaction history
- [ ] **Persistence**: Save/load machine state to database
- [ ] **GUI**: Build user interface with JavaFX or Swing
- [ ] **Unit Tests**: Add comprehensive tests with JUnit and Mockito
- [ ] **State History**: Track state transitions for analytics
- [ ] **Notifications**: Implement observer pattern for events

## 📊 Performance Considerations

- **Memory**: Minimal overhead; state objects can be singletons
- **Time Complexity**: O(1) for state transitions and operations
- **Scalability**: Easily handles many states and transitions

## 🐛 Common Pitfalls to Avoid

1. ❌ **Circular Dependencies**: Ensure states don't directly depend on each other
2. ❌ **Complex State Logic**: Keep state classes focused and simple
3. ❌ **Mutable States**: Consider making state objects immutable
4. ❌ **Missing Transitions**: Document all possible state transitions
5. ❌ **Hardcoded States**: Use state classes, not string constants

## 👤 Author

**JLNerecina**

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

## 📞 Support & Questions

- 📌 **Issues**: Open an issue on GitHub for bugs or feature requests
- 💬 **Discussions**: Use GitHub Discussions for questions and ideas
- 📧 **Contact**: Reach out through your GitHub profile

## 🔍 UML Class Diagram

![UML Class Diagram](https://github.com/JLNerecina/State_Design_Pattern_-Vending_Machine-/blob/main/State%20Design%20Pattern%20UML%20Diagram.png)

---

**Last Updated**: February 2026  
**Status**: ✅ Complete and Production Ready  
**Java Version**: 8+  
