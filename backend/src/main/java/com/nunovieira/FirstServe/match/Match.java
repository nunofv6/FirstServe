package com.nunovieira.FirstServe.match;

import com.nunovieira.FirstServe.match.enums.DrawType;
import com.nunovieira.FirstServe.match.enums.MatchEventStatus;
import com.nunovieira.FirstServe.match.enums.MatchLifecycleStatus;
import com.nunovieira.FirstServe.match.enums.MatchOutcome;
import com.nunovieira.FirstServe.match.enums.RoundCode;
import com.nunovieira.FirstServe.player.Player;
import com.nunovieira.FirstServe.tournament.Tournament;

import java.text.Format;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DrawType draw;

    @Enumerated(EnumType.STRING)
    private Format format;

    @ManyToOne
    @JoinColumn (name = "tournament_id")
    private Tournament tournament;

    private Boolean isDoubles;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Player player2;

    @Enumerated(EnumType.STRING)
    private RoundCode round; 

    private LocalDateTime scheduledStartTime;

    @Enumerated(EnumType.STRING)
    private MatchLifecycleStatus lifecycleStatus;

    @Enumerated(EnumType.STRING)
    private MatchEventStatus eventStatus;

    @Enumerated(EnumType.STRING)
    private MatchOutcome outcome;

    private Integer winner;

    private LocalDateTime eventStatusUpdatedAt;

    public void setEventStatus(MatchEventStatus newStatus) {
        if (this.eventStatus != newStatus) {
            this.eventStatus = newStatus;
            this.eventStatusUpdatedAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DrawType getDraw() {
        return draw;
    }

    public void setDraw(DrawType draw) {
        this.draw = draw;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Boolean isDoubles() {
        return isDoubles;
    }

    public void setDoubles(Boolean isDoubles) {
        this.isDoubles = isDoubles;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public RoundCode getRound() {
        return round;
    }

    public void setRound(RoundCode round) {
        this.round = round;
    }

    public LocalDateTime getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(LocalDateTime scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public MatchLifecycleStatus getLifecycleStatus() {
        return lifecycleStatus;
    }

    public void setLifecycleStatus(MatchLifecycleStatus lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }

    public MatchEventStatus getEventStatus() {
        return eventStatus;
    }

    public MatchOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(MatchOutcome outcome) {
        this.outcome = outcome;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public LocalDateTime getEventStatusUpdatedAt() {
        return eventStatusUpdatedAt;
    }

    public void setEventStatusUpdatedAt(LocalDateTime eventStatusUpdatedAt) {
        this.eventStatusUpdatedAt = eventStatusUpdatedAt;
    }

}