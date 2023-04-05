# InsuranceBackend



the project uses the database is called as insurance. It uses MySql database and the software used to interact was Xampp . It uses layered archiecture where there is a service layer , a database layer and an entity layer . The Service is interface and its implementation is what carries out the actions of the methods . In order to check if the same userName exists in the database or not a check has been implemented at the signup api to make sure unqiue userName exits in the database . There is also a unique constraint on the email and phone number to make sure a person cannot signup with the same email or phone number. We have also implemented Jwt bases authentication for signup and login we have also assigned roles to the user. The jwt authentication makes our api secured. we have also made methods in the InsurancePolicyService for finding policy using different methods like policy number . we also have implemented a method to generate unqiue policy number for policy 


the database configuration are here as follows :- 

spring.jpa.hibernate.ddl-auto=update

spring.datasource.name= insurance

spring.datasource.url=jdbc:mysql://localhost:3306/insurance

spring.datasource.username=root

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect =   org.hibernate.dialect.MySQL5Dialect

spring.jpa.show-sql = true

spring.jpa.properties.hibernate.format_sql=true

logging.level.org.springframework.web: DEBUG

#declaring the secretKey

jwt.secret = jwtangsecretkey

# declaring expiration time 
jwt.ExpirationMs = 86400000

# string to generate policy number 
characters = ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789

the entiire configuration is in application properties
