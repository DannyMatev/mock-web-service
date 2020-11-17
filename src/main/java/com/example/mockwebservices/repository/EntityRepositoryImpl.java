package com.example.mockwebservices.repository;

import com.example.mockwebservices.controller.EntityController;
import com.example.mockwebservices.exception.EntityNotFoundException;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
public class EntityRepositoryImpl implements EntityRepository {

    private MongoTemplate mongoTemplate;


    private static final Logger LOGGER = LoggerFactory.getLogger(EntityController.class);

    @Autowired
    public EntityRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public String saveEntity(String entityName, String entity) {
        Document document = Document.parse(entity);

        Document savedEntity = mongoTemplate.save(document, entityName);

        LOGGER.info(String.format("Entity saved in collection %s", entityName));

        return savedEntity.get("_id").toString();
    }

    public Document updateEntity(String entityName, String entityId, String entity) {
        Document document = Document.parse(entity);

        Document savedEntity = mongoTemplate.findAndReplace(Query.query(Criteria.where("_id").is(entityId)), document, entityName);

        if(savedEntity== null) {
            throw new EntityNotFoundException(String.format("Entity of type '%s' and id '%s' not found", entityName, entityId));
        }

        LOGGER.info(String.format("Entity saved in collection %s", entityName));

        return savedEntity;
    }

    public void deleteEntity(String entityName, String entityId) {
        DeleteResult deleteResult = mongoTemplate.remove(Query.query(Criteria.where("_id").is(entityId)), entityName);

        if(!deleteResult.wasAcknowledged()) {
            throw new EntityNotFoundException(String.format("Entity of type '%s' and id '%s' not found", entityName, entityId));
        }
    }

    public List<String> fetchEntities(String entityName) {
        List<String> result = new ArrayList<>();
        mongoTemplate.getCollection(entityName).find().cursor().forEachRemaining(e -> result.add(e.toJson()));


        return result;
    }

    public String fetchEntityById(String entityName, String entityId) {
        Document result = mongoTemplate.findById(entityId, Document.class, entityName);

        if (result == null) {
            throw new EntityNotFoundException(String.format("Entity of type '%s' and id '%s' not found", entityName, entityId));
        }

        return result.toJson();
    }

    public Set<String> fetchCollections() {
        return mongoTemplate.getCollectionNames();
    }


}
