import { Component, OnInit } from "@angular/core"

@Component({
  selector: "home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  handleClick(row: number, col: number) {
    console.log(`Cell clicked: Row ${row}, Column ${col}`);
    // Ajoute ici la logique pour le jeu (par exemple, marquer un coup)
  }

  constructor() {}

  ngOnInit(): void {}
}
