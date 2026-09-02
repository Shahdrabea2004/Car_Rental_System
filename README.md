# Car Rental System

A Java-based console application designed to simulate the core operations of a car rental agency. The system provides functionality for fleet management, customer registration, rental processing, vehicle maintenance, car searching, and rental history tracking.

The project demonstrates core **Object-Oriented Programming (OOP)** principles, including **Encapsulation, Inheritance, Abstraction, Polymorphism, and Exception Handling**.

---

## 📋 Overview

The system models a car rental agency where employees can manage the fleet, customers, rentals, and vehicle maintenance, while customers can search for available cars, rent vehicles, return them, and view their rental history.

Each car type has its own pricing behavior, while car and rental statuses are managed through controlled state transitions with validation.

---

## ✨ Features

### 👨‍💼 Employee Features

* Add and manage cars
* Manage different types of vehicles
* Search and filter cars using different criteria
* Manage fleet availability
* Register and manage customers
* Create and manage rental records
* Process vehicle returns
* Calculate rental prices and late fees
* Manage vehicle maintenance
* Track car and rental statuses
* View rental history

### 👤 Customer Features

* Register as a customer
* Search for available cars
* View car information
* Rent an available vehicle
* Return a rented vehicle
* View rental history
* View rental details and final price

---

## 🚗 Car Types

The system supports four different car types, each with its own pricing behavior:

* **EconomyCar** – Includes fuel efficiency and an optional discount.
* **LuxuryCar** – Supports chauffeur service, premium features, and additional surcharges.
* **SUV** – Includes the number of seats, four-wheel drive capability, and additional charges.
* **Truck** – Includes cargo capacity and special license requirements.

Each subclass overrides the pricing behavior of the abstract `Car` class, demonstrating **Polymorphism**.

---

## 🧩 OOP Concepts

The project demonstrates the main Object-Oriented Programming principles:

### Encapsulation

Object data is protected using private fields with controlled access through methods.

### Inheritance

Specific car types such as `EconomyCar`, `LuxuryCar`, `SUV`, and `Truck` inherit common properties and behavior from the `Car` class.

### Abstraction

The abstract `Car` class defines common behavior and requires subclasses to implement their own pricing logic.

### Polymorphism

Different car types implement `calculatePrice()` according to their specific pricing rules.

### Exception Handling

Custom exceptions are used to handle invalid operations and state transitions.

---

## 🗂️ Project Structure

```text
CarRentalSystem/
├── src/
│   ├── Main.java
│   │
│   ├── exception/
│   │   └── CarStatusException.java
│   │
│   ├── model/
│   │   ├── Car.java
│   │   ├── CarStatus.java
│   │   ├── EconomyCar.java
│   │   ├── LuxuryCar.java
│   │   ├── SUV.java
│   │   ├── Truck.java
│   │   ├── Customer.java
│   │   ├── Employee.java
│   │   └── Rental.java
│   │
│   └── service/
│       ├── CarService.java
│       ├── CustomerService.java
│       ├── EmployeeService.java
│       ├── RentalService.java
│       └── CarSearchService.java
│
├── .gitignore
└── README.md
```

---

## 🧱 Main Components

### `Car`

The abstract parent class for all vehicle types.

It contains common information such as:

* `id`
* `brand`
* `model`
* `manufacturingYear`
* `baseDailyRentalPrice`
* `CarStatus`

It also defines the common operations for managing the vehicle lifecycle:

* `rent()`
* `returnCar()`
* `sendToMaintenance()`
* `returnFromMaintenance()`

The `calculatePrice()` method is implemented differently by each car subclass.

### `CarStatus`

An enum representing the current state of a vehicle:

* `AVAILABLE`
* `RENTED`
* `UNDER_MAINTENANCE`

The system validates every status transition to prevent invalid operations.

### `Customer`

Represents a customer registered in the rental system and stores customer-related information and rental history.

### `Employee`

Represents an employee responsible for managing fleet operations, customers, rentals, and maintenance.

### `Rental`

Represents a rental transaction between a customer and a vehicle, including rental dates, status, and final price.

### `CarSearchService`

Provides functionality for searching and filtering cars based on different criteria.

### `RentalService`

Handles rental-related business logic, including:

* Creating rentals
* Processing car returns
* Calculating rental prices
* Applying late fees
* Updating rental status

### `CarStatusException`

A custom exception used when an invalid car status operation is attempted, such as renting a car that is already rented or under maintenance.

---

## 💰 Rental Price Calculation

Each car type uses its own pricing strategy.

| Car Type     | Pricing Logic                                    |
| ------------ | ------------------------------------------------ |
| `EconomyCar` | `(Base Price × Days) − Discount`                 |
| `LuxuryCar`  | `(Base Price × Days) + Surcharge`                |
| `SUV`        | `(Base Price × Days) + Surcharge + Seat Charges` |
| `Truck`      | `(Base Price × Days) + (50 × Cargo Capacity)`    |

The system also supports **late-return fees** when a vehicle is returned after the agreed rental period.

All rental calculations validate that the rental duration is at least one day.

---

## 🔄 Car Status Management

A vehicle can have one of three statuses:

```text
AVAILABLE
    │
    ▼
 RENTED
    │
    ▼
AVAILABLE
```

A vehicle can also be sent to maintenance:

```text
AVAILABLE
    │
    ▼
UNDER_MAINTENANCE
    │
    ▼
AVAILABLE
```

Invalid transitions are rejected using `CarStatusException`.

For example:

* A rented car cannot be rented again.
* A car under maintenance cannot be rented.
* A rented car cannot be sent directly to maintenance.

---

## 🔎 Car Search

Employees and customers can search for cars based on available search criteria.

The search functionality is implemented separately from the model classes to keep the business logic organized and maintainable.

---

## ⚙️ Technologies

* **Java**
* **Object-Oriented Programming (OOP)**
* **Java Collections Framework**
* **Exception Handling**
* **Git & GitHub**
* **IntelliJ IDEA**

---

## 📋 Requirements

* **JDK 8 or higher**
* IntelliJ IDEA or any Java-compatible IDE

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Shahdrabea2004/Car_Rental_System.git
```

### 2. Open the project

Open the project using IntelliJ IDEA or another Java IDE.

### 3. Configure the source folder

Make sure the `src` directory is configured as the **Sources Root**.

### 4. Run the application

Run:

```text
Main.java
```

The application will display the main menu in the console.

---

## 🧪 Simple Test

### Customer Registration

**Input:**

```text
Choose an option:
1. Employee
2. Customer
3. Exit

Enter your choice: 2
```

After entering valid customer information:

**Expected Output:**

```text
Customer registered successfully.
```

### Car Search

**Input:**

```text
Search for available cars
```

**Expected Output:**

```text
Available cars matching the criteria are displayed.
```

### Create Rental

After selecting an available car and providing valid rental information:

**Expected Output:**

```text
Rental created successfully.
Final price: <calculated price>
```

The selected car status should change from:

```text
AVAILABLE → RENTED
```

### Return Car

After returning the vehicle:

**Expected Output:**

```text
Car returned successfully.
Rental completed.
```

The car status should change back to:

```text
RENTED → AVAILABLE
```

### Invalid Operation

Attempting to rent a car that is already rented should result in an error:

```text
CarStatusException: Car is not available for rental.
```

---

## 📌 Project Status

The core functionality of the Car Rental System has been implemented, including:

* Car hierarchy and pricing
* Customer registration and management
* Employee operations
* Fleet management
* Car search
* Rental processing
* Car returns
* Rental price calculation
* Late-return handling
* Vehicle maintenance
* Rental history
* Car and rental status management
* Console-based application flow

---

