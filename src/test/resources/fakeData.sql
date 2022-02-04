-- Cleaning up
delete from finlancamentoextrato;
delete from fintransacao;
delete from regconta;
delete from authusuario;
delete from authpermissao;
delete from regpessoafisica;
delete from regpessoajuridica;
delete from regpessoa;
delete from regcadastronacional;
delete from finmodelotransacao;

insert into authpermissao(id, descricao, optmisticklock, dtcreate, dtupdate)
values (nextval('seqauthpermissao'), 'Cliente', 1, '2022-02-03', '2022-02-03');

insert into regcadastronacional(id, numero, digito, emissor, tipo, optmisticklock, dtcreate, dtupdate)
values (nextval('seqregcadastronacional'), '766264150', '34', 'ssp', 0, 1, '2022-01-30', '2022-01-30');
insert into regpessoa(id, cadastronacionalid, optmisticklock, dtcreate, dtupdate)
values (nextval('seqregpessoa'), currval('seqregcadastronacional'), 1, '2022-01-30', '2022-01-30');
insert into regpessoafisica(id, nome, datanascimento, sexo, nomepai, nomemae, nomeconjugue)
values (currval('seqregpessoa'), 'Guilherme Leivas', '1998-10-02', 0, 'Carlos Alberto', 'Susi de F Leivas', null);

insert into authusuario(id, identificacao, username, senha, permissaoid, optmisticklock, dtcreate, dtupdate)
values(nextval('seqauthusuario'), 'Guilherme Leivas', 'Guilherme450', '$2a$09$rWuGW7YUmGNuH/cel5po3uP1lV0PFpqLrHo7lyZaG9BbXfu21GvcK',
       currval('seqauthpermissao'), 1, '2022-02-03', '2022-02-03');

insert into regconta(id, numero, pessoaid, usuarioid, saldo, optmisticklock, dtcreate, dtupdate)
values (nextval('seqregconta'), 450, currval('seqregpessoa'), currval('seqauthusuario'), 1500, 1, '2022-01-30', '2022-01-30');

insert into regcadastronacional(id, numero, nrfilial, digito, emissor, tipo, optmisticklock, dtcreate, dtupdate)
values (nextval('seqregcadastronacional'), '20139621', '0001', '68', 'br', 1, 1, '2022-01-30', '2022-01-30');
insert into regpessoa(id, cadastronacionalid, optmisticklock, dtcreate, dtupdate)
values (nextval('seqregpessoa'), currval('seqregcadastronacional'), 1, '2022-01-30', '2022-01-30');
insert into regpessoajuridica(id, inscestadual, inscmunicipal, nomefantasia, datafundacao)
values (currval('seqregpessoa'), '122', '23434', 'Susi de F Leivas', '2014-01-12');

insert into authusuario(id, identificacao, username, senha, permissaoid, optmisticklock, dtcreate, dtupdate)
values(nextval('seqauthusuario'), 'Susi de F Leivas', 'Susi1000', '$2a$09$rWuGW7YUmGNuH/cel5po3uP1lV0PFpqLrHo7lyZaG9BbXfu21GvcK',
       currval('seqauthpermissao'), 1, '2022-02-03', '2022-02-03');

insert into regconta(id, numero, pessoaid, usuarioid, saldo, optmisticklock, dtcreate, dtupdate)
values (nextval('seqregconta'), 1000, currval('seqregpessoa'), currval('seqauthusuario'), 10500, 1, '2022-01-30', '2022-01-30');


insert into authpermissao(id, descricao, optmisticklock, dtcreate, dtupdate)
values (nextval('seqauthpermissao'), 'Admin', 1, '2022-02-03', '2022-02-03');

insert into authusuario(id, identificacao, username, senha, permissaoid, optmisticklock, dtcreate, dtupdate)
values(nextval('seqauthusuario'), 'Administrador', 'Admin', '$2a$09$rWuGW7YUmGNuH/cel5po3uP1lV0PFpqLrHo7lyZaG9BbXfu21GvcK',
       currval('seqauthpermissao'), 1, '2022-02-03', '2022-02-03');

insert into finmodelotransacao (id, codigo, nome, executacaodatransacao, transacaostrategy, optmisticklock, dtcreate, dtupdate)
values (nextval('seqfinmodelotransacao'), 200, 'Depósito', 1, 'br.com.leivas.bancoleivas.strategy.DepositoStrategy', 1, '2022-01-30', '2022-01-30');

insert into finmodelotransacao (id, codigo, nome, executacaodatransacao, transacaostrategy, optmisticklock, dtcreate, dtupdate)
values (nextval('seqfinmodelotransacao'), 201, 'Transferência', 0, 'br.com.leivas.bancoleivas.strategy.TransferenciaStrategy', 1, '2022-01-30', '2022-01-30');