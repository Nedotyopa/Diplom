import { Injectable } from '@angular/core';
import { BehaviorSubject } from "rxjs";
import { Post } from "../model/post";

@Injectable({
  providedIn: 'root'
})
export class PostDataService {

  private post = new BehaviorSubject(null);

  getPost() {
    let currentPost = this.post.asObservable();
    this.post = new BehaviorSubject(null);
    return currentPost;
  }

  putPost(post: Post) {
    this.post.next(post);
  }
}
