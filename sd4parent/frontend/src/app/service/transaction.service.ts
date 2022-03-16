import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import { Observable } from "rxjs"
import { Transaction } from "../model/transaction"

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient) { }

  getTransaction(transactionId: string): Observable<Transaction> {
    return this.http.get<Transaction>("api/t/" + transactionId);
  }

  getTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>("api/t");
  }

  saveTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>("api/t", transaction);
  }

  deleteTransaction(transactionId: string): Observable<void> {
    return this.http.delete<void>("api/t/" + transactionId);
  }

  getTransactionsByLogin(login: string): Observable<Transaction[]> {
    return this.http.get<Transaction[]>("api/t/?login=" + login);
  }
}
