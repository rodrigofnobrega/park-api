Boas Práticas

#### **API REST - Boas Práticas**

Quando desenvolvemos uma API REST é considerado seguir aquilo que está  classificado como boas práticas. Por exemplo, responder uma solicitação  com o HTTP Status Code apropriado, seja para sucesso ou para erro, é uma boa prática. Outra boa prática muito importante, mas que muitas vezes  não é seguida por desenvolvedores de APIs, é usar corretamente o nome  dos recursos.

Como já foi citado anteriormente, um recurso não  deve ser um verbo e sim um substantivo. O verbo, ou seja, a ação, será  identificado pelo HTTP METHOD (GET, POST, PUT, …). Entretanto, o nome do recurso é a primeira parte do caminho de acesso a esse recurso e ele  deve ser sempre um substantivo no plural.

Algumas regras sobre como nomear corretamente seus recursos:

1. O nome do recurso deve ser um substantivo
2. O nome do recurso deve estar no plural
3. O nome do recurso deve estar por inteiro em letras minúsculas
4. Em caso de nome composto, os nomes devem ser separados por hífen
5. Evite usar `/` ao final da URI

Veja alguns exemplos:

```
GET http://example.com/clientes/1
```

O recurso acima busca por um cliente com identificador igual a **1**. Observe que temos um substantivo como nome do recurso, `clientes`, e não um verbo `buscarClientes` ou `getClientes`. O recurso faz alusão a uma coleção de registros, por isso, deve estar no plural.
É possível também ter um sub recurso, às vezes chamado de recurso aninhado, associado a outro recurso.

```
GET http://example.com/clientes/15/pedidos
```

Observe que o recurso `/clientes/15/` tem um sub recurso chamado `pedidos`. Assim, todos os pedidos, ligados ao cliente de identificador igual a **15**, seriam o alvo da solicitação.

Ao montar a URI de um recurso é sempre válido considerar o uso dos  caminhos. Os caminhos, ou PATH em inglês, são as partes da URI que  identifica qual recurso deve ser acessado. Por exemplo, `/clientes/1` tem dois caminhos. O primeiro, mais a esquerda, é a raiz do recurso, o que indica que **clientes** será acessado. O segundo caminho está depois da segunda barra, o qual  indica a intenção de recuperar exclusivamente o recurso identificado  como **1**.

Já no caso de `/clientes/15/pedidos` temos três caminhos, onde o primeiro aponta para o recurso **clientes**, o segundo para o cliente alvo (15) e o terceiro para o sub recurso **pedidos** do cliente alvo. Seria possível ainda ter um quarto caminho, indicando qual o pedido deveria ser recuperado, por exemplo: `/clientes/15/pedidos/2`

Caso existem recursos que indiquem pedidos concluídos, poderíamos nomeá-lo da seguinte 

forma `pedidos-concluidos`:

```
GET http://example.com/clientes/15/pedidos-concluidos
```

Dessa forma, a solicitação buscaria todos os sub recursos do cliente 15 que estão concluídos.

Considere uma API REST com os seguintes recursos:

```
POST http://example.com/clientesGET http://example.com/clientes
```

A primeira vista podemos interpretar como algo errado. Isso porque, os  dois recursos possuem a mesma URI. Como bem sabemos, uma URI deve ser  única, já que aponta para um recurso específico. Entretanto, quando  falamos de REST, não é somente a URI que será considerada, mas também o  verbo HTTP.

Como as URIs apresentadas têm verbos diferentes, elas  se tornam distintas, assim, quando o cliente enviar uma solicitação com o verbo POST a API saberá qual recurso o cliente está solicitando, sem  fazer qualquer tipo de confusão. Por isso, é importante sempre definir  corretamente os verbos HTTP de suas APIs.

#### **Path e Query Params**

Suponha que um recurso de usuários possui os campos de armazenamento para id,  nome, data de nascimento e cpf. Ao criar um recurso para localizar por  id temos duas possibilidades aceitáveis:

```
GET http://example.com/usuarios/1GET http://example.com/usuarios/id/1
```

Entretanto, especificamente para um campo do tipo id, não é costumeiro usar a path  id. Sendo assim, embora aceitável, é considerada uma boa prática não  usá-lo.
Agora, suponha que a ideia seja recuperar o usuário pelo CPF. Neste caso, será necessário indicar qual o campo fará parte da busca:

```
GET http://example.com/usuarios/cpf/23735034098
```

Esse tipo de operação faz uso do que chamamos de PATH PARAMETERS, onde  informamos os parâmetros via caminhos da URI. Entretanto, podemos fazer  uso de QUERY PARAMETERS:

```
GET http://example.com/usuarios?cpf=23735034098
```

As duas formas são consideradas uma boa prática. Porém, existem alguns  especialistas que fazem uma distinção no uso de Path ou Query  Parameters. É sugerido que, apenas os campos de valores únicos e que  não mudem com o tempo, sejam usados com Path Params. Os campos que, não são considerados únicos ou que mudem com o tempo, sejam selecionados  com Query Params. Seguindo esse princípio, o campo de id será sempre  único e imutável, é provável que o campo de CPF também seja único. Já os campos para nome e data de nascimento poderiam se repetir na tabela.  Assim, esses dois últimos deveriam ser usados apenas com Query Params:

```
GET http://example.com/usuarios?nome=beltranoGET http://example.com/usuarios?dtNascimento=2000-10-05
```

Dessa forma, você só está usando path params ao especificar qual recurso  buscar, mas isso não classifica os recursos de forma alguma. Quem  classifica os recursos são os atributos que se repetem ou podem ser  modificados no futuro. Por exemplo, um recurso carros possui os  atributos id, marca, modelo, chassi e cor.

