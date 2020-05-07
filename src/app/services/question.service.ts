import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Question } from '../datamodel/question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  url :string = "http://localhost:8080/quiz-rest/rest/question";

  constructor(private httpClient: HttpClient) { }

  save(question :Question){
    return this.httpClient.post(this.url, question);
  }

  getById(questionId :number){
    return this.httpClient.get<Question>(this.url+"/"+questionId);
  }

  get(examId :number){
    return this.httpClient.get<Question>(this.url+"/exam/"+examId);
  }

  update(question :Question){
    return this.httpClient.put(this.url, question);
  }
  
  delete(questionId :number){
    return this.httpClient.delete(this.url+"/"+questionId);
  }

}
