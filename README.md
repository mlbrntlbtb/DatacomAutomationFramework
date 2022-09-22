# DatacomAutomationFramework
Automation Framework for Datacom -- Information Technology Company
Developer: Melbourne Talibutab

Technologies used: Selenium, RestAssured, TestNG

Key-Features:
1. Web Testing and REST API Testing capabilities  
2. Keyword-driven framework. Pre-defined keywords per element type (src/main/java/BaseElements or src/main/java/BaseHTTP)
3. Hybrid Page Object Model using XML files as Object Page Repository (products/<target_product>/page-objects)
4. Data-driven test capabilities. Store test data under (products/<target_product>/user-test-data)
5. Test Results & Reporting (TestNG, ExtentReports) (products/<target_product>/test-results)

Steps to use:
1. Pull on local repository
2. See configuration of target test suites under 'pom.xml' -- 'maven-surefire-plugin' plugin
3. Include file path of target test suites
4. Include required test environment on 'config.properties' (config/config.properties)
5. Use docker-compose.yaml for SeleniumGrid Hub-Node setup on Docker. This includes setup of latest nodes for Chrome and FF. 
6. Run docker-compose up on host machine with Docker installed.
7. Execute mvn clean install on CLI or as build step on CI/CD server

Example Test Suites (src/test/java):
1. Web Automation Test Suite 
    - TS_PayeesPayment_Workflow.xml --- Executes workflow on BNZ test environment which includes verification of elements and succesful payment transfer.
2. API Automation Test Suites
    - TS_API_VerifyUsers --- Executes HTTP requests for Users test API environment which includes get, post and response verifications.
    - TS_CRUD_API_Operations --- Executes HTTP requets for 'go-rest-co.in' test API environment which includes typical CRUD (Create, Read, Update, Delete) operations
    

