import { Book } from './book';
import { Member } from './member';

export class Review {
  reviewId: number;
  book: Book;
  member: Member;
  rating: number;
  comment: string;

  constructor(reviewId: number, book: Book, member: Member, rating: number, comment: string) {
    this.reviewId = reviewId;
    this.book = book;
    this.member = member;
    this.rating = rating;
    this.comment = comment;
  }

}
