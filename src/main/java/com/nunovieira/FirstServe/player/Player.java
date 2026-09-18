package com.nunovieira.FirstServe.player;

import com.nunovieira.FirstServe.player.enums.BackhandType;
import com.nunovieira.FirstServe.player.enums.PlayingHand;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer ranking;
    private String country;
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private PlayingHand hand;

    @Enumerated(EnumType.STRING)
    private BackhandType backhand;

    public Player() {}

    public Player(String name, String country, LocalDate birthday, PlayingHand hand, BackhandType backhand) {
        this.name = name;
        this.country = country;
        this.birthday = birthday;
        this.hand = hand;
        this.backhand = backhand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public PlayingHand getHand() {
        return hand;
    }

    public void setHand(PlayingHand hand) {
        this.hand = hand;
    }

    public BackhandType getBackhand() {
        return backhand;
    }

    public void setBackhand(BackhandType backhand) {
        this.backhand = backhand;
    }

}