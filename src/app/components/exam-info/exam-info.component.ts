import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params} from '@angular/router';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { QuestionService } from 'src/app/services/question.service';
import { Exam } from 'src/app/datamodel/exam';

@Component({
  selector: 'app-exam-info',
  templateUrl: './exam-info.component.html',
  styleUrls: ['./exam-info.component.css']
})
export class ExamInfoComponent implements OnInit {

  exam : Exam = new Exam();
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;
  questions;

  constructor(private activatedRoute: ActivatedRoute, private questionService :QuestionService) { }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe(params => {
      this.exam.id = params['id'];
      this.exam.title = params['title'];
      this.get();
    });

  }
  
  get(){
    this.questionService.get(this.exam.id).subscribe((data) => {
      this.questions = data;
    });
  }

  delete(questionId){
    this.questionService.delete(questionId).subscribe((data) => {
      this.get();
    });
  }
}
