Introdução a Arquitetura REST

#### **Introdução a Arquitetura REST**

Representational State Transfer, ou simplesmente REST, é um estilo de arquitetura para  sistemas distribuídos com base em princípios da web, como HTTP, URI e  formato de dados como JSON ou XML, para permitir a comunicação entre  sistemas distintos de forma simples, escalável e padronizada. Essa  arquitetura é amplamente utilizada para a criação de APIs (Application  Programming Interface) e serviços web, permitindo que diferentes  aplicativos se comuniquem e compartilhem recursos entre si. O REST  enfatiza a interoperabilidade entre diferentes sistemas e a separação de preocupações entre servidor e cliente, tornando os sistemas mais  flexíveis, fáceis de manter e evoluir.

A arquitetura REST foi  criada pelo cientista da computação Roy Fielding durante seu doutorado  pela Universidade da Califórnia. Diferentemente de web services SOA  (Simple Object Access Protocol), que é um protocolo, REST não é um  protocolo e sim uma arquitetura que faz uso do protocolo HTTP como  solução para requisições e respostas. É importante destacar que API REST não é a única nomenclatura aceita, embora seja a mais utilizada,  podemos ver referências citando API RESTful ou Web Service REST.

#### **O que é API REST?**

Uma API REST é um tipo de API baseado na arquitetura REST que permite a  comunicação entre diferentes sistemas de softwares. Ela define um  conjunto de operações (métodos HTTP) que podem ser usadas para criar,  ler, atualizar e excluir recursos (dados) em um servidor web. Uma API  REST usa URIs (Identificadores Uniformes de Recursos) para identificar  os recursos e o formato JSON ou XML para representar dados transferidos  entre os sistemas. Com uma API REST, diferentes aplicativos podem se  comunicar e compartilhar recursos entre si de forma padronizada e  escalável. Isso permite a criação de soluções de softwares mais  flexíveis e integráveis.

– **Clientes**
Quando  você acessa uma API como a do Youtube ou Google Maps, o acesso será  realizado por meio de uma aplicação cliente. Essa aplicação pode ser o  navegador web do seu laptop, um aplicativo em seu smartphone, tablet ou  até mesmo smart tv. As APIs REST podem ser classificadas em três tipos  com base em seu nível de acesso: privadas, públicas e híbridas.

- **API REST privada:** é uma API que só pode ser acessada por aplicativos e sistemas  específicos, geralmente dentro da mesma organização ou rede. Isso  garante que somente aplicativos autorizados possam usar a API e acessar  os recursos protegidos.
- **API REST pública:** é uma API que pode ser acessada por qualquer aplicativo por meio da  internet. Essas APIs são frequentemente usadas por desenvolvedores para  integrar seus aplicativos com serviços populares de terceiros, como  Google Maps, Facebook, Twitter, etc.
- **API REST híbrida:** é uma combinação de API pública e privada, onde a API pode ser acessada por qualquer aplicativo, mas alguns recursos e funcionalidades são  protegidos e só podem ser acessados por aplicativos autorizados.

A escolha de qual tipo de API REST usar depende do nível de segurança  necessário e dos recursos que se deseja disponibilizar para os  aplicativos clientes. As APIs privadas são geralmente usadas para  proteger dados sensíveis e garantir a segurança dos aplicativos  internos. As APIs públicas são usadas para disponibilizar serviços para o público em geral e para promover a integração de aplicativos de  terceiros. As APIs híbridas são usadas quando é necessário equilibrar a  segurança dos dados com a acessibilidade pública dos recursos.

Uma aplicação cliente poderá ser desenvolvida em qualquer linguagem de  programação que faça uso do protocolo HTTP. Essa é uma vantagem para os  desenvolvedores de aplicações clientes, já que não ficam obrigados a  utilizar uma linguagem de programação que não estão habituados ou mesmo  que não tenham o domínio necessário.

– **O que são Recursos**
Os recursos são os registros que as APIs REST fornecem a seus clientes.  Eles podem ser de diversos tipos como textos, imagens, vídeos, etc. Os  recursos são representados por URIs (Uniform Resource Identifiers), que  são endereços únicos que identificam o recurso a ser acessado. Cada  recurso pode ser acessado e manuseado usando um conjunto de métodos HTTP (como GET, POST, PUT, DELETE) que são definidas pela API. Os recursos  são a base da arquitetura REST e permitem que os clientes manipulem os  dados da API de forma padronizada e intuitiva.

