 CRUD Android com Jetpack Compose + Firebase
 Sobre o projeto

Este projeto é um sistema CRUD (Create, Read, Update, Delete) desenvolvido em Kotlin utilizando Jetpack Compose e integrado ao Firebase Firestore.

O objetivo é praticar desenvolvimento Android moderno com arquitetura organizada em Model, ViewModel, Repository e Screens, simulando um sistema real de gestão.

Link video Projeto:
https://youtu.be/Mb4KjgVpeNY

  Funcionalidades
  
✔ Cadastro de Clientes
✔ Cadastro de Produtos
✔ Cadastro de Agenda
✔ Listagem em tempo real
✔ Exclusão de registros
✔ Atualização de dados (estrutura pronta)
✔ Validação de campos
✔ Integração com Firebase Firestore

🏗️ Arquitetura do projeto

O projeto segue uma estrutura organizada:

model → Entidades (Cliente, Produto, Agenda, etc)
viewModel → Regras de negócio e estado da UI
repository → Comunicação com Firebase
uix.Screens → Telas em Jetpack Compose
navigation → Controle de rotas

🛠️ Tecnologias utilizadas
Kotlin
Jetpack Compose
Firebase Firestore
Android Studio
ViewModel
State Management (Compose State)
📱 Telas do sistema
🏠 Home

Menu principal de navegação

👤 Cliente
Cadastro de clientes
Listagem
Exclusão
📦 Produto
Cadastro de produtos
Controle de estoque
Listagem
📅 Agenda
Cadastro de tarefas/compromissos
Status de conclusão
Listagem
🔥 Arquitetura Firebase

O banco de dados utiliza:

Firestore Database
├── Cliente
├── Produto
└── Agenda

Cada coleção possui documentos com ID automático gerado pelo Firebase.

📂 Estrutura do projeto
com.example.crudp1
│
├── model
├── repository
├── viewModel
├── navigation
├── uix.Screens
└── ui.theme
⚙️ Como executar o projeto
Clone o repositório:
git clone https://github.com/SEU_USUARIO/SEU_REPO.git
Abra no Android Studio
Configure o Firebase:
Adicione o google-services.json
Ative Firestore Database
Execute o projeto em um emulador ou celular
📌 Aprendizados

Durante o desenvolvimento deste projeto foi possível praticar:

Arquitetura MVVM
Integração com Firebase
Jetpack Compose
CRUD completo em Android
Manipulação de estado
Navegação entre telas
👨‍💻 Autor

Desenvolvido por Gabryel Rodrigues

⭐ Melhorias futuras
🔐 Sistema de login (Firebase Auth)
✏️ Edição de registros (Update completo)
🔍 Busca de dados
🎨 Melhorias de UI/UX
📊 Dashboard com gráficos
