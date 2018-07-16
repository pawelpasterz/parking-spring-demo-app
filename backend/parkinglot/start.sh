#!/usr/bin/env bash

echo Some clean up
mvn clean

echo Create Database
mysql -h localhost -u root -e "create database parking;"

echo Run application
mvn spring-boot:run