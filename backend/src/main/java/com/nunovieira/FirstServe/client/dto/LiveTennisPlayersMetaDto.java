package com.nunovieira.FirstServe.client.dto;

public record LiveTennisPlayersMetaDto(
    Integer count,
    Boolean has_more,
    Integer limit,
    Integer offset,
    Integer total
) {}
