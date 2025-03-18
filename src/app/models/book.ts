import { Author } from './author';
import { Genre } from './genre';

export class Book {
  bookId: number;
  title: string;
  publishedDate: Date;
  genre: Genre;
  authors: Author[];

  constructor(bookId: number, title: string, publishedDate: Date, genre: Genre, authors: Author[]) {
    this.bookId = bookId;
    this.title = title;
    this.publishedDate = publishedDate;
    this.genre = genre;
    this.authors = authors;
  }
}
