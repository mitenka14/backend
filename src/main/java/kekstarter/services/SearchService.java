package kekstarter.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SearchService {


    @Transactional
    List search(String text, String fields[], Class<?> entity);
}
