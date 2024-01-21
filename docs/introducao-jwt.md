# Introdução a JWT

## Stateful vs Stateless

As diferenças entre os termos “stateless” e “stateful” estão relacionadas à maneira como os sistemas ou protocolos lidam com o armazenamento e o  gerenciamento de informações de estado.

### **Stateful** (Com estado):
Em um sistema stateful, o servidor mantém informações de estado do cliente e usa essas informações para processar solicitações subsequentes. O  estado do cliente é armazenado no servidor e é usado para acompanhar a  interação contínua com o cliente ao longo do tempo. O servidor pode  armazenar informações em uma sessão, como dados de autenticação,  preferências do usuário, histórico de transações etc. Essas informações  são usadas para personalizar a experiência do cliente e processar as  solicitações de forma contextual. Por exemplo, um aplicativo de carrinho de compras online mantém um estado de sessão para cada cliente,  rastreando os itens no carrinho, o histórico de compras etc.

### **Stateless** (Sem estado):
Em um sistema stateless, não há armazenamento de informações de estado do  cliente no servidor. Cada solicitação é tratada de forma independente e  isolada, sem levar em consideração solicitações anteriores. O servidor  não mantém informações sobre o estado do cliente entre as solicitações.  Cada solicitação contém todas as informações necessárias para ser  processada de forma completa. Por exemplo, o protocolo HTTP é  considerado stateless, pois cada solicitação HTTP é tratada de forma  independente, sem que o servidor mantenha informações de sessão do  cliente.

Com base nestes dois tipos de estados podemos definir uma aplicação REST como sendo Stateless. Isso porque, uma aplicação REST é  construída independentemente do cliente que se conecta a ela. Poderá até mesmo, ter vários clientes conectados como navegadores, aplicativos de  smartphones, tablets, smart tvs, etc.

Um dos principais desafios  no uso de stateless é o processo de autenticação. Como não existe um  estado, ou seja, uma sessão para o servidor controlar o cliente que está conectado ao servidor, não é possível saber se duas requisições  seguidas ou intercaladas pertencem ao mesmo cliente. Por isso,  diferentes técnicas de autenticação foram criadas como as baseadas em  token (Token-based Authentication), chave de API (API Key  Authentication), certificado de autenticação (Certificate  Authentication), autenticação por assinatura (Signature Authentication)e autenticação por tempo (Time-based Authentication).

Entre os  métodos de autenticação o mais popular talvez seja a autenticação por  token. Ele envolve a emissão de um token de autenticação após um  processo bem-sucedido do pedido de autenticação pelo cliente. O token é  então gerado pelo servidor e respondido ao cliente que deverá incluí-lo  nas solicitações subsequentes como parte do cabeçalho “Authorization”. O servidor valida e verifica a assinatura do token para autenticar o  cliente e realizar as operações solicitadas. Para esse tipo de operação o uso mais frequente é JSON Web Token (JWT).

##  **JSON Web Token**

O JSON Web Token (JWT) é um padrão aberto (RFC 7519) para representação  compacta e segura de informações entre duas partes. Ele é frequentemente usado como um mecanismo de autenticação em aplicativos da web.

O JWT é composto por três partes:

- **Header:** O cabeçalho especifica o algoritmo usado para assinar o token
- **Payload:** O payload contém as informações que são codificadas no token, como  dados do usuário, data de criação e expiração do token, permissões de  acesso, etc.
- **Signature:** A assinatura é  gerada usando uma chave secreta, de escolha do desenvolvedor, com o  objetivo de verificar se o token é válido. Essa é a única parte do token que não poderá ser visualizada pelo cliente, justamente por ser a parte criptografada.

Após a geração de um token ele terá o seguinte formato:

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.
SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

Observando com atenção podemos ver entre a sequência de caracteres a separação das três partes pelo caractere ponto.
A primeira parte é o cabeçalho:
`eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9`
A segunda parte é o payload:
`eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ`
E a terceira parte é a assinatura:
`SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c`

Uma das principais vantagens do JWT é ele ser autocontido, ou seja, todas  as informações necessárias para verificar a autenticidade do token estão incluídas no próprio token. Isso significa que não é necessário  armazenar informações de autenticação em um servidor, o que ajuda a  simplificar a implementação de sistemas distribuídos.

Outra  vantagem do JWT é a capacidade dele ser usado com qualquer linguagem de  programação e plataforma, tornando-o uma opção popular para aplicativos  da web e móveis.

No entanto, é importante notar que a segurança do JWT depende da implementação correta. É fundamental manter a chave  secreta segura e proteger o token de ataques de força bruta ou de  interceptação de rede. Alguns desenvolvedores consideram uma boa prática realizar a troca da chave de tempos em tempos para dificultar as  possíveis tentativas de quebra da chave.

Além disso, os tokens  devem ser configurados com tempo de vida adequado e expirados após o  tempo limite definido para evitar o uso indevido. Mas é importante  salientar que, não existe um tempo de vida do token considerado ideal.  Esse tempo vai depender das necessidades específicas da aplicação e dos  riscos de segurança associados a ela.

