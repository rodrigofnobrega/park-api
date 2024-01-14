# Validação e Jakarta Bean Validation

#### Validação Back-End em API REST

A validação no back-end em APIs REST é importante por várias razões:

**Segurança:** A validação no back-end ajuda a garantir que os dados recebidos pela  API sejam seguros e confiáveis. Isso impede que dados inválidos ou  maliciosos sejam processados e armazenados no banco de dados ou causem  problemas na lógica de negócio.

**Consistência:** A  validação no back-end garante que os dados estejam em conformidade com  as regras de negócio e as restrições do sistema. Isso ajuda a manter a  consistência dos dados e evita que informações inconsistentes sejam  armazenadas ou transmitidas.

**Melhoria da experiência do usuário:** Ao validar os dados no back-end, é possível fornecer feedback imediato e detalhado aos usuários sobre problemas nos dados enviados. Isso ajuda a melhorar a experiência do usuário, pois eles recebem informações claras sobre erros e podem corrigi-los rapidamente.

**Manutenção simplificada:** A validação no back-end centraliza a lógica de validação em um único  local, facilitando a manutenção e atualização das regras de validação.  Se houver necessidade de alterar ou adicionar regras de validação, isso  pode ser feito no código do back-end sem exigir alterações nas várias  interfaces de usuário que utilizam a API.

**Redução de carga na rede:** A validação no back-end reduz a quantidade de dados inválidos ou  indesejados que precisam ser transmitidos pela rede. Isso ajuda a  otimizar o desempenho da aplicação, minimizando o tráfego de rede e  reduzindo a sobrecarga nos dispositivos cliente.

Embora seja  importante realizar validações no front-end para fornecer feedback  imediato ao usuário, a validação no back-end é fundamental para garantir a integridade, segurança e consistência dos dados no sistema como um  todo. A combinação de validação no front-end e no back-end é a abordagem recomendada para garantir uma API REST robusta e confiável. Contudo, a  validação do front-end, aplicação cliente que consome o API REST, é de  responsabilidade dos desenvolvedores do front-end. Por isso, se torna  ainda mais importante que a API REST seja colocada em produção já com  todas as validações necessárias para garantir os itens citados nos  parágrafos anteriores.

#### Tipos de validação back-end no Spring Framework

Existem várias formas de realizar validação no back-end em APIs REST  desenvolvidas em Java com o framework Spring. O Spring suporta a  especificação de validação [Jakarta Bean Validation](https://beanvalidation.org/), que permite adicionar anotações de validação nos campos dos objetos de entrada das APIs. Você pode usar anotações como `@Valid`, `@NotNull`, `@Size`, `@NotBlank`, entre outras, para definir as restrições de validação. Essas anotações  podem ser adicionadas aos parâmetros dos métodos dos controladores ou  aos campos dos objetos de entrada dos modelos.

Exemplo:

```
@PostMapping("/users")
public ResponseEntity<User> create(@Valid @RequestBody User user) 
{
	// ...
}
```

O Spring também fornece uma API de validação nativa que pode ser utilizada para realizar validações customizadas. Com a [Spring Validator](https://docs.spring.io/spring-framework/reference/core/validation/validator.html) você pode criar classes de validação personalizadas que implementam a interface `org.springframework.validation.Validator` e definir as regras de validação no método `validate()`. Em seguida, você pode usar o Validator nos seus controllers para validar os objetos de entrada.
Exemplo:

```
@Component 
publicclass UserValidator implements Validator {
    @Override       
    public boolean supports(Class<?> clazz) {     
        return User.class.isAssignableFrom(clazz);      
    }
    @Override
    public void validate(Object target, Errors errors) { 
        User user = (User) target;
        if (user.getName() == null || user.getName().isEmpty()) {
            errors.rejectValue("name", "NotEmpty", "Name cannot be empty");               
        }       
      }
}

@RestControllerpublic 
class User {        
    @PostMapping("/api/users")   
    public ResponseEntity<User> create(@Validated @RequestBody User user, Errors errors) {     
            if (errors.hasErrors()) {      
                // Handle validation errors           
            }             
            // ...      
        }
}
```

Essas são apenas algumas das opções disponíveis para realizar validação no  back-end em APIs REST com Java e Spring. A escolha da abordagem depende  das necessidades específicas do seu projeto e das regras de validação  que você precisa implementar.