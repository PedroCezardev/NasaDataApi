package com.requestapi.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("NASA Data API via API Ninjas")
                        .version("1.0.0")
                        .description("A NASA Data API via API Ninjas oferece uma maneira acessível de explorar e integrar informações espaciais e astronômicas em suas aplicações, utilizando a robusta API Ninjas. Embora a API não seja diretamente da NASA, ela proporciona acesso a dados e recursos relacionados ao espaço, como informações de Planetas e Estrelas e outros dados relevantes que imitam o tipo de informação que você encontraria em APIs da NASA. Esta solução é ideal para desenvolvedores que desejam incorporar dados espaciais e de astronomia em suas aplicações com a facilidade e a flexibilidade da API Ninjas.")
                        .contact(new Contact()
                            .name("Pedro Cezar")
                            .url("https://meu-portifolio-lime.vercel.app/")
                            .email("pcdasilvabeserra@gmail.com")));
    }
}
