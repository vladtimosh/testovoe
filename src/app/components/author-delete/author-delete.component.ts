import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../../services/author.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-author-delete',
  standalone: true,
  imports: [],
  templateUrl: './author-delete.component.html',
  styleUrls: ['./author-delete.component.css'],
})
export class AuthorDeleteComponent implements OnInit {
  constructor(
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.deleteAuthor(+id);
    }
  }

  deleteAuthor(id: number): void {
    this.authorService.deleteAuthor(id).subscribe(() => {
      this.router.navigate(['/authors']);
    });
  }
}
