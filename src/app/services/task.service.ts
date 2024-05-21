import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {LoadingController} from "@ionic/angular";
import {ToastService} from "./toast.service";
import {RestClientService} from "./rest-client.service";
import {Task} from "../model/task";

@Injectable({
  providedIn: 'root',
})
export class TaskService {

  constructor(private restClient: RestClientService, private toastService: ToastService) {

  }

  addTask(task: any) {
    this.restClient.post("tasks/add", null, task, false, false)
      .subscribe(value => {
      },
      (error: HttpErrorResponse) => {
        let listError:[] = error.error ;
        let displayError= listError.join("\n");
        console.log("Error",displayError)
        this.toastService.presentSimpleToast("bottom",
          displayError
          );
      })

  }
  update(task: any) {
    this.restClient.put("tasks/update", null, task, false, false)
      .subscribe(value => {
        },
        (error: HttpErrorResponse) => {
          let listError:[] = error.error ;
          let displayError= listError.join("\n");
          console.log("Error",displayError)
          this.toastService.presentSimpleToast("bottom",
            displayError
          );
        })

  }
  getTask(id:string):Observable<Task>{
    return this.restClient.get(`tasks/${id}`,null,false)
  }




}
