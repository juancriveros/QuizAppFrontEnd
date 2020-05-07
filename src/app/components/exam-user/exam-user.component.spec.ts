import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamUserComponent } from './exam-user.component';

describe('ExamUserComponent', () => {
  let component: ExamUserComponent;
  let fixture: ComponentFixture<ExamUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
