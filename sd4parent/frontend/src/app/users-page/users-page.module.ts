import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import { NgxSpinnerModule } from 'ngx-spinner';
import { FormsModule }   from '@angular/forms';

import { UsersPageComponent } from './users-page.component';
import { UsersComponent } from "./users/users.component";

@NgModule({
  imports: [
    CommonModule,
    NgxSpinnerModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'users',
        component: UsersPageComponent
      }
    ])
  ],
  declarations: [
    UsersPageComponent,
    UsersComponent
  ]
})
export class UsersPageModule { }
