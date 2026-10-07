package com.nunovieira.FirstServe.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.nunovieira.FirstServe.client.dto.LiveTennisPlayersResponse;

@Component 
public class LiveTennisClient {

    private final RestClient restClient;

    public LiveTennisClient(@Value("${livetennis.api.key}") String apiKey ) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.livetennisapi.com/api/public/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public LiveTennisPlayersResponse getPlayers(int limit, int offset) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/players")
                        .queryParam("limit", limit)
                        .queryParam("offset", offset)
                        .build())
                .retrieve()
                .body(LiveTennisPlayersResponse.class);
    }
}