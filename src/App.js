import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AuthorList from './components/AuthorList';
import AuthorDetails from './components/AuthorDetails';
import AuthorForm from './components/AuthorForm';
import AuthorDelete from './components/AuthorDelete';
import BookList from './components/BookList';
import BookDetails from './components/BookDetails';
import BookForm from './components/BookForm';
import BookDelete from './components/BookDelete';
import GenreList from './components/GenreList';
import GenreDetails from './components/GenreDetails';
import GenreForm from './components/GenreForm';
import GenreDelete from './components/GenreDelete';
import LoanList from './components/LoanList';
import LoanDetails from './components/LoanDetails';
import LoanForm from './components/LoanForm';
import LoanDelete from './components/LoanDelete';
import MemberList from './components/MemberList';
import MemberDetails from './components/MemberDetails';
import MemberForm from './components/MemberForm';
import MemberDelete from './components/MemberDelete';
import ReviewList from './components/ReviewList';
import ReviewDetails from './components/ReviewDetails';
import ReviewForm from './components/ReviewForm';
import ReviewDelete from './components/ReviewDelete';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/authors" element={<AuthorList />} />
        <Route path="/authors/create" element={<AuthorForm />} />
        <Route path="/authors/:id" element={<AuthorDetails />} />
        <Route path="/authors/:id/edit" element={<AuthorForm />} />
        <Route path="/authors/:id/delete" element={<AuthorDelete />} />
        <Route path="/" element={<AuthorList />} />
        <Route path="/books" element={<BookList />} />
        <Route path="/books/create" element={<BookForm />} />
        <Route path="/books/:id" element={<BookDetails />} />
        <Route path="/books/:id/edit" element={<BookForm />} />
        <Route path="/books/:id/delete" element={<BookDelete />} />
        <Route path="/" element={<BookList />} />
        <Route path="/genres" element={<GenreList />} />
        <Route path="/genres/create" element={<GenreForm />} />
        <Route path="/genres/:id" element={<GenreDetails />} />
        <Route path="/genres/:id/edit" element={<GenreForm />} />
        <Route path="/genres/:id/delete" element={<GenreDelete />} />
        <Route path="/" element={<GenreList />} />
        <Route path="/loans" element={<LoanList />} />
        <Route path="/loans/create" element={<LoanForm />} />
        <Route path="/loans/:id" element={<LoanDetails />} />
        <Route path="/loans/:id/edit" element={<LoanForm />} />
        <Route path="/loans/:id/delete" element={<LoanDelete />} />
        <Route path="/" element={<LoanList />} />
        <Route path="/members" element={<MemberList />} />
        <Route path="/members/create" element={<MemberForm />} />
        <Route path="/members/:id" element={<MemberDetails />} />
        <Route path="/members/:id/edit" element={<MemberForm />} />
        <Route path="/members/:id/delete" element={<MemberDelete />} />
        <Route path="/" element={<MemberList />} />
        <Route path="/reviews" element={<ReviewList />} />
        <Route path="/reviews/create" element={<ReviewForm />} />
        <Route path="/reviews/:id" element={<ReviewDetails />} />
        <Route path="/reviews/:id/edit" element={<ReviewForm />} />
        <Route path="/reviews/:id/delete" element={<ReviewDelete />} />
        <Route path="/" element={<ReviewList />} />
      </Routes>
    </Router>
  );
}

export default App;
