package com.nunovieira.FirstServe.client.dto;

import java.time.LocalDate;

public record LiveTennisPlayerDto(
    Long id,
    String name,
    String tour,
    String country,
    Integer ranking,
    String ranking_movement,
    Integer ranking_points,
    String hand,
    Integer backhand,
    LocalDate birthday,
    Boolean is_doubles_team
) {}