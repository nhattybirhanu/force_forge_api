export class DurationInMin{
  name="";
  minutes=0;

  constructor(name: string, minutes: number) {
    this.name = name;
    this.minutes = minutes;
  }


}
export function durations():DurationInMin[]{
  return [
    new DurationInMin("15min",15),
    new DurationInMin("20min",20),
    new DurationInMin("30min",35),
    new DurationInMin("45min",45),
    new DurationInMin("1hr",60),
    new DurationInMin("1.5hr",90),
    new DurationInMin("2hr",120),
  ]
}
