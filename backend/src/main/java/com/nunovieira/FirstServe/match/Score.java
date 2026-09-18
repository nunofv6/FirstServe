package com.nunovieira.FirstServe.match;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;

@Embeddable
public class Score {
    
    private Integer player1Sets;
    private Integer player2Sets;
    private String player1Points;
    private String player2Points;
    private Integer server;
    private Boolean tiebreak;
    
    @ElementCollection
    @CollectionTable (
        name = "match_set_scores",
        joinColumns = @JoinColumn(name = "match_id")
    )
    @OrderColumn(name = "set_number")
    private List<SetScore> games;

    public Integer getPlayer1Sets() {
        return player1Sets;
    }

    public void setPlayer1Sets(Integer player1Sets) {
        this.player1Sets = player1Sets;
    }

    public Integer getPlayer2Sets() {
        return player2Sets;
    }

    public void setPlayer2Sets(Integer player2Sets) {
        this.player2Sets = player2Sets;
    }

    public String getPlayer1Points() {
        return player1Points;
    }

    public void setPlayer1Points(String player1Points) {
        this.player1Points = player1Points;
    }

    public String getPlayer2Points() {
        return player2Points;
    }

    public void setPlayer2Points(String player2Points) {
        this.player2Points = player2Points;
    }

    public Integer getServer() {
        return server;
    }

    public void setServer(Integer server) {
        this.server = server;
    }

    public Boolean getTiebreak() {
        return tiebreak;
    }

    public void setTiebreak(Boolean tiebreak) {
        this.tiebreak = tiebreak;
    }

    public List<SetScore> getGames() {
        return games;
    }

    public void setGames(List<SetScore> games) {
        this.games = games;
    }

    
}
