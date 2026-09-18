package com.nunovieira.FirstServe.match;

import jakarta.persistence.Embeddable;

@Embeddable 
public class SetScore {
    
    private Integer player1Games;
    private Integer player2Games;

    public Integer getPlayer1Games() {
        return player1Games;
    }

    public void setPlayer1Games(Integer player1Games) {
        this.player1Games = player1Games;
    }

    public Integer getPlayer2Games() {
        return player2Games;
    }

    public void setPlayer2Games(Integer player2Games) {
        this.player2Games = player2Games;
    }

}
