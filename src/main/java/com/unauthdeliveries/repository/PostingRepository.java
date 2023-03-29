package com.unauthdeliveries.repository;

import com.unauthdeliveries.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findByDocDateBetween(LocalDate startDate, LocalDate endDate);

    List<Posting> findByDocDateBetweenAndAuthorizedDelivery(LocalDate startDate, LocalDate endDate, boolean isAuth);
}
