import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewPasswordPageComponent } from './new-password-page.component';
import { NewPasswordComponent } from './new-password/new-password.component';

import { FormsModule }   from '@angular/forms';
import { NgxSpinnerModule } from 'ngx-spinner';
import { RouterModule } from "@angular/router";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxSpinnerModule,
    RouterModule.forRoot([
      {
        path: 'new-password',
        component: NewPasswordPageComponent
      }
    ])
  ],
  declarations: [NewPasswordPageComponent, NewPasswordComponent]
})
export class NewPasswordPageModule { }
