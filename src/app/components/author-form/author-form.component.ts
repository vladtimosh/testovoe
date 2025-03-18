import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../../services/author.service';
import { Author } from '../../models/author';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-author-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.css'],
})
export class AuthorFormComponent implements OnInit {
  author: Author = {  name: '', birthDate: new Date() } as Author;
  isEditMode = false;

  constructor(
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.getAuthorById(+id);
    }
  }

  getAuthorById(id: number): void {
    this.authorService.getAuthorById(id).subscribe((data) => {
      this.author = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.authorService
        .updateAuthor(this.author.authorId, this.author)
        .subscribe(() => {
          this.router.navigate(['/authors']);
        });
    } else {
      this.authorService.createAuthor(this.author).subscribe(() => {
        this.router.navigate(['/authors']);
      });
    }
  }
}
