import { Component, OnInit } from '@angular/core';
import { GenreService } from '../../services/genre.service';
import { Genre } from '../../models/genre';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-genre-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './genre-details.component.html',
  styleUrls: ['./genre-details.component.css'],
})
export class GenreDetailsComponent implements OnInit {
  genre: Genre | undefined;

  constructor(
    private genreService: GenreService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.getGenreById(+id);
    }
  }

  getGenreById(id: number): void {
    this.genreService.getGenreById(id).subscribe((data) => {
      this.genre = data;
    });
  }
}
