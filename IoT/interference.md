#### 1.连接

port : 7078

http请求

http://localhost:7078

#### 2.接口

##### 1.获取数据库全部数据

type : GET

router : "/data/getAll"

数据格式 : json

成功案例 :

```json
{
    "code": 200,
    "message": "操作成功",
    "success": "success",
    "data": [
        {
            "time": "2014-02-13T00:00:00",
            "temperature": 4.0,
            "pressure": 995,
            "humidity": 66
        },
        {
            "time": "2014-02-13T00:20:00",
            "temperature": 4.0,
            "pressure": 994,
            "humidity": 75
        }
       ]
}
```

##### 2.获取某一天的数据

type : GET

router : "/data/getByDate?data=YY-MM-DD"

数据格式 : String

成功案例 :

```json
{
    "code": 200,
    "message": "操作成功",
    "success": "success",
    "data": [
        {
            "time": "2014-02-13T00:00:00",
            "temperature": 4.0,
            "pressure": 995,
            "humidity": 66
        },
        {
            "time": "2014-02-13T00:20:00",
            "temperature": 4.0,
            "pressure": 994,
            "humidity": 75
        }
        ]
}
```



