package com.etan.force_forge_api.repositories;

import com.etan.force_forge_api.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepositories extends MongoRepository<Task,String> {
}
