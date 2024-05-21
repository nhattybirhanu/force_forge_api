import {Category} from "./category";
import {Tag} from "./tag";
import {Subtask} from "./subtask";
import {Days} from "./days";

export class Task {
  id: string | undefined
  userId: string | undefined
  title: string | undefined
  description: string | undefined
  repetition: string | undefined
  priority: string | undefined
  category: Category | undefined
  tags: Tag[] = [];
  subTasks: Subtask[] = []
  onAlarm: boolean =false;
  startTime :Date | undefined;
  endTime :Date | undefined;
  taskStartDate :Date | undefined;
  taskEndDate :Date | undefined;
  days:Days[]=[];
}
