insert into regagencia(id, nome, numero)
values (1, 'Agencia Sao Paulo', 395);

insert into regcadastronacional(id, numero, digito, emissor, tipo)
values (1, '766264150', '34', 'ssp', 0);
insert into regpessoa(id, cadastronacionalid)
values (1, 1);
insert into regpessoafisica(id, nome, datanascimento, sexo, nomepai, nomemae, nomeconjugue)
values (1, 'Guilherme Leivas', '1998-10-02', 0, 'Carlos Alberto', 'Susi de F Leivas', null);

insert into regcadastronacional(id, numero, nrfilial, digito, emissor, tipo)
values (2, '20139621', '0001', '68', 'br', 1);
insert into regpessoa(id, cadastronacionalid)
values (2, 2);
insert into regpessoajuridica(id, inscestadual, inscmunicipal, nomefantasia, datafundacao)
values (2, '122', '23434', 'Susi de F Leivas', '2014-01-12');

insert into regconta(id, agenciaid, numero, pessoaid, saldo)
values (1, 1, 450, 1, 1500);

insert into regconta(id, agenciaid, numero, pessoaid, saldo)
values (2, 1, 1000, 2, 10500);