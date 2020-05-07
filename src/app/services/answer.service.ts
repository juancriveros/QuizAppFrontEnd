import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Answer } from '../datamodel/answer';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  url :string = "http://localhost:8080/quiz-rest/rest/answer";

  constructor(private httpClient: HttpClient) { }

  save(answers :Answer){
    return this.httpClient.post(this.url, answers);
  }

  get(examId: number, userId: string){
    return this.httpClient.get(this.url+"/"+examId+"/"+userId);
  }
}
