# Restaurant table reservation

I made this project with the objective of practising testing while also reinforcing my previous knowledge in Spring.

This project is a rest API where you can see all the availables tables in a restaurant. You can register or login (be aware passwords are not hashed as it wasn't my priority at the moment). Then when you login, an id is sent to you in the response.
With that id you can make a reservation per user at the moment. Also, if you are an admin user, you can add more tables to the restaurant with the id.

I followed the MVC architecture and I used dependency injection via the `@Autowired` annotation.

#### Endpoints:

- /user/register:
```json
"body": {
  "username": "String",
  "name": "String",
  "surname": "String",
  "password": "String"
}
```
- /user/login:
```json
"body": {
  "username": "String",
  "password": "String"
}
```
- /table
- /table/create:
```json
"body": {
  "description": "String",
}
```
- /reservation/create:
```json
"body": {
  "table": "Table object",
  "date": "Date(format='dd/MM/yy HH:mm'",
  "user": "User object"
}
```

#### Technologies used:
- Java 17
- Spring 3.2.1
- Maven
- JUnit and Mockito
- Github Actions
- Hibernate
- MySQL

#### Running the project:

You need to have installed Java 17 as well as maven.
Also you have to set up your own database credentials in the application.properties file.
If you want to run the tests you'll have to set them in the corresponding application.properties for testing.
Then you run the following commands:
- `mvn clean install`
- `java -jar ./target/reservation-0.0.1-SNAPSHOT.jar`

##### To be added:

- Make table model have more information
- Add graphic interface
- Add correct user and session management
- Password encrypting
- Make reservations unique
