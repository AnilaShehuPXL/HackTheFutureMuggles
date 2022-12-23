import { environment } from './../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class QuestService {

  constructor(private http : HttpClient) { }

  getProblems() : Observable<any> {
    return this.http.get(environment.apiUrl + "/quest/" + environment.teamId);
  }

  postProblems(data: string) {
    console.log("posting data");
    console.log(data);
    return this.http.post(environment.localhost + "/spellsolver/setquest", data);
  }
}
