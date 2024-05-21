import {Injectable, input} from '@angular/core';
import {IonAlert} from "@ionic/angular";
import {AlertController} from "@ionic/angular/standalone";

@Injectable({
  providedIn: 'root',

})
export class CommonDataEditorAlertService {

  constructor(private ionAlert:AlertController) {

  }

  async showAlert(title:string,placeHolder:string, value:string|undefined, showDelete:boolean=false):Promise<string|undefined |boolean>{
    return await new Promise<any>(async resolve => {
      let outPutVal:any=undefined;
    let buttons=[
      {
        role: "destructive",
        text: "Ok",
      }, {
        role: "cancel",
        text: "Cancel",
        handler:() => {
          outPutVal=undefined;
        }
      },

    ]
      if (showDelete){
        buttons.push({
          role: 'delete',
          text:"Delete",
          handler:()=>{
            outPutVal=true;
          }
        })
      }
      const alert = await this.ionAlert.create({
        buttons:buttons ,
        inputs: [
          {
            id:"input",
            placeholder: placeHolder,
            type: "text",
            value:value,
            handler:input1 => {
              outPutVal=input1.value;
            }

          }
        ],
        header: title
      })
      await alert.present();
      alert.onDidDismiss().then(value => {
        if (outPutVal==undefined && outPutVal !=='true'){
          resolve(value.data['values'][0])
        } else
        resolve(outPutVal)

      })
    })


  }
}
