package kekstarter.services;

import kekstarter.dto.CompaniesDto;
import kekstarter.mappers.companiesMappers.CompaniesAddMapper;
import kekstarter.mappers.companiesMappers.CompaniesInfoMapper;
import kekstarter.models.Companies;
import kekstarter.repositories.CompaniesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesService {

    private final CompaniesRepo companiesRepo;
    private final CompaniesInfoMapper companiesInfoMapper;
    private final CompaniesAddMapper companiesAddMapper;

    public CompaniesService(CompaniesRepo companiesRepo, CompaniesInfoMapper companiesInfoMapper, CompaniesAddMapper companiesAddMapper) {
        this.companiesRepo = companiesRepo;
        this.companiesInfoMapper = companiesInfoMapper;
        this.companiesAddMapper = companiesAddMapper;
    }

    public void addCompany(CompaniesDto companiesDto) {
        Companies company = companiesAddMapper.makeModel(companiesDto);
        this.companiesRepo.save(company);
    }

    public List<CompaniesDto> getCompanies() {
        return companiesInfoMapper.makeListDto(companiesRepo.findAll());
    }
}
