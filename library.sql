CREATE TABLE Authors (
    author_id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    birth_date DATE
);

CREATE TABLE Genres (
    genre_id SERIAL PRIMARY KEY,
    genre_name VARCHAR NOT NULL
);

CREATE TABLE Books (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    published_date DATE,
    genre_id INT REFERENCES Genres(genre_id)
);

CREATE TABLE Members (
    member_id SERIAL PRIMARY KEY,
    full_name VARCHAR NOT NULL,
    membership_date DATE
);

CREATE TABLE Loans (
    loan_id SERIAL PRIMARY KEY,
    book_id INT REFERENCES Books(book_id),
    member_id INT REFERENCES Members(member_id),
    loan_date DATE,
    return_date DATE
);

CREATE TABLE Book_Authors (
    book_id INT REFERENCES Books(book_id),
    author_id INT REFERENCES Authors(author_id),
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE Reviews (
    review_id SERIAL PRIMARY KEY,
    book_id INT REFERENCES Books(book_id),
    member_id INT REFERENCES Members(member_id),
    rating INT NOT NULL,
    comment TEXT
);