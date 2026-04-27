package com.turkcell.library.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.CreateLoanRequest;
import com.turkcell.library.dto.FineResponse;
import com.turkcell.library.dto.LoanResponse;
import com.turkcell.library.dto.ReturnLoanRequest;
import com.turkcell.library.entity.BookCopy;
import com.turkcell.library.entity.Fine;
import com.turkcell.library.entity.Loan;
import com.turkcell.library.entity.Staff;
import com.turkcell.library.entity.Student;
import com.turkcell.library.repository.BookCopyRepository;
import com.turkcell.library.repository.FineRepository;
import com.turkcell.library.repository.LoanRepository;
import com.turkcell.library.repository.StaffRepository;
import com.turkcell.library.repository.StudentRepository;

@Service
public class LoanServiceImpl {

    private final LoanRepository loanRepository;
    private final BookCopyRepository bookCopyRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final FineRepository fineRepository;

    public LoanServiceImpl(
            LoanRepository loanRepository,
            BookCopyRepository bookCopyRepository,
            StudentRepository studentRepository,
            StaffRepository staffRepository,
            FineRepository fineRepository) {
        this.loanRepository = loanRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
        this.fineRepository = fineRepository;
    }

    public LoanResponse create(CreateLoanRequest request) {

        BookCopy bookCopy = bookCopyRepository.findById(request.getBookCopyId())
                .orElseThrow(() -> new RuntimeException("Kitap kopyası bulunamadı."));

        if (!"Available".equalsIgnoreCase(bookCopy.getStatus())) {
            throw new RuntimeException("Bu kitap şu anda ödünç verilemez. Durum: " + bookCopy.getStatus());
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı."));

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new RuntimeException("Görevli bulunamadı."));

        Loan loan = new Loan();
        loan.setBookCopy(bookCopy);
        loan.setStudent(student);
        loan.setStaff(staff);
        loan.setDueDate(request.getDueDate());
        loan.setReturnDate(null);

        bookCopy.setStatus("Loaned");

        bookCopyRepository.save(bookCopy);
        loan = loanRepository.save(loan);

        return mapToLoanResponse(loan);
    }

    public List<LoanResponse> getAll() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanResponse> responseList = new ArrayList<>();

        for (Loan loan : loans) {
            responseList.add(mapToLoanResponse(loan));
        }

        return responseList;
    }

    public LoanResponse getById(UUID id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ödünç alma kaydı bulunamadı."));

        return mapToLoanResponse(loan);
    }

    public LoanResponse returnBook(UUID loanId, ReturnLoanRequest request) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Ödünç alma kaydı bulunamadı."));

        if (loan.getReturnDate() != null) {
            throw new RuntimeException("Bu kitap zaten iade edilmiş.");
        }

        LocalDate returnDate = request.getReturnDate();

        if (returnDate == null) {
            returnDate = LocalDate.now();
        }

        loan.setReturnDate(returnDate);

        BookCopy bookCopy = loan.getBookCopy();
        bookCopy.setStatus("Available");

        if (returnDate.isAfter(loan.getDueDate())) {
            long delayDaysLong = ChronoUnit.DAYS.between(loan.getDueDate(), returnDate);
            int delayDays = (int) delayDaysLong;

            BigDecimal dailyFine = BigDecimal.valueOf(5);
            BigDecimal amount = dailyFine.multiply(BigDecimal.valueOf(delayDays));

            Fine fine = new Fine();
            fine.setLoan(loan);
            fine.setDelayDays(delayDays);
            fine.setAmount(amount);
            fine.setIsPaid(false);

            fineRepository.save(fine);
        }

        bookCopyRepository.save(bookCopy);
        loan = loanRepository.save(loan);

        return mapToLoanResponse(loan);
    }

    public void delete(UUID id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ödünç alma kaydı bulunamadı."));

        loanRepository.delete(loan);
    }

    private LoanResponse mapToLoanResponse(Loan loan) {
        LoanResponse response = new LoanResponse();

        response.setId(loan.getId());
        response.setBookTitle(loan.getBookCopy().getBook().getTitle());
        response.setStudentName(
                loan.getStudent().getFirstName() + " " + loan.getStudent().getLastName());
        response.setStaffName(
                loan.getStaff().getFirstName() + " " + loan.getStaff().getLastName());
        response.setDueDate(loan.getDueDate());
        response.setReturnDate(loan.getReturnDate());
        response.setBookCopyStatus(loan.getBookCopy().getStatus());

        return response;
    }

    private FineResponse mapToFineResponse(Fine fine) {
        FineResponse response = new FineResponse();

        response.setId(fine.getId());
        response.setLoanId(fine.getLoan().getId());
        response.setAmount(fine.getAmount());
        response.setIsPaid(fine.getIsPaid());
        response.setDelayDays(fine.getDelayDays());

        return response;
    }
}