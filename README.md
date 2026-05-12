## This project is a technical assessment for a position at Dachser

### Dependencies

All you need is Java: 17 and maven: 3.9.3.

## How to run

$ mvn spring-boot:run

### After that, you can access [Swagger](http://localhost:8080/swagger-ui/index.html#/). It is all set up automatically with some initial data.

## Considerations

- This is a small project that packs a lot of structure to showcase some architecture principles, but have in mind that some shortcuts were taken, for example not having a proper MapStruct implementation.
- Based on the documentation provided, I was not able to be sure about how to represent the Shipment. Whether it has one or many incomes and if It is necessary to save incomes and expenses separately. So I took the simplistic approach to do it all in one request.
- Profit is not saved in DB, but calculated whenever necessary, to avoid possible mistakes of it not being updated.