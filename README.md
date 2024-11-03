<div align="center">
  <h1> Crosoften Technical Challenge </h1>
</div>

## Ferramentas de compilação e versões usadas
- **Kotlin**: linguagem de programação usada para desenvolver o aplicativo | 2.0.0
- **Retrofit**: biblioteca de solicitação e manipulação APIRest | v.2.11.0 | com.squareup.retrofit2:retrofit
- **Gson Converter** : Biblioteca de extensão e implementação para conversão de dados JSON | v.2.11.0 | com.squareup.retrofit2:converter-gson
- **Serializable** : Biblioteca de seriaização de componentes | 'org.jetbrains.kotlin.plugin.serialization:2.0.20
- **Jetpack Compose** : Jetpack Compose é o kit de ferramentas moderno recomendado pelo Android para criar UI nativa
- **MVVM** : Model-view-viewmodel é um padrão de arquitetura em software de computador que facilita a separação do desenvolvimento de uma interface gráfica de usuário
- **UI State** : Padrão de regra de negócios para manipular estados de tela

## Gerenciamento de Sessão
Para organizar o fluxo de navegação e o gerenciamento de sessões, foi criado o objeto PreferenceHelper. Esse objeto é responsável por armazenar e manter o token de autenticação obtido através da chamada à API, garantindo que o usuário permaneça em uma sessão ativa após o login.

Na MainActivity, foi implementado um listener para monitorar o estado de login. Quando o usuário realiza login, o token é registrado, e ele é redirecionado para a tela principal do aplicativo.

## Estrutura de Navegação
A navegação entre as telas é gerenciada por três principais **NavHosts**:

- **NavHost**: Controla a navegação entre as telas de autenticação, incluindo as telas de login e registro.
- **MainNavHost**: Responsável pela transição para a tela HomeScreen, que serve como o esqueleto principal da aplicação.
- **BottomBarNavigation**: Gerencia os fluxos principais de navegação através da BottomBar, permitindo que o usuário selecione opções e ative as telas correspondentes.

### Observações
O controle de navegação é baseado em contextos reutilizáveis, aplicados de forma consistente em todas as telas para garantir uma experiência de navegação fluida e coesa.

## Documentação do Sistema de Login e Cadastro

## Visão Geral
Para organizar e gerenciar o sistema de login e cadastro de usuários, foi implementado um serviço de hospedagem da API utilizando Retrofit. A estrutura do projeto segue o padrão de camadas, com uma hierarquia de pacotes que garante a separação de responsabilidades e facilita a manutenção do código.

## Estrutura do Projeto

O projeto é dividido em duas camadas principais:

- **Data**: Responsável por armazenar os componentes relacionados às requisições da API, incluindo repositórios, serviços, modelos de dados e factories para gerenciar os parâmetros do ViewModel.

- **Presentation**: Contém todos os elementos visuais do aplicativo, como telas, lógica de navegação, ViewModels e gerenciadores de estados de UI (UiStates), garantindo o controle adequado do estado das telas.

## Hierarquia de pacotes:

```plaintext
.
└── App/
    ├── data/
    │   ├── factories           # Factories para criação de objetos e configuração
    │   ├── models              # Modelos de dados utilizados no app
    │   ├── repository          # Repositórios para gerenciamento de dados
    │   └── util/
    │       └── helpers         # Funções auxiliares e utilitárias
    └── presentation/
        └── ui/
            ├── components/     # Componentes de UI reutilizáveis
            │   └── items       # Itens específicos dentro dos componentes
            ├── navigation      # Lógica de navegação entre telas
            ├── state           # Gerenciamento de estados de UI
            ├── theme           # Definição do tema e estilos
            └── view/
                └── auth
                    ├── login    # Tela de login
                    └── register # Tela de cadastro
                ├── bookregister # Tela de registro de livros
                ├── details     # Tela de detalhes de itens
                ├── feed        # Tela principal de feed
                └── profile     # Tela de perfil do usuário
```
## Login
Para a execução do login, foi criado um repositório chamado UserRegisterRepository, responsável por fornecer os dados necessários para as chamadas da API de registro e login de usuários. A lógica da tela e o gerenciamento de dados foram separados utilizando um ViewModel e um Composable.
### Componentes Principais
- **LoginScreen**: Constrói o formulário de login do usuário, incluindo campos de entrada e botões de ação.
- **LoginUserState**: Classe modelo responsável por gerenciar as variáveis e funções de atualização dos campos de entrada.
- **LoginViewModel**: Gerencia o estado atual dos dados do usuário, realiza a atualização dos campos, faz a chamada da API, e verifica a autenticidade dos dados fornecidos.

### Responsabilidades do ViewModel
O **LoginViewModel** é encarregado de:

- Armazenar e retornar os dados da API para a LoginScreen.
- Verificar a integridade dos campos, identificando campos vazios e tratando erros de autenticação ou sucessos.
- Gerenciar e retornar os estados para a interface de usuário (UI), permitindo que a UI se ajuste conforme o resultado das ações (erro ou sucesso).
- Essa estrutura permite uma separação clara de responsabilidades, simplificando a manutenção e garantindo uma experiência de usuário fluida e intuitiva.

