import {Component, OnDestroy, OnInit} from '@angular/core';
import { NgxSpinnerService } from "ngx-spinner";
import * as CanvasJS from '../../canvasjs.min';

import { StatisticModel } from "../../model/statisticModel";
import { TransactionService } from "../../service/transaction.service";
import { AuthService } from "../../service/auth.service";
import { Transaction } from "../../model/transaction";

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {

  constructor(private loadingService: NgxSpinnerService, private transactionService: TransactionService,
              private authService: AuthService) { }

  ngOnInit() {
    this.loadTransactions();
  }

  private loadTransactions(): void {
    this.transactionService.getTransactionsByLogin(this.authService.getUsername()).subscribe(transactions => {
      let statisticArray: StatisticModel[] = [];
      for(let transaction of transactions as Transaction[]) {
        let index: number = statisticArray.map(function (e) { return e.label }).indexOf(transaction.title);
        if(index > -1) {
          statisticArray[index].y += 1;
        } else {
          statisticArray.push(new StatisticModel(1, transaction.title));
        }
      }
      this.createChart(statisticArray);
    })
  }

  private createChart(array: StatisticModel[]): void {
    for(let el of array) {
      console.log("Element: " + el.label + ", y: " + el.y);
    }
    let chart = new CanvasJS.Chart("chartContainer", {
      backgroundColor: "#DFDCE3",
      animationEnabled: true,
      title: {
        text: "Transaction chart"
      },
      data: [{
        type: "pie",
        dataPoints: array
      }]
    });

    chart.render();
  }

}
