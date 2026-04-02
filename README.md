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


