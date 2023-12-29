___
# Matriz de Insumos
___



## Esquema do diretório principal

* :file_folder: **matriz-insumos-v1**
    * **:file_folder: exception**
        contém as exceções compartilhadas
    * **:file_folder: handle**
        contém os handles do sistema, com interceptadores globais
    * **:file_folder: util**
        contém classes específicas para o projeto , conforme especificação da Klabin
    * **:file_folder: lib**
        contém as classes bases/lib do sistema 
    * **:file_folder: crud**
        * **:file_folder: lookup** 
            tabelas auxiliares/lookup com os campos Id, Nome onde o Id é do tipo auto-increment
        * **:file_folder: internal**
            tabelas de controle interno do sistema id fixo, são gerenciandas pelo DBA/Admin do sistema
        * **:file_folder: produto**
            tabela de produto
        * **:file_folder: matriz**
            tabela de matriz
        * **:file_folder: matriz-documento**
            tabela de relacionamento da tabela matriz e documento
        * **:file_folder: informacao-adicional**
            tabela da informação adicionai
        * **:file_folder: informacao-adicional-matriz**
            tabela de informação adicional relacionada com a matriz
        * **:file_folder: especie**
            tabela de espécia 
        * **:file_folder: tipo-risco-potencial**
            tabela de risco potencial
        * **:file_folder: planta-daninha**
            tabela de planta daninha
        
## Estrutura das pastas dentro de cada  :file_folder: ***/crud/\<artefato>/***

