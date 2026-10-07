package com.nunovieira.FirstServe.client.mappers;

import org.springframework.stereotype.Component;

import com.nunovieira.FirstServe.client.dto.LiveTennisPlayerDto;
import com.nunovieira.FirstServe.player.Player;
import com.nunovieira.FirstServe.player.enums.BackhandType;
import com.nunovieira.FirstServe.player.enums.PlayingHand;
import com.nunovieira.FirstServe.tournament.enums.TourTournaments;

@Component 
public class LiveTennisPlayerMapper {
    
    public Player mapToPlayer(LiveTennisPlayerDto dto) {
        return new Player(dto.name(), dto.ranking(), dto.country(), dto.birthday(), mapTour(dto.tour()), mapPlayingHand(dto.hand()), mapBackhand(dto.backhand()));
    }

    private TourTournaments mapTour(String tour) {
        if (tour == null) {
            return null;
        }

        return switch (tour.toLowerCase()) {
            case "atp" -> TourTournaments.ATP;
            case "wta" -> TourTournaments.WTA;
            case "challenger_men" -> TourTournaments.CHALLENGER;
            default -> null;

        };
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
