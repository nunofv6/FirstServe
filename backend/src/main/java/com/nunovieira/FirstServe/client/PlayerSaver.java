package com.nunovieira.FirstServe.client;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nunovieira.FirstServe.client.livetennis.dto.LiveTennisPlayerDto;
import com.nunovieira.FirstServe.client.livetennis.mappers.LiveTennisPlayerMapper;
import com.nunovieira.FirstServe.player.Player;
import com.nunovieira.FirstServe.player.PlayerRepository;

@Component 
public class PlayerSaver {
    
    private LiveTennisPlayerMapper liveTennisPlayerMapper;
    private PlayerRepository playerRepository;

    public PlayerSaver(LiveTennisPlayerMapper liveTennisPlayerMapper, PlayerRepository playerRepository) {
        this.liveTennisPlayerMapper = liveTennisPlayerMapper;
        this.playerRepository = playerRepository;
    }

    @Transactional 
    public List<Player> upsertPlayers(List<LiveTennisPlayerDto> dtos) {
        List<Long> ids = dtos.stream().map(LiveTennisPlayerDto::id).toList();
        
        Map<Long, Player> existingPlayers = playerRepository.findByExternalIdIn(ids)
                .stream()
                .collect(Collectors.toMap(Player::getExternalId, p -> p));

        List<Player> players = dtos.stream()
                    .map(dto -> {
                        Player player = existingPlayers.get(dto.id());
                        if (player == null) {
                            return liveTennisPlayerMapper.mapToPlayer(dto);
                        }
                        liveTennisPlayerMapper.updatePlayer(player, dto);
                        return player;
                    }).toList();

        playerRepository.saveAll(players); 
        
        return players;
    }
}
