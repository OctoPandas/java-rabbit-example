# RabbitMQ

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
