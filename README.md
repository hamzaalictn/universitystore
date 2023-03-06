ZEBER  : Cucumber Selenium FrameWork
============================

Copyright/Licensing Information : READ LICENSE
---
Project source can be downloaded from : https://github.com/hamzaalictn/zeber.git

Author
--------
Hamza Ali CETIN


Aim
--------

The aim of developing the ZEBER test automation framework is to enhance the quality of the Zeber Web Application by enabling reliable and efficient testing processes. The framework intends to reduce the time and cost of testing while simultaneously increasing the accuracy and thoroughness of test coverage. Ultimately, the project aims to improve the overall software quality and user experience, leading to increased customer satisfaction. Additionally, the framework promotes the use of industry-standard tools and practices for test automation to help organizations stay competitive in the rapidly evolving software development industry.

Test Executions
-------- 

Prerequisites: Maven and Java installation

In order to start the tests by using Maven in your local computer, you need to follow these steps:

	1) Download the project from : https://github.com/hamzaalictn/zeber.git
	2) Open the command prompt and cd until the project root directory
	3) Run the following command in the command prompt: mvn test




Overview
--------

The ZEBER test automation framework is designed to comprehensively test the e-commerce website, ZEBER, which sells various types of products. The framework incorporates industry-standard tools and practices for test automation and employs the page object model and page factory design to enhance maintainability and reusability. The framework uses Selenium WebDriver for browser automation and JUnit for unit testing. Moreover, the framework implements the behavior-driven development (BDD) approach, allowing expected software behaviors to be specified in a language understandable to customers. The framework generates detailed Cucumber HTML reports that provide insights into test results and enable easy tracking of defects. By using the ZEBER test automation framework, organizations can efficiently and effectively test the ZEBER e-commerce website, ensuring high-quality performance, user experience, and customer satisfaction.


Tools
-------

Java - My framework is written using Java language, 8 version.

Maven - My framework is created as a maven project, maven is a Java building application tool, in this project I have used maven to manage dependencies and also run our tests as mvn goals from terminal

Selenium WebDriver - Is the browser automation library/tool/api  which I have used in this project.

JUnit  - Is a unit testing framework for the Java programming language. JUnit has been important in the development of test-driven development

Cucumber - I used cucumber to achieve behavior-driven development (BDD). With Gherkin, it allows expected software behaviours to be specified in a logical language that customers can understand. And It runs automated acceptance tests written in a behavior-driven development (BDD) style.


Extent - My framework generates detailed Cucumber-html reports which makes it is easy to read and understand to non-technical team members. My reports have details test steps and screenshots for any failures that may occur. If requested with a small adjustment, it can also make metrics on the percentage of passing, failing, skipped, etc.

IDE - I used IntelliJ in my current framework.




Design
-------

Page Object model - My framework used page object model according to which I created a separate classes for the pages of my application. All the pages are in pages folder.

PageFactory design - a design which makes it easy to access the page object class. I created a Pages class that enables access to each pages by calling the related methods. Page Factory in Selenium is a built-in Page Object Model framework concept for Selenium WebDriver but it is highly optimised.

Singleton Driver - My frameworks use a singleton pattern to share the Web Driver instance between different classes.

Hooks - My framework has a Hook Class which are blocks of code that can run at various points in the Cucumber execution cycle. They are typically used for setup and teardown of the environment before and after each scenario.

Configuration file - I used to store the important test data. Such as username, password, etc.

Utilities - have reusable utilities that are being used from different classes of my framework.




Benefits
-------

1) Easy to maintain:

My framework uses page object model which makes it easy to maintain. For example if i have to update any locator, I only need to do one code change.
I try to make my tests independent from each other, this means if I update one test, it will not affect others and also if one fails, others will not be affected.



2) Easy to extend:

It is easy to add new test cases to my framework, and new pages. The design is smooth and clear.

3) Easy to reuse:

I have page object model, utilities which I can reuse for any tests. For example, I have the "waitForClickablility" method which takes the WebElement and timeout length as parameter an waits until the element is clickable for the givent time before throwing exception. Instead of repeating this lines of codes in the test classes, I have stored them in BrowserUtils page as static methods and they are accessible to public.

4) Multi browser testing:

My framework can run the same tests against different browsers with minimal code change.

5) Types of tests:

My framework can test the UI, database and API of the application.

6) Packaging:

I have created different packages for different types of classes and logic. Each page package only contains classes with same functionality.

7) Naming conventions:

I do pay a lot of attention to coding standards, especially naming conventions. Classes, methods variable are named on based on what they do and follow a standard

Page object class:
homePage, loginPage
variable: loginButton, signOutLink
methods: login(): this methods only used to login, not for any other functionality.



NOTE : Test reports are intentionally loaded to remote repo for practice purposes.



#   Z E B E R 
 