import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params} from '@angular/router';
import { Question } from 'src/app/datamodel/question';
import { QuestionService } from 'src/app/services/question.service';
import { Exam } from 'src/app/datamodel/exam';

@Component({
  selector: 'app-exam-questions',
  templateUrl: './exam-questions.component.html',
  styleUrls: ['./exam-questions.component.css']
})
export class ExamQuestionsComponent implements OnInit {

  name = 'Angular 6';
  jsonFormOptions = {
    loadExternalAssets: true,
  };
  schema = {
    "type": "object",
    "properties": {
      "id": {"type": "string", "readonly":true},
      "title": { "type": "string" },
      "choices": {
        "type": "array",
        "items": {
          "type": "object",
          "properties": {
            "id" : {"type":"string", "readonly":true},
            "choice": { "type": "string" },
            "valid": { "type": "boolean", "default": false }
          },
          "required": [ "choice"]
        }
      }
    },
    "required": [ "title" ]
  };

  questionData;
  submittedFormData;

  showModal: boolean;
  content: string;
  title: string;
  question : Question = new Question();
  exam : Exam = new Exam();

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private questionService : QuestionService) { }

  ngOnInit(): void {
    
    this.activatedRoute.queryParams.subscribe(params => {
      this.exam.id = params['ExamId'];
      this.question.id = params['QuestionId'];
      if(this.question.id > 0){
        this.getById();
      }
    });
  }

  onSubmit(data: any) {
    if(data.choices.length < 2){
      this.showModal = true; // Show-Hide Modal Check
      this.content = "The question should have at leaste two choices"; // Dynamic Data
      this.title = "Choices required";    // Dynamic Data
    }
    else{
      var trueValid = 0;
      var nameValid = 0;
      data.choices.forEach(element => {
          if(element.valid == true){
            trueValid = trueValid + 1;
          }
          if((data.choices.filter(e => e.choice === element.choice)).length > 1)
            nameValid = 1;
      });
      if(trueValid == 0){
        this.showModal = true; // Show-Hide Modal Check
        this.content = "At least one choice must be valid"; // Dynamic Data
        this.title = "Choices required";    // Dynamic Data
      }
      else if (nameValid == 1){
        this.showModal = true; // Show-Hide Modal Check
        this.content = "The options must be different"; // Dynamic Data
        this.title = "Options different";    // Dynamic Data
      }
      else{
        this.question = data;
        this.question.examId = this.exam.id;
        if(this.question.id > 0){
          this.questionService.update(this.question).subscribe((data) => {
            this.router.navigate(['/ExamInfo'], { queryParams: {id: this.exam.id, title : this.exam.title}});
          });
        }else{
        this.questionService.save(this.question).subscribe((data) => {
          this.router.navigate(['/ExamInfo'], { queryParams: {id: this.exam.id, title : this.exam.title}});
        });
      }
      }
    }
    
  }

  show()
  {
    this.showModal = true; // Show-Hide Modal Check
    this.content = "This is content!!"; // Dynamic Data
    this.title = "This is title!!";    // Dynamic Data
  }
  //Bootstrap Modal Close event
  hide()
  {
    this.showModal = false;
  }

  getById(){
    this.questionService.getById(this.question.id).subscribe((data) => {
      this.questionData = data;
    });
  }

}
