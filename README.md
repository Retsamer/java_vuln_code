## Java Vuln Code

本项目基于springboot2.5.4+mybatis2.1.1+mysql8.0.26，主要目的是个人学习总结Java相关漏洞以及对应代码修复方式。很多参考学习了JoyChou93大佬的java-sec-code项目，在此感谢。

本项目包含漏洞均经过个人代码审计实践，漏洞修复方案仅供参考，切勿直接运用。因为springboot版本相对较高，部分漏洞无法直接回显，需要结合dnslog进行使用。

目前包括：命令注入，Fastjson反序列化漏洞，文件上传漏洞，IDOR，路径穿越，SQL注入，SSRF，Thymeleafi模板注入，重定向漏洞，XSS漏洞，XXE漏洞，Shiro反序列化漏洞。shiro key为：3AvVhmFLUs0KTA3Kprsdag==。

## 运行说明

### 访问路径

```
http://127.0.0.1:8000
账号密码：
	管理员权限 admin password
	普通用户1 user user
```

### 数据库配置

创建数据库java_code,导入create.sql文件

```
spring.datasource.url=jdbc:mysql://localhost:3306/java_code
spring.datasource.username=root
spring.datasource.password=123456
```

### IDEA

下载源代码，直接运行即可

### Docker

运行：

```
docker-compose pull
docker-compose up
```

关闭

```
docker-compose down
```
