# Java gRPC - Rest DEMO

[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

project to make a PoC about how use gRpc and REST and how implement a demo server

This repository contains:
- simple api that shows a CRUD with GRPC

## Install

This project uses gradle (remenber get conection with mvncentral for donwload dependencies) and java Go check them out if you don't have them locally installed.

```sh
$ gradle build
```

## Usage

run the ExampleApiApplication.java file (spring boot aplication)


open h2 database at http://localhost:8080/h2-console/ with JDBC URL jdbc:h2:mem:testdb (it could cathed from the console log)



### references 
- https://github.com/Qleoz12/grpcCourse
- https://platzi.com/cursos/go-protobuffers-grpc/
- https://armeria.dev/docs/client-grpc/
- https://grpc.io/docs/what-is-grpc/core-concepts/


