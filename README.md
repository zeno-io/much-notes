# Much Notes :: 慕夏手记

[![Build Status](https://api.travis-ci.org/SvenAugustus/much-notes.svg?branch=master)](https://travis-ci.org/SvenAugustus/much-notes) [![License](https://img.shields.io/badge/license-MIT-green)](https://opensource.org/licenses/MIT)

#### Description

一款简单的共享记账微信小程序，后端使用的是 **Java ::`Spring Boot`**。
> Fork from : https://gitee.com/chniccs/jizhang

#### Feature

- 1、 支出、收入记录
- 2、 账本共享，多账本
- 3、 账本管理，邀请链接有时效限制，避免了长期在外被乱用
- 4、 账本人员管理，可对同一个账本内的人员进行管理，包括移除，移交管理员
- 5、 数据统计，图表形式展示收支情况

--------------------------

#### Architecture

- `much-notes-api`: 后端，Java ::`Spring Boot`
- `much-notes-parent`：后端 Maven BOM
- `much-notes-miniprogram`: 微信小程序::慕夏手记

#### `much-notes-api` 安装教程

* 1、导入 `Eclipse` 或 `IntelliJ IDEA` 开发工具
* 2、微信平台注册微信小程序，并绑定开放平台（`unionId`）
* 3、增加启动参数 `VM Options`，并启动 **`xyz.flysium.MuchNotesApplication`**

```shell
-Dwx.miniapp.appid=wxfbf8cccc79c5c29b
-Dwx.miniapp.secret=yourSecret
-Dwx.miniapp.token=yourToken
-Dwx.miniapp.aesKey=youraseKey
```

> 参数根据实际注册和配置的信息替换

##### `much-notes-miniprogram` 安装教程

* 1、`npm install` (项目中因使用了`antv/wx-f2`的图表框架)
* 2、 导入微信开发者工具
* 3、 修改`app.js`文件的 `globalData`中`appId`及后端地址
* 4、 在开发者工具中使用：工具->构建npm ，即可构建完成

> 参数根据实际注册和配置的信息替换

#### 内网穿透

推荐：http://u.tools/ ，插件中心-安装内网穿透。效率挺快

> https://github.com/Wechat-Group/WxJava/wiki/%E5%87%A0%E4%B8%AA%E5%86%85%E7%BD%91%E7%AB%AF%E5%8F%A3%E6%98%A0%E5%B0%84%E6%9C%8D%E5%8A%A1%E7%BD%91%E7%AB%99

#### Contract

* Email: zeno531@outlook.com
* GitHub: https://github.com/SvenAugustus
* GitLab: https://gitlab.com/SvenAugustus


