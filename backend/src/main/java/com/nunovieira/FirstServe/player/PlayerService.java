package com.nunovieira.FirstServe.player;

import java.util.List;
import org.springframework.stereotype.Service;

import com.nunovieira.FirstServe.client.LiveTennisClient;
import com.nunovieira.FirstServe.client.dto.LiveTennisPlayersResponse;
import com.nunovieira.FirstServe.client.mappers.LiveTennisPlayerMapper;

@Service 
public class PlayerService {
    
    private final PlayerRepository playerRepository;
    private final LiveTennisClient liveTennisClient;
    private final LiveTennisPlayerMapper liveTennisPlayerMapper;

    public PlayerService(PlayerRepository playerRepository, LiveTennisClient liveTennisClient, LiveTennisPlayerMapper liveTennisPlayerMapper) {
        this.playerRepository = playerRepository;
        this.liveTennisClient = liveTennisClient;
        this.liveTennisPlayerMapper = liveTennisPlayerMapper;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.findAll();

        if (players.isEmpty()) {
            syncPlayers();
            players = playerRepository.findAll();
        }

        return players;
    }

    public void syncPlayers() {
        LiveTennisPlayersResponse response = liveTennisClient.getPlayers(200, 0);
        List<Player> players = response.data()
                .stream()
                .filter(dto -> !Boolean.TRUE.equals(dto.is_doubles_team()))
                .map(liveTennisPlayerMapper::mapToPlayer)
                .toList();
        
        playerRepository.saveAll(players);  
    }

}
