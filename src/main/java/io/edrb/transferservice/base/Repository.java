package io.edrb.transferservice.base;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    T save(T entity);

    T deleteById(String id);

    Optional<T> findById(String id);

    List<T> findAll();
}
