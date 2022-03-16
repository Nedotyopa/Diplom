import { Injectable } from '@angular/core';
import { BehaviorSubject } from "rxjs";
import { Post } from "../model/post";

@Injectable({
  providedIn: 'root'
})
export class PackageDataService {

  private posts = new Array<Post>();
  private observablePosts = <BehaviorSubject<Post[]>>new BehaviorSubject<Post[]>([]);
  private count: number = 0;
  private observableCount = <BehaviorSubject<number>>new BehaviorSubject<number>(0);

  public getPosts() {
    let value = this.observablePosts.value;
    this.observablePosts = <BehaviorSubject<Post[]>>new BehaviorSubject<Post[]>([]);
    return value;
  }

  public getCount() {
    let value = this.observableCount.value;
    this.observableCount = <BehaviorSubject<number>>new BehaviorSubject<number>(0);
    return value;
  }

  public addPost(post: Post) {
    let index: number = this.posts.indexOf(post);

    if(index > -1) {
      this.count -= 1;
      this.posts.splice(index, 1);
    }
    else {
      this.count += 1;
      this.posts.push(post);
    }

    this.observableCount.next(this.count);
    this.observablePosts.next(this.posts);
    console.log(this.observableCount.value);
    console.log(this.observablePosts.value);
  }

  public leavePage() {
    this.posts = [];
  }

}
