# backend-empresa
sistema back-end do projeto integrador senac sc

back-end empresa - gerenciar colaboradores:

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

