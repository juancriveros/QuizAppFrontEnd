import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../datamodel/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url :string = "http://localhost:8080/quiz-rest/rest/user";

  constructor(private httpClient: HttpClient) { }

  save(user : User){
    return this.httpClient.post(this.url, user);
  }
}
