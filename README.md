# backend-empresa

Sistema back-end do projeto integrador Senac SC. Aplicação finalizada.

## Descrição
API para gerenciar colaboradores e departamentos, com autenticação de usuários.

## Funcionalidades implementadas

- Entidades:
    - `UsuarioDto`, `UsuarioLoginDto`
    - `Departamento`
    - `Colaborador` (com relação a `Departamento`)

- Autenticação e usuários:
    - POST `/usuarios/cadastrar` - email único, senha criptografada
    - POST `/usuarios/login` - validação de credenciais

- Colaboradores:
    - POST `/colaboradores` - cadastrar colaborador com departamento
    - GET `/colaboradores?nome=...` - buscar colaborador por nome
    - PUT `/colaboradores/{id}` - editar colaborador (incluindo departamento)
    - GET `/colaboradores` - listar todos (se implementado)

- Departamentos:
    - POST `/departamentos` - cadastrar departamento
    - GET `/departamentos` - listar departamentos

## Status
- Sistema concluído e em produção/testes finais.
- Relacionamento entre colaborador e departamento finalizado.

## Observações
- Banco de dados configurado e migrado.
- Validações de entrada e tratamento de erros implementados.
- Segurança com senhas criptografadas e login com token (JWT).

Funções Concluídas: 
Crud colaborador:
- PUT editar colaborador
- GET buscar colaborador pelo nome
- GET listar colaboradores
- POST cadastrar colaborador
- DELETE deletarColaborador

Crud usuario:
- PUT editar usuario
- POST login usuário
- POST cadastrar usuário com validação de email único e salvar com senha criptografada
- GET listar usuarios
- DELETE deletar usuario

- listar departamentos que estão no bd
  
Entidades:
- Colaborador
- Departamento
- Usuario

Repositórios: 
- ColaboradorRepository
- DepartamentoRepository
- UsuarioRepository
  
Controller:
- ColaboradorController
- DepartamentoController
- UsuarioController

DTO:
- ColaboradorDto
- ColaboradorLoginDto
- UsuarioDto
- UsuarioLoginDto

Funções em andamento:

- senha criptografada para usuario, colaborador e profissional
- cadastrar colaborador com validações
- fazer login colaboardor com colaboradorLoginDto
- entidade checkIn e conectar com colaborador
- crud profissional

