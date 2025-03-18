import { Book } from './book';
import { Member } from './member';

export class Loan {
  loanId: number;
  book: Book;
  member: Member;
  loanDate: Date;
  returnDate: Date;

  constructor(loanId: number, book: Book, member: Member, loanDate: Date, returnDate: Date) {
    this.loanId = loanId;
    this.book = book;
    this.member = member;
    this.loanDate = loanDate;
    this.returnDate = returnDate;
  }

}
