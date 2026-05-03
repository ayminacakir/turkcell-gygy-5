package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.BookCopy;
import com.turkcell.library_cqrs.domain.entities.Loan;
import com.turkcell.library_cqrs.domain.entities.Staff;
import com.turkcell.library_cqrs.domain.entities.Student;
import com.turkcell.library_cqrs.persistence.repositories.BookCopyRepository;
import com.turkcell.library_cqrs.persistence.repositories.LoanRepository;
import com.turkcell.library_cqrs.persistence.repositories.StaffRepository;
import com.turkcell.library_cqrs.persistence.repositories.StudentRepository;

@Component
public class BorrowBookCommandHandler implements CommandHandler<BorrowBookCommand, UUID> {

    private final LoanRepository loanRepository;
    private final BookCopyRepository bookCopyRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;

    public BorrowBookCommandHandler(
            LoanRepository loanRepository,
            BookCopyRepository bookCopyRepository,
            StudentRepository studentRepository,
            StaffRepository staffRepository) {
        this.loanRepository = loanRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    @Transactional
    public UUID handle(BorrowBookCommand command) {

        BookCopy bookCopy = bookCopyRepository.findById(command.bookCopyId())
                .orElseThrow(() -> new RuntimeException("Kitap kopyası bulunamadı."));

        Student student = studentRepository.findById(command.studentId())
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı."));

        Staff staff = staffRepository.findById(command.staffId())
                .orElseThrow(() -> new RuntimeException("Görevli bulunamadı."));

        if (!"AVAILABLE".equals(bookCopy.getStatus())) {
            throw new RuntimeException("Bu kitap kopyası şu anda ödünç verilemez.");
        }

        bookCopy.setStatus("BORROWED");

        Loan loan = new Loan();
        loan.setBookCopy(bookCopy);
        loan.setStudent(student);
        loan.setStaff(staff);
        loan.setDueDate(command.dueDate());
        loan.setReturnDate(null);

        Loan savedLoan = loanRepository.save(loan);

        bookCopyRepository.save(bookCopy);

        return savedLoan.getId();
    }
}