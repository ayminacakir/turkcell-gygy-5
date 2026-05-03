package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.LoanRepository;

@Component
public class GetAllLoansQueryHandler implements QueryHandler<GetAllLoansQuery, List<ListLoanResponse>> {

    private final LoanRepository loanRepository;

    public GetAllLoansQueryHandler(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListLoanResponse> handle(GetAllLoansQuery query) {

        return loanRepository.findAll()
                .stream()
                .map(loan -> new ListLoanResponse(
                        loan.getId(),

                        loan.getBookCopy().getId(),
                        loan.getBookCopy().getBook().getTitle(),

                        loan.getStudent().getId(),
                        loan.getStudent().getFirstName() + " " + loan.getStudent().getLastName(),

                        loan.getStaff().getId(),
                        loan.getStaff().getFirstName() + " " + loan.getStaff().getLastName(),

                        loan.getDueDate(),
                        loan.getReturnDate(),

                        loan.getReturnDate() == null ? "ACTIVE" : "RETURNED"))
                .toList();
    }
}
