import { Routes } from '@angular/router';
import { PlayerList } from './players/player-list';

export const routes: Routes = [
    {
        path: 'players',
        component: PlayerList,
    }
];
