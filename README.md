# Apache_SpringBoot

## Version 0.1
2020年1月7日16:00:32：注册功能

表单信息校验：头像文件大小不能超过1M，登录账号长度在6-10个字符之间，用户名长度在4-10个字符之间，密码长度在8-20个字符之间，登录账号存入数据库时全部大写。

判断账号是否已存在：使用ajax异步请求checkAccount。

上传头像：使用jquery中的FormData来包装，再使用commonsMultipartResolver，MultipartHttpServletRequest来获取图片，再使用MultipartFile把图片保存到本地。

## Version 0.2

2020年1月7日16:50:25：注册邮件发送

注册后发送激活邮件：使用RabbitMQ，注册后将信息发送给RabbitMQ的消息队列，RabbitMQ的监听器接收到消息后实现邮件发送。

## Version 0.3
2020年1月8日16:24:12：使用Shiro进行认证和权限管理

认证：整个项目分三种角色：customer，business，admin，每种角色分别有页面的不同权限。

## Version 0.4
2020年1月9日15:26:19：用户登录的回显，用户注销

## Version 0.5
2020年1月10日10:20:21：磁盘目录映射

将用户上传的头像文件和网页的url对应，主要使用addResourceHandlers

## Version 0.6

2020年05月21日08:43:03：支付功能

集成支付宝的支付功能，替代原先的默认支付成功。