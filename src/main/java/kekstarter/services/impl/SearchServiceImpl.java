package kekstarter.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements kekstarter.services.SearchService {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public List search(String text, String fields[], Class<?> entity){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder =  fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query luceneQuery = queryBuilder.keyword().fuzzy().onFields(fields).matching(text).createQuery();
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, entity);
        return jpaQuery.getResultList();
    }

}
