import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/datamodel/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-exam-user',
  templateUrl: './exam-user.component.html',
  styleUrls: ['./exam-user.component.css']
})
export class ExamUserComponent implements OnInit {

  user : User = new User();
  showModal: boolean;
  content: string;
  title: string;

  constructor(private activatedRoute: ActivatedRoute, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  save(){
    
    if((this.user.id != "" && this.user.id != undefined) && (this.user.name != "" && this.user.name != undefined)){
      this.showModal = true; // Show-Hide Modal Check
        this.content = "Loading..."; // Dynamic Data
        this.title = "Loading";    // Dynamic Data
    this.userService.save(this.user).subscribe((data) => {
      console.log(data);
      this.router.navigate(['/ExamSelection'], { queryParams: {user: this.user.id}});
    });
  }
  else{
    this.showModal = true; // Show-Hide Modal Check
        this.content = "Id and name are required"; // Dynamic Data
        this.title = "Fields required";    // Dynamic Data
  }
  }

  hide()
  {
    this.showModal = false;
  }

}
