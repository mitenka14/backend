package kekstarter.services;

import kekstarter.dto.companiesDto.CompaniesInfoDto;
import kekstarter.mapers.CompaniesInfoMapper;
import kekstarter.repositories.CompaniesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesService {

    private final CompaniesRepo companiesRepo;
    private final CompaniesInfoMapper companiesInfoMapper;

    public CompaniesService(CompaniesRepo companiesRepo, CompaniesInfoMapper companiesInfoMapper) {
        this.companiesRepo = companiesRepo;
        this.companiesInfoMapper = companiesInfoMapper;
    }

    public List<CompaniesInfoDto> getCompanies() {
        return companiesInfoMapper.makeListDto(companiesRepo.findAll());
    }
}
