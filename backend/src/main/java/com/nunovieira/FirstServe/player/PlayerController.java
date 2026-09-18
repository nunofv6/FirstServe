package com.nunovieira.FirstServe.player;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class PlayerController {
    
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping ("/players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

}