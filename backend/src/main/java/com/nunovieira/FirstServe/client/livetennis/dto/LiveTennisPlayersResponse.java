package com.nunovieira.FirstServe.client.livetennis.dto;

import java.util.List;

public record LiveTennisPlayersResponse(
    List<LiveTennisPlayerDto> data,
    LiveTennisPlayersMetaDto meta
) {}
