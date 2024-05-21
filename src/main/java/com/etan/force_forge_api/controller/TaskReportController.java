package com.etan.force_forge_api.controller;

import com.etan.force_forge_api.Service.TaskTrackerService;
import com.etan.force_forge_api.model.TaskReport;
import com.etan.force_forge_api.model.TaskReportRequest;
import com.etan.force_forge_api.model.TaskTracker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks/reports/")
public class TaskReportController {

    @Autowired
    TaskTrackerService taskTrackerService;


    @PostMapping("done")
    public TaskReport done(@Valid @RequestBody TaskReportRequest reportRequest){
        return taskTrackerService.markTaskAsDone(reportRequest);
    }
    @GetMapping("tracker/{taskId}")
    public TaskTracker taskTracker(@PathVariable("taskId") String taskId){
        return taskTrackerService.taskTracker(taskId);
    }
}
