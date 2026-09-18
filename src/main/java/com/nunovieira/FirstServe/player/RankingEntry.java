package com.nunovieira.FirstServe.player;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RankingEntry {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "player_id")
    private Player player;

    private LocalDate rankingDate;
    private Integer rank;
    private Integer points;

    protected RankingEntry() {

    }

    public RankingEntry(Player player, LocalDate rankingDate, Integer rank, Integer points) {
        this.player = player;
        this.rankingDate = rankingDate;
        this.rank = rank;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LocalDate getRankingDate() {
        return rankingDate;
    }
    
    public void setRankingDate(LocalDate rankingDate) {
        this.rankingDate = rankingDate;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
