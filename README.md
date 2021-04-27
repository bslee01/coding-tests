To run the project, type

```
mvn clean package
```

Some additional thoughts and ideas
----------------------------------

- In real commercial project, there would likely be a ShoppingCart class in com.dius.commerce.shopping package. HashMap can be used for supporting the implementation behind the scene instead of using HashMap directly as shopping cart.
- In real commercial project, we can consider using project Lombok to reduce boilerplate code. However, do consider its disadvantages too.
- If managing hardcoding test data in Unit Test is not ideal, we could use JUnit5 @CsvFileSource to load test data from CSV file.
   
Some thoughts from architecture perspective
-------------------------------------------
If this shopping system expands into real commercial project, I would propose using clean/hexagonal architecture, DDD to,
- Draw the business boundary/context among catalogue, pricing, shopping as different logical modules
- For each of the modules, we could structure them into (in hexagonal architecture terms)
  - Input Adapter (implementation) and Port (interface) to handle incoming request from Rest Controller, batch job or even messaging system
  - Output Adapter (implementation) and Port (interface) to persist data into DB, send data to downstream webservices or messaging system 
  - Service to implement user stories or use cases
  - Domain Object to capture most of the complex business domain logic if possible (But it really depends on the nature of the application)
- For pure CRUD kind of application, DDD might not be that relevant.
