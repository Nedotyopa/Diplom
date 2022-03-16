import {Component, OnDestroy, OnInit} from '@angular/core';
import { Subscription } from "rxjs";
import { Post } from "../../model/post";
import { PostService } from "../../service/post.service";
import { PackageDataService } from "../../service/package-data.service";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  public posts: Post[];
  public checkedPosts: string[] = [];

  constructor(private postService: PostService, private packageDataService: PackageDataService) { }

  ngOnInit() {
    this.loadPosts();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
    this.packageDataService.leavePage();
  }

  private loadPosts(): void {
    this.subscriptions.push(this.postService.getPosts().subscribe(products => {
      this.posts = products as Post[];
    }))
  }

  public addPost(post: Post): void {
    this.packageDataService.addPost(post);
    this.checkPost(post.title);
  }

  public checkPost(title: string): void {
      let index: number = this.checkedPosts.indexOf(title);

      if(index == -1)
        this.checkedPosts.push(title);
      else
        this.checkedPosts.splice(index, 1);
      console.log(this.checkedPosts);
  }

  public currentPost(title: string): boolean {
    if(this.checkedPosts.indexOf(title) > -1) {
      return true;
    }
    return false;
  };

  public shortDescription(description: string): string {
    let point: number;
    description = description.slice(0,110);
    if(description.length === 220) {
      for(let j: number = 0; j<description.length; j++) {
        if(description[j] === '.')
          point = j+1;
      }
    }
    return description.slice(0,point);
  }

}
