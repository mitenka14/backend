package kekstarter.controllers;

import kekstarter.dto.CompaniesDto;
import kekstarter.services.CompaniesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CompaniesController {

    private final CompaniesService companiesService;

    public CompaniesController(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    @GetMapping("/list")
    public List<CompaniesDto> getCompanies() {
        return this.companiesService.getCompanies();
    }

    @PostMapping("/addcompany")
    public void addCompany(@RequestBody CompaniesDto post) {
        this.companiesService.addCompany(post);
    }
}
