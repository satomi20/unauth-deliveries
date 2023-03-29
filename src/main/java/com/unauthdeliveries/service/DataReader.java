package com.unauthdeliveries.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.unauthdeliveries.model.Login;
import com.unauthdeliveries.model.Posting;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataReader {

    public List<Login> readLogins(String filePath) {
        List<Login> logins = new ArrayList<>();
            try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
                String[] header = reader.readNext();
                String[] line;
                while ((line = reader.readNext()) != null) {
                    if (line.length < 5) {
                        throw new CsvValidationException("Line does not have enough elements");
                    }
                    Login login = new Login();
                    login.setApplication(line[0]);
                    login.setAppAccountName(line[1]);
                    login.setActive(Boolean.parseBoolean(line[2]));
                    login.setJobTitle(line[3]);
                    login.setDepartment(line[4]);
                    logins.add(login);
                }
            } catch (IOException | CsvValidationException e) {
                System.err.println("Error with file: " + e.getMessage());
            }
        return logins;
    }


    public List<Posting> readPostings(String filePath){
        List<Posting> postings = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] header = reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null){
                if (line.length < 10) {
                    System.err.println("Invalid line: " + Arrays.toString(line));
                    continue;
                }
                Posting posting = new Posting();
                posting.setMatDoc(line[0]);
                posting.setItem(line[1]);
                posting.setDocDate(LocalDate.parse(line[2].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                posting.setPostingDate(LocalDate.parse(line[3].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                posting.setMaterialDescription(line[4]);
                posting.setQuantity(Integer.parseInt(line[5]));
                posting.setBun(line[6]);
                posting.setAmountLc(new BigDecimal(line[7].trim()));
                posting.setCurrency(line[8]);
                posting.setUserName(line[9]);
                postings.add(posting);
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error with file: " + e.getMessage());
        }
        return postings;
    }
}
