package com.io.qleoz12.api.services;

import com.io.qleoz12.api.entities.Schools;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SchoolsService {
    CompletableFuture<Schools> save(Schools school);
    CompletableFuture<List<Schools>> saveAll(Iterable<Schools> schools);
    CompletableFuture<Schools> getOneById(Long id);
    CompletableFuture<List<Schools>> getAll();
    CompletableFuture<Schools> update(Schools school, Long id);
    CompletableFuture<Schools> delete(Long id);
}
