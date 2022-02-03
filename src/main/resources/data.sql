insert into authpermissao(id, descricao, optmisticklock, dtcreate, dtupdate)
values (nextval('seqauthpermissao'), 'Cliente', 1, '2022-02-03', '2022-02-03');

insert into authpermissao(id, descricao, optmisticklock, dtcreate, dtupdate)
values (nextval('seqauthpermissao'), 'Admin', 1, '2022-02-03', '2022-02-03');

insert into authusuario(id, identificacao, username, senha, permissaoid, optmisticklock, dtcreate, dtupdate)
values(nextval('seqauthusuario'), 'Administrador', 'Admin', '$2a$09$rWuGW7YUmGNuH/cel5po3uP1lV0PFpqLrHo7lyZaG9BbXfu21GvcK',
       currval('seqauthpermissao'), 1, '2022-02-03', '2022-02-03');