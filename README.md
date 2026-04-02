# backend-empresa

Sistema Back-End Respire Bem do Projeto Integrador Jovem Programador Senac SC. Aplicação finalizada.

## Descrição
O sistema Respire Bem é uma API REST desenvolvida em Spring Boot com o objetivo de permitir que empresas acompanhem e gerenciem o bem-estar e a saúde mental de seus colaboradores de forma estruturada e segura.

Por meio da plataforma, a empresa pode cadastrar e administrar colaboradores, departamentos e profissionais especializados (como psicólogos), além de registrar check-ins periódicos dos colaboradores, que servem para monitorar seu estado emocional ao longo do tempo. Esses dados são organizados e disponibilizados em dashboards, facilitando a visualização de indicadores e apoiando a tomada de decisões voltadas à qualidade de vida no ambiente corporativo.

O sistema conta com autenticação via JWT, garantindo segurança no acesso, e oferece controle administrativo completo, incluindo gestão de usuários, atualização de dados da empresa e manutenção das informações relacionadas à equipe. Dessa forma, a solução integra gestão organizacional e cuidado com a saúde mental em uma única plataforma.

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

## Status
- Sistema concluído, com API REST e segurança implementadas
- Relacionamentos (empresa -> colaboradores/profissionais; colaborador -> departamento) concluídos


