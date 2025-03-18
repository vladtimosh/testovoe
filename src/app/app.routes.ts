import { Routes } from '@angular/router';
import { AuthorListComponent } from './components/author-list/author-list.component';
import { AuthorDetailsComponent } from './components/author-details/author-details.component';
import { AuthorFormComponent } from './components/author-form/author-form.component';
import { AuthorDeleteComponent } from './components/author-delete/author-delete.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { BookFormComponent } from './components/book-form/book-form.component';
import { BookDeleteComponent } from './components/book-delete/book-delete.component';
import { GenreListComponent } from './components/genre-list/genre-list.component';
import { GenreDetailsComponent } from './components/genre-details/genre-details.component';
import { GenreFormComponent } from './components/genre-form/genre-form.component';
import { GenreDeleteComponent } from './components/genre-delete/genre-delete.component';
import { LoanListComponent } from './components/loan-list/loan-list.component';
import { LoanDetailsComponent } from './components/loan-details/loan-details.component';
import { LoanFormComponent } from './components/loan-form/loan-form.component';
import { LoanDeleteComponent } from './components/loan-delete/loan-delete.component';
import { MemberListComponent } from './components/member-list/member-list.component';
import { MemberDetailsComponent } from './components/member-details/member-details.component';
import { MemberFormComponent } from './components/member-form/member-form.component';
import { MemberDeleteComponent } from './components/member-delete/member-delete.component';
import { ReviewListComponent } from './components/review-list/review-list.component';
import { ReviewDetailsComponent } from './components/review-details/review-details.component';
import { ReviewFormComponent } from './components/review-form/review-form.component';
import { ReviewDeleteComponent } from './components/review-delete/review-delete.component';

export const routes: Routes = [
  { path: 'authors', component: AuthorListComponent },
  { path: 'authors/create', component: AuthorFormComponent },
  { path: 'authors/:id', component: AuthorDetailsComponent },
  { path: 'authors/:id/edit', component: AuthorFormComponent },
  { path: 'authors/:id/delete', component: AuthorDeleteComponent },
  { path: '', redirectTo: '/authors', pathMatch: 'full' },
  { path: 'books', component: BookListComponent },
  { path: 'books/create', component: BookFormComponent },
  { path: 'books/:id', component: BookDetailsComponent },
  { path: 'books/:id/edit', component: BookFormComponent },
  { path: 'books/:id/delete', component: BookDeleteComponent },
  { path: '', redirectTo: '/books', pathMatch: 'full' },
  { path: 'genres', component: GenreListComponent },
  { path: 'genres/create', component: GenreFormComponent },
  { path: 'genres/:id', component: GenreDetailsComponent },
  { path: 'genres/:id/edit', component: GenreFormComponent },
  { path: 'genres/:id/delete', component: GenreDeleteComponent },
  { path: '', redirectTo: '/genres', pathMatch: 'full' },
  { path: 'loans', component: LoanListComponent },
  { path: 'loans/create', component: LoanFormComponent },
  { path: 'loans/:id', component: LoanDetailsComponent },
  { path: 'loans/:id/edit', component: LoanFormComponent },
  { path: 'loans/:id/delete', component: LoanDeleteComponent },
  { path: '', redirectTo: '/loans', pathMatch: 'full' },
  { path: 'members', component: MemberListComponent },
  { path: 'members/create', component: MemberFormComponent },
  { path: 'members/:id', component: MemberDetailsComponent },
  { path: 'members/:id/edit', component: MemberFormComponent },
  { path: 'members/:id/delete', component: MemberDeleteComponent },
  { path: '', redirectTo: '/members', pathMatch: 'full' },
  { path: 'reviews', component: ReviewListComponent },
  { path: 'reviews/create', component: ReviewFormComponent },
  { path: 'reviews/:id', component: ReviewDetailsComponent },
  { path: 'reviews/:id/edit', component: ReviewFormComponent },
  { path: 'reviews/:id/delete', component: ReviewDeleteComponent },
  { path: '', redirectTo: '/reviews', pathMatch: 'full' },
];



