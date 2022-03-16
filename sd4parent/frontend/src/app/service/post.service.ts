import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import { Observable } from "rxjs"
import { Post } from "../model/post"

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getPost(postId: string): Observable<Post> {
    return this.http.get<Post>("/api/p/" + postId);
  }

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>('/api/p')
  }

  savePost(post: Post): Observable<Post> {
    return this.http.post<Post>('/api/p', post)
  }

  deletePost(postId: string): Observable<void> {
    return this.http.delete<void>('/api/p/' + postId)
  }

  getPostsByLogin(login: string): Observable<Post[]> {
    return this.http.get<Post[]>('/api/p/find?login=' + login);
  }

  getPostsByPage(page: number, quantity: number): Observable<Post[]> {
    return this.http.get<Post[]>('/api/p/page/' + page + "?qt=" + quantity);
  }

  getTotalPages(quantity: number): Observable<number> {
    return this.http.get<number>('api/p/total-pages?qt=' + quantity);
  }
}
