# RabbitMQ

## Start the project

Create a container for RabbitMQ:

```shell
docker pull rabbitmq:management
docker run -d --hostname rabbit --name rabbit rabbitmq:management
```

Get IP address of the container:

```shell
docker exec rabbit hostname -I
```

Attach to the container:

```shell
docker exec -it rabbit /bin/bash
```

## Some key points

Provided that a consumer with slow consume ratio and enabled auto ACK mechanism, the server will constantly push new messages to the client and maybe flood the client. After the client disabled this mechanism, the server will provide the consumer messages at a reasonable speed.
