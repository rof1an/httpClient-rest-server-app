package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class HttpChat implements IChat {
    private final String baseUrl = "http://localhost:8080/api/v1/messages";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(HttpChat.class);

    public HttpChat() {
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    @Override
    public List<Message> getNewMessages(String username) {
        logger.info("username = {}", username);
        if (!username.isEmpty()) {
            try {

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(baseUrl))
                        .header("username", username)
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    return objectMapper.readValue(response.body(), new TypeReference<>() {});
                } else {
                    System.err.println("Ошибка при получении сообщений. Статус: " + response.statusCode());
                    return new ArrayList<>();
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Message sendMessage(Message message) {
//        log.info(String.valueOf(message));
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(message)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (!response.body().isEmpty()) {
                return objectMapper.readValue(response.body(), new TypeReference<>() {});
            }
            return null;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
