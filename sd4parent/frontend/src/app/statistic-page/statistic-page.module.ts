import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatisticComponent } from './statistic/statistic.component';
import { StatisticPageComponent } from './statistic-page.component';

import { NgxSpinnerModule } from 'ngx-spinner';
import { RouterModule } from "@angular/router";

@NgModule({
  imports: [
    CommonModule,
    NgxSpinnerModule,
    RouterModule.forRoot([
      {
        path: 'statistic',
        component: StatisticPageComponent
      }
    ])
  ],
  declarations: [StatisticComponent, StatisticPageComponent]
})
export class StatisticPageModule { }
