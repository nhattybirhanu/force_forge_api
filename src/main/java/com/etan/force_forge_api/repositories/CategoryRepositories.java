package com.etan.force_forge_api.repositories;

import com.etan.force_forge_api.model.Category;
import com.etan.force_forge_api.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositories extends MongoRepository<Category,String> {
}
