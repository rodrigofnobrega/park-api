API REST - Fundamentos

#### **API REST - Fundamentos**

A arquitetura REST trabalha sobre o protocolo HTTP e cada URI funciona  como um recurso da API. Portanto, devemos usar substantivos para  determinar recursos em vez de verbos.

> URI - Uniform Resource Identifier, ou Identificador Uniforme de Recursos
> Exemplo: `https://mballem.com/cursos/`
> A URI une o Protocolo (https://);
> A localização do recurso (URL - [mballem.com](http://mballem.com));
> E o nome do recurso (URN - /cursos/)

Endpoints estilo RPC usam verbos, por exemplo, `api/v1/getPersons`. Em contrapartida, em REST, esse endpoint deve ser escrito como `api/v1/persons`. Então, você deve estar se perguntando como poderemos diferenciar as  ações executadas em um recurso REST? Pois bem, é agora que faremos uso  dos chamados Métodos HTTP (HTTP METHOD). A ideia é fazer com que os  métodos HTTP trabalhem como um verbo, por exemplo, GET (recuperar),  DELETE (remover), POST (criar), PUT (modificar) e PATCH (atualização  parcial), entre outros.

> Endpoint
> O endpoint REST é um URI exclusivo que representa um recurso REST.
> Por exemplo, `http://app/api/v1/persons` é um endpoint REST. Além disso, `/api/v1/persons` é o caminho do endpoint e as pessoas são recurso REST.

#### **Métodos HTTP**

O protocolo HTTP define um conjunto de métodos ou verbos de requisição  responsáveis por indicar a ação a ser executada para um dado recurso. Em APIs REST alguns desses verbos são mais comuns que outros de serem  utilizados. Isso acontece não por preferência, mas por conta dos tipos  de ações sobre os recursos. Por exemplo, o método GET é utilizado para  operações que recuperam um recurso e provavelmente uma API REST terá  muito mais ações do tipo GET do que do tipo DELETE (usado para exclusão  de recursos).

Um conceito muito falado quando começamos a estudar  REST é quais métodos são ou não são idempotentes. Na verdade, esse não  seria um conceito específico da arquitetura REST, mas sim, do protocolo  HTTP. Porém, por algum motivo, sempre foi muito citado em estudos sobre  REST.
Um método HTTP idempotente é um método HTTP que pode ser  chamado várias vezes sem alterar os resultados. Não importa se o método é chamado apenas uma vez ou dezenas de vezes. O resultado deve ser o  mesmo. Importante destacar, idempotência se aplica ao resultado, não ao  recurso. Entretanto, esse assunto pode gerar controvérsias, assim como  vemos na postagem [*O que é ser idempotente em REST? O debate continua*](https://www.infoq.com/br/news/2013/05/idempotent/) do site Infoq.

#### GET

O GET é usado para recuperar um recurso do servidor. É uma operação  segura e idempotente, ou seja, várias solicitações para o mesmo recurso  retornarão o mesmo resultado e não modificará o estado do recurso no  servidor.

Alguns casos de uso do GET:

- Recuperar uma página da web
- Buscar a lista de postagens de um blog
- Download de arquivo de um servidor

#### POST

POST é usado para criar um novo recurso no servidor. Não é considerado um  método idempotente, o que significa que várias solicitações para criar o mesmo recurso resultará em vários recursos sendo criados. As  solicitações POST também podem modificar o estado do recurso no  servidor.

Alguns casos de uso do POST:

- Criação de uma nova conta de usuário
- Carregar uma nova imagem ou arquivo
- Enviar um formulário a um servidor para criar um novo recurso

#### PUT

PUT é usado para atualizar um recurso existente no servidor. É considerado  idempotente, o que significa que várias solicitações para atualizar o  mesmo recurso resultarão em o mesmo estado do recurso no servidor. O PUT também pode criar um novo recurso se o recurso que está sendo  atualizado não existe.

Casos de uso para PUT:

- Atualizar o conteúdo de uma postagem de blog
- Modificar os detalhes de uma conta de usuário
- Substituir um arquivo ou imagem em um servidor por um nova versão
- Atualizar todo o endereço de um contato

#### PATCH

O método PATCH é usado para fazer uma atualização parcial em um recurso  existente no servidor. O objetivo é realizar atualizações em campos ou  propriedades específicas de um recurso, sem exigir que todo o recurso  seja enviado ao servidor.

Casos em que o PATCH é usado:

- Atualizar o título ou descrição de uma postagem de blog
- Alterar a senha ou endereço de e-mail associado a uma conta de usuário
- Fazer uma atualização parcial de um arquivo ou imagem

#### DELETE

O verbo DELETE é usado para excluir um recurso do servidor. É considerado idempotente, o que significa que várias solicitações para excluir o  mesmo recurso resultarão no mesmo estado do servidor. Isso parece  estranho e gera muitas dúvidas. Mas na verdade, ao executar a primeira  solicitações de DELETE para o recurso, ele será excluído e a resposta  será 200 (OK) ou 204 (No Content). As próximas solicitações para o mesmo recurso (já excluído) retornarão 404 (não encontrado). Claramente, a  resposta é diferente da primeira solicitação, mas não há mudança de  estado para nenhum recurso do lado do servidor porque o recurso original já foi excluído.

Usar casos para DELETE incluem:

- Remover uma postagem de blog
- Excluir uma conta de usuário
- Remover um arquivo ou imagem

Podemos destacar que uma operação de exclusão pode ser física ou lógica. A  física é quando o recurso é realmente excluído da base de dados e a  lógica quando uma coluna na tabela do recurso indica que ele foi  excluído, mesmo ainda estando presente na tabela. A exclusão lógica é  muito usada para manter dados históricos.

#### **Demais métodos HTTP**

O protocolo HTTP tem mais alguns métodos além dos citados. Para algumas  APIs eles poderão vir a ser úteis, mas talvez para outras, nem tanto.  Portanto, nossa abordagem será sobre o GET, POST, PUT, PATCH e DELETE.  Caso queira conhecer outros métodos, como HEAD, OPTIONS, TRACE, etc,  acesse - [Métodos de requisição HTTP](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Methods).

#### **Status HTTP**

Toda vez que uma solicitação HTTP é enviada a uma API REST, algum tipo de  resposta será retornada de volta ao solicitante. Independentemente se a  solicitação foi aceita, gerou erro, se o recurso não foi encontrado ou  se teve sucesso na execução.
Essa resposta é chamada de HTTP Status Code e existem cinco categorias de Status Code no protocolo HTTP.

- Informational responses (100 – 199)
- Successful responses (200 – 299)
- Redirects (300 – 399)
- Client errors (400 – 499)
- Server errors (500 – 599)

Alguns dos mais comuns códigos em respostas REST

- **200 - OK** - Padrão de resposta para solicitações HTTP sucesso. A resposta real  dependerá do método de solicitação usado. Em uma solicitação GET, a  resposta conterá uma entidade que corresponde ao recurso solicitado. Em  uma solicitação POST a resposta conterá a descrição de uma entidade, ou  contendo o resultado da ação.
- **201 - Create** - O pedido foi cumprido e resultou em um novo recurso que está sendo  criado. Usado em solicitação POST e PUT quando criar um novo recurso.
- **204 - No Content** - O servidor processou a solicitação com sucesso, mas não é necessária  nenhuma resposta. Resposta comum na solicitação de um DELETE.
- **400 - Bad Request** - O pedido não pôde ser entregue devido à sintaxe incorreta.
- **401 - Unauthorized** - Basicamente usado quando a solicitação exige que o cliente esteja  autenticado, entretanto, o cliente ainda não realizou a autenticação. A  resposta deve incluir um cabeçalho do campo www-authenticate contendo um desafio aplicável ao recurso solicitado.
- **403 - Forbidden** - O pedido é reconhecido pelo servidor, mas este recusa-se a  executá-lo. Ao contrário da resposta “401”, aqui a autenticação não fará diferença e o pedido não deve ser requisitado novamente.
- **404 - Not Found** - O recurso solicitado não foi encontrado, mas pode ser disponibilizado novamente no futuro. As solicitações subsequentes pelo cliente são  permitidas.
- **405 - Method Not Allowed** -  Foi feita uma solicitação de um recurso usando um método de pedido que  não é compatível com esse recurso, por exemplo, usando GET em um  formulário, que exige que os dados sejam apresentados via POST, PUT; ou  usados em um recurso somente de leitura.
- **409 - Conflict** - Indica que a solicitação não pôde ser processada por causa do conflito no pedido, como um conflito de edição.
- **422 - Unprocessable Entity** - O pedido foi bem formatado, mas foi incapaz de ser seguido devido a erros de semântica.
- **500 - Internal Server Error** - Indica um erro do servidor ao processar a solicitação.
- **501 - Not Implemented** - O servidor ainda não suporta a funcionalidade ativada.
- **503 - Service Unavailable** - O servidor está em manutenção ou não consegue dar conta dos  processamentos de recursos devido à sobrecarga do sistema. Isto deve ser uma condição temporária.

É muito importante que todas  as respostas de solicitações possuam um Status Code definido para caso  da solicitação ter sido processada ou ter gerado algum tipo de erro. O  Status Code é a forma como a aplicação cliente vai saber tratar a  resposta da requisição. Por exemplo, se a resposta for 200 o cliente  poderá listar os dados recebidos, mas se for 403 poderá informar ao  usuário que ele não tem permissão de acesso àquele recurso.


Para saber mais saber mais sobre status code do HTTP acesse - [HTTP response status codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)