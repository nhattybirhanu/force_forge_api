package com.etan.force_forge_api.repositories;

import com.etan.force_forge_api.model.Task;
import com.etan.force_forge_api.model.TaskTracker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskTrackerRepositories extends MongoRepository<TaskTracker,String> {
}
