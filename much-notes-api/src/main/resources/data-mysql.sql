INSERT INTO note_user (id, username, nickname, password, register_time, register_ip, status,
                       open_id, union_id, type, session_key, head_img, create_time, update_time,
                       remark)
VALUES (1, 'root', 'root', '11dfa46679892d53c8fa2445c8733469', '2021-02-15 13:03:59', '', 1, '', '',
        10, null, null, '2021-02-15 13:03:59', '2021-02-15 13:04:20', null);

INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (1, '吃喝',
        '["https://www.flysium.xyz/oss/notes/category/icon_sh_normal.png","https://www.flysium.xyz/oss/notes/category/icon_sh_active.png"]',
        0, 0, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (2, '日用品',
        '["https://www.flysium.xyz/oss/notes/category/icon_ryp_normal.png","https://www.flysium.xyz/oss/notes/category/icon_ryp_active.png"]',
        0, 0, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (3, '运动娱乐',
        '["https://www.flysium.xyz/oss/notes/category/icon_ylyd_normal.png","https://www.flysium.xyz/oss/notes/category/icon_ylyd_active.png"]',
        0, 0, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (4, '出行',
        '["https://www.flysium.xyz/oss/notes/category/icon_cx_normal.png","https://www.flysium.xyz/oss/notes/category/icon_cx_active.png"]',
        0, 0, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (5, '其它',
        '["https://www.flysium.xyz/oss/notes/category/icon_other_normal.png","https://www.flysium.xyz/oss/notes/category/icon_other_active.png"]',
        0, 0, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (11, '红包',
        '["https://www.flysium.xyz/oss/notes/category/icon_other_normal.png","https://www.flysium.xyz/oss/notes/category/icon_other_active.png"]',
        0, 0, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);

INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (6, '工资',
        '["https://www.flysium.xyz/oss/notes/category/icon_other_normal.png","https://www.flysium.xyz/oss/notes/category/icon_other_active.png"]',
        0, 1, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (7, '红包',
        '["https://www.flysium.xyz/oss/notes/category/icon_other_normal.png","https://www.flysium.xyz/oss/notes/category/icon_other_active.png"]',
        0, 1, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (8, '基金股票',
        '["https://www.flysium.xyz/oss/notes/category/icon_other_normal.png","https://www.flysium.xyz/oss/notes/category/icon_other_active.png"]',
        0, 1, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (9, '奖金',
        '["https://www.flysium.xyz/oss/notes/category/icon_other_normal.png","https://www.flysium.xyz/oss/notes/category/icon_other_active.png"]',
        0, 1, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (21, '其它',
        '["https://www.flysium.xyz/oss/notes/category/icon_other_normal.png","https://www.flysium.xyz/oss/notes/category/icon_other_active.png"]',
        0, 1, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);
INSERT INTO category (id, name, icon, is_custom, type, create_time, update_time, remark)
VALUES (22, '退款',
        '["https://www.flysium.xyz/oss/notes/category/icon_cx_normal.png","https://www.flysium.xyz/oss/notes/category/icon_cx_active.png"]',
        0, 1, '2021-02-15 12:59:36', '2021-02-15 12:59:36', null);

INSERT INTO account_type (type, name)
VALUES ('0', '现金');
INSERT INTO account_type (type, name)
VALUES ('1', '支付宝');
INSERT INTO account_type (type, name)
VALUES ('2', '微信');
INSERT INTO account_type (type, name)
VALUES ('3', '不选');

