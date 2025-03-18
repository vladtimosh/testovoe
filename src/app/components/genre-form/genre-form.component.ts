import { Component, OnInit } from '@angular/core';
import { GenreService } from '../../services/genre.service';
import { Genre } from '../../models/genre';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from '../../models/author';

@Component({
  selector: 'app-genre-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './genre-form.component.html',
  styleUrls: ['./genre-form.component.css'],
})
export class GenreFormComponent implements OnInit {
  genre: Genre = { genreName: '' } as Genre;
  isEditMode = false;

  constructor(
    private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.getGenreById(+id);
    }
  }

  getGenreById(id: number): void {
    this.genreService.getGenreById(id).subscribe((data) => {
      this.genre = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.genreService
        .updateGenre(this.genre.genreId, this.genre)
        .subscribe(() => {
          this.router.navigate(['/genres']);
        });
    } else {
      this.genreService.createGenre(this.genre).subscribe(() => {
        this.router.navigate(['/genres']);
      });
    }
  }
}
