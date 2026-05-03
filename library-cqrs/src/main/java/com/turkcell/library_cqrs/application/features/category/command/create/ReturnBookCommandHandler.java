package com.turkcell.library_cqrs.application.features.category.command.create;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.BookCopy;
import com.turkcell.library_cqrs.domain.entities.Fine;
import com.turkcell.library_cqrs.domain.entities.Loan;
import com.turkcell.library_cqrs.persistence.repositories.BookCopyRepository;
import com.turkcell.library_cqrs.persistence.repositories.FineRepository;
import com.turkcell.library_cqrs.persistence.repositories.LoanRepository;

@Component
public class ReturnBookCommandHandler implements CommandHandler<ReturnBookCommand, UUID> {

    private final LoanRepository loanRepository;
    private final BookCopyRepository bookCopyRepository;
    private final FineRepository fineRepository;

    public ReturnBookCommandHandler(
            LoanRepository loanRepository,
            BookCopyRepository bookCopyRepository,
            FineRepository fineRepository) {
        this.loanRepository = loanRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.fineRepository = fineRepository;
    }

    @Override
    @Transactional
    public UUID handle(ReturnBookCommand command) {

        Loan loan = loanRepository.findById(command.loanId())
                .orElseThrow(() -> new RuntimeException("Ödünç kaydı bulunamadı."));

        if (loan.getReturnDate() != null) {
            throw new RuntimeException("Bu kitap zaten iade edilmiş.");
        }

        LocalDate returnDate = command.returnDate();

        if (returnDate == null) {
            returnDate = LocalDate.now();
        }

        loan.setReturnDate(returnDate);

        BookCopy bookCopy = loan.getBookCopy();
        bookCopy.setStatus("AVAILABLE");

        if (returnDate.isAfter(loan.getDueDate())) {

            long delayDays = ChronoUnit.DAYS.between(loan.getDueDate(), returnDate);

            BigDecimal dailyFineAmount = BigDecimal.valueOf(5);
            BigDecimal totalFineAmount = dailyFineAmount.multiply(BigDecimal.valueOf(delayDays));

            Fine fine = new Fine();
            fine.setLoan(loan);
            fine.setDelayDays((int) delayDays);
            fine.setAmount(totalFineAmount);
            fine.setIsPaid(false);

            fineRepository.save(fine);
        }

        bookCopyRepository.save(bookCopy);

        Loan savedLoan = loanRepository.save(loan);

        return savedLoan.getId();
    }
}