package kekstarter.mapers;

import kekstarter.dto.companiesDto.CompaniesInfoDto;
import kekstarter.models.Companies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompaniesInfoMapper {

    public CompaniesInfoDto makeDto(Companies companies) {
        CompaniesInfoDto companiesInfoDto = new CompaniesInfoDto();
        companiesInfoDto.setId(companies.getId());
        companiesInfoDto.setName(companies.getName());
        companiesInfoDto.setText(companies.getText());
        return companiesInfoDto;
    }

    public List<CompaniesInfoDto> makeListDto(List<Companies> companiesList) {
        List<CompaniesInfoDto> companiesInfoDtoList = new ArrayList<>();
        for (Companies companies : companiesList) {
            companiesInfoDtoList.add(makeDto(companies));
        }
        return companiesInfoDtoList;
    }
}
