package com.turkcell.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.FineResponse;
import com.turkcell.library.entity.Fine;
import com.turkcell.library.repository.FineRepository;

@Service
public class FineServiceImpl {

    private final FineRepository fineRepository;

    public FineServiceImpl(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public List<FineResponse> getAll() {
        List<Fine> fines = fineRepository.findAll();
        List<FineResponse> responseList = new ArrayList<>();

        for (Fine fine : fines) {
            responseList.add(mapToResponse(fine));
        }

        return responseList;
    }

    public FineResponse getById(UUID id) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ceza kaydı bulunamadı."));

        return mapToResponse(fine);
    }

    public FineResponse markAsPaid(UUID id) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ceza kaydı bulunamadı."));

        fine.setIsPaid(true);

        fine = fineRepository.save(fine);

        return mapToResponse(fine);
    }

    public void delete(UUID id) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ceza kaydı bulunamadı."));

        fineRepository.delete(fine);
    }

    private FineResponse mapToResponse(Fine fine) {
        FineResponse response = new FineResponse();

        response.setId(fine.getId());
        response.setLoanId(fine.getLoan().getId());
        response.setAmount(fine.getAmount());
        response.setIsPaid(fine.getIsPaid());
        response.setDelayDays(fine.getDelayDays());

        return response;
    }
}