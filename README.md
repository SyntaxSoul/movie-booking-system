# Movie Booking System

A console-based Movie Booking System built using Core Java, JDBC, MySQL, Maven, and Liquibase.

## Tech Stack

- Java 17
- Maven
- MySQL
- JDBC
- Liquibase

## Create Database

CREATE DATABASE movie_booking;

## Run Liquibase

mvn liquibase:update

## Rollback

mvn liquibase:rollback -Dliquibase.rollbackCount=11

## Build

mvn clean package

## Run

...

## Documentation

- Functional Requirements
- Entity Discovery
- Business Rules
- Database Schema
- ERD