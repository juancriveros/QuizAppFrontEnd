import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import { Exam } from 'src/app/datamodel/exam';
import { Question } from 'src/app/datamodel/question';
import { Choices } from 'src/app/datamodel/choices';
import { Answer } from 'src/app/datamodel/answer';
import { ExamService } from 'src/app/services/exam.service';
import { AnswerService } from 'src/app/services/answer.service';
import { User } from 'src/app/datamodel/user';

@Component({
  selector: 'app-present-exam',
  templateUrl: './present-exam.component.html',
  styleUrls: ['./present-exam.component.css']
})
export class PresentExamComponent implements OnInit {

  exam: Exam = new Exam();
  studentAnswer: Answer = new Answer();
  questions: Question = new Question();
  user : User = new User();
  showModal: boolean;
  content: string;
  title: string;
  validResponse : number = 0;

  constructor(private activatedRoute: ActivatedRoute, private questionService :QuestionService, private answerService: AnswerService, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.exam.id = params['id'];
      this.exam.title = params['title'];
      this.user.id = params['user'];
      this.studentAnswer.choices = [];
      this.studentAnswer.exam = this.exam;
      this.studentAnswer.student = this.user;
      this.get();
    });
  }

  get(){
    this.questionService.get(this.exam.id).subscribe((data) => {
      this.questions = data;
    });
  }

  onSelectionChange(choice) {
    this.studentAnswer.choices = this.studentAnswer.choices.filter( x => x.questionId !== choice.questionId); 
    
    this.studentAnswer.choices.push(choice);
  
  }

  save(){
    this.answerService.save(this.studentAnswer).subscribe((data) => {
      this.studentAnswer.choices.forEach(element => {
        if(element.valid){
          this.validResponse += 1;
        }
      });
      console.log(this.validResponse);
      this.showModal = true; // Show-Hide Modal Check
      this.content = "Congratilations here is your results: " + this.validResponse.toString() + "/" + this.studentAnswer.choices.length; // Dynamic Data
      this.title = "Exam results";    // Dynamic Data
      //this.router.navigate(['/ExamResults'], { queryParams: {id: this.exam.id, title : this.exam.title}});
    });
  }

  hide()
  {
    console.log("SALIOOOOOOOOOO");
    this.router.navigate(['/ExamSelection'], { queryParams: {user: this.user.id}});
  }

}
