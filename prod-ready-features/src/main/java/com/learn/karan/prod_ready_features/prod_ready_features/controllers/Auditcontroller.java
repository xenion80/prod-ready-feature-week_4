package com.learn.karan.prod_ready_features.prod_ready_features.controllers;

import com.learn.karan.prod_ready_features.prod_ready_features.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "audit")
public class Auditcontroller {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/post/{postid}")
    List<PostEntity> getPostRevision(@PathVariable Long postid){
        AuditReader reader= AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revision=reader.getRevisions(PostEntity.class,postid);
        return revision.stream().map(revisionNumber->reader.find(PostEntity.class,postid,revisionNumber))
                .collect(Collectors.toList());
    }


}
