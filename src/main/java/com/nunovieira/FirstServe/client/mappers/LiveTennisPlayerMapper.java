package com.nunovieira.FirstServe.client.mappers;

import org.springframework.stereotype.Component;

import com.nunovieira.FirstServe.client.dto.LiveTennisPlayerDto;
import com.nunovieira.FirstServe.player.Player;

@Component 
public class LiveTennisPlayerMapper {
    
    public Player mapToPlayer(LiveTennisPlayerDto dto) {
        Player player = new Player();
        player.setName(dto.name());
        player.setCountry(dto.country());
        player.setRanking(dto.ranking());
        player.setBirthday(dto.birthday());
        return player;
    }
}
