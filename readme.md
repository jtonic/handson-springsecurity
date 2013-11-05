### This project is only for learning purposes - spring security and spring mvc

Because the user info is DB based and the DB is mysql the following is required:

- install mysql 5.5+

- create a db

- create schema required by spring security

`
    create table users(
      `username` VARCHAR (200) not null primary key,
      `password` VARCHAR (200) not null,
      `enabled` boolean not null
    );
    
    create table authorities (
      `username` VARCHAR (200) not null,
      `authority` VARCHAR (200) not null,
      constraint `fk_authorities_users` foreign key(`username`) references users(`username`)
    );
    
    create unique index `ix_auth_username` on `authorities` (username,authority);
    
    insert into 
      users (username, password, enabled) 
      values ('user', '4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde', true);
    
    insert into 
      users (username, password, enabled) 
      values ('admin', '4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde', true);
    
    
    insert into authorities (username, authority) values ('admin', 'admin, user');
    insert into authorities (username, authority) values ('user', 'admin')
`
- install tomcat 7

- change the  datasource.properties accordingly

- change the tomcat.username and tomcat-password properties accordingly in pom.xml

- $ clean install tomcat:deploy

- point browser to http://localhost:8080/handson-springsecurity

  