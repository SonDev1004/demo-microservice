1. Đảm bảo đã cài đặt docker
```bash
    docker --version
```

2. Chạy Redis container
```bash

    docker run --name redis-dev -p 6379:6379 -d redis
```
| Parameter         | Description                                              |
|-------------------|----------------------------------------------------------|
| `docker run`      | Chạy một container bằng Docker                           |
| `--name redis-dev`| Đặt tên cho container `redis-dev`                        |
| `-p 6379:6379`    | Bản đồ cổng 6379 của máy chủ đến cổng 6379 của container |
| `-d`              | Chạy container ở chế độ detached                         |
| `redis`           | Sử dụng image Redis chính thức                           |

3. Kiểm tra trạng thái của Redis container
```bash
    docker ps
```

| CONTAINER ID | IMAGE  | COMMAND      | CREATED         | STATUS         | PORTS           | NAMES      |
|--------------|--------|--------------|-----------------|----------------|-----------------|------------|
| abcd1234efgh | redis  | "docker-entrypoint..." | 2 seconds ago | Up 2 seconds | 0.0.0.0:6379->6379/tcp | redis-dev  |

4. Kiểm tra kết nối đến Redis
```bash
    docker exec -it redis-dev redis-cli
```
Sau đó gõ:
```bash
    ping
```
Nếu kết nối thành công, bạn sẽ nhận được phản hồi:
```bash
    PONG
```
Stop và xóa container nếu cần
- Dừng container
```bash
    docker stop redis-dev
```
- Xóa container
```bash
    docker rm redis-dev
```

## Test data trong redis cache
### Redis đang cache cái gì?

Redis lưu theo key: allUser
Bạn có thể dùng Redis CLI để xem:
```bash
    docker exec -it redis-dev redis-cli
    keys *
```
Xóa cache cũ nếu muốn
```bash
   FLUSHALL
```
```bash
GET "allUsers::SimpleKey []"
```