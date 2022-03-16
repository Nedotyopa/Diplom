import { Component, OnInit } from '@angular/core';
import { PostPackageService } from "../service/post-package.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  public type: string;

  constructor(private postPackageService: PostPackageService) { }

  ngOnInit() {
    this.postPackageService.getShow().subscribe(type => {
      this.type = type;
    })
  }

}
