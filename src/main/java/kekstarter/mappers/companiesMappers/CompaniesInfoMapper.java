package kekstarter.mappers.companiesMappers;

import kekstarter.dto.CompaniesDto;
import kekstarter.models.Companies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompaniesInfoMapper {

    private final CompaniesAddMapper companiesAddMapper;

    public CompaniesInfoMapper(CompaniesAddMapper companiesAddMapper) {
        this.companiesAddMapper = companiesAddMapper;
    }


    public CompaniesDto makeDto(Companies companies) {
        CompaniesDto companiesAddDto = new CompaniesDto();
        companiesAddDto.setId(companies.getId());
        companiesAddDto.setName(companies.getName());
        companiesAddDto.setText(companies.getText());
        return companiesAddDto;
    }

    public List<CompaniesDto> makeListDto(List<Companies> companiesList) {
        List<CompaniesDto> companiesInfoDtoList = new ArrayList<>();
        for (Companies companies : companiesList) {
            companiesInfoDtoList.add(makeDto(companies));
        }
        return companiesInfoDtoList;
    }
}
