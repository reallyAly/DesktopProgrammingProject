CREATE TABLE student (
    entity_id int AUTO_INCREMENT PRIMARY KEY, 
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    RA VARCHAR(255) NOT NULL
);

CREATE TABLE librarian (
    entity_id int AUTO_INCREMENT PRIMARY KEY, 
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL
);

CREATE TABLE book (
    entity_id int AUTO_INCREMENT PRIMARY KEY, 
    book_name VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);

CREATE TABLE loan (
    entity_id int AUTO_INCREMENT PRIMARY KEY, 
    student_id int NOT NULL,
    book_id int NOT NULL,
    devolution_id int DEFAULT 0,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(entity_id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES book(entity_id) ON DELETE CASCADE
);

CREATE TABLE devolution (
    entity_id int AUTO_INCREMENT PRIMARY KEY,
    librarian_id int,
    loan_id int,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (librarian_id) REFERENCES librarian(entity_id) ON DELETE CASCADE,
    FOREIGN KEY (loan_id) REFERENCES loan(entity_id) ON DELETE CASCADE
);