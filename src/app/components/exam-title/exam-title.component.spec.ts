import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamTitleComponent } from './exam-title.component';

describe('ExamTitleComponent', () => {
  let component: ExamTitleComponent;
  let fixture: ComponentFixture<ExamTitleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamTitleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
