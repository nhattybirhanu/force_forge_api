import { Injectable } from '@angular/core';
import {ToastController} from "@ionic/angular";
import {PredefinedColors} from "@ionic/core";

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private toastController:ToastController) {

  }

  async presentSimpleToast(position: 'top' | 'middle' | 'bottom', message:string,duration:number=1500,color:PredefinedColors="primary") {
    const toast = await this.toastController.create({
      message: message,
      duration: duration,
      position: position,
      color:color

    });

    await toast.present();
  }

}
