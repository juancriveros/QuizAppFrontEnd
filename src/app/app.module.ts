import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
import { ExamListComponent } from './components/exam-list/exam-list.component';
import { ExamInfoComponent } from './components/exam-info/exam-info.component';
import { ExamQuestionsComponent } from './components/exam-questions/exam-questions.component';
import { MaterialDesignFrameworkModule, Bootstrap4FrameworkModule } from 'angular6-json-schema-form';
import { ExamTitleComponent } from './components/exam-title/exam-title.component';
import { FormsModule } from '@angular/forms';
import { ExamSelectionComponent } from './components/exam-selection/exam-selection.component';
import { PresentExamComponent } from './components/present-exam/present-exam.component';
import { ExamResultsComponent } from './components/exam-results/exam-results.component';
import { ExamUserComponent } from './components/exam-user/exam-user.component';

@NgModule({
  declarations: [
    AppComponent,
    ExamListComponent,
    ExamInfoComponent,
    ExamQuestionsComponent,
    ExamTitleComponent,
    ExamSelectionComponent,
    PresentExamComponent,
    ExamResultsComponent,
    ExamUserComponent
  ],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    AppRoutingModule,
    MaterialDesignFrameworkModule,
    Bootstrap4FrameworkModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
