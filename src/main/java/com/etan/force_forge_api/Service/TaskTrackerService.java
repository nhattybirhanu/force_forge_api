package com.etan.force_forge_api.Service;

import com.etan.force_forge_api.excepetion.ExceptionResponse;
import com.etan.force_forge_api.excepetion.TaskException;
import com.etan.force_forge_api.model.TaskReport;
import com.etan.force_forge_api.model.TaskReportRequest;
import com.etan.force_forge_api.model.TaskTracker;
import com.etan.force_forge_api.repositories.TaskReportRepositories;
import com.etan.force_forge_api.repositories.TaskRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class TaskTrackerService {
    @Autowired
    private TaskReportRepositories taskReportRepositories;
    @Autowired
    private TaskRepositories taskRepositories;

    public TaskReport markTaskAsDone(TaskReportRequest taskReportRequest) {
        AtomicReference<TaskReport> taskTracker = new AtomicReference<>();
        taskRepositories.findById(taskReportRequest.getTaskId()).ifPresentOrElse(task -> {
            log.info("Find task by id {task} ",task);
            if (taskReportRequest.getDate().isBefore(task.getTaskStartDate())){
                throw new TaskException(new ExceptionResponse("Task Start date "+task.getTaskStartDate()+" task report done time "+taskReportRequest.getDate(),"Task  completed date can be before start of task date", HttpStatus.BAD_REQUEST, LocalDateTime.now()));
            }
            TaskReport taskReport = new TaskReport();
            taskReport.setTaskId(task.getId());
            taskReport.setDone(true);
            taskReport.setDate(taskReportRequest.getDate());

            taskReport.setStartTime(taskReportRequest.getStartTime());
            taskReport.setEndTime(taskReportRequest.getEndTime());
            if (taskReportRequest.getDailyNote() != null)
                taskReport.setDailyNote(taskReportRequest.getDailyNote());
            taskReportRepositories.save(taskReport);
            taskTracker.set(taskReport);


        }, () ->  new TaskException(new ExceptionResponse("Task  is not found for id "+taskReportRequest.getTaskId(),"Task  is not found for task report", HttpStatus.NOT_FOUND, LocalDateTime.now())));
        return taskTracker.get();
    }

    public TaskTracker taskTracker(String taskId){
        TaskTracker taskTracker=new TaskTracker(taskId,taskReportRepositories.findAllByTaskIdIs(taskId));
        return taskTracker;
    }

}
