package com.etan.force_forge_api.model;

import java.util.Arrays;
import java.util.Optional;

public enum FeedBack {


   Bad("Bad",0), Fair("Fair",1), Good("Good",2),
   Better("Better",3), Great("Great",4), Excellent("Excellent",5);

   String title;
   int value;
   FeedBack(String title, int value){
      this.title=title;
      this.value=value;
   }
   FeedBack(String title){
      FeedBack feedBack= Arrays.stream(FeedBack.values()).filter(fb -> fb.title.equals(title)).findFirst().orElseThrow();
      this.title=title;
      this.value=feedBack.value;
   }
}
