package com.requestapi.demo.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Utilizando o valor da chave da API a partir das propriedades
    private static final String API_KEY = "SKw6Z7soPol79PaolJbg3Q==63FFIatVDw3pjs7k";  

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ApiKeyInterceptor(API_KEY));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    // Classe interna que implementa a interface de interceptação de requisições HTTP
    static class ApiKeyInterceptor implements ClientHttpRequestInterceptor {

        // Armazenando a chave API
        private final String apiKey;

        // Construtor que inicializa o campo apiKey
        public ApiKeyInterceptor(String apiKey) {
            this.apiKey = apiKey;
        }

        // Método que intercepta as requisições HTTP para adicionar a chave de API no cabeçalho da requisição.
        @Override // Sobrescreve o método intercept da interface
        public @NonNull ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body, @NonNull ClientHttpRequestExecution execution) throws IOException {
            request.getHeaders().set("X-Api-Key", apiKey); // Adiciona a chave de API no cabeçalho da requisição.
            logRequest(request);  // Chama o método logRequest para registrar a requisição.
            return execution.execute(request, body); // Executa a requisição com as modificações.
        }

        // Método privado para registrar detalhes da requisição.
        private void logRequest(HttpRequest request) {
            System.out.println("Request URI: " + request.getURI()); // Imprime URI da requisição
            System.out.println("Request Headers: " + request.getHeaders()); // Imprime os cabeçalhos da requisição
        }
    }
}