## Cadastrar Usuário

Assim como a lógica de login, a tela de cadastro de usuário é dividida em três componentes principais: **RegisterUserState**, **RegisterScreen** e **RegisterViewModel**.

## Componentes Principais
- **RegisterUserState**: Classe modelo que gerencia as variáveis e funções necessárias para atualizar o estado dos campos de cadastro.
- **RegisterScreen**: Responsável por construir a interface do formulário de cadastro, permitindo que o usuário insira seus dados.
- **RegisterViewModel**: Gerencia o estado dos dados do usuário durante o processo de registro, lidando com a lógica de validação e chamadas de API.

## Verificação de Senhas

Uma diferença significativa na tela de cadastro é a implementação da verificação de igualdade das senhas do usuário. Para isso, abstraímos um método chamado **passwordConfirm**, que retorna um estado booleano indicando se as senhas inseridas correspondem.

Essa abordagem não apenas melhora a experiência do usuário, evitando erros comuns durante o registro, mas também assegura que os dados sejam válidos antes de serem enviados à API.

## Registrando e Enviando Livros

Para realizar o upload de livros, é necessário abrir uma **Inten**t que permita a recuperação da URL de uma imagem. Esta URL será utilizada posteriormente para formatar a imagem, garantindo que ela esteja no formato PNG válido para envio à API.

O componente **UploadForm()** é responsável por gerenciar toda a lógica de transferência das imagens para o **BookRegisterViewModel**. Este ViewModel realizará a validação dos elementos a serem enviados, assegurando que todas as informações estejam corretas antes do envio à API.

## Componentes Principais

- **RegisterBookUIState**: Gerencia os valores das variaveis e atualização dos campos de atualização.
- **BookRegisterScreen**: Responsável por construir a interface do formulário de cadastro, permitindo que o usuário insira seus dados.
- **BookRegisterViewModel**: Classe modelo gerenciando o envio dos livros e convertendo o file de imagens para formato compátivel a API no modelo Mutltipart.Body.
- **UploadForm**: Componente para construir o formulário e instanciar o ViewModel.

## Recuperando Livros

A recuperação dos livros é realizada por meio da tela **FeedScreen()**, que é exibida no início da aplicação após o login. Esta tela permite que o usuário visualize os livros registrados e remova itens, conforme necessário.

O ViewModel da **FeedScreen()** desempenha um papel crucial na validação dos estados da interface, incluindo **isLoading()**, **Error()**, e **Success()**. Esses estados são utilizados para indicar se os livros estão disponíveis, se não há livros cadastrados e se ocorre alguma exceção, como a falta de conexão com a internet. Essa abordagem garante que a aplicação não sofra crashes em situações de erro, proporcionando uma experiência mais robusta para o usuário,

### Responsabilidades do ViewModel
O **FeedViewModel** é encarregado de:

- Atualizar o estado da FeedScreenUIState.
- Fazer a chamada da API para recuperar os livros cadastrados do usuário.
- Certificar que o usuário esteje conectado a rede para não ocorrer erros de crash ao reabrir a aplicação posteriormente.
- Gerencia a chamada do diretório de exclusão de livros da API via ID.

## Componentes Principais

- **FeedScreenUIState**: Gerencia os valores das variaveis e atualização dos campos de atualização.
- **FeedScreen**: Responsável por construir a interface de recuperação dos livros.
- **FeedViewModel**: Classe modelo gerenciando a requisição de livros e atualização de UI State.

## Pesquisando Livros

Para a recuperação dos livros com base em uma consulta do usuário, foi criada uma tela no pacote details, chamada **SearchScreen()**, juntamente com o **SearchViewModel()**. A lógica de funcionamento é similar, com o **SearchViewModel()** recebendo valores da **SearchUIState()**, que é atualizada pela tela à medida que o usuário insere informações.

Na tela, há um campo de pesquisa implementado com o componente **OutlinedTextField()**, que recebe a descrição fornecida pelo usuário. Esse valor é utilizado para atualizar o estado no **ViewModel**, permitindo, assim, a recuperação dos livros que correspondem à consulta realizada.

## Componentes Principais

- **SearchUIState**: Classe modelo para gerenciar o estado do input da query de pesquisa.
- **SearchScreen**: Responsável por construir a interface de recuperação dos livros.
- **SearchViewModel**: Responsável por realizar a chamada API após recuperar o estado atual do SearchUIState.

## Gereciando Persistência e Sessão
Para gerenciar o controle de sessão, o **SharedPreferences** será acessado na **MainActivity**, onde o último token será recuperado. Isso permite que a última sessão do usuário permaneça ativa, eliminando a necessidade de um novo login. É importante destacar que o usuário terá a opção de encerrar a sessão na **ProfileScreen()**.
___

<h1 align="center">
   Developed by Rhyan Araujo Chaves @2024
</h1>

