import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import { Observable } from "rxjs"
import { Comment } from "../model/comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getComment(commentId: string): Observable<Comment> {
    return this.http.get<Comment>("api/c/" + commentId);
  }

  saveComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>("api/c", comment);
  }

  deleteComment(commentId: string): Observable<void> {
    return this.http.delete<void>("api/c/" + commentId);
  }

  getCommentsByPostId(postId: string): Observable<Comment[]> {
    return this.http.get<Comment[]>("api/c/find?post=" + postId);
  }

}
