//@SpringBootApplication
//@EnableSwagger2 //Enable swagger 2.0 spec
//@ComponentScan(basePackageClasses = {
//        PetController.class,
//        HomeController.class,
//        FileUploadController.class
//})
//public class Application {
//    public static void main(String[] args) {
//        ApplicationContext ctx = SpringApplication.run(Application.class, args);
//    }
//
//    @Bean
//    public Docket petApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("full-petstore-api")
//                .apiInfo(apiInfo())
//                .select()
//                .paths(petstorePaths())
//                .build();
//    }
//
//    @Bean
//    public Docket categoryApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("category-api")
//                .apiInfo(apiInfo())
//                .select()
//                .paths(categoryPaths())
//                .build();
//    }
//
//    @Bean
//    public Docket multipartApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("multipart-api")
//                .apiInfo(apiInfo())
//                .select()
//                .paths(multipartPaths())
//                .build();
//    }
//
//    private Predicate<String> categoryPaths() {
//        return regex("/category.*");
//    }
//
//    private Predicate<String> multipartPaths() {
//        return regex("/upload.*");
//    }
//
//    @Bean
//    public Docket userApi() {
//        AuthorizationScope[] authScopes = new AuthorizationScope[1];
//        authScopes[0] = new AuthorizationScopeBuilder()
//                .scope("read")
//                .description("read access")
//                .build();
//        SecurityReference securityReference = SecurityReference.builder()
//                .reference("test")
//                .scopes(authScopes)
//                .build();
//        ArrayList<SecurityContext> securityContexts = newArrayList(SecurityContext.builder().securityReferences
//                (newArrayList(securityReference)).build());
//        return new Docket(DocumentationType.SWAGGER_2)
//                .securitySchemes(newArrayList(new BasicAuth("test")))
//                .securityContexts(securityContexts)
//                .groupName("user-api")
//                .apiInfo(apiInfo())
//                .select()
//                .paths(userOnlyEndpoints())
//                .build();
//    }
//
//    private Predicate<String> petstorePaths() {
//        return or(
//                regex("/api/pet.*"),
//                regex("/api/user.*"),
//                regex("/api/store.*")
//        );
//    }
//
//    private Predicate<String> userOnlyEndpoints() {
//        return new Predicate<String>() {
//            @Override
//            public boolean apply(String input) {
//                return input.contains("user");
//            }
//        };
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Springfox petstore API")
//                .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
//                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
//                        + "took a " +
//                        "galley of type and scrambled it to make a type specimen book. It has survived not only five " +
//                        "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
//                        "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
//                        "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
//                        "versions of Lorem Ipsum.")
//                .termsOfServiceUrl("http://springfox.io")
//                .contact("springfox")
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
//                .version("2.0")
//                .build();
//    }
//}