package com.unauthdeliveries.controller;

import com.unauthdeliveries.model.Posting;
import com.unauthdeliveries.repository.PostingRepository;
import com.unauthdeliveries.service.CsvJdbcSaver;
import com.unauthdeliveries.service.DataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final CsvJdbcSaver csvJdbcSaver;
    private final DataReader dataReader;
    private final PostingRepository postingRepository;

    @Autowired
    public DeliveryController(CsvJdbcSaver csvJdbcSaver, DataReader dataReader, PostingRepository postingRepository) {
        this.csvJdbcSaver = csvJdbcSaver;
        this.dataReader = dataReader;
        this.postingRepository = postingRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(){
        csvJdbcSaver.writeToDb(dataReader);
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }

    @GetMapping("/filter")
    public List<Posting> getPostingsByDateRange(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                @RequestParam(name = "authorized", required = false) Boolean authorized) {
        if (authorized != null) {
            return postingRepository.findByDocDateBetweenAndAuthorizedDelivery(startDate, endDate, authorized);
        } else {
            return postingRepository.findByDocDateBetween(startDate, endDate);
        }
    }
}
