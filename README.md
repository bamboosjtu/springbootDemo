# springbootDemo
Springboot Learning



## 持久层

### SQL ORM框架

SpringBoot框架针对关系型数据库的持久层解决方案有多种选择，国内似乎比较倾向于用Mybatis，国外则是JPA。

##### 1. JDBC API

直接操作JdbcTemplate或者JdbcOperations，依赖`spring-boot-starter-jdbc`提供了开箱即用的Bean。

##### 2. MyBatis

JDBC API需要手动编SQL语句，并通过PreparedStatement或Statement对象执行SQL语句，还要手动编写RowMapper来处理ResultSet对象，将结果集转换为Java对象。而MyBatis则是通过XML或注解的方式来配置SQL语句，自动将SQL语句转换为PreparedStatement对象和执行，结果集的映射关系也是通过XML或注解的方式来配置，并自动转换为Java对象。

##### 3. Spring Data JDBC

提供的`CrudRepository`接口内置了各种常见的SQL操作，如findAll、findById、save，省略了自己实现Repo的代码，但是增加了对实体类的修饰代码，如`@Table`、`@Column`、`@Id`。灵活性不如JDBC API，建表时候需要遵守一定的命名规则。

##### 4. Spring Data JPA

与Srping Data JDBC同属于Spring Data项目，同样提供`CrudRepository`接口，但对实体类的修饰，使用`@Entity`、`@Id`（与JDBC不同），`@ManyToMany`，会自动创建中间表。但数据库访问默认基于Hibernate，数据库操作基于对象而非SQL。

### 配置数据库

SQL数据库有很多种，需要使用那种数据库，就在`pom.xml`中添加那种数据库的依赖，并修改`applicalition.yml`文件。

- H2

```pom.xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

```application.yml
spring:
  datasource:
    name: db_name
    generate-unique-name: false
    
spring:
  jpa:
    show-sql: true
```

- MySQL

```pom.xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

```application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db_name
    username: username
    password: password
    driver-class-name: com.mysql.jdbc.Driver
    
spring:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5Dialect
```

