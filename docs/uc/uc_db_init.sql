DROP DATABASE IF EXISTS `db_usercenter`;
CREATE DATABASE `db_usercenter` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
use db_usercenter;

DROP TABLE IF EXISTS `t_uc_oauth_user`;
CREATE TABLE `t_uc_oauth_user`
(
    `id`          bigint(20)                         NOT NULL COMMENT '主键',
    `username`    varchar(50)                        NOT NULL COMMENT '账号',
    `password`    varchar(200)                       NULL COMMENT '密码',
    `email`       varchar(200)                       NULL COMMENT '电子邮件,冗余字段',
    `mobile`      varchar(200)                       NULL COMMENT '电话号码,冗余字段',
    `create_time` datetime default current_timestamp NULL COMMENT '创建时间',
    `create_user` int(11)                            NULL COMMENT '创建人ID',
    `update_time` datetime                           NULL COMMENT '更新时间',
    `update_user` int(11)                            NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT '用户信息表';


DROP TABLE IF EXISTS `t_uc_oauth_client_detail`;
CREATE TABLE `t_uc_oauth_client_detail`
(
    `id`                             bigint(20)                         NOT NULL COMMENT '主键',
    `client_id`                      varchar(50)                        NULL COMMENT '',
    `access_token_validity_seconds`  int(11)                            NULL COMMENT 'token有效期',
    `additional_information`         varchar(50)                        NULL COMMENT '',
    `authorities`                    varchar(50)                        NULL COMMENT '授权信息',
    `authorized_grant_types`         varchar(50)                        NULL COMMENT '授权类型',
    `auto_approve`                   varchar(50)                        NULL COMMENT '',
    `client_secret`                  varchar(50)                        NULL COMMENT '密码',
    `refresh_token_validity_seconds` int(11)                            NULL COMMENT 'refreshToken有效期',
    `registered_redirect_uri`        varchar(50)                        NULL COMMENT '',
    `resource_ids`                   varchar(50)                        NULL COMMENT '资源IDs',
    `scope`                          varchar(50)                        NULL COMMENT '生效范围',
    `secret_required`                varchar(50)                        NULL COMMENT '密码是否必填',
    `create_time`                    datetime default current_timestamp NULL COMMENT '创建时间',
    `create_user`                    int(11)                            NULL COMMENT '创建人ID',
    `update_time`                    datetime                           NULL COMMENT '更新时间',
    `update_user`                    int(11)                            NULL COMMENT '更新人ID'
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT 'OauthClient信息表';

create table oauth_client_token
(
    `token_id`          VARCHAR(256),
    `token`             BLOB,
    `authentication_id` VARCHAR(256) PRIMARY KEY,
    `user_name`         VARCHAR(256),
    `client_id`         VARCHAR(256)
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT 'ClientToken存放信息';

create table oauth_access_token
(
    `token_id`          VARCHAR(256),
    `token`             BLOB,
    `authentication_id` VARCHAR(256) PRIMARY KEY,
    `user_name`         VARCHAR(256),
    `client_id`         VARCHAR(256),
    `authentication`    BLOB,
    `refresh_token`     VARCHAR(256)
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT 'AccessToken存放信息';

create table oauth_refresh_token
(
    `token_id`       VARCHAR(256),
    `token`          BLOB,
    `authentication` BLOB
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT 'ResfreshToken存放信息';

create table oauth_code
(
    `code`           VARCHAR(256),
    `authentication` BLOB
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT '授权码存放信息';

create table oauth_approvals
(
    `userId`         VARCHAR(256),
    `clientId`       VARCHAR(256),
    `scope`          VARCHAR(256),
    `status`         VARCHAR(10),
    `expiresAt`      DATETIME,
    `lastModifiedAt` DATETIME
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT '授权信息保存策略';


DROP TABLE IF EXISTS `t_uc_role`;
CREATE TABLE `t_uc_role`
(
    `id`          bigint(20)                         NOT NULL COMMENT '主键',
    `name`        varchar(50)                        NOT NULL COMMENT '名称',
    `desc`        varchar(200)                       NULL COMMENT '描述',
    `create_time` datetime default current_timestamp NULL COMMENT '创建时间',
    `create_user` int(11)                            NULL COMMENT '创建人ID',
    `update_time` datetime                           NULL COMMENT '更新时间',
    `update_user` int(11)                            NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT '角色表';

DROP TABLE IF EXISTS `t_uc_resource`;
CREATE TABLE `t_uc_resource`
(
    `id`            bigint(20)                         NOT NULL COMMENT '主键',
    `name`          varchar(50)                        NOT NULL COMMENT '名称',
    `desc`          varchar(200)                       NULL COMMENT '描述',
    `resource_type` int(11)                            NULL COMMENT '资源类型',
    `resource_key`  varchar(2000)                      NULL COMMENT '资源信息',
    `ext_info`      varchar(2000)                      NULL COMMENT '拓展信息',
    `create_time`   datetime default current_timestamp NULL COMMENT '创建时间',
    `create_user`   int(11)                            NULL COMMENT '创建人ID',
    `update_time`   datetime                           NULL COMMENT '更新时间',
    `update_user`   int(11)                            NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT '资源表';

DROP TABLE IF EXISTS `t_uc_permission`;
CREATE TABLE `t_uc_permission`
(
    `id`          bigint(20)                         NOT NULL COMMENT '主键',
    `name`        varchar(50)                        NOT NULL COMMENT '名字',
    `desc`        varchar(200)                       NULL COMMENT '描述',
    `create_time` datetime default current_timestamp NULL COMMENT '创建时间',
    `create_user` int(11)                            NULL COMMENT '创建人ID',
    `update_time` datetime                           NULL COMMENT '更新时间',
    `update_user` int(11)                            NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT '权限表';

DROP TABLE IF EXISTS `t_uc_user_role_rel`;
CREATE TABLE `t_uc_user_role_rel`
(
    `id`      bigint(20)   NOT NULL COMMENT '主键',
    `user_id` varchar(50)  NOT NULL COMMENT '用户',
    `role_id` varchar(200) NULL COMMENT '角色'
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT '用户角色关联表';

DROP TABLE IF EXISTS `t_uc_permission_resource_rel`;
CREATE TABLE `t_uc_permission_resource_rel`
(
    `permission_id` varchar(50)  NOT NULL COMMENT '权限',
    `resource_id`   varchar(200) NULL COMMENT '资源'
) ENGINE = INNODB ,
  DEFAULT CHARSET = utf8 ,COMMENT '权限资源关联表';