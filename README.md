# backend-empresa

Sistema Back-End Respire Bem do Projeto Integrador Jovem Programador Senac SC. Aplicação finalizada.

## Descrição
API REST em Spring Boot para gerenciar:
- Empresa
- Usuários e autenticação (JWT)
- Departamentos
- Colaboradores
- Profissionais e especialidades
- Check-ins e dashboard

## Entidades principais
- `Empresa`
- `Usuario` (com roles via `UserRole`)
- `Departamento`
- `Colaborador`
- `Especialidade`
- `Profissional`
- `CheckIn` (histórico de check-ins)

## DTOs
- `RegistroEmpresaDto`
- `LoginDto`
- `TokenResponse`
- `ColaboradorDto`
- `ProfissionalDto`
- `CheckInDto`
- `HistoricoCheckInDto`
- `DashboardDto`

## Autenticação e autorização
- `POST /auth/registrar/empresa` - cadastro inicial de empresa + usuário ADMIN
- `POST /auth/login` - login retorna `TokenResponse` com JWT

> Observação: endpoints protegidos exigem header `Authorization: Bearer <token>`.

## Endpoints da Empresa
- `GET /empresa/meus-dados` - retorna dados da empresa do usuário autenticado
- `PUT /empresa/atualizar-dados?nomeEmpresa={nome}&cnpjEmpresa={cnpj}` - atualiza nome/cnpj

## Endpoints de Usuário (role admin)
- `GET /usuario/listarUsuarios` - lista todos usuários
- `PUT /usuario/alterar-credenciais/{idUsuario}?novoEmail={email}&novaSenha={senha}` - atualiza credenciais
- `DELETE /usuario/deletar/{idUsuario}` - deleta usuário

## Endpoints de Departamento
- `GET /departamento/listarDepartamentos` - lista todos departamentos

## Endpoints de Especialidade
- `GET /especialidade/listarEspecialidades` - lista todas especialidades

## Endpoints de Colaborador
- `POST /colaborador/cadastrarColaborador` - cria colaborador a partir de `ColaboradorDto` (vincula à empresa autenticada)
- `GET /colaborador/listarColaboradores` - lista todos colaboradores
- `GET /colaborador/buscarNomeColaborador?nomeColaborador={nome}` - busca colaborador por nome (retorna 404/mesma resposta de não encontrado)
- `PUT /colaborador/editarColaborador/{idColaboradorAlterar}` - edita usando `ColaboradorDto`
- `DELETE /colaborador/deletar/{idColaborador}` - remove colaborador

## Endpoints de Profissional
- `POST /profissional/cadastrarProfissional` - cria profissional a partir de `ProfissionalDto` (vincula à empresa autenticada)
- `GET /profissional/listarProfissionais` - lista todos profissionais
- `GET /profissional/buscarNomeProfissional?nomeProfissional={nome}` - busca profissional por nome
- `PUT /profissional/editarProfissional/{idProfissionalAlterar}` - edita profissional
- `DELETE /profissional/deletar/{idProfissional}` - remove profissional

## Endpoints de Check-in
- `POST /checkin/fazerCheckIn` - registra check-in do colaborador usando `CheckInDto`
- `GET /checkin/historico` - obtém histórico de check-ins do usuário autenticado

## Endpoint de Dashboard
- `GET /dashboard/dados` - retorna dados agregados para gráficos/visões (via `DashboardDto`)

## Observações técnicas
- Spring Security + JWT (via `SecurityConfig` e `SecurityFilter`)
- Senha criptografada com BCrypt
- Repositórios JPA com `spring-boot-starter-data-jpa`
- Base de dados configurada em `application.properties`

## Execução
- `./mvnw spring-boot:run`
- Testes: `./mvnw test`

## Status
- Sistema concluído, com API REST e segurança implementadas
- Relacionamentos (empresa -> colaboradores/profissionais; colaborador -> departamento) concluídos


