#!/usr/bin/env bash

echo ++++Start MySql Docker++++
docker run --name demo-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=parking -p 3306:3306 -d mysql:5.7

echo ++++Some clean up++++
mvn clean

echo ++++Run application++++
mvn spring-boot:run