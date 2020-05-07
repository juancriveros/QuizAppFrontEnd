import { Component, OnInit } from '@angular/core';
import { AnswerService } from 'src/app/services/answer.service';
import { ActivatedRoute } from '@angular/router';
import { Exam } from 'src/app/datamodel/exam';
import { Choices } from 'src/app/datamodel/choices';

@Component({
  selector: 'app-exam-results',
  templateUrl: './exam-results.component.html',
  styleUrls: ['./exam-results.component.css']
})
export class ExamResultsComponent implements OnInit {


  exam : Exam = new Exam();
  showModal: boolean;
  content: string;
  title: string;
  validResponse : number = 0;
    

  constructor(private activatedRoute: ActivatedRoute, private answerService: AnswerService) { }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe(params => {
      this.exam.id = params['id'];
      this.exam.title = params['title'];
      this.get();
    });

  }

  get(){

    this.answerService.get(this.exam.id, "s").subscribe(data => {
      var resultArray = Object.keys(data).map(function(index){
        let choice = data[index];
        return choice;
      });
      resultArray.forEach(element => {
        if(element.valid){
          this.validResponse += 1;
        }
      });

        this.showModal = true; // Show-Hide Modal Check
        this.content = "You already present this exam with this result: " + this.validResponse.toString() + "/" + resultArray.length; // Dynamic Data
        this.title = "Exam taken";    // Dynamic Data
    });
  }

}
