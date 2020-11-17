package com.example.mockwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class TestConfiguration {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @PostConstruct
//    public void init() {
//        for (String collectionName : mongoTemplate.getCollectionNames()) {
//            mongoTemplate.dropCollection(collectionName);
//        }
//    }
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
