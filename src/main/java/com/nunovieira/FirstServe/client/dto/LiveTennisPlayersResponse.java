package com.nunovieira.FirstServe.client.dto;

import java.util.List;

public record LiveTennisPlayersResponse(
    List<LiveTennisPlayerDto> data,
    LiveTennisPlayersMetaDto meta
) {}
