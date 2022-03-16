import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPackagePageComponent } from './new-package-page.component';

describe('NewPackagePageComponent', () => {
  let component: NewPackagePageComponent;
  let fixture: ComponentFixture<NewPackagePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewPackagePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPackagePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
