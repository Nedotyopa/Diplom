import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetSocialComponent } from './get-social.component';

describe('GetSocialComponent', () => {
  let component: GetSocialComponent;
  let fixture: ComponentFixture<GetSocialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetSocialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetSocialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