Mas lembre-se que, o token  deve ter um tempo mínimo para o cliente autenticado possa realizar suas  atividades sem a necessidade de a todo instante ser desligado e precisar autenticar novamente.

### O cabeçalho de um Token

O cabeçalho de um JSON Web Token (JWT) é a primeira parte do token e contém  informações sobre o tipo de token e o algoritmo de criptografia usado  para gerar a assinatura do token. O cabeçalho é uma parte do token que é codificada em base64 e decodificada para revelar seus conteúdos.

O cabeçalho de um JWT contém dois campos obrigatórios:

“alg” (algorithm): indica qual algoritmo de criptografia é usado para gerar a assinatura do token. Os valores possíveis para esse campo incluem HMAC  com SHA-256, RSA com SHA-256 e ECDSA com SHA-256, entre outros.
“typ” (type): indica o tipo de token, que é sempre JWT (JSON Web Token).
Além desses dois campos obrigatórios, o cabeçalho de um JWT pode incluir  quaisquer outros campos personalizados que a aplicação precisa  transmitir para a entidade que está usando o token. Esses campos  personalizados são adicionados ao cabeçalho do token como pares  chave/valor e são separados por vírgulas.

Por exemplo, um cabeçalho JWT pode ser representado em formato JSON da seguinte forma:

```
{    "alg": "HS256",    "typ": "JWT"}
```

Nesse exemplo, o campo “alg” indica que a assinatura do token foi gerada  usando o algoritmo HMAC com SHA-256 e o campo “typ” indica que é um  token do tipo JWT.

### Os ítens de um Payload

O payload de um  token JWT (JSON Web Token) contém informações sobre o usuário ou objeto  para o qual o token foi emitido e quaisquer metadados adicionais que são relevantes para a aplicação que está usando o token. O payload é uma  parte do token que é codificada em base64 e decodificada para revelar  seus conteúdos.

Entre algumas das informações de um payload as mais comuns são:

- “iss” (issuer): o emissor do token
- “sub” (subject): o assunto do token, geralmente o identificador exclusivo do usuário ou objeto (ex.: id ou username)
- “aud” (audience): a audiência para a qual o token foi emitido
- “exp” (expiration time): o tempo de expiração do token
- “nbf” (not before): a hora a partir da qual o token é válido
- “iat” (issued at): o momento em que o token foi emitido
- “jti” (JWT ID): um identificador exclusivo para o token

Além desses itens, o payload também pode incluir quaisquer outros dados  relevantes que a aplicação precisa transmitir para a entidade que está  usando o token. Um item que poderá ser necessário em aplicações que  possuem restrições por perfil é justamente o perfil do usuário  autenticado. Isso servirá para o cliente conhecer e criar as regras  necessárias para diferentes perfis no lado cliente.

Por exemplo, um payload JWT pode ser representado em formato JSON da seguinte forma:

```
{    
    "sub": "[email protected]",   
    "iat": 1682193714,   
    "exp": 1682194614,   
    "role": "ADMIN"
}
```

### A Assinatura de um Token

A assinatura de um JSON Web Token (JWT) é uma etapa importante no  processo de segurança do JWT. Ela é formada por uma sequência de  caracteres anexada ao final do token para verificar sua integridade e  autenticidade. A assinatura é baseada em um algoritmo de criptografia de chave compartilhada (symmetric key cryptography) ou chave  pública/privada (asymmetric key cryptography).

O processo de assinatura funciona da seguinte maneira:

1. O cabeçalho e o payload do JWT são codificados em base64.
2. Os valores codificados do cabeçalho e do payload são combinados usando um  ponto (.) como separador para criar uma sequência de caracteres.
3. Essa sequência de caracteres é criptografada usando um algoritmo de  criptografia, juntamente com uma chave secreta ou chave pública/privada, dependendo do tipo de algoritmo de criptografia escolhido.
4. O resultado da criptografia é a assinatura do token JWT.
5. O token JWT final é criado concatenando o cabeçalho, o payload e a assinatura, separados por pontos.

### Geração de Token

