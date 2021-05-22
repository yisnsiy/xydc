

# 1. 介绍

## 1.1 系统介绍

​		此系统是本人的毕设设计项目—**校园订餐系统**，主要服务是学生在**微信小程序端**在线订餐，商家在**web端**接单，骑手在**微信小程序端**接单，并配送到寝的外卖服务。其全部功能点均由本人一人实现。

## 1.2 所用技术

后端：Spring Boot

web端：Layui

微信小程序：Uni-app

# 2. 功能模块图

![功能模块图](E:\Code\xydc\screenshots\functional_module.png)

# 3. 项目目录结构

xydc
├── README.md	//自述文件
├── screenshots	//项目运行截图
├── xydc-hd	//系统后端及其web端项目
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml	//springboot配置文件
│   ├── src	//源代码所在路径
│   ├── target
│   └── xydc-hd.iml
└── xydc-wx	//系统微信端项目
    ├── App.vue
    ├── colorui	//微信端所用前端框架
    ├── components	//通用组件
    ├── main.js
    ├── manifest.json
    ├── pages	//主要页面代码
    ├── pages.json
    ├── static
    ├── uni.scss
    ├── uni_modules
    ├── unpackage
    └── util

# 4.项目部分运行截图

## 1.管理员/店主登录

![管理员/店主登录](https://github.com/yisnsiy/xydc/screenshots/web_login.png)

## 2.用户/骑手登录

![用户/骑手登录](https://github.com/yisnsiy/xydc/screenshots/wx_login.jpg)

## 3.管理员主页

![管理员主页](https://github.com/yisnsiy/xydc/screenshots/admin_home.png)

## 4.店主主页

![店主](https://github.com/yisnsiy/xydc/screenshots/store_home.png)

## 5.微信小程序店铺列表

![微信小程序店铺列表](https://github.com/yisnsiy/xydc/screenshots/store_list.jpg)

## 6.店铺详情

![店铺详情](https://github.com/yisnsiy/xydc/screenshots/store_detail.jpg)

## 7.店铺评价

![店铺评价](https://github.com/yisnsiy/xydc/screenshots/store_evaluate.jpg)

## 8.结账

![结账](https://github.com/yisnsiy/xydc/screenshots/buy.jpg)

## 9.骑手配送

![骑手配送](https://github.com/yisnsiy/xydc/screenshots/delivery.jpg)

