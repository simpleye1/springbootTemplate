package com.ysg.servicetemplate.common.general.baseentity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class BatchRepository<T_DOMAIN, T_PO> {

    @PersistenceContext
    private final EntityManager entityManager;

    public void batchCreate(List<T_DOMAIN> list, Function<T_DOMAIN, T_PO> function) {
        batchOperation(list, function, entityManager::persist);
    }

    public void batchUpdate(List<T_DOMAIN> list, Function<T_DOMAIN, T_PO> function) {
        batchOperation(list, function, entityManager::merge);
    }

    private void batchOperation(List<T_DOMAIN> list, Function<T_DOMAIN, T_PO> function, Consumer<T_PO> operation) {
        list.stream()
                .map(function)
                .forEach(operation);
        entityManager.flush();
        entityManager.clear();
    }
}
