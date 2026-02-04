//package com.app.backend.configs;
//
//import javax.sql.datasource;
//
//import org.springframework.beans.factory.annotation.qualifier;
//import org.springframework.boot.autoconfigure.jdbc.datasourceproperties;
//import org.springframework.boot.context.properties.configurationproperties;
//import org.springframework.boot.orm.jpa.entitymanagerfactorybuilder;
//import org.springframework.context.annotation.bean;
//import org.springframework.context.annotation.configuration;
//import org.springframework.context.annotation.primary;
//import org.springframework.data.jpa.repository.config.enablejparepositories;
//import org.springframework.orm.jpa.jpatransactionmanager;
//import org.springframework.orm.jpa.localcontainerentitymanagerfactorybean;
//import org.springframework.transaction.platformtransactionmanager;
//import org.springframework.transaction.annotation.enabletransactionmanagement;
//
//import jakarta.persistence.entitymanagerfactory;
//import lombok.nonnull;
//
//@configuration
//@enabletransactionmanagement
//@enablejparepositories(
//        basepackages = "com.app.backend.repositories.primary",
//        entitymanagerfactoryref = "primaryentitymanagerfactory",
//        transactionmanagerref = "primarytransactionmanager"
//)
//public class primarydbconfig {
//
//    @primary
//    @bean
//    @configurationproperties("spring.datasource.primary")
//    public datasourceproperties primarydatasourceproperties() {
//        return new datasourceproperties();
//    }
//
//    @primary
//    @bean
//    public datasource primarydatasource() {
//        return primarydatasourceproperties().initializedatasourcebuilder().build();
//    }
//
//    @primary
//    @bean
//    public localcontainerentitymanagerfactorybean primaryentitymanagerfactory(
//            @qualifier("primarydatasource") datasource datasource,
//            entitymanagerfactorybuilder builder) {
//        return builder
//                .datasource(datasource)
//                .packages("com.app.backend.entities.primary")
//                .persistenceunit("primary")
//                .build();
//    }
//
//    @primary
//    @bean
//    public platformtransactionmanager primarytransactionmanager(
//            @qualifier("primaryentitymanagerfactory") @nonnull entitymanagerfactory entitymanagerfactory) {
//        return new jpatransactionmanager(entitymanagerfactory);
//    }
//}