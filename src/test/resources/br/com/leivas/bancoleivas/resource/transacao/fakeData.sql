insert into regagencia(id, nome, numero, optmisticklock, dtcreate, dtupdate)
values (1, 'Agencia Sao Paulo', 395, 1, '2022-01-30', '2022-01-30');

insert into regcadastronacional(id, numero, digito, emissor, tipo, optmisticklock, dtcreate, dtupdate)
values (1, '766264150', '34', 'ssp', 0, 1, '2022-01-30', '2022-01-30');
insert into regpessoa(id, cadastronacionalid, optmisticklock, dtcreate, dtupdate)
values (1, 1, 1, '2022-01-30', '2022-01-30');
insert into regpessoafisica(id, nome, datanascimento, sexo, nomepai, nomemae, nomeconjugue)
values (1, 'Guilherme Leivas', '1998-10-02', 0, 'Carlos Alberto', 'Susi de F Leivas', null);

insert into regcadastronacional(id, numero, nrfilial, digito, emissor, tipo, optmisticklock, dtcreate, dtupdate)
values (2, '20139621', '0001', '68', 'br', 1, 1, '2022-01-30', '2022-01-30');
insert into regpessoa(id, cadastronacionalid, optmisticklock, dtcreate, dtupdate)
values (2, 2, 1, '2022-01-30', '2022-01-30');
insert into regpessoajuridica(id, inscestadual, inscmunicipal, nomefantasia, datafundacao)
values (2, '122', '23434', 'Susi de F Leivas', '2014-01-12');

insert into regconta(id, agenciaid, numero, pessoaid, saldo, optmisticklock, dtcreate, dtupdate)
values (1, 1, 450, 1, 1500, 1, '2022-01-30', '2022-01-30');

insert into regconta(id, agenciaid, numero, pessoaid, saldo, optmisticklock, dtcreate, dtupdate)
values (2, 1, 1000, 2, 10500, 1, '2022-01-30', '2022-01-30');

insert into authpermissao(id, descricao, optmisticklock, dtcreate, dtupdate)
values (1, 'Cliente', 1, '2022-02-03', '2022-02-03');

insert into authusuario(id, contaid, senha, permissaoid, optmisticklock, dtcreate, dtupdate)
values(1, 1, '$2a$10$Do0ZKEbU695XVsE1efVXM.kAxj0pOMv79xULt6pVD8MD2vvgwwb/.', 1, 1, '2022-02-03', '2022-02-03');

insert into authusuario(id, contaid, senha, permissaoid, optmisticklock, dtcreate, dtupdate)
values(2, 2, '$2a$10$8Ny3ofum.985oxR.ZHDh/ubvnRVKmko6BVRT20rgHRZ5neWvgwtaq', 1, 1, '2022-02-03', '2022-02-03');

insert into finmodelotransacao (id, codigo, nome, executacaodatransacao, transacaostrategy, optmisticklock, dtcreate, dtupdate)
values (1, 200, 'Depósito', 1, 'br.com.leivas.bancoleivas.strategy.DepositoStrategy', 1, '2022-01-30', '2022-01-30');

insert into finmodelotransacao (id, codigo, nome, executacaodatransacao, transacaostrategy, optmisticklock, dtcreate, dtupdate)
values (2, 201, 'Transferência', 0, 'br.com.leivas.bancoleivas.strategy.TransferenciaStrategy', 1, '2022-01-30', '2022-01-30');