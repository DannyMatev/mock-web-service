package com.example.mockwebservices.repository;

import com.fasterxml.jackson.databind.JsonNode;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EntityRepository {

    String saveEntity(String entityName, String entity);

    Document updateEntity(String entityName, String entityId, String entity);

    void deleteEntity(String entityName, String entityId);

    List<String> fetchEntities(String entityName);

    String fetchEntityById(String entityName, String entityId);

    Set<String> fetchCollections();
}
