package com.nunovieira.FirstServe.match;

import com.nunovieira.FirstServe.match.enums.DrawType;
import com.nunovieira.FirstServe.match.enums.MatchEventStatus;
import com.nunovieira.FirstServe.match.enums.MatchLifecycleStatus;
import com.nunovieira.FirstServe.match.enums.MatchOutcome;
import com.nunovieira.FirstServe.match.enums.RoundCode;
import com.nunovieira.FirstServe.match.enums.SurfaceType;
import com.nunovieira.FirstServe.match.enums.Tour;
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

    @Enumerated(EnumType.STRING)
    private SurfaceType surface;

    @Enumerated(EnumType.STRING)
    private Tour tour;

    private boolean indoor;
    private boolean isDoubles;

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

}