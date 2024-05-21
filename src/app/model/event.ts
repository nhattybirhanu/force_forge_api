import {Subtask} from "./subtask";

export class Event{

  name:string="";
  note:string=""
  icon:string=""
  color="";
  duration:number=20;
  startTime:Date=new Date();
  subTasks:Subtask[]=[];

}
