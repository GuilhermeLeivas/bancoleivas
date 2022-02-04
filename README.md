
<html lang="en">
<head>
</head>
<body>
 
Fala Dev!

 <h2>Tecnologias utilizadas</h2>

  - JDK 17
  - Spring boot
  - Spring Data JPA
  - Spring Actuator
  - Spring security
  - Postgresql
  - Hibernate
  - IntelliJ

 <h4> Algumas informações para rodar o projeto corretamente:</h4>

 <h2>Requisitos</h2>

  - Você precisará de um container docker postgresql rodando na sua máquina, ou um postgresql instalado na sua máquina.
  Importante ressaltar que se preciso, você pode mudar a senha utilizada para acessar sua database no arquivo application.properties.
  
  - Após você ter seu postgresql rodando, utilize alguma ferramenta como Dbeaver, DataGrip ou até mesmo o terminal, e crie uma database chamada "bancoleivas". 
  Isto é necessário, pois diferente do Mysql, no postgresql não é possível definir a propriedade createDatabaseIfNotExists no application.properties.
  
  - Após esse setup inicial, você pode clonar o projeto e abri-lo na IDE IntelliJ (De preferência).
  
  - Você pode iniciar a aplicação, ela estará rodando na porta 8080 por padrão. É importante falar que por padrão o profile ativo é "oauth-security". 
  Todavia, você pode setar o profile "security-disabled", o qual deixará todos endpoints desprotegidos, isso facilitará seus testes, caso deseje.
  
  - Caso tenha escolhido testar API sem segurança, você pode se dirigir até o endereço 
 <a href="http://localhost:8080/swagger-ui-/index.html">Documentação Swagger</a>, nesta página você terá acesso a todos endpoints documentados e além disso você testa-los diretamente por lá!
  
  - Caso tenha escolhido testar a API com segurança oauth, sem problemas! você novamente pode acessar a página referida acima, mas no entanto você precisará
  de autenticação. Utilize o usuário Admin que será criado no startup da aplicação (para fins de teste).
  
      Usuário Admin
        username: Admin
        senha: teste123
  
  Utilizando este usuário você terá acesso a página de documentação, mas ficará complicado testar por lá em função de precisarmos gerar 
  um access token para acessarmos os endpoints protegidos. Pensando nisto irei disponibilizar um link com uma collection do postman que conta com todos requests
  para geração do token, bem como os endpoints protegidos com seus jsons pré preenchidos.
 
 <h2>Geração de access Token e Endpoints</h2>
  
  - Para gerar um acess token, bem como um refresh token via postman, você primeiramente precisa gerar um authorization code, o qual será assim, utilizado para gerar
 o access token. Somente com o access token poderemos acessar os endpoints. Um tutorial detalhado com fotos se encontra aqui nesse link 
 <a href="https://docs.google.com/document/d/13z88Flto8DMpYwQdiGzoNBChQdfukP4ASnifBbnuKss/edit?usp=sharing">Geração Token</a>, por favor dê uma olhada!
 
  <h2>Links</h2>
  - <a href="http://localhost:8080/swagger-ui-/index.html">Documentação Swagger</a>
  - <a href="https://docs.google.com/document/d/13z88Flto8DMpYwQdiGzoNBChQdfukP4ASnifBbnuKss/edit?usp=sharing">Geração Token</a>
  - <a href="https://www.getpostman.com/collections/c0d201e6ac4bb45f4dbf">Collection Postman</a>
 
 <h2>Adendos</h2>
 
  Este projeto ainda está em desenvolvimento, algumas partes muito interessantes e necessárias ainda precisam ser feitas. Mas por hora, é um projeto totalmente
  testável.
 
</body>
</html>
