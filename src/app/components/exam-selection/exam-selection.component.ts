import { Component, OnInit } from '@angular/core';
import { faEdit, faEye } from '@fortawesome/free-solid-svg-icons';
import { ExamService } from 'src/app/services/exam.service';
import { AnswerService } from 'src/app/services/answer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/datamodel/user';

@Component({
  selector: 'app-exam-selection',
  templateUrl: './exam-selection.component.html',
  styleUrls: ['./exam-selection.component.css']
})
export class ExamSelectionComponent implements OnInit {

  faEye = faEye;
  faEdit = faEdit;
  exams;
  user : User = new User();
  showModal: boolean;
  content: string;
  title: string;
  validResponse : number = 0;


  constructor(private activatedRoute: ActivatedRoute, private examService: ExamService, private answerService: AnswerService, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.user.id = params['user'];
      this.get();
    });
    this.get();
  }

  get(){
    this.examService.get().subscribe((data) => {
      if(data[0].length > 0)
        this.exams = data[0];
    });
  }

  presentExam(examId, title){
    this.answerService.get(examId, this.user.id).subscribe(data => {
      var resultArray = Object.keys(data).map(function(index){
        let choice = data[index];
        // do something with person
        return choice;
      });
      if(resultArray.length == 0){
        this.router.navigate(['/PresentExam'], { queryParams: {user: this.user.id, id: examId, title: title}});
      }
      else{
        this.validResponse = 0;
        resultArray.forEach(element => {
          if(element.valid){
            this.validResponse += 1;
          }
        });
          this.showModal = true; // Show-Hide Modal Check
          this.content = "You already present this exam with this result: " + this.validResponse.toString() + "/" + resultArray.length; // Dynamic Data
          this.title = "Exam taken";    // Dynamic Data
        }
    });
  }

  
  hide()
  {
    this.showModal = false;
  }

}
