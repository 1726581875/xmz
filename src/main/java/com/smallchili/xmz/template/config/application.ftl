#配置数据库连接参数
spring:
  datasource:
    driver-class-name: ${driver}
    url: ${url}
    username: ${username}
    password: ${password}
#执行数据库操作时，控制台打印sql语句    
  jpa:
    show-sql: true
    properties:
      hibernate:
      format_sql: true

server:
  port: 9001