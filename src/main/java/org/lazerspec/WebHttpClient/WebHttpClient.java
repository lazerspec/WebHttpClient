package org.lazerspec.WebHttpClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class WebHttpClient {

    public WebHttpClient(String baseURL) {
        this.webClient = WebClient.create(baseURL);
    }

    public WebHttpClient() {
        this.webClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private final WebClient webClient;

    public Mono<String> doGetRequest(String url) {

        return webClient.get()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> doPostRequest(String url, String body) {

        return webClient.post()
                .uri(url)
                .body(Mono.just(body), String.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<Void> doDeleteRequest(String url) {
        return webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<String> doPutRequest(String url, String body) {
        return webClient.put()
                .uri(url)
                .body(Mono.just(body), String.class)
                .retrieve()
                .bodyToMono(String.class);
    }


}
