import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewPostPageComponent } from './new-post-page.component';
import { NewPostComponent } from "./new-post/new-post.component";

import { FormsModule }   from '@angular/forms';
import { NgxSpinnerModule } from 'ngx-spinner';
import { RouterModule } from "@angular/router";

@NgModule({
  imports: [
    CommonModule,
    NgxSpinnerModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'new-post',
        component: NewPostPageComponent
      }
    ])
  ],
  declarations: [
    NewPostPageComponent,
    NewPostComponent
  ]
})
export class NewPostPageModule { }