* design-pattern
    * **:file_folder: entrypoint**
        deverá conter os pontos de entrada para api
        * **:file_folder: config**
        contém as classes de configuração para o entrypoint, como por exemplo o ControllerAdvisor, que captura as exceptions
        ```java
        public class ControllerAdvisor extends ResponseEntityExceptionHandler  {

            @ExceptionHandler(BusinessException.class)
            protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
                return handle(HttpStatus.UNPROCESSABLE_ENTITY, request, Arrays.asList(ex.getMessage()));
            }

            private ResponseEntity<Object> handle(HttpStatus status, WebRequest request, List<String> errors) {
                var body = new LinkedHashMap<String, Object>();
                body.put("timestamp", LocalDate.now());
                body.put("status", status.value());
                body.put("errors", errors);

                if (request != null) {
                    body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
                }

                return new ResponseEntity<>(body, status);
            }            
        }
        ```        
        * **:file_folder: rest**
        controller de entrada da aplicação, devem ser configurado o swagger da aplicação
        ```java
        @RestController
        @RequestMapping("/classe")
        public class ClasseRestController {

            private final ClasseUseCase classeUseCase;

            public ClasseRestController(ClasseUseCase classeUseCase) {
                this.classeUseCase = classeUseCase;
            }

            @Operation(summary = "Cadastrar classes para <nome do projeto>")
            @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
            @ApiResponses(value = 
            { 
                @ApiResponse(responseCode = "201", 
                             description = "Classe cadastrada com sucesso", 
                             content = {@Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE, 
                                            schema = @Schema(implementation = ClasseResponse.class)
                                        ) }),
			    @ApiResponse(responseCode = "422", description = "Classe não cadastrada", content = @Content) 
            })
            public ResponseEntity<Object> insert(@RequestBody AtuacaoDTO atuacao) {
                
                return ResponseEntity.status(HttpStatus.CREATED).body(atuacaoDTO);
            }
    
        }
        ```        
    
    * **:file_folder: domain**
            contém as regras de negócio da API, é uma camada isolada que não conhece os pontos de entrada, nem a origem dos dados 
        * **:file_folder: cross**
            classe de formatação/uteis, como por exemplo classe de formatação de datas
        * **:file_folder: dto**
            classe de transporte para comunicação entre EntryPoint e UseCase
            ```java
            public class ClasseDTO {

                private Long id;

                private String nome;
            }
            ```
        * **:file_folder: entity**
            classe de transporte para comunicação entre UseCase e DataProvider
            ```java
            public class ClasseEntity {

                private Long id;

                private String nome;
            }
            ```            

        * **:file_folder: exception**
            exceções específicas das regras de negócio
            ```java
           public class BusinessException extends RuntimeExceptiony {

                private static final long serialVersionUID = 6993878570229158267L;

                public BusinessException() {
                }

                public BusinessException(String message) {
                    super(message);
                }
            }
            ```                     
        * **:file_folder: gateway**
           interface para que na infra seja implementada pela classe \<artefato>DataProvider e faça a busca de dados, a entrada são do tipo **dto** e retorno como **entity**
            ```java
            public interface ClasseGateway {
               ClasseEntity salvar(ClasseDTO dto);
            }
            ```
        * **:file_folder: mapper**
            Classe de conversão de **request** para **dto**
            ```java
            import org.mapstruct.Mapper;

            @Mapper(componentModel = "spring")
            public interface ClasseDTOMapper{

                ClasseDTOMapper INSTANCE = Mappers.getMapper(ClasseDTOMapper.class);

                ClasseDTO toDTO(ClasseRequest request);              
            }
            ```           
        * **:file_folder: usecase**
            contém as interfaces para as implementações das regras de negócio, tem como entrada a classe **request** e de retorno a classe **response**
            ```java
            
            public interface ClasseUseCase{
                ClasseResponse salvar(ClasseRequest request);              
            }
            ```            
            * **:file_folder: request**
            classe de entrada de dados para o usecase
            ```java
            public class ClasseRequest {

                private Long id;

                private String nome;
            }
            ```              
            * **:file_folder: response**
            classe de saída de dados para o usecase
            ```java
            public class ClasseResponse {

                private Long id;

                private String nome;
            }
            ```

            * **/:file_folder: response**
                * **/:file_folder: mapper**
                Classe de conversão de **entity** para **response** 
            ```java
            import org.mapstruct.Mapper;

            @Mapper(componentModel = "spring")
            public interface ClasseResponseMapper{

                ClasseResponseMapper INSTANCE = Mappers.getMapper(ClasseResponseMapper.class);

                ClasseResponse toDTO(ClasseEntity entity);              
            }
            ```   
            * **:file_folder: impl**  
            Implementação da interface do useCase
            ```java
            public interface ClasseUseCaseImpl implements ClasseUseCase {

                private final ClasseGateway classeGateway;

                public ClasseUseCaseImpl(ClasseGateway classeGateway) {
                    this.classeGateway = classeGateway;
                }

                public ClasseResponse salvar(ClasseRequest request){
                    validar(request);
                    var dto = ClasseDTOMapper.INSTANCE.toDTO(request);
                    var entity = classeGateway.salvar(dto);
                    return ClasseResponseMapper.INSTANCE.toResponse(entity);
                }      
                
                public validar(ClasseRequest request){
                    throw new BusinessException("Mensagem de erro caso haja algo de errado");
                }
                         
            }
            ```              


    * **:file_folder: infra**
        local de storage dos dados, neste projeto a base de dados em MySql 
        * **:file_folder: dataprovider**
            contém as classes de implementação das interface de /gateway
            ```java
            public class ClasseDataProvider implements ClasseGateway {

                private final ClasseRepository classeRepository;

                public ClasseDataProvider(ClasseRepository classeRepository){
                    this.classeRepository = classeRepository;
                }

            @Override
            public ClasseEntity salvar(ClasseEntity entity) {
                var jpa = ClasseMapper.INSTANCE.toJpa(entity);
                var responseJPA = repository.save(jpa);
                return ClasseMapper.INSTANCE.toEntity(responseJPA);
            }


               
            }
            ```        
        * **:file_folder: jpa**
            classe de mapeamento para dos dados em jpa
            * **:file_folder: model (@entity )**
                classe de mapeamento das tabelas de dados, as classe devem terminar com o sufixo <NomeDaClase>Jpa.java
                ```java
                @Entity
                @Table(name = "Classe")
                public class ClasseJpa implements Serializable {

                    private static final long serialVersionUID = -8742454888558739122L;

                    @Id
                    @Column(name = "Id")
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    private Long id;

                    @Column(name = "Nome")
                    private String nome;

                }
                ```                   
            * **:file_folder: repository**
                interface de mapeamento do JPARepository
            ```java
            public class ClasseRepository extends JpaRepository<ClasseJpa, Long> {

                boolean existsByNomeIgnoreCase(String nome);

                boolean existsById(Long id);

                Optional<ClasseJpa> findByNomeIgnoreCase(String nome);

                Long findIdByNomeIgnoreCaseAndIdNot(String nome, Long id);                

            }
            ```                     
        
        * **:file_folder: mapper** 
            classe de mapeamento dos entity e model(jpa)
            ```java
            @Mapper(componentModel = "spring")
            public interface ClasseMapper{

                ClasseMapper INSTANCE = Mappers.getMapper(ClasseMapper.class);

                ClasseEntity toEntity(ClasseJpa model);              
                ClasseJpa toJpa(ClasseEntity entity);
            }
            ```               
        
---
### Atualizações

| Item | Data | Autor - Empresa | Descrição 
| ---- |---- | ----- | ---------
| 1 | 28/12/2023 | Dannyrooh - Regazzo | Criação do documento com as definições da estrutura de desenvolvimento para o modelo de design pattern
