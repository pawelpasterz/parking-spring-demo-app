#!/usr/bin/env bash

echo ++++Shutting down spring app++++
curl -X POST localhost:8080/actuator/shutdown
echo

echo ++++Shutting down MySql database++++
docker stop demo-mysql
docker rm demo-mysql

echo BYE!
