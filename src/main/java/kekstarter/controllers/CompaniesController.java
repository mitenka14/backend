package kekstarter.controllers;

import kekstarter.dto.companiesDto.CompaniesInfoDto;
import kekstarter.services.CompaniesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompaniesController {

    private final CompaniesService companiesService;

    public CompaniesController(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    @GetMapping
    public List<CompaniesInfoDto> getCompanies() {
        return this.companiesService.getCompanies();
    }
}
