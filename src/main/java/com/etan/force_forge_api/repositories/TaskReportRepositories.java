package com.etan.force_forge_api.repositories;

import com.etan.force_forge_api.model.TaskReport;
import com.etan.force_forge_api.model.TaskTracker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskReportRepositories extends MongoRepository<TaskReport,String> {

    List<TaskReport> findAllByTaskIdIs(String taskId);
}
