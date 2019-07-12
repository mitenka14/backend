package kekstarter.mappers.companiesMappers;

import kekstarter.dto.CompaniesDto;
import kekstarter.models.Companies;
import org.springframework.stereotype.Component;

@Component
public class CompaniesAddMapper {

    public CompaniesDto makeDto(Companies companies) {
        CompaniesDto companyData = new CompaniesDto();
        companyData.setId(companies.getId());
        companyData.setName(companies.getName());
        companyData.setText(companies.getText());
        return companyData;
    }

    public Companies makeModel(CompaniesDto companiesDto) {
        Companies companies = new Companies();
        companies.setId(companiesDto.getId());
        companies.setName(companiesDto.getName());
        companies.setText(companiesDto.getText());
        return companies;
    }

}
