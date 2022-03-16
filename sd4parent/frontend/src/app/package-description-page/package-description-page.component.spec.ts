import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PackageDescriptionPageComponent } from './package-description-page.component';

describe('PackageDescriptionPageComponent', () => {
  let component: PackageDescriptionPageComponent;
  let fixture: ComponentFixture<PackageDescriptionPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PackageDescriptionPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PackageDescriptionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
