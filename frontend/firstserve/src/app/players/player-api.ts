import { HttpClient } from "@angular/common/http";
import { inject, Service } from "@angular/core";
import { Observable } from "rxjs";
import { Player } from "./player-model";

@Service()
export class PlayerApi {
    private httpClient = inject(HttpClient);

    getPlayers(): Observable<Player[]> {
        return this.httpClient.get<Player[]>('/api/v1/players');
    }
}