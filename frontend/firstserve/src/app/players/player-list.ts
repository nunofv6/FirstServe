import { Component, inject } from "@angular/core";
import { PlayerApi } from "./player-api";
import { toSignal } from "@angular/core/rxjs-interop";

@Component({
    selector: 'player-list',
    templateUrl: './player-list.html',
})
export class PlayerList {
    private api = inject(PlayerApi);
    protected players = toSignal(this.api.getPlayers(), {initialValue: []});
}
