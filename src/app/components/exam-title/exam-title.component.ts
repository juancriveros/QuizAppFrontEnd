import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params} from '@angular/router';
import { Exam } from 'src/app/datamodel/exam';
import { ExamService } from 'src/app/services/exam.service';

@Component({
  selector: 'app-exam-title',
  templateUrl: './exam-title.component.html',
  styleUrls: ['./exam-title.component.css']
})
export class ExamTitleComponent implements OnInit {

  exam : Exam = new Exam();
  showModal: boolean;
  content: string;
  title: string;

  constructor(private activatedRoute: ActivatedRoute, private examService: ExamService, private router: Router) { }

  ngOnInit(): void {

    
    this.activatedRoute.queryParams.subscribe(params => {
      this.exam.id = params['id'];
      this.exam.title = params['title'];
      
      if(this.exam.id != null){
        this.getById();
        
      }
    });
  }

  save(){
    this.showModal = true; // Show-Hide Modal Check
      this.content = "Loading...";
      this.title = "Loading";    // Dynamic Data
    if(this.exam.id > 0){
      this.examService.update(this.exam).subscribe((data) => {
        this.exam.id = data['id'];
        this.exam.title = data['title']; 
        console.log(data);
        this.router.navigate(['/ExamInfo'], { queryParams: {id: this.exam.id, title : this.exam.title}});
      });
    }
    else{
      this.examService.save(this.exam).subscribe((data) => {
        this.exam.id = data['id'];
        this.exam.title = data['title']; 
        console.log(data);
        this.router.navigate(['/ExamInfo'], { queryParams: {id: this.exam.id, title : this.exam.title}});
      });
    }
  }

  getById(){
    this.examService.getById(this.exam.id).subscribe((data) => {
      this.exam.id = data['id'];
      this.exam.title = data['title']; 
    });
  }

  hide()
  {
    console.log("SALIOOOOOOOOOO");
  }



}
