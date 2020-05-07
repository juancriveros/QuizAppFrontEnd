import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ExamListComponent } from './components/exam-list/exam-list.component';
import { ExamInfoComponent } from './components/exam-info/exam-info.component';
import { ExamTitleComponent } from './components/exam-title/exam-title.component';
import { ExamQuestionsComponent } from './components/exam-questions/exam-questions.component';
import { ExamSelectionComponent } from './components/exam-selection/exam-selection.component';
import { PresentExamComponent } from './components/present-exam/present-exam.component';
import { ExamResultsComponent } from './components/exam-results/exam-results.component';
import { ExamUserComponent } from './components/exam-user/exam-user.component';


const routes: Routes = [
  { path: 'ExamList', component: ExamListComponent },
  { path: 'ExamInfo', component: ExamInfoComponent },
  { path: 'ExamTitle', component: ExamTitleComponent },
  { path: 'ExamQuestions', component: ExamQuestionsComponent },
  { path: 'ExamSelection', component: ExamSelectionComponent },
  { path: 'PresentExam', component: PresentExamComponent }, 
  { path: 'ExamResults', component: ExamResultsComponent },
  { path: 'ExamUser', component: ExamUserComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
  