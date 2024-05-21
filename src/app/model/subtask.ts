export class Subtask{
  id:string | undefined
  description:string="";
  done=false;

  constructor(description: string) {
    this.description = description;
  }
}
