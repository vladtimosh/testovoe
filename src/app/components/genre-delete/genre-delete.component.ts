import { Component, OnInit } from '@angular/core';
import { GenreService } from '../../services/genre.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-genre-delete',
  standalone: true,
  imports: [],
  templateUrl: './genre-delete.component.html',
  styleUrls: ['./genre-delete.component.css'],
})
export class GenreDeleteComponent implements OnInit {
  constructor(
    private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.deleteGenre(+id);
    }
  }

  deleteGenre(id: number): void {
    this.genreService.deleteGenre(id).subscribe(() => {
      this.router.navigate(['/genres']);
    });
  }
}
