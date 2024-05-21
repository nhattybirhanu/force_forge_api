import {Component, inject, OnInit, ViewChild} from '@angular/core';

import {
  AlertController,
  IonButton,
  IonButtons,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle,
  IonCheckbox,
  IonChip,
  IonCol,
  IonContent,
  IonDatetime,
  IonDatetimeButton,
  IonFooter,
  IonGrid,
  IonHeader,
  IonIcon,
  IonInput,
  IonItem,
  IonItemOption,
  IonItemOptions,
  IonLabel,
  IonList, IonListHeader,
  IonMenuButton,
  IonModal,
  IonPicker, IonPickerColumn,
  IonPickerColumnOption, IonPopover,
  IonRow,
  IonSegment,
  IonSegmentButton,
  IonSelect,
  IonSelectOption, IonText,
  IonTextarea,
  IonTitle, IonToggle,
  IonToolbar
} from "@ionic/angular/standalone";
import {addIcons} from "ionicons";
import {
  addOutline, bookmark, bookmarkOutline,
  closeOutline,
  colorFillOutline,
  documentOutline,
  documentsOutline,
  duplicateOutline,
  ellipsisVertical,
  notifications,
  notificationsOffOutline,
  notificationsOutline,
  pencilOutline,
  remove, trashOutline
} from "ionicons/icons";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {Days, daysList} from "../model/days";
import {DurationInMin, durations} from "../model/duration-in-min";
import {Category} from "../model/category";
import {CommonService} from "../services/common.service";
import {Tag} from "../model/tag";
import {CommonDataEditorAlertService} from "../services/common-data-editor-alert.service";
import {DatetimeCustomEvent, InputCustomEvent, SelectChangeEventDetail} from "@ionic/angular";
import {TaskService} from "../services/task.service";
import {FormControl, ReactiveFormsModule, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {Task} from "../model/task";
import {Subtask} from "../model/subtask";

// import {formatISO} from "date-fns";

@Component({
  selector: 'app-task-editor',
  templateUrl: './task-editor.component.html',
  styleUrls: ['./task-editor.component.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonButtons, IonMenuButton, IonTitle, IonContent, IonInput, IonItem, IonIcon, IonTextarea, IonLabel, IonSegmentButton, IonSegment, IonDatetime, NgIf, NgForOf, IonSelectOption, IonItemOption, IonItemOptions, IonSelect, IonRow, IonChip, IonList, IonCheckbox, IonCard, IonCardTitle, IonCardContent, IonButton, IonCardHeader, IonGrid, IonCol, IonModal, IonDatetimeButton, NgClass, IonFooter, IonPicker, IonPickerColumnOption, IonPickerColumn, IonSelectOption, IonPopover, IonListHeader, IonToggle, ReactiveFormsModule, IonText]


})
export class TaskEditorComponent implements OnInit {
  days = daysList();
  durationsInMin: DurationInMin[] = durations();
  subtasks: Subtask[] = []
  priorities: string[] = []
  repetitions: string[] = []
  categories: Category[] = []
  selectedCategory: Category | undefined
  selectedTag: Tag[] = []
  tags: Tag[] = [];
  selectedRepetition: string = "Weekly";
  minDate: string | undefined = undefined
  startDate: Date | undefined;
  minTime: string | undefined
  selectedPriority: string = "Medium";

  // @ViewChild("title") title: IonInput | undefined;
  titleFormContol: FormControl;
  @ViewChild("description") description: IonInput | undefined;

  @ViewChild("subtaskInput") subtaskInput: IonInput | undefined;
  @ViewChild("startTime") startIonicTime: IonDatetime | undefined;
  @ViewChild("endTime") endIonicTime: IonDatetime | undefined;
  private activatedRoute = inject(ActivatedRoute);
  task: Task | undefined;

  constructor(private commonService: CommonService, private commandDataEditor: CommonDataEditorAlertService, private taskService: TaskService , private alertController:AlertController) {
    addIcons({
      notificationsOutline,
      notificationsOffOutline,
      addOutline,
      documentOutline,
      closeOutline,
      duplicateOutline,
      colorFillOutline,
      pencilOutline,
      ellipsisVertical,
      trashOutline,
      bookmarkOutline
    })
    this.titleFormContol = new FormControl<any>("", Validators.required);
  }

  ngOnInit() {
    this.commonService.getCategories().subscribe(value => {
      this.categories = value;
    })
    this.commonService.getRepetition().subscribe(value => {
      this.repetitions = value;
    })
    this.commonService.getPriority().subscribe(value => {
      this.priorities = value;
    })
    this.commonService.getTags().subscribe(value => {
      this.tags = value;
    })

    let taskId = this.activatedRoute.snapshot.paramMap.get('id');
    if (taskId) {
      this.taskService.getTask(taskId).subscribe(value => {

        this.task = value;
        console.log("Task id ", taskId, this.task)

        this.titleFormContol.setValue(this.task.title);
        if (this.task.priority)
          this.selectedPriority = this.task.priority;
        if (this.task.repetition)
          this.selectedRepetition = this.task.repetition
        this.selectedCategory = this.task.category;

        this.startDate = this.task.taskStartDate;
        if (this.startIonicTime && this.task.startTime)
          this.startIonicTime.value =(this.task.startTime.toString());

        if (this.endIonicTime && this.task.endTime)
          this.endIonicTime.value =  this.task.endTime.toString();

        this.startDate = this.task.taskStartDate;
        this.selectedCategory = this.task.category;

        this.subtasks = this.task.subTasks;
        this.selectedTag=this.task.tags;

      let selectedDayId=  this.task.days.map(value1 => value1.id);
        this.days.map(value1 => {
          if (selectedDayId.includes(value1.id)) value1.selected=1;
        })
      })
    }
  }

  selectRepetition(e: any) {
    this.selectedRepetition = e.detail.value;
    console.log(this.selectedRepetition)
  }


  addSubTask(subtask: any) {
    this.subtasks.push(new Subtask(subtask))
    if (this.subtaskInput) this.subtaskInput.value = ''
    // this.removeSubTaskAuto()
  }

  getNow() {
    if (this.minDate == undefined) {
      let today = new Date();
      this.minDate = new Date(today.getTime() - (24 * 60 * 60 * 1000)).toISOString();

    }
    return this.minDate;
  }

  removeTask(subtask: string , id:string| undefined) {
    this.subtasks = this.subtasks.filter(value => value.id != id || value.description != subtask);
  }

  updateRepeat(day: Days) {

    this.days.filter(value => value.id == day.id).map(value => {
        value.selected = value.selected == 1 ? 0 : 1;
      }
    )


  }

  isSelectedTag(tag: Tag): boolean {
    return this.selectedTag.filter(value => value.id===tag.id).length>0;
  }

  addOrRemoveTag(tag: Tag) {
    console.log("Tag",tag)
    if (this.selectedTag.filter(value => value.id===tag.id).length>0)
      this.selectedTag = this.selectedTag.filter(value => tag.id != value.id);
    else
      this.selectedTag.push(tag);
  }

  removeSubTaskAuto(value: any, i: number) {
    if (value == undefined || value === '') this.subtasks.splice(i, 1);
    else {
      this.subtasks[i].description=value;
    }
  }


  updateTime() {
    if (this.endIonicTime && this.startIonicTime) {
      this.endIonicTime.locale
      console.log(this.startIonicTime.value as string)
      let dateValue = new Date(this.startIonicTime.value as string);

      this.endIonicTime.min = (new Date(dateValue.getTime() + (60 * 5 * 1000)).toLocaleDateString());
      console.log("min", this.endIonicTime.min)

    }
  }

  createStartEndTime(duration: 30) {
    for (let i = 1; i <= 13; i++) {

    }
  }

  onSelectedPriority(e: any) {
    this.selectedPriority = e.detail.value;
  }

  openCategoryEditor(oldValue: string | undefined, id: string | undefined) {
    let title = oldValue ?
      "Edit category" :
      "Add your own category"
    this.commandDataEditor.showAlert(title, "Category", oldValue, oldValue != undefined).then((value: any) => {
      const deleteCat = value === true;
      if (value) {

        this.commonService.addOrUpdateCategories(value, id, deleteCat).subscribe(value1 => {
            if (deleteCat) {
              this.categories = this.categories.filter(value2 => value2.id !== id)
              this.selectedCategory = undefined;
            } else if (oldValue)
              this.categories.filter(value2 => value2.id == id).map(value2 => {
                value2.title = value;
                this.selectedCategory = value2;
              })
            else {
              this.categories.push(value1)
              this.selectedCategory = value1;


            }
          }
        )
      }
    })
  }

  openTagEditor(oldValue: string | undefined, id: string | undefined) {
    let placeholder = oldValue ?
      "Edit tag" :
      "Add your own tag";
    this.commandDataEditor.showAlert("Tag", placeholder, oldValue, oldValue != undefined).then((value: any) => {
      const deleteTag = value === true;
      if (value) {

        this.commonService.addOrUpdateTags(value, id, deleteTag).subscribe(value1 => {
            if (deleteTag) {
              this.tags = this.tags.filter(value2 => value2.id !== id)

            } else if (oldValue)
              this.tags.filter(value2 => value2.id == id).map(value2 => {
                value2.title = value;
              })
            else {
              this.tags.push(value1)
              this.addOrRemoveTag(value1);


            }
          }
        )
      }
    })
  }

  setSelectedCat(e: undefined | Category) {
    this.selectedCategory = e
  }

  onChangeStartedDate(e: any) {
    console.log("Started Date ")
    this.startDate = new Date(e.detail.value);
  }

  validForm() {
    if (this.titleFormContol)
      return this.titleFormContol.valid && this.selectedRepetition && this.startDate && this.selectedCategory && this.selectedPriority
        && this.days.filter(value => value.selected == 1).length > 0
        ;
    return false;
  }

  isRepeatDaysChose() {
    return this.days.filter(value => value.selected == 1).length > 0
  }

  submitTaskForm() {
    let task: any = {};
    task['title'] = this.titleFormContol?.value;
    task['repetition'] = this.selectedRepetition;
    task['taskStartDate'] = this.startDate;
    task['startTime'] = this.startIonicTime?.value;
    task['endTime'] = this.endIonicTime?.value;
    task['priority'] = this.selectedPriority;
    task['category'] = this.selectedCategory;
    task['selectedTag'] = this.selectedTag;
    task['subTasks'] = this.subtasks;
    task['days'] = this.days.filter(value => value.selected == 1);
    task["repetitionCount"] = task['days'].length;
    if (this.task){
      task['taskId'] = this.task.id;
      this.taskService.update(task);

    }
    else {

      this.taskService.addTask(task);
    }

  }

  async deleteTask() {
    let alert = await this.alertController.create({
      header: `Are you sure want to delete ${this.task?.title} ?`,
      buttons: [
        {
          text: "Cancel",
          role: "cancel"
        },
        {
          text: "Yes",
          role: "destructive"
        }
      ]
    })

   await alert.present()
  }


}
