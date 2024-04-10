# SENAI Fullstack [Education]

## Resolução dos exercícios da Semana 11

### M1S11 | Ex 1 - Setup do Projeto Anotações

O projeto será um CRUD e terá 3 (três) entidades:

- Caderno: id, nome, id_usuario;
- Nota: id, title, content, id\_caderno, id\_usuario;
- Usuario -> essa será demonstrada em aula.

### M1S11 | Ex 2 - CRUD Caderno

Crie o CRUD para Caderno:

- Busca de todos os Cadernos;
- Busca de Caderno por id;
- Update de Caderno;
- Delete de Caderno.

### M1S11 | Ex 3 - CRUD Notas

Crie o CRUD para Nota:

- Busca de todos os Notas;
- Busca de Nota por id;
- Update de Nota;
- Delete de Nota.

### M1S11 | Ex 4 - Configuração Inicial de Segurança

Crie as classes de configuração de segurança. Essa classe deve permitir o acesso irrestrito a uma rota /test, que retorna um texto "TESTE".

### M1S11 | Ex 5 - JWT na Segurança

Alterar as configurações de segurança para usar JWT, onde teremos a geração e a validação de JWT.

### M1S11 | Ex 6 - Endpoint de Cadastro Usuário

Crie um endpoint de Cadastrar usuário, siga o exemplo de aula!

### M1S11 | Ex 7 - Endpoint de Login

Crie um endpoint de Login de usuário.

- Esse deve retornar um JWT.

### M1S11 | Ex 8 - Validação de Dono

Adicione uma validação, se o user que está acessando os enpoints CRUD é dono de um Caderno ou de uma Nota. Sendo assim, apenas pode consultar os próprios dados.