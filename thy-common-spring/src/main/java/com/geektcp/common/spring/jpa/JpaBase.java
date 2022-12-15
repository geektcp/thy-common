package com.geektcp.common.spring.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by tanghaiyang on 2018/12/25.
 */
@NoRepositoryBean
public class JpaBase {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    protected JPAQueryFactory jpa;

    @PostConstruct
    public void initFactory() {
        jpa = new JPAQueryFactory(entityManager);
    }

}
