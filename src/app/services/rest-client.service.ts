import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable, Subject} from "rxjs";
import {LoadingController} from "@ionic/angular";
import {ToastService} from "./toast.service";

@Injectable({
  providedIn: 'root',
})
export class RestClientService {
  BASE_URL=environment.BASE_URL;
  accessToken:string|undefined;
  AUTHORIZATION_HEADER="Authorization";
  constructor(private httpClient:HttpClient , private loaderController:LoadingController, private toastService:ToastService) {


  }
  get(url:string,params:any,useToken:boolean=true,showErrorAlert=false,errorMessage:string="Network error",showLoader:{message:string}| undefined=undefined):Observable<any>{
    let observable=new Subject<any>();
    let headers:HttpHeaders=new HttpHeaders()
    if (useToken&&this.accessToken){
      headers.set(this.AUTHORIZATION_HEADER,"Bearer "+this.accessToken);
    }
    let  loader:Promise<HTMLIonLoadingElement> | undefined;
    if (showLoader){
      loader= this.showLoader(showLoader.message)

    }
    this.httpClient.get(this.BASE_URL+url,{params:params,headers:headers},).subscribe(value => {
      if (loader)
        this.closeLoader(loader)
      observable.next(value);

    },error => {
      if (loader)
        this.closeLoader(loader)
      if (showErrorAlert)
        this.toastService.presentSimpleToast('bottom',errorMessage,1500,"danger")
      observable.error(error)

    })

    return observable
  }
  post(url:string,params:any,body:any,useToken:boolean=true,showErrorAlert=false,errorMessage:string="Network error",showLoader:{message:string}| undefined=undefined):Observable<any>{
    let observable=new Subject<any>();
    let headers:HttpHeaders=new HttpHeaders()
    if (useToken&&this.accessToken){
      headers.set(this.AUTHORIZATION_HEADER,"Bearer "+this.accessToken);
    }
    let  loader:Promise<HTMLIonLoadingElement> | undefined;
    if (showLoader){
      loader= this.showLoader(showLoader.message)

    }
    this.httpClient.post(this.BASE_URL+url,body,{params:params,headers:headers},).subscribe(value => {
      if (loader)
        this.closeLoader(loader)
      observable.next(value);
    },error => {
      if (loader)
        this.closeLoader(loader)
      if (showErrorAlert)
        this.toastService.presentSimpleToast('bottom',errorMessage,3000,"danger")
      console.error(error)
      observable.error(error)
    })

    return observable
  }
  put(url:string,params:any,body:any,useToken:boolean=true,showErrorAlert=false,errorMessage:string="Network error",showLoader:{message:string|undefined}| undefined=undefined):Observable<any>{
    let observable=new Subject<any>();
    let headers:HttpHeaders=new HttpHeaders()
    if (useToken&&this.accessToken){
      headers.set(this.AUTHORIZATION_HEADER,"Bearer "+this.accessToken);
    }
    let  loader:Promise<HTMLIonLoadingElement> | undefined;
    if (showLoader){
      loader= this.showLoader(showLoader.message)

    }
    this.httpClient.put(this.BASE_URL+url,body,{params:params,headers:headers},).subscribe(value => {
      if (loader)
        this.closeLoader(loader)
      observable.next(value);
    },error => {
      observable.error(error)
      if (loader)
        this.closeLoader(loader)

      if (showErrorAlert)
        this.toastService.presentSimpleToast('bottom',errorMessage,3000,"danger")
    })

    return observable
  }
  delete(url:string,params:any,useToken:boolean=true,showErrorAlert=false,errorMessage:string="Network error"):Observable<any>{
    let observable=new Subject<any>();
    let headers:HttpHeaders=new HttpHeaders()
    if (useToken&&this.accessToken){
      headers.set(this.AUTHORIZATION_HEADER,"Bearer "+this.accessToken);
    }
    this.httpClient.delete(this.BASE_URL+url,{params:params,headers:headers},).subscribe(value => {
      observable.next(value);
    },error => {
      if (showErrorAlert)
        this.toastService.presentSimpleToast('bottom',errorMessage,3000,"danger")
    })

    return observable
  }

  async showLoader(message:string|undefined){
    const  loader= await this.loaderController.create({
      message:message
    })

    loader.present();
    return loader
  }
  closeLoader(loader:Promise<HTMLIonLoadingElement>){
    loader.then(value => {
      value.dismiss()
    })
  }
}
