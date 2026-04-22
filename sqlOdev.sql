CREATE TABLE Book (
    BookID SERIAL PRIMARY KEY,
    ISBN VARCHAR(20) UNIQUE NOT NULL,
    Title VARCHAR(255) NOT NULL,
    Publisher VARCHAR(100),
    PublicationYear INTEGER
);

CREATE TABLE Author (
    AuthorID SERIAL PRIMARY KEY,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Biography TEXT
);

CREATE TABLE BookAuthor (
    BookID INTEGER REFERENCES Book(BookID),
    AuthorID INTEGER REFERENCES Author(AuthorID),
    PRIMARY KEY (BookID, AuthorID)
);

CREATE TABLE Category (
    CategoryID SERIAL PRIMARY KEY,
    CategoryName VARCHAR(100) NOT NULL
);

CREATE TABLE BookCategory (
    BookID INTEGER REFERENCES Book(BookID),
    CategoryID INTEGER REFERENCES Category(CategoryID),
    PRIMARY KEY (BookID, CategoryID)
);

CREATE TABLE Location (
    LocationID SERIAL PRIMARY KEY,
    ShelfNumber VARCHAR(20),
    Floor INTEGER,
    Section VARCHAR(50)
);

CREATE TABLE BookCopy (
    BarcodeID SERIAL PRIMARY KEY,
    BookID INTEGER REFERENCES Book(BookID),
    Status VARCHAR(50) DEFAULT 'Available',
    LocationID INTEGER REFERENCES Location(LocationID)
);

CREATE TABLE Student (
    StudentID SERIAL PRIMARY KEY,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20),
    MembershipDate DATE DEFAULT CURRENT_DATE
);
CREATE TABLE Staff (
    StaffID SERIAL PRIMARY KEY,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Username VARCHAR(50) UNIQUE NOT NULL,
    PasswordHash VARCHAR(255) NOT NULL,
    Role VARCHAR(50)
);

CREATE TABLE Loan (
    LoanID SERIAL PRIMARY KEY,
    BarcodeID INTEGER REFERENCES BookCopy(BarcodeID),
    StudentID INTEGER REFERENCES Student(StudentID),
    StaffID INTEGER REFERENCES Staff(StaffID),
    DueDate DATE NOT NULL,
    ReturnDate DATE -- İade edilmediyse NULL kalır
);

CREATE TABLE Fine (
    FineID SERIAL PRIMARY KEY,
    LoanID INTEGER REFERENCES Loan(LoanID),
    Amount DECIMAL(10,2) NOT NULL,
    IsPaid BOOLEAN DEFAULT FALSE,
    DelayDays INTEGER
);

CREATE TABLE Reservation (
    ReservationID SERIAL PRIMARY KEY,
    BookID INTEGER REFERENCES Book(BookID),
    StudentID INTEGER REFERENCES Student(StudentID),
    ReservationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Status VARCHAR(50) DEFAULT 'Pending'
);
INSERT INTO Author (FirstName, LastName, Biography) VALUES 
('Orhan', 'Pamuk', 'Nobel ödülü alan ilk Türk yazar.'),
('J.K.', 'Rowling', 'Harry Potter serisiyle tanınır.'),
('Victor', 'Hugo', 'Fransız edebiyatının en meşhur isimlerinden, Sefillerin yazarı.'),
('Zülfü', 'Livaneli', 'Hem müzisyen hem de çok okunan romanların yazarı.'),
('Dan', 'Brown', 'Da Vinci Şifresi gibi macera ve polisiye romanları yazar.');

INSERT INTO Book (ISBN, Title, Publisher, PublicationYear) VALUES 
('978975080001', 'Kar', 'İletişim Yayınları', 2002),
('978045152492', 'Harry Potter Felsefe Taşı', 'YKY', 1997),
('978975363163', 'Sefiller', 'Can Yayınları', 1862),
('978080521044', 'Serenad', 'Doğan Kitap', 2011),
('978605090415', 'Da Vinci Şifresi', 'Altın Kitaplar', 2003);

INSERT INTO Category (CategoryName) VALUES ('Roman'), ('Tarih'), ('Felsefe'), ('Polisiye'), ('Bilim Kurgu');

INSERT INTO Location (ShelfNumber, Floor, Section) VALUES 
('A-12', 1, 'Türk Edebiyatı'), 
('C-04', 2, 'Dünya Klasikleri'), 
('B-45', 1, 'Çocuk Kitapları'), 
('Z-99', -1, 'Depo'), 
('K-18', 3, 'Araştırma');

INSERT INTO Student (FirstName, LastName, Email, PhoneNumber) VALUES 
('Ahmet', 'Yılmaz', 'ahmet@gmail.com', '05551112233'),
('Ayşe', 'Demir', 'ayse@gmail.com', '05552223344'),
('Mehmet', 'Kaya', 'mehmet@gmail.com', '05553334455'),
('Elif', 'Şahin', 'elif@gmail.com', '05554445566'),
('Can', 'Öztürk', 'can@gmail.com', '05555556677');

INSERT INTO Staff (FirstName, LastName, Username, PasswordHash, Role) VALUES 
('Hakan', 'Gür', 'hakan_admin', 'hash123', 'Admin'),
('Selin', 'Ak', 'selin_kutuphane', 'hash456', 'Librarian'),
('Murat', 'Can', 'murat_stajyer', 'hash789', 'Assistant'),
('Zeynep', 'Ece', 'zeynep_admin', 'hash101', 'Admin'),
('Ali', 'Veli', 'ali_memur', 'hash202', 'Librarian');

INSERT INTO BookCopy (BookID, Status, LocationID) VALUES 
(1, 'Available', 1), (2, 'Loaned', 1), (3, 'Available', 1), (4, 'Maintenance', 4), (5, 'Available', 2);

INSERT INTO Loan (BarcodeID, StudentID, StaffID, DueDate, ReturnDate) VALUES 
(2, 1, 2, '2023-12-01', '2023-11-28'),
(1, 2, 2, '2023-12-10', NULL),
(3, 3, 3, '2023-11-15', '2023-11-20'),
(5, 4, 2, '2023-12-20', NULL),
(2, 5, 3, '2023-11-25', '2023-11-30');

INSERT INTO Fine (LoanID, Amount, IsPaid, DelayDays) VALUES 
(3, 15.50, TRUE, 5), (5, 10.00, FALSE, 5), (1, 0.00, TRUE, 0), (2, 0.00, FALSE, 0), (4, 0.00, FALSE, 0);

INSERT INTO Reservation (BookID, StudentID, Status) VALUES 
(1, 4, 'Pending'), (2, 3, 'Completed'), (3, 5, 'Cancelled'), (4, 1, 'Pending'), (5, 2, 'Pending');

SELECT * FROM Staff WHERE Role = 'Admin';

SELECT * FROM Book WHERE PublicationYear > 2000;

SELECT * FROM Loan WHERE ReturnDate IS NULL;

SELECT * FROM Fine WHERE Amount > 10;

SELECT * FROM Book WHERE Title LIKE '%ar%';

ALTER TABLE Student ADD COLUMN Birthdate DATE;

ALTER TABLE BookCopy ALTER COLUMN Status SET DEFAULT 'Available';

SELECT * FROM Fine WHERE IsPaid = FALSE;













