### run in local
api docs: [auth](/docs/auth.md) dan [home](/docs/home.md)

required: openjdk21, maven

clone repo `git clone git@github.com:reimiii/code-be.git`

masuk ke root project dir `cd code-be`

ubah file properties di dalam folder resources 
```bash
mv src/main/resources/application.properties.example src/main/resources/application.properties

```
lalu sesuaikan isinya dengan database mysqldi local...seperti username, password, dan nama db, buat database dan table users di [database.sql](database.sql)
```properties
spring.application.name=code-be
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.url=jdbc:mysql://localhost:3306/be
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.maximum-pool-size=50
server.port=8181
spring.jpa.properties.hibernate.show_sql=true
jwt.secret.key=
```

jalankan `mvn install validate test` kalo sudah generate `jwt.secret.key` buat di .properties pake `mvn test -Dtest=JwtServiceTest#generateSecretKey | grep "key = "` lalu copy yang di dalam [] ke `jwt.secret.key` di `/src/main/resources/application.properties`
```properties
#contoh
jwt.secret.key=A365B300.......
```

jika sudah tinggal jalankan menggunakan `mvn spring-boot:run` jika sudah jalan backend nya tinggan clone dan jalanin frontendnya... di [login-app-react](https://github.com/reimiii/login-app-react)