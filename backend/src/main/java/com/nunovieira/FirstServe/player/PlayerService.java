package com.nunovieira.FirstServe.player;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nunovieira.FirstServe.client.livetennis.LiveTennisClient;
import com.nunovieira.FirstServe.client.livetennis.mappers.LiveTennisPlayerMapper;

@Service 
public class PlayerService {
    
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository, LiveTennisClient liveTennisClient, LiveTennisPlayerMapper liveTennisPlayerMapper) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.findAll(Sort.by("ranking"));

        return players;
    }

}
