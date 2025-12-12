package com.school;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<journal, String> {
}