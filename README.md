# 图书管理系统

> 纯 JavaWeb 图书管理系统 - Servlet + JSP + JDBC

## 目录

- [项目概述](#项目概述)
- [技术栈](#技术栈)
- [项目结构](#项目结构)
- [快速开始](#快速开始)
  - [环境要求](#环境要求)
  - [克隆项目](#克隆项目)
  - [编译项目](#编译项目)
  - [运行项目](#运行项目)
- [访问地址](#访问地址)
- [功能说明](#功能说明)
  - [图书管理](#图书管理)
  - [分类管理](#分类管理)
- [数据库配置](#数据库配置)
  - [使用 H2 数据库（默认）](#使用-h2-数据库默认)
  - [切换到 MySQL 数据库](#切换到-mysql-数据库)
- [数据库表结构](#数据库表结构)
  - [book_category 表](#book_category-表)
  - [book 表](#book-表)
- [预置测试数据](#预置测试数据)
- [已知问题与修复](#已知问题与修复)
  - [Maven 无法识别](#maven-无法识别)
  - [Maven 依赖解析错误](#maven-依赖解析错误)
  - [中文乱码问题](#中文乱码问题)
  - [Git Remote Origin Already Exists](#git-remote-origin-already-exists)
- [常见问题](#常见问题)
- [许可证](#许可证)

---

## 项目概述

本项目是一个基于纯 JavaWeb 技术栈的图书管理系统，包含以下核心功能：

- ✅ 图书 CRUD（增删改查）
- ✅ 图书分类管理
- ✅ 分页查询
- ✅ 模糊检索
- ✅ 图书库存统计

---

## 技术栈

| 分类 | 技术 | 版本 |
| :--- | :--- | :--- |
| 语言 | Java | 8 |
| 框架 | Servlet | 4.0.1 |
| 视图 | JSP | 2.3.3 |
| 数据库 | H2 / MySQL | H2 2.2.224 / MySQL 8.0+ |
| 构建工具 | Maven | 3.8+ |
| 前端 | Bootstrap | 3.x |
| 前端 | jQuery | 3.x |

---

## 项目结构

```
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       ├── dao/          # 数据访问层
│       │       │   ├── BookDAO.java
│       │       │   └── BookCategoryDAO.java
│       │       ├── entity/       # 实体类
│       │       │   ├── Book.java
│       │       │   └── BookCategory.java
│       │       ├── filter/       # 过滤器
│       │       │   └── EncodingFilter.java
│       │       ├── service/      # 业务逻辑层
│       │       │   ├── BookService.java
│       │       │   └── BookCategoryService.java
│       │       ├── servlet/      # 控制器
│       │       │   ├── BookServlet.java
│       │       │   └── CategoryServlet.java
│       │       └── util/         # 工具类
│       │           └── DBUtil.java
│       ├── resources/
│       │   ├── db.properties     # 数据库配置
│       │   ├── schema.sql        # MySQL 初始化脚本
│       │   └── schema-h2.sql     # H2 初始化脚本（含测试数据）
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml       # Web 应用配置
│           ├── index.jsp         # 首页
│           ├── bookList.jsp      # 图书列表页
│           ├── bookForm.jsp      # 图书新增/编辑页
│           ├── bookDetail.jsp    # 图书详情页
│           ├── categoryList.jsp  # 分类列表页
│           └── categoryForm.jsp  # 分类新增/编辑页
├── data/                         # H2 数据库文件目录
├── target/                       # Maven 构建输出目录
└── pom.xml                       # Maven 配置文件
```

---

## 快速开始

### 环境要求

- JDK 8 或更高版本
- Maven 3.8 或更高版本
- Git（可选，用于克隆项目）

### 验证环境

打开 **Windows PowerShell**，执行以下命令验证环境：

```powershell
# 验证 Java 版本
java -version

# 验证 Maven 版本
mvn -v
```

### 克隆项目

```powershell
git clone https://github.com/wen0906/javaWeb-bookManagementSystem-.git
cd javaWeb-bookManagementSystem-
```

### 编译项目

```powershell
mvn clean package -DskipTests
```

编译成功后，会在 `target/` 目录下生成 `book-management-system.war` 文件。

### 运行项目

项目默认使用 **H2 嵌入式数据库**，无需额外配置数据库：

```powershell
mvn tomcat7:run
```

启动成功后，控制台输出类似：

```
[INFO] Tomcat started on port(s): 8080 (http)
[INFO] Started application in 3.2 seconds
```

---

## 访问地址

| 页面 | URL |
| :--- | :--- |
| 首页 | http://localhost:8080/book-management-system/ |
| 图书列表 | http://localhost:8080/book-management-system/book?action=list |
| 分类列表 | http://localhost:8080/book-management-system/category?action=list |

---

## 功能说明

### 图书管理

| 功能 | 说明 |
| :--- | :--- |
| 图书列表 | 分页展示所有图书，每页显示 5 条 |
| 新增图书 | 填写 ISBN、书名、作者、出版社、出版日期、分类、价格、库存、描述 |
| 编辑图书 | 修改已有图书信息 |
| 删除图书 | 删除指定图书 |
| 查看详情 | 查看图书完整信息 |
| 模糊搜索 | 支持按书名、作者、ISBN 进行模糊检索 |
| 分类筛选 | 按图书分类进行筛选 |
| 库存统计 | 显示图书总数和总库存 |

### 分类管理

| 功能 | 说明 |
| :--- | :--- |
| 分类列表 | 展示所有图书分类 |
| 新增分类 | 添加新的图书分类 |
| 编辑分类 | 修改分类名称和描述 |
| 删除分类 | 删除分类（有关联图书时无法删除） |

---

## 数据库配置

### 使用 H2 数据库（默认）

项目默认使用 H2 嵌入式数据库，数据存储在 `./data/bookdb.mv.db` 文件中。

**配置文件**: `src/main/resources/db.properties`

```properties
jdbc.driver=org.h2.Driver
jdbc.url=jdbc:h2:file:./data/bookdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;AUTO_SERVER=TRUE
jdbc.username=sa
jdbc.password=
```

### 切换到 MySQL 数据库

1. **创建 MySQL 数据库**：

```powershell
mysql -u root -p
CREATE DATABASE bookdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. **修改配置文件** `src/main/resources/db.properties`：

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/bookdb?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
jdbc.username=root
jdbc.password=your_password
```

3. **执行初始化脚本**：

```powershell
mysql -u root -p bookdb < src/main/resources/schema.sql
```

4. **重新编译运行**：

```powershell
mvn clean package -DskipTests
mvn tomcat7:run
```

---

## 数据库表结构

### book_category 表

| 字段 | 类型 | 说明 |
| :--- | :--- | :--- |
| id | INT | 主键，自增 |
| name | VARCHAR(50) | 分类名称，唯一 |
| description | VARCHAR(200) | 分类描述 |
| created_at | TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | 更新时间 |

### book 表

| 字段 | 类型 | 说明 |
| :--- | :--- | :--- |
| id | INT | 主键，自增 |
| isbn | VARCHAR(20) | ISBN 编号，唯一 |
| title | VARCHAR(100) | 书名 |
| author | VARCHAR(50) | 作者 |
| publisher | VARCHAR(100) | 出版社 |
| publish_date | DATE | 出版日期 |
| category_id | INT | 分类ID，外键关联 book_category |
| price | DECIMAL(10,2) | 价格 |
| stock | INT | 库存数量 |
| description | TEXT | 图书描述 |
| created_at | TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | 更新时间 |

---

## 预置测试数据

系统启动时会自动初始化以下测试数据：

### 图书分类（5个）

| ID | 名称 | 描述 |
| :--- | :--- | :--- |
| 1 | 计算机 | 计算机科学与技术相关书籍 |
| 2 | 文学小说 | 各类文学作品和小说 |
| 3 | 历史传记 | 历史书籍和人物传记 |
| 4 | 经济管理 | 经济学和管理学书籍 |
| 5 | 艺术设计 | 艺术、设计类书籍 |

### 图书（7本）

| ID | ISBN | 书名 | 作者 | 出版社 | 分类 | 价格 | 库存 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | 9787111544937 | Java编程思想 | Bruce Eckel | 机械工业出版社 | 计算机 | ¥109.00 | 50 |
| 2 | 9787111407010 | 深入理解计算机系统 | Randal E.Bryant | 机械工业出版社 | 计算机 | ¥139.00 | 30 |
| 3 | 9787532772460 | 活着 | 余华 | 上海文艺出版社 | 文学小说 | ¥39.00 | 100 |
| 4 | 9787020142235 | 百年孤独 | 加西亚·马尔克斯 | 人民文学出版社 | 文学小说 | ¥55.00 | 80 |
| 5 | 9787508653883 | 明朝那些事儿 | 当年明月 | 浙江人民出版社 | 历史传记 | ¥188.00 | 45 |
| 6 | 9787111414346 | 经济学原理 | 曼昆 | 机械工业出版社 | 经济管理 | ¥99.00 | 60 |
| 7 | 9787115411370 | 设计心理学 | 唐纳德·诺曼 | 中信出版社 | 艺术设计 | ¥89.00 | 35 |

---

## 已知问题与修复

### Maven 无法识别

**问题**：执行 `mvn clean package` 时报错：

```
mvn : 无法将“mvn”项识别为 cmdlet、函数、脚本文件或可运行程序的名称。
```

**解决方案**：

1. **安装 JDK**（确保已安装）：

```powershell
# 检查 Java 版本
java -version

# 若未安装，使用 winget 安装
winget install OpenJDK.OpenJDK.17
```

2. **手动安装 Maven**：

- 下载地址：https://maven.apache.org/download.cgi
- 解压到 `C:\apache-maven`
- 设置环境变量：
  - `MAVEN_HOME` = `C:\apache-maven`
  - 在 `PATH` 中添加 `%MAVEN_HOME%\bin`
- 验证：

```powershell
mvn -v
```

---

### Maven 依赖解析错误

**问题**：Maven 无法下载某些依赖包，报错：

```
Could not find artifact javax.servlet.jsp:jsp-api:jar:2.3.3
Could not find artifact mysql:mysql-connector-j:jar:8.0.33
```

**解决方案**：

已在 `pom.xml` 中修复，关键改动：

1. **JSP API**：使用 `javax.servlet.jsp-api` 替代 `jsp-api`

```xml
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>javax.servlet.jsp-api</artifactId>
    <version>2.3.3</version>
    <scope>provided</scope>
</dependency>
```

2. **MySQL 驱动**：使用 `mysql-connector-java` 替代 `mysql-connector-j`

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

3. **添加阿里云 Maven 镜像**：

```xml
<repositories>
    <repository>
        <id>aliyun-public</id>
        <url>https://maven.aliyun.com/repository/public</url>
    </repository>
</repositories>
```

---

### 中文乱码问题

**问题**：图书名称和分类名称显示为乱码（如：`鍘嗗彶浼犺`）

**原因**：`DBUtil.java` 读取 SQL 初始化文件时未指定 UTF-8 编码，Windows 系统默认使用 GBK 编码读取。

**解决方案**：

修改 `src/main/java/com/example/util/DBUtil.java`，使用 UTF-8 编码读取文件：

```java
// 修复前
String sql = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));

// 修复后
String sql = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
```

**注意**：修改后需删除 `data/bookdb.mv.db` 文件，重新启动项目以重新初始化数据。

---

### Git Remote Origin Already Exists

**问题**：执行 `git remote add origin <url>` 时报错：

```
error: remote origin already exists.
```

**解决方案**：

#### 方案一：检查现有远程配置

```powershell
git remote -v
```

如果输出的 URL 与目标 URL 一致，则无需修改，直接推送：

```powershell
git push -u origin main
```

#### 方案二：更新远程 URL

如果现有 URL 不正确，更新为新地址：

```powershell
git remote set-url origin https://github.com/wen0906/javaWeb-bookManagementSystem-.git
```

#### 方案三：删除并重新添加

```powershell
git remote remove origin
git remote add origin https://github.com/wen0906/javaWeb-bookManagementSystem-.git
```

---

## 常见问题

### 端口被占用

**问题**：启动时报错 `Address already in use`

**解决方案**：

1. 查找占用 8080 端口的进程：

```powershell
netstat -ano | findstr :8080
```

2. 杀死进程（替换 PID 为实际进程号）：

```powershell
taskkill /F /PID 12345
```

3. 或修改 `pom.xml` 中的端口配置：

```xml
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <configuration>
        <port>8081</port>  <!-- 修改为其他端口 -->
    </configuration>
</plugin>
```

### 数据库连接失败

**问题**：切换到 MySQL 后无法连接

**检查项**：

1. 确认 MySQL 服务已启动
2. 确认数据库 `bookdb` 已创建
3. 确认 `db.properties` 中的用户名和密码正确
4. 确认 MySQL 允许远程连接（本地连接无需此项）

---

## 许可证

MIT License

---

## 联系作者

如有问题或建议，请联系18575015632@126.com。
```
