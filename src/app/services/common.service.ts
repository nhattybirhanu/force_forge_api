import { Injectable } from '@angular/core';
import {RestClientService} from "./rest-client.service";
import {Observable} from "rxjs";
import {Category} from "../model/category";
import {Tag} from "../model/tag";

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private restClient:RestClientService) {


  }

  getCategories():Observable<Category[]>{
    return this.restClient.get("common/categories",undefined,false)
  }
  getPriority():Observable<string[]>{
    return this.restClient.get("common/priorities",undefined,false)
  }
  getRepetition():Observable<string[]>{
    return this.restClient.get("common/repetitions",undefined,false)
  }
  getTags():Observable<Tag[]>{
    return this.restClient.get("common/tags",undefined,false)
  }
  addOrUpdateCategories(title:string,id:string| undefined, deleteCat:boolean):Observable<Category>{
    if (deleteCat&&id)
      return  this.restClient.delete(`common/categories/${id}`,undefined,false)

    else if(id)
      return  this.restClient.put(`common/categories/${id}/${title}`,undefined,undefined,false,true,"Category can not be added Network Error")

    else
   return  this.restClient.post("common/categories",undefined,{title},false,true,"Category can not be updated Network Error")

  }

  addOrUpdateTags(title:string,id:string| undefined, deleteCat:boolean):Observable<Tag>{
    if (deleteCat&&id)
      return  this.restClient.delete(`common/tags/${id}`,undefined,false)

    else if(id)
      return  this.restClient.put(`common/tags/${id}/${title}`,undefined,undefined,false,true,"Category can not be added Network Error")

    else
      return  this.restClient.post("common/tags",undefined,{title},false,true,"Category can not be updated Network Error")

  }
}
