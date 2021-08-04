# shiro-study

#### 项目介绍
用来学习shiro的demo（springboot、shiro、mybatis-plus、redis），非常简洁，不带其它无关的框架，代码拥有详细注释。

#### 开发环境

| 工具    | 版本或描述                |
| ----- | -------------------- |
| OS    | Windows 10           |
| JDK   | 1.8+                 |
| IDE   | IntelliJ IDEA 2021.1 |
| Maven | 3.5.4                |
| MySQL | 5.7.18                |


#### 数据库模型

![sql model](https://raw.githubusercontent.com/xueqian77/shiro-study/main/doc/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%BB%93%E6%9E%84.png)

#### 使用说明

1. 使用IDE导入本项目
2. 新建数据库`CREATE DATABASE shiro-study;`
3. 导入数据`resources/sql/init.sql`
4. 按照(`resources/application.yml_template`)增加(`resources/application.yml`)配置文件
    1. 修改数据库链接
5. 运行项目
    1. springboot启动类启动

#### 开源协议
[MIT](https://github.com/xueqian77/shiro-study/blob/main/LICENSE)