– **Documentação**
A documentação de uma API REST é formada por um conjunto de informações  que descrevem a funcionalidade, os recursos e os endpoints disponíveis  na API. Ela é destinada aos desenvolvedores que desejam consumir a API  por meio de aplicações clientes e fornece detalhes sobre como acessar e  usar seus recursos.
A documentação da API REST também pode incluir  exemplos de código que mostram como usar a API em diferentes linguagens  de programação e plataformas, bem como informações de autenticação e  segurança, limites de taxa de solicitação e outras informações  relevantes. Uma boa documentação da API REST é importante para garantir  que os desenvolvedores de aplicações cliente possam integrar facilmente a API em seus aplicativos, entender como usar seus recursos e resolver  problemas quando eles surgem.

#### **Benefícios de APIs REST**

O modelo arquitetural REST se tornou preferível em relação ao SOAP com o  passar dos anos. Isso ocorreu por várias razões, algumas delas citadas a seguir:

- **Escalabilidade:** As APIs REST são altamente escaláveis e podem lidar com grandes volumes de solicitações  sem comprometer o desempenho ou a disponibilidade. Isso é possível  devido ao fato da arquitetura REST ser Stateless, ou seja, não é mantido um estado ou contexto entre a aplicação cliente e o servidor.
- **Flexibilidade:** As APIs REST permitem que os desenvolvedores criem soluções de software flexíveis e modulares, que podem ser facilmente adaptadas para atender  às necessidades em constante mudança dos usuários.
- **Padronização:** As APIs REST são baseadas em padrões abertos e amplamente adotados,  como HTTP, URIs e JSON ou XML, o que torna mais fácil para os  desenvolvedores integrar diferentes sistemas de software.
- **Reutilização:** As APIs REST permitem que os recursos e serviços sejam compartilhados e reutilizados em diferentes aplicativos, o que pode economizar tempo e  recursos de desenvolvimento.
- **Integração:** As APIs REST permitem a integração fácil de diferentes aplicativos e  sistemas de software, permitindo que as organizações compartilhem dados e informações entre si.
- **Acessibilidade:**  As APIs REST podem ser acessadas por diferentes plataformas e  dispositivos, incluindo navegadores da web, aplicativos móveis e  dispositivos IoT, o que amplia o alcance da solução de software.
- **Segurança:** As APIs REST oferecem recursos de segurança, como autenticação e  autorização, para garantir que apenas usuários autorizados possam  acessar os recursos protegidos.

Esses benefícios tornam  as APIs REST uma solução popular e eficiente para a construção de  aplicativos e sistemas de software modernos e escaláveis.

#### **Segurança em APIs REST**

Existem diferentes tipos de segurança que podem vir a ser utilizados em APIs  REST e assim, proteger os dados e garantir a integridade das  solicitações e respostas. Alguns dos tipos de segurança mais aplicados  em API REST são:

- **Autenticação:** verificar a identidade do usuário que está solicitando acesso à API. Os métodos de  autenticação incluem senhas, tokens de acesso, certificados digitais e  autenticação de dois fatores.
- **Autorização:** verificar se o usuário tem permissão para acessar o recurso solicitado  na API. Isso é geralmente feito com base em regras de acesso  predefinidas, como as funções do usuário.
- **Criptografia:** protege os dados em trânsito, tornando-os inelegíveis para qualquer  pessoa que possa interpretá-los. A criptografia pode ser aplicada a  diferentes níveis, como TLS/SSL no nível de transporte ou criptografia  ponta a ponta para dados sensíveis.
- **Monitoramento de atividades:** rastreia as atividades do usuário na API para detectar comportamentos  suspeitos e garantir a conformidade com as políticas de segurança. Isso  pode incluir registros de auditoria, monitoramento de eventos e alertas  de segurança.
- **Gerenciamento de tokens:**  protege os tokens de acesso usados na autenticação, garantindo que  apenas usuários autorizados possam acessar os recursos da API. Isso pode incluir a validação de tokens, a expiração de tokens e a revogação de  tokens em caso de comprometimento de segurança.

Os  diferentes tipos de segurança são de grande valia para garantir que as  APIs REST se mantenham seguras e protejam os dados confidenciais dos  usuários e das organizações.