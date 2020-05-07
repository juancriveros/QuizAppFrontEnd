import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Exam } from '../datamodel/exam';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  url :string = "http://localhost:8080/quiz-rest/rest/exam";

  constructor(private httpClient: HttpClient) { }

  save(exam :Exam){
    return this.httpClient.post(this.url, exam);
  }

  getById(examId :number){
    return this.httpClient.get<Exam>(this.url+"/"+examId);
  }

  get(){
    return this.httpClient.get<Exam>(this.url);
  }

  update(exam :Exam){
    return this.httpClient.put(this.url, exam);
  }
}
