import { Component, OnInit } from '@angular/core';
import { faEdit, faEye } from '@fortawesome/free-solid-svg-icons';
import { ExamService } from 'src/app/services/exam.service';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.css']
})
export class ExamListComponent implements OnInit {
  faEye = faEye;
  faEdit = faEdit;
  exams;

  constructor(private examService: ExamService) { }

  ngOnInit(): void {
    this.get();
  }

  get(){
    this.examService.get().subscribe((data) => {
      console.log(data);
      if(data[0].length > 0)
        this.exams = data[0];
    });
  }

  delete(examId){
    
  }

}