Quais desses atributos  são únicos e imutáveis com o tempo? Apenas o id e o chassi. Você pode  estar se perguntando, mas a marca de um carro não muda. Sim, não muda,  você está certo. Mas em uma tabela teríamos inúmeros carros da mesma  marca, ou seja, não seria um recurso único. Assim, a marca, o modelo e a cor são considerados atributos de classificação/filtro.

```
GET http://example.com/carros?marca=honda
```

A solicitação acima está filtrando a busca dos carros da marca honda.

```
GET http://example.com/carros?cor=verde
```

A solicitação acima está filtrando a busca dos carros de cor verde. A cor é um atributo que além de se repetir pode ser mutável. Ou seja, um  carro que hoje é verde, amanhã pode ter sua cor substituída por azul.

```
GET http://example.com/carros/chassi/100990058
```

A solicitação acima está especificando que deseja o recurso de chassi 100990058.

#### **Paginação de Recursos**

Ainda sobre Query Params, existe uma boa prática que deve ser sempre seguida. Ela se refere a paginação de recursos.
Imagine que tenha um recurso de clientes com 1000 registros ou mais. Uma  solicitação do tipo GET será realizada para recuperá-los. Como bem  sabemos, não é uma boa pratica trazer uma quantidade tão grande de  registros em uma única solicitação, então, o aconselhado é usar a  paginação de resultados.

Uma paginação contém algumas informações  básicas como o número da página, a quantidade máxima de registros  retornados e por vezes a ordenação por algum campo específico.

Quando falamos sobre paginação em API REST, os parâmetros usados na paginação  devem sempre ser enviados na solicitação como Query Params. Normalmente  os nomes dos parâmetros são `page` para o número da página, `size` ou `limit` para a quantidade máxima de registros a serem retornados e `sort` ou `order` para a ordenação. Mas, eles podem ser encontrados com outros nomes ou abreviações em alguns APIs. Veja um breve exemplo:

```
GET http://example.com/clientes?page=0&size=10&sort=nome,asc
```

A solicitação acima está ordenando os clientes pelo nome de forma  ascendente (a-z), iniciando o retorno pela página 0 e limitando a  quantidade de registros por página em 10.

#### **HTTP HEADERS (Cabeçalhos)**

Os cabeçalhos HTTP são usados para fornecer informações adicionais sobre  uma solicitação ou resposta na arquitetura REST. Existem tanto os  cabeçalhos enviados pelo cliente quanto aqueles enviados na resposta  pela API.
Aqui estão alguns cabeçalhos comuns usados nas APIs REST e suas explicações:

**Content-Type**
O cabeçalho Content-Type pode ser usado para especificar o tipo de mídia  dos dados que estão sendo enviados na solicitação ou resposta. Isso  permite que o cliente e o servidor concordem sobre o formato dos dados  que estão sendo trocados. Por padrão, quem define o tipo de dados é a  API e o cliente deve enviar solicitações baseadas nesse tipo. Algumas  vezes, uma API pode aceitar mais de um tipo de formato de dados, por  exemplo, JSON e XML. Nesse caso, a aplicação cliente deve informar no  cabeçalho qual tipo está enviando.

Alguns exemplos:

```
Content-Type: application/json Content-Type: application/x-www-form-urlencodedContent-Type: text/html; charset=utf-8Content-Type: multipart/form-dataContent-Type: text/plain
```

**Accept**
Este cabeçalho é usado para especificar os tipos de mídia que o cliente pode aceitar na resposta. Se o servidor não pode fornecer uma resposta em um dos especificados tipos de mídia, ele retornará um erro. Como dito,  quem vai especificar os tipos de dados sempre será a API. O cliente  deverá ser desenvolvido com base nos tipos de dados oferecidos pela API.

Alguns exemplos:

```
Accept: */* (Padrão, aceita o tipo da API) Accept: text/html Accept: image/* Accept: text/html, application/xhtml+xml, application/xml;q=0.9, */*;q=0.8 
```

**Location**
Este cabeçalho é usado em uma resposta 201 (Create) para indicar a URL do  recurso recém-criado. Ou seja, quando um novo recurso é criado, o  cabeçalho Location vai ser enviado na resposta com o endereço completo  de acesso ao recurso. 

Por exemplo, se um cliente for criado e um id número 10 for gerado, o Location será:

```
Location: GET http://example.com/clientes/10Location: http://example.com/clientes/10
```

**Authorization**
O cabeçalho de autorização é usado pelo cliente para fornecer informações de autenticação, como um token de acesso, para o servidor. Isso permite que o servidor verifique que o cliente está autorizado a acessar o  pedido de recurso.

O formato esperado segue as instruções:

```
Authorization: <auth-scheme> <authorization-parameters>
```

Basic Authentication:

```
Authorization: Basic <credentials>
```

Bearer Authentication:

```
Authorization: Bearer <token>
```

**WWW-Authenticate**
A API, quando receber uma solicitação a um recurso que necessite de  autenticação e o cliente não estiver autenticado, deve responder com o  cabeçalho WWW-Authenticate. Essa resposta deve incluir pelo menos um  desafio, para indicar quais esquemas de autenticação podem ser usados  para acessar o recurso (e quaisquer dados adicionais necessários para  cada esquema específico).

```
WWW-Authenticate: "Bearer realm='/api/v1/login'"
```

O desafio indica que a próxima requisição, para o mesmo recurso, deve  conter uma autenticação do tipo Bearer, que deve ser realizada usando a  URI `/api/v1/login`.

Estes são alguns dos cabeçalhos  principais entre cliente e servidor quando se trata de APIs REST. Outros cabeçalhos podem vir a ser usados conforme o desejo dos desenvolvedores da API.
Para saber mais sobre cabeçalhos HTTP acesse - [HTTP headers](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers)