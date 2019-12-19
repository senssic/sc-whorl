# sc-whorl

## 项目简介

[![License](https://img.shields.io/github/license/Senssic/sc-whorl.svg?style=flat)](https://github.com/Senssic/sc-whorl/blob/master/LICENSE)
[![JDK 1.8+](https://img.shields.io/badge/JDK-1.8-blue.svg)](#sc-whorl)

sc-whorl是一个基于springboot2.x的微服务开发脚手架,整合了多种开源框架配置,可以作为领域单体直接进行使用,可以根据自己的实际需求进行二次开发和扩展.

此项目不但包括springBoot体系相关的配置集成示例,还包括了一些自定义的注解以及注解实现.包括**防重提交注解**,**限流注解**,**常用验证注解**以及扩展,另外为了方便开发还增加了对于**统一日志打印**,**统一异常处理**,**分页**,**分布式锁**等基础服务等一些常用规范示例.




## 项目使用技术栈

- springboot2.0
- docker +docker plugin
- mybatis +pageHelper
- redis
- springSecurity+jwt
- swagger2
- mysql
- Spring Boot Cache
- Srping Boot Mail

## 自定义注解说明

## @limit 应用服务器限流注解

```java
//限流 10秒内最多允许1000次访问
@Limit(key = "USER:LOGIN", period = 10, count = 1000)
public MsgResponseBody<JWTUserDetail> login(@RequestJsonParam("userName") String userName, @RequestJsonParam("passWord") String passWord) {
}
```

**注解字段说明**

```java
//资源的名字
String name() default "";
//资源的key
String key() default "";
//Key的前缀
String prefix() default "";
//给定的时间段,单位秒
int period();
//最多的访问限制次数
int count();
//类型
LimitType limitType() default LimitType.CUSTOMER;
```



## @PreventResubmitLock & @PreventParam 应用服务防重提交

```java
//默认5秒内同一手机号不可重复提交
@PreventResubmitLock(prefix = "USER:REGISTER")
public MsgResponseBody<String> register(@RequestBody @PreventParam(name = "userPhone") UserVo userVo) {
    userService.register(userVo);
    return MsgResponseBody.success().setResult("注册成功");
}
```

**注解字段说明**

@PreventResubmitLock

```java
//redis 锁key的前缀
String prefix() default "";
//轮询锁的时间,过期秒数,默认为5秒
int expire() default 5;
//超时时间单位,秒
TimeUnit timeUnit() default TimeUnit.SECONDS;
//Key的分隔符（默认 :)
String delimiter() default ":";
```

@PreventParam

```java
//字段名称,即为防止重复提交的入参key,用于反射
String name() default "";
```

## @RequestJsonParam 请求体为json的属性提取

```java
public MsgResponseBody<JWTUserDetail> login(@RequestJsonParam("userName") String userName, @RequestJsonParam("passWord") String passWord) {
}
```

**注解字段说明**

```java
//用于绑定的请求参数名字
String value() default "" ;
//是否必须，默认是
boolean required() default true;
//是否是当前json对象,默认false,表示当前josn对象中的某一个值,若是true,则忽略value
boolean current() default false;
//如果是日期类型的,设置其时间.默认为:yyyy-MM-dd HH:mm:ss
String dateFormat() default YMDHMS;
```

# 项目部署

- 新建mysql的whorl库编码格式为UTF-8,导入工程中的whorl_db.sql
- 修改spring-boot的配置文件,数据库连接,redis连接等指向对应的配置路径
- 以springboot或dockerFile的方式启动工程





# 呜谢

部分代码和参考实现来源于网络

感谢

[battch](https://blog.battcn.com/categories/SpringBoot/)

[awesome-spring-boot](https://github.com/ityouknow/awesome-spring-boot)
