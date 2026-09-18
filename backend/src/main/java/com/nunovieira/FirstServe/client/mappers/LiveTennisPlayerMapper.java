package com.nunovieira.FirstServe.client.mappers;

import org.springframework.stereotype.Component;

import com.nunovieira.FirstServe.client.dto.LiveTennisPlayerDto;
import com.nunovieira.FirstServe.player.Player;
import com.nunovieira.FirstServe.player.enums.BackhandType;
import com.nunovieira.FirstServe.player.enums.PlayingHand;

@Component 
public class LiveTennisPlayerMapper {
    
    public Player mapToPlayer(LiveTennisPlayerDto dto) {
        return new Player(dto.name(), dto.ranking(), dto.country(), dto.birthday(), mapPlayingHand(dto.hand()), mapBackhand(dto.backhand()));
    }

    private PlayingHand mapPlayingHand(String hand) {
        if (hand == null) {
            return null;
        }

        return switch (hand) {
            case "R" -> PlayingHand.RIGHT;
            case "L" -> PlayingHand.LEFT;
            default -> null;
        };
    }

    private BackhandType mapBackhand(Integer backhand) {
        if (backhand == null) {
            return null;
        }

        return switch (backhand) {
            case 1 -> BackhandType.ONE_HANDED;
            case 2 -> BackhandType.TWO_HANDED;
            default -> null;
        };
    }

}
