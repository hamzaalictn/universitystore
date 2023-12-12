UNIVERSITY STORE  : Cucumber Selenium FrameWork
============================

Copyright/Licensing Information : READ LICENSE
---
Project source can be downloaded from : https://github.com/hamzaalictn/universitystore.git

Author
--------
Hamza Ali CETIN


Purpose 
--------

The aim of developing the University Store test automation framework is to enhance the quality of the University Store Web Application by enabling reliable and efficient testing processes. The framework intends to reduce the time and cost of testing while simultaneously increasing the accuracy and thoroughness of test coverage. Ultimately, the project aims to improve the overall software quality and user experience, leading to increased customer satisfaction. Additionally, the framework promotes the use of industry-standard tools and practices for test automation to help organizations stay competitive in the rapidly evolving software development industry.

Test Executions
-------- 

Prerequisites: Maven and Java installation

In order to start the tests by using Maven in your local computer, you need to follow these steps:

	1) Download the project from : https://github.com/hamzaalictn/universitystore.git
	2) Open the command prompt and cd until the project root directory
	3) Run the following command in the command prompt: mvn test



Overview
--------

The University Store test automation framework is designed to comprehensively test the e-commerce website, University Store, which sells various types of products. The framework incorporates industry-standard tools and practices for test automation and employs the page object model and page factory design to enhance maintainability and reusability. The framework uses Selenium WebDriver for browser automation and JUnit for unit testing. Moreover, the framework implements the behavior-driven development (BDD) approach, allowing expected software behaviors to be specified in a language understandable to customers. The framework generates detailed Cucumber HTML reports that provide insights into test results and enable easy tracking of defects. By using the University Store test automation framework, organizations can efficiently and effectively test the University Store e-commerce website, ensuring high-quality performance, user experience, and customer satisfaction.


Tools
-------

`Java` - My framework is written using Java language, 17 version.

`Maven` - My framework is a Maven project, which is a Java build application tool that I use to manage dependencies and run tests from the endpoint as Maven goals. This allows me to streamline the process of managing dependencies and running tests.

`Selenium WebDriver` - This is a tool and library used for browser automation that I have implemented in this project. It allows me to automate browser actions such as clicking buttons, filling out forms, and navigating pages to perform automated tests.

`Cucumber` - I used Cucumber to perform behavior-driven development (BDD) in my project. Gherkin allows me to specify the expected behaviors of the software in a logical language that anyone can understand. Cucumber runs automated acceptance tests that are written in a behavior-driven development (BDD) style, which streamlines the testing process and ensures that the software meets the expected requirements.

`JUnit` - It is a unit testing framework for Java that helps write and run automated unit tests, and has been instrumental in the development of test-driven development.

`Extent` - I've used Extent (an open-source reporting library used in Java-based automation frameworks) to generate detailed Cucumber HTML reports that are easy for non-technical team members to read and understand. These reports include detailed test steps and screenshots for any test failures that may occur, which helps to identify issues and troubleshoot them quickly. Additionally, with a small adjustment, the reports can also provide metrics on the percentage of passing, failing, skipped tests, etc.

`IDE` - I have used IntelliJ as my integrated development environment (IDE).




Design
-------

`Page Object model` - In my framework, we adopted the Page Object Model design for our automated tests. This model involves creating a separate class for each page in our web application, which is stored in the "pages" folder. By using the Page Object Model, I was able to separate my test code from the code that defines the elements and actions of each page. This allows us to create a more organized and scalable testing framework that can easily adapt to changes in our web application. In addition, the page object model helps in writing tests that are easier to maintain and reuse, which improves the overall quality of our test suite.

`PageFactory Design Pattern` - It is a design pattern in Selenium WebDriver that enables me to create object repositories for my web application's pages. Each page is represented by a Java class with elements and actions mapped using annotations. This design pattern makes it easier to access page objects and maintain my tests, and is highly optimized for use in Selenium WebDriver.

`Singleton Driver` - The Singleton Driver design pattern in Selenium uses a single instance of the WebDriver object that is shared between different classes in my test framework. This pattern helps improve test efficiency by avoiding the overhead of creating multiple WebDriver instances, and also makes it easier to manage the lifecycle of the WebDriver object. By using a Singleton Driver pattern, I can ensure that your tests are efficient, reliable, and easy to maintain.

`Webdriver Manager` - By using WebDriverManager in the framework, I was able to save time on browser driver management and improve the reliability of my tests by ensuring that the correct driver version was used.

`Hooks` - In my framework, I've implemented a Hook Class which contains blocks of code that can be executed at various points in the Cucumber execution cycle. These blocks of code are commonly used for setting up the environment before each scenario runs, and tearing it down after each scenario completes. Hooks provide a convenient and efficient way to automate repetitive setup and teardown tasks, such as initializing test data, starting and stopping a web server, or cleaning up after a test run.

`Centralized Configuration Data` - I have implemented a Configuration file that stores important test data, such as usernames, passwords, and other configurations. The Configuration file provides a centralized location for storing and managing this data, making it easy to update and maintain. Storing test data in a Configuration file reduces the amount of hard-coded data in my test code, which improves readability and maintainability.

`Utilities` - I've created a utils package in my framework, which contains reusable classes like BrowserUtils and DBUtils. These classes provide common functionality that can be used across different parts of the framework, such as interacting with the browser or working with databases. Using these Utilities helps reduce code duplication and improve the efficiency and maintainability of my tests.



Benefits
-------

1) Easy to maintain:

My framework uses the page object model, which simplifies maintenance. For instance, if I need to update any locator, I only need to make one code change. I also strive to make my tests independent from each other, so if I update one test, it will not impact others, and if one test fails, it will not affect the others.

2) Easy to extend:

I've implemented the page object model and utilities that can be reused across tests. For instance, I have the "waitForClickablility" method that waits until an element is clickable for a given amount of time before throwing an exception. Instead of repeating these lines of code in multiple tests, I store them as static methods in the BrowserUtils page, making them publicly accessible.

4) Easy to reuse:

I have page object model, utilities which I can reuse for any tests. For example, I have the "waitForClickablility" method which takes the WebElement and timeout length as parameter an waits until the element is clickable for the givent time before throwing exception. Instead of repeating this lines of codes in the test classes, I have stored them in BrowserUtils page as static methods and they are accessible to public.

4) Multi browser testing:

My framework can run the same tests on different browsers with minimal code changes.

5) Types of tests:

My framework can test the UI, database and API of the application.

6) Packaging:

I have created different packages for different types of classes and logic. Each page package only contains classes with similar functionality.

7) Naming conventions:

I pay close attention to coding standards, especially naming conventions. Classes, methods, and variables are named according to their functionality and follow a standard.

`Page Object Clases`: homePage, loginPage

`Variable`: loginButton, signOutLink

`methods`: login(): this methods only used to login, not for any other functionality.


NOTE : Test reports are intentionally loaded to remote repo for practice purposes.





#   U N I V E R S I T Y -  S T O R E  
 
