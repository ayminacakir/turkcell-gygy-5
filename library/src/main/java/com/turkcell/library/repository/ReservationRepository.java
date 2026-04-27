package com.turkcell.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.library.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

}