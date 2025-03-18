import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/book.service';
import { Book } from '../../models/book';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css'],
})
export class BookFormComponent implements OnInit {
  book: any = {
    // bookId: 2,
    title: '',
    publishedDate: new Date(),
    genre: { genreId: 0, genreName: '' },
    authors: [],
  };
  isEditMode = false;

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.getBookById(+id);
    }
  }

  getBookById(id: number): void {
    this.bookService.getBookById(id).subscribe((data) => {
      this.book = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.bookService
        .updateBook(this.book.bookId, this.book)
        .subscribe(() => {
          this.router.navigate(['/books']);
        });
    } else {
      this.bookService.createBook(this.book).subscribe(() => {
        this.router.navigate(['/books']);
      });
    }
  }
}
