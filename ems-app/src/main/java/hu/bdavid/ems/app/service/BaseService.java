package hu.bdavid.ems.app.service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.Assert;

/**
 * BaseService a Crud műveletekhez.
 *
 * @author balogh.david
 */
public class BaseService<T, ID extends String> {

    @Autowired
    private CrudRepository<T, ID> crudRepository;

    /**
     * Összes entitás.
     *
     * @return az entitások.
     */
    public List<T> findAll() {
        return StreamSupport.stream(crudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

    }

    /**
     * Entitás keresése azonosító szerint.
     *
     * @param entityId az entitás azonosítója.
     * @return az entitás.
     */
    public T findById(ID entityId) {
        Assert.notNull(entityId, "entityID should be not null");
        return crudRepository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by id: " + entityId));
    }

    /**
     * Entitás törlése azonosító szerint.
     *
     * @param entityId az entitás azonosítója.
     */
    public void deleteById(ID entityId) {
        Assert.notNull(entityId, "entityID should be not null");
        crudRepository.deleteById(entityId);
    }

    /**
     * Entitás létrehozása.
     *
     * @param entity az entitás.
     * @return a létrehozott entitás.
     */
    public T save(T entity) {
        Assert.notNull(entity, "entity should be not null");
        return crudRepository.save(entity);
    }
}
