import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";

import { GetSocialComponent } from "./get-social.component";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot([
      {
        path: "get-social",
        component: GetSocialComponent
      }
    ])
  ],
  declarations: [GetSocialComponent]
})
export class GetSocialModule { }
