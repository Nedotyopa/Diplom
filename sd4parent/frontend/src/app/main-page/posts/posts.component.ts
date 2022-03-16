import {Component, OnDestroy, OnInit} from '@angular/core';
import { Post } from "../../model/post"
import { PostService } from "../../service/post.service"
import { Subscription } from "rxjs/internal/Subscription"
import { NgxSpinnerService } from 'ngx-spinner';
import { PostPackageService } from "../../service/post-package.service";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit, OnDestroy {

  public posts: Post[];
  private subscriptions: Subscription[] = [];
  public pages: number[] = [];
  public total: number;
  public currentPage: number = 1;
  public quantity: number = 6;

  constructor(private postService: PostService, private loadingService: NgxSpinnerService,
              private postPackageService: PostPackageService) { }

  ngOnInit() {
    this.getPostsByPage(1);
    this.getTotalPages();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private getTotalPages(): void {
    this.subscriptions.push(this.postService.getTotalPages(this.quantity).subscribe(totalPages => {
      this.total = totalPages;
      console.log(this.total);
      this.pages = [];
      for(let i=1; i<=totalPages; i++) {
        this.pages.push(i);
      }
    }))
  }

  public getPostsByPage(page: number): void {
    this.loadingService.show();
    this.subscriptions.push(this.postService.getPostsByPage(page, this.quantity).subscribe(products => {
      this.posts = products as Post[];
      this.loadingService.hide();
    }))
  }

  public _shortDescription(description: string): string {
    let point: number;
    description = description.slice(0,220);
    if(description.length === 220) {
      for(let j: number = 0; j<description.length; j++) {
        if(description[j] === '.')
          point = j+1;
      }
    }
    return description.slice(0,point);
  }

  public setType(): void {
    this.postPackageService.setShow("packages");
  }

  public setQuantity(qt: number): void {
    this.quantity = qt;
    this.getPostsByPage(1);
    this.currentPage = 1;
    this.getTotalPages();
  }
}
