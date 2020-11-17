package com.example.mockwebservices.controller;

import com.example.mockwebservices.metrics.EntityCounter;
import com.example.mockwebservices.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
public class EntityController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private EntityService entityService;
    private EntityCounter entityCounter;

    @Autowired
    public EntityController(EntityService entityService, EntityCounter entityCounter) {
        this.entityService = entityService;
        this.entityCounter = entityCounter;
    }

    @PostMapping(value = "/{entity}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createEntity(@PathVariable String entity, @RequestBody String payload) {
        entityCounter.handleCreatedEntity();
        String id = entityService.createEntity(entity, payload);
        LOGGER.info(String.format("Created entity '%s' with body \n %s", entity, payload));

        return ResponseEntity.status(HttpStatus.CREATED).body(URI.create(id).toString());
    }

    @PutMapping(value = "/{entity}/{entityId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEntity(@PathVariable String entity, @PathVariable String entityId, @RequestBody String payload) {
        entityService.updateEntity(entity, entityId, payload);
        LOGGER.info(String.format("Updated entity '%s' with body \n %s", entity, payload));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/{entity}/{entityId}")
    public ResponseEntity<Void> deleteEntity(@PathVariable String entity, @PathVariable String entityId) {
        entityService.deleteEntity(entity, entityId);
        LOGGER.info(String.format("Updated entity '%s'", entity));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/{entity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchEntitiesForEntityType(@PathVariable String entity) {
        LOGGER.info(String.format("Fetching all documents for entity type '%s'", entity));

        return ResponseEntity.ok(entityService.fetchAllForEntity(entity).toString());
    }

    @GetMapping(value = "/{entity}/{entityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchEntityById(@PathVariable String entity, @PathVariable String entityId) {
        LOGGER.info(String.format("Fetching entity for entity type '%s' with id \n %s", entity, entityId));

        return ResponseEntity.ok(entityService.fetchEntityById(entity, entityId));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> fetchCollections() {
        LOGGER.info("Fetching all entity types");

        return ResponseEntity.ok(entityService.fetchCollections());
    }
}
