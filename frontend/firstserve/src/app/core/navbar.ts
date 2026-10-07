import { Component } from "@angular/core";
import { RouterLink } from "@angular/router";

@Component({
    imports: [RouterLink],
    selector: 'nav-bar',
    templateUrl: './navbar.html',
})
export class Navbar {}