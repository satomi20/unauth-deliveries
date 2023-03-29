package com.unauthdeliveries.service;

import com.unauthdeliveries.model.Login;
import com.unauthdeliveries.model.Posting;
import com.unauthdeliveries.repository.LoginRepository;
import com.unauthdeliveries.repository.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CsvJdbcSaver {

    private final LoginRepository loginRepository;
    private final PostingRepository postingRepository;
    private static final String loginPath = "src/main/resources/logins.csv";
    private static final String postingPath = "src/main/resources/postings.csv";

    @Autowired
    public CsvJdbcSaver(LoginRepository loginRepository, PostingRepository postingRepository) {
        this.loginRepository = loginRepository;
        this.postingRepository = postingRepository;
    }

    public void writeToDb(DataReader dataReader){
        List<Posting> postings = dataReader.readPostings(postingPath);
        List<Login> logins = dataReader.readLogins(loginPath);
        loginRepository.saveAll(logins);
        postings.stream()
                .forEach(posting -> {
                    boolean authorized = logins.stream()
                            .anyMatch(login -> login.getAppAccountName()
                                    .equals(posting.getUserName()) && login.isActive());
                    posting.setAuthorizedDelivery(authorized);
                    postingRepository.save(posting);
                });
    }
}
