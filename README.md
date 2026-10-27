# Swag Labs Automation Testing Project

## 📌 Project Overview
This project is a Selenium WebDriver automation framework developed to test the core functionalities of the [Swag Labs](https://www.saucedemo.com/) web application.

The project was completed as part of a hands-on automation testing assignment and covers login functionality, inventory verification, shopping cart operations, price calculations, checkout behavior, and cart state validation.

The framework follows the **Page Object Model (POM)** design pattern and uses **JSON-based Data-Driven Testing** to improve code reusability, maintainability, and test data management.

## 🛠️ Technologies & Tools
- **Java** – Programming language
- **Selenium WebDriver** – Browser automation
- **TestNG** – Test execution, annotations, and assertions
- **Maven** – Dependency and project management
- **JSON** – Test data storage
- **Page Object Model (POM)** – Test framework design pattern
- **IntelliJ IDEA** – Development environment
- **Git & GitHub** – Version control and project hosting

## 🧪 Test Coverage

### Part 1: Login & Inventory Testing
- Verify successful login using valid credentials.
- Verify login failure with invalid credentials.
- Verify login validation when the password field is empty.
- Verify redirection to the Inventory page after successful login.
- Verify the page title, shopping cart icon, and number of displayed products.

### Part 2: Cart & Checkout Testing
- Verify social media links for LinkedIn, Facebook, and X.
- Verify that the shopping cart is initially empty.
- Add three specific products using test data stored in JSON.
- Verify that the selected products appear in the cart in the expected order.
- Remove a product and verify the updated button states for all selected products.
- Calculate the total price of selected products and compare it with the Item Total displayed at checkout.
- Verify the application's behavior when attempting to check out with an empty cart.
- Verify whether cart items persist after logging out and logging in again.

## 🏗️ Framework Design

The project uses the Page Object Model (POM) to separate page elements and actions from test logic.

- **BaseTest:** Handles browser setup and teardown.
- **LoginPage:** Contains login page locators and actions.
- **InventoryPage:** Contains inventory page locators and actions.
- **CartPage:** Contains shopping cart locators and actions.
- **DataDriven:** Reads test data from a JSON file.
- **LoginTest:** Contains login-related test cases.
- **InventoryTest:** Contains inventory verification tests.
- **CartTest:** Contains shopping cart and checkout test cases.
- **testData.json:** Stores login credentials and product test data.

*Note: The component names above describe the framework's intended responsibilities; refer to the actual project files for the exact implementation and package structure.*

## 📂 Test Data Management

Test data is stored in a JSON file and loaded through the `jsonReader()` method in the `DataDriven` class.

The JSON file contains the credentials used for positive and negative login scenarios, along with the product names required for data-driven cart testing.

This approach helps separate test data from test logic and makes it easier to update test scenarios without hardcoding values in the test methods.

## ▶️ How to Run the Project

### Prerequisites
- Java JDK installed.
- IntelliJ IDEA or another Java IDE.
- Maven installed or configured through the IDE.
- A supported web browser and a compatible Selenium WebDriver setup.

### Steps
1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Allow Maven to resolve the project dependencies.
4. Review the browser configuration and JSON test data file.
5. Execute the TestNG test classes or the configured TestNG suite.

If the project is configured to run tests through Maven, execute:

```bash
mvn clean test
```

The command above assumes that the Maven test configuration is set up to discover and execute the project's TestNG tests.

## 🎯 Key Learning Outcomes
- Applying the Page Object Model to organize an automation framework.
- Writing automated functional tests using Selenium WebDriver and TestNG.
- Implementing JSON-based data-driven testing.
- Validating web elements, navigation, and error messages.
- Handling shopping cart operations and product state changes.
- Performing price calculations and validating checkout totals.
- Testing edge cases and verifying actual application behavior.
- Writing modular and maintainable automation code.

## 🔗 Application Under Test
[Swag Labs – Sauce Demo](https://www.saucedemo.com/)

## 👨‍💻 Author

**Nader Elbana**  
Aspiring QA Automation Engineer

- GitHub: [https://github.com/Naderelbana]
- LinkedIn: [https://www.linkedin.com/in/nader-elbana-b19119255/]

Developed as a practical assignment to strengthen web automation testing skills using Java, Selenium WebDriver, TestNG, POM, and JSON.
