package com.nunovieira.FirstServe.player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nunovieira.FirstServe.client.LiveTennisClient;
import com.nunovieira.FirstServe.client.dto.LiveTennisPlayerDto;
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
        List<LiveTennisPlayerDto> dtos = response.data()
                .stream()
                .filter(dto -> !Boolean.TRUE.equals(dto.is_doubles_team()))
                .toList();
        
        List<Long> ids = dtos.stream().map(LiveTennisPlayerDto::id).toList();
        
        Map<Long, Player> existingPlayers = playerRepository.findByExternalIdIn(ids)
                .stream()
                .collect(Collectors.toMap(Player::getExternalId, p -> p));

        List<Player> toSavePlayers = dtos.stream()
                .map(dto -> {
                    Player player = existingPlayers.get(dto.id());
                    if (player == null) {
                        return liveTennisPlayerMapper.mapToPlayer(dto);
                    }
                    liveTennisPlayerMapper.updatePlayer(player, dto);
                    return player;
                })
                .toList();

        playerRepository.saveAll(toSavePlayers);  
    }

}
