# Posting system

The posting microservice system that uses Docker.
There are two services:
- user service (port 8070);
- post service (port 8071);

There some interactions between these services.
For example, post service updates amount of posts for user on the user service.
Each service uses own database.

![Architecture](https://github.com/semerkov/posting-system/blob/main/architecture.png?raw=true)

[Postman collection](https://api.postman.com/collections/259611-dde12487-76c8-4369-9bb8-1c6b166f69da?access_key=PMAT-01GYJ7BEBX1AVJ93QCB6BEKW2Y)

## User service
Build image with tag (1.0.0 in this case)

```shell
docker build -t epamvolodymyrsemerkov/user-service:1.0.0 ./
```

Publish image to Docker repository

```shell
docker push epamvolodymyrsemerkov/user-service:1.0.0
```

## Post service

Build image with tag (1.0.0 in this case)

```shell
docker build -t epamvolodymyrsemerkov/post-service:1.0.0 ./
```

Publish image to Docker repository

```shell
docker push epamvolodymyrsemerkov/post-service:1.0.0
```

## Run

For deploying the next command are used

```shell
docker-compose up
```