Existem várias bibliotecas de suporte para geração de tokens. É possível ver uma lista delas na página oficial [jwt.io](https://jwt.io/libraries). A seguinte temos um breve exemplo da geração de um token usando a biblioteca jjwt:
**Artefato Maven**

```
<!-- JWT -->
<dependency>    
    <groupId>io.jsonwebtoken</groupId>  
    <artifactId>jjwt</artifactId>     
    <version>0.9.1</version>
</dependency>
```

**Código fonte Java**

```
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date; 

public class TokenGenerator {    
public static void main(String[] args) {   
// Define a chave secreta para assinatura do token
Key key = Keys            
		.secretKeyFor(SignatureAlgorithm.HS256);         
// Define a data de expiração do token       
// 1 dia em milissegundos (86400000)       
Date expiration = new Date(System.currentTimeMillis() + 86400000);     
// Gera o token com os dados desejados       
String token = Jwts.builder()              
        // Define o assunto do token               
        .setSubject("usuario")                 
        // Define a data de expiração do token        
        .setExpiration(expiration)                
        // Assina o token com a chave secreta        
        .signWith(key)        
        .compact();                  
// Exibe o token gerado        
System.out.println("Token gerado: " + token);    }}
```

Em resumo, o JWT é um padrão aberto para representação compacta e segura  de informações entre duas partes, amplamente utilizado como mecanismo de autenticação em aplicativos da web e móveis. No entanto, é importante  implementar corretamente para garantir a segurança dos dados.

### O Cliente e Autenticação

Após a autenticação do usuário, o servidor geralmente envia o JSON Web Token (JWT) para o cliente como uma resposta à solicitação de autenticação  bem-sucedida. O token pode ser enviado em uma resposta HTTP ou em um  cookie. Há também que responda com o token em um cabeçalho da resposta  http.

A maneira mais comum de enviar o token para o cliente é incluí-lo no corpo da resposta HTTP como um objeto JSON. Por exemplo:

```
HTTP/1.1 200 OK
Content-Type: application/json
{  
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```

Nesse exemplo, o token JWT é incluído no campo “token” do objeto JSON  retornado pelo servidor. O cliente pode armazenar o token em um cookie  ou em outro local seguro para usá-lo em solicitações futuras.

Alternativamente, o servidor também pode enviar o token em um cookie HTTP seguro,  definido com a opção “HttpOnly” e “Secure” para evitar ataques de roubo  de cookies. Isso pode ser útil para aplicativos que usam autenticação  baseada em sessão.

Independentemente de como o token é enviado ao  cliente, é importante lembrar que o token JWT contém informações  confidenciais do usuário e deve ser tratado com cuidado para evitar  vazamento de informações ou adulteração. O cliente deve armazenar o  token em um local seguro e protegê-lo contra ataques mal-intencionados.

### O Cliente autenticado

Após a autenticação o cliente terá o token de acesso aos recursos protegidos da aplicação. Para enviar solicitações protegidas usando o JWT, o  cliente deve incluir o token no cabeçalho `Authorization` da  solicitação HTTP. O valor do cabeçalho de autorização é formado pela  palavra “Bearer” seguida de um espaço e o valor do token. Por exemplo:

```
GET /api/users/123 HTTP/1.1Host: api.example.comAuthorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

Neste exemplo, o token JWT é incluído no cabeçalho “Authorization” da  solicitação GET. O valor do cabeçalho é formado pela palavra “Bearer”  seguida de um espaço e o valor do token.

É importante lembrar que o token JWT é sensível a letras maiúsculas e minúsculas e deve ser  transmitido exatamente como foi recebido do servidor. O cliente deve  armazenar o token em um local seguro e protegê-lo contra ataques  mal-intencionados.

Além disso, é importante observar que o token  JWT pode ter um tempo de expiração. Se o token expirar, o servidor não  aceitará mais solicitações enviadas com esse token e o cliente deverá  obter um novo token de autenticação para continuar a acessar recursos  protegidos.

### Um passo a passo na autenticação com JWT

Os passos de uma autenticação entre o cliente e o servidor usando JSON Web Tokens (JWT) geralmente segue:

1- O cliente envia as credenciais de autenticação (geralmente nome de  usuário e senha) para o servidor por meio de uma solicitação de login.
2- O servidor verifica as credenciais do cliente em relação aos dados  armazenados, como um banco de dados de usuários ou serviço de  autenticação.
3- Se as credenciais forem válidas, o servidor gera um  JWT contendo informações relevantes (payload) sobre o usuário e  quaisquer permissões associadas.
4- O servidor assina o JWT usando uma chave secreta ou chave privada, dependendo do algoritmo de criptografia selecionado.
5- O servidor retorna o JWT para o cliente, geralmente como parte da resposta de autenticação bem-sucedida.
6- O cliente armazena o JWT de forma segura, como em um cookie seguro ou  em armazenamento local (como Local Storage ou Session Storage) do  navegador.
7- Em solicitações subsequentes a recursos protegidos, o  cliente inclui o JWT no cabeçalho “Authorization” da solicitação HTTP,  usando o formato “Bearer <token>”. Isso permite que o servidor  verifique a autenticidade e autorização do cliente com base no JWT  recebido.
8- O servidor valida a assinatura do JWT usando a chave  secreta ou chave pública correspondente. Se a assinatura for válida, o  servidor confia nas informações contidas no payload do JWT para  autorizar ou negar o acesso ao recurso solicitado.
9- Se a  autenticação for bem-sucedida e o cliente tiver permissão para acessar o recurso solicitado, o servidor processa a solicitação e retorna a  resposta apropriada para o cliente.

Esse processo permite que o  cliente autentique-se de forma segura e eficiente em recursos protegidos do servidor usando um JWT, sem a necessidade de armazenar informações  de autenticação do usuário no servidor ou em sessões. O JWT contém todas as informações necessárias para autenticar e autorizar o cliente.

## Referências

[Introcução ao JSON Web Tokens](https://jwt.io/)
[Documentação biblioteca io.jsonwebtoken](https://github.com/jwtk/jjwt)