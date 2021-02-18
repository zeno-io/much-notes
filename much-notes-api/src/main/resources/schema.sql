# create database much_notes default charset  utf8mb4;

CREATE TABLE `note_user`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `username`      varchar(64)  NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname`      varchar(64)           DEFAULT '' COMMENT '用户昵称',
    `password`      char(32)     NOT NULL DEFAULT '' COMMENT '用户密码',
    `register_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `register_ip`   varchar(100) NOT NULL DEFAULT '' COMMENT '注册IP',
    `status`        int          NOT NULL DEFAULT '1' COMMENT '账号状态：0-封号； 1-正常；',
    `open_id`       varchar(100) NOT NULL DEFAULT '' COMMENT '三方登录唯一ID',
    `union_id`      varchar(100) NOT NULL DEFAULT '' COMMENT '微信开放平台的唯一ID',
    `type`          int          NOT NULL DEFAULT '0' COMMENT '用户类型：0-普通用户； 5-vip用户； 10-管理员；',
    `session_key`   varchar(100)          DEFAULT NULL COMMENT 'session_key 用于与微信交换信息的密钥',
    `head_img`      varchar(255)          DEFAULT NULL COMMENT '用户头像',
    `create_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime              DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`        varchar(512)          DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `union_id` (`union_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='管理员认证信息';

CREATE TABLE `category`
(
    `id`          bigint           NOT NULL AUTO_INCREMENT,
    `name`        varchar(255)     NOT NULL,
    `icon`        varchar(512)              DEFAULT NULL,
    `is_custom`   tinyint          NOT NULL DEFAULT '0' COMMENT '是否为用户自定义分类：0-否； 1-是；',
    `type`        tinyint unsigned NOT NULL DEFAULT '0' COMMENT '分类：0-支出； 1-收入；',
    `create_time` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                  DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(512)              DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `account_type`
(
    `type`   int         NOT NULL COMMENT '账户分类：3-不选帐户； 2-微信； 1-支付宝； 0-现金；',
    `name`   varchar(56) NOT NULL,
    `remark` varchar(512) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`type`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='账户类型表';

CREATE TABLE `user_account_info`
(
    `uid`          bigint   NOT NULL,
    `account_type` int      NOT NULL COMMENT '账户类型，关联表 account_type',
    `balance`      bigint            DEFAULT NULL COMMENT '账户余额',
    `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime          DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`       varchar(512)      DEFAULT NULL COMMENT '备注',
    KEY `uid_account_type` (`uid`, `account_type`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '用户账户余额数据';


CREATE TABLE `user_account_book`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT,
    `uid`         bigint            DEFAULT NULL COMMENT '用户 ID',
    `name`        varchar(255)      DEFAULT NULL COMMENT '名称',
    `type`        tinyint  NOT NULL DEFAULT '0' COMMENT '账单的用户类型：0-流水账单； 5-AA账单；',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime          DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(512)      DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `uid` (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `user_account_book_auth`
(
    `id`              bigint           NOT NULL AUTO_INCREMENT,
    `uid`             bigint           NOT NULL,
    `account_book_id` bigint           NOT NULL,
    `is_admin`        tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否是管理者：0-否； 1-是；',
    `block`           tinyint          NOT NULL DEFAULT '0' COMMENT '是否被阻止进入此账单：0-否； 1-是；',
    `create_time`     datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                  DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`          varchar(512)              DEFAULT NULL COMMENT '备注',
    `is_deleted`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0-否； 1-是；',
    PRIMARY KEY (`id`),
    KEY `uid_owner` (`uid`, `is_admin`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='记录用户与账单的关系表';

CREATE TABLE `user_account_record`
(
    `id`              bigint           NOT NULL AUTO_INCREMENT,
    `uid`             bigint           NOT NULL COMMENT '添加的人员用户 ID',
    `money`           bigint                    DEFAULT NULL COMMENT '金额，分为单位，实际金额要 x100',
    `type`            tinyint unsigned          DEFAULT '0' COMMENT '分类：0-支出； 1-收入；',
    `account_type`    int                       DEFAULT '3' COMMENT '账户分类：3-不选帐户； 2-微信； 1-支付宝； 0-现金；',
    `account_book_id` bigint           NOT NULL COMMENT '账本 ID',
    `time`            date                      DEFAULT NULL COMMENT '支出或收入时间',
    `time_year`       int                       DEFAULT NULL COMMENT '支出或收入时间-年',
    `time_month`      int                       DEFAULT NULL COMMENT '支出或收入时间-月',
    `time_day`        int                       DEFAULT NULL COMMENT '支出或收入时间-日',
    `category_name`   varchar(255)              DEFAULT NULL COMMENT '支出或者收入事项名',
    `category_id`     bigint                    DEFAULT NULL COMMENT '支出或收入事项 ID',
    `create_time`     datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                  DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`          varchar(512)              DEFAULT NULL COMMENT '备注',
    `is_deleted`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0-否； 1-是；',
    PRIMARY KEY (`id`),
    KEY `abid_uid_type_money_time` (`account_book_id`, `uid`, `type`, `money`, `time`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='账单记录表';

CREATE TABLE `user_account_book_category`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT,
    `uid`         bigint   NOT NULL,
    `gid`         bigint   NOT NULL,
    `cid`         bigint   NOT NULL COMMENT '分类 ID',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime          DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(512)      DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='这个表是用户ID、账单ID、分类ID的关联表，用来处理不同用户对于不同账单的建立不同分类的需求';
