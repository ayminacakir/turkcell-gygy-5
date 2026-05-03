package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.FineRepository;

@Component
public class GetAllFinesQueryHandler implements QueryHandler<GetAllFinesQuery, List<ListFineResponse>> {

    private final FineRepository fineRepository;

    public GetAllFinesQueryHandler(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListFineResponse> handle(GetAllFinesQuery query) {

        return fineRepository.findAll()
                .stream()
                .map(fine -> new ListFineResponse(
                        fine.getId(),
                        fine.getLoan().getId(),

                        fine.getLoan().getStudent().getFirstName()
                                + " "
                                + fine.getLoan().getStudent().getLastName(),

                        fine.getLoan().getBookCopy().getBook().getTitle(),

                        fine.getAmount(),
                        fine.getIsPaid(),
                        fine.getDelayDays()))
                .toList();
    }
}