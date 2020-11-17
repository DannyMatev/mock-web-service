package com.example.mockwebservices.service;

import com.example.mockwebservices.exception.EntityNotFoundException;
import com.example.mockwebservices.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EntityService {

    private EntityRepository entityRepository;

    @Autowired
    public EntityService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public String createEntity(String entityName, String entity) {
        return entityRepository.saveEntity(entityName, entity);
    }

    public List<String> fetchAllForEntity(String entityName) {
        return entityRepository.fetchEntities(entityName);
    }

    public Set<String> fetchCollections() {
        return entityRepository.fetchCollections();
    }

    public void deleteEntity(String entityName, String entityId) throws EntityNotFoundException {
        entityRepository.deleteEntity(entityName, entityId);
    }

    public void updateEntity(String entityName, String entityId, String entity) {
        entityRepository.updateEntity(entityName, entityId, entity);
    }

    public String fetchEntityById(String entityName, String entityId) {
        return entityRepository.fetchEntityById(entityName, entityId);
    }

}
