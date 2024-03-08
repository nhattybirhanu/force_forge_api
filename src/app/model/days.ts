export class Days{
  name="";
  selected=0;
  id:number=0

  constructor(name: string, selected: number,id:number) {
    this.name = name;
    this.selected = selected;
    this.id=id;
  }
}

export function daysList():Days[] {
  return [
    new Days("M",0,0),
    new Days("T",0,1),
    new Days("W",0,2),
    new Days("T",0,3),
    new Days("F",0,4),
    new Days("S",0,5),
    new Days("S",0,6),
  ]
}
