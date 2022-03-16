import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import { Observable } from "rxjs"
import { SubscriptionPost } from "../model/subscription"
import { SubscribeCondition } from "../model/subscribeCondition"
import {SubscriptionRenewal} from "../model/subscriptionRenewal";
import {StringResponse} from "../model/stringResponse";
import {PackageSubscription} from "../model/packageSubscription";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  constructor(private http: HttpClient) { }

  getSubscription(subscriptionId: string): Observable<SubscriptionPost> {
    return this.http.get<SubscriptionPost>("api/s/" + subscriptionId);
  }

  getSubscriptions(): Observable<SubscriptionPost[]> {
    return this.http.get<SubscriptionPost[]>("api/s");
  }

  saveSubscription(sub: SubscriptionPost ): Observable<StringResponse> {
    return this.http.post<StringResponse>("api/s", sub);
  }

  savePackageSubscription(subs: PackageSubscription): Observable<StringResponse> {
    return this.http.post<StringResponse>("api/s/package", subs);
  }

  deleteSubscription(subscriptionId: string): Observable<void> {
    return this.http.delete<void>("api/s/" + subscriptionId);
  }

  getSubscriptionsByLogin(login: string): Observable<SubscriptionPost[]> {
    return this.http.get<SubscriptionPost[]>("api/s/?login=" + login);
  }

  computePrice(condition: SubscribeCondition): Observable<SubscribeCondition> {
    return this.http.post<SubscribeCondition>("api/s/compute", condition);
  }

  extendSubscription(sub: SubscriptionRenewal): Observable<void> {
    return this.http.post<void>("api/s/extend", sub);
  }

  chargeMoney(): Observable<void> {
    return this.http.post<void>("api/s/charge", null);
  }

  getCount(login: string): Observable<number> {
    return this.http.get<number>("api/s/get-count?login=" + login);
  }
}
