INSERT INTO db_usercenter.t_uc_user (id, username, password, email, mobile)
VALUES (1, 'admin', '$2a$10$omy/yrXdss75j3PHx/wBHuPs5LLKY6gGt7LrOPQ0boK4fE.qKWySu', null, '18826457890');

INSERT INTO db_usercenter.t_uc_oauth_client_detail (id, client_id, access_token_validity_seconds,
                                                    additional_information, authorities, authorized_grant_types,
                                                    auto_approve, client_secret, refresh_token_validity_seconds,
                                                    registered_redirect_uri, resource_ids, scope, secret_required)
VALUES (1, 'usercenter', 3600, null, null, 'password,client_credentials', null,
        '$2a$10$omy/yrXdss75j3PHx/wBHuPs5LLKY6gGt7LrOPQ0boK4fE.qKWySu', 3600, null, 'usercenter', 'all', 'true');

insert into t_uc_role(id, name, `desc`)
values (1, 'root', '管理员');

insert into t_uc_permission(id, name, `desc`)
values (1, '测试权限', '测试权限');

insert into t_uc_resource(id, name, `desc`, resource_type, resource_key, ext_info)
values (1, '测试权限api', 'JustATest', 2, '/okokok', '');

insert into t_uc_resource(id, name, `desc`, resource_type, resource_key, ext_info)
values (2, '主页菜单', '菜单test', 5, 'index-menu', '');

insert into t_uc_resource(id, name, `desc`, resource_type, resource_key, ext_info)
values (3, '用户身份','', 5, 'userId', '');

insert into t_uc_user_role_rel(id, user_id, role_id)
values (1, 1, 1);

insert into t_uc_role_permission_rel(id, role_id, permission_id)
values (1,1,1);

insert into t_uc_permission_resource_rel(id, permission_id, resource_id)
values (1,1,1),(1,1,2);