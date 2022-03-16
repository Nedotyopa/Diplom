import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import { Observable } from "rxjs"
import { Package } from '../model/package'

@Injectable({
  providedIn: 'root'
})
export class PackageService {

  constructor(private http: HttpClient) { }

  getPackage(packageId: string): Observable<Package> {
    return this.http.get<Package>("api/pa/" + packageId);
  }

  getPackages(): Observable<Package[]> {
    return this.http.get<Package[]>('/api/pa')
  }

  savePackage(pack: Package): Observable<Package> {
    return this.http.post<Package>('/api/pa', pack)
  }

  deletePackage(packageId: string): Observable<void> {
    return this.http.delete<void>('/api/pa/' + packageId)
  }

  getPackagesByPage(page: number, quantity: number): Observable<Package[]> {
    return this.http.get<Package[]>('/api/pa/page/' + page + "?qt=" + quantity);
  }

  getTotalPages(quantity: number): Observable<number> {
    return this.http.get<number>('api/pa/total-pages?qt=' + quantity);
  }

}
