import { environment } from './../environments/environment';
import { QuestService } from './../services/quest.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'htf-2022-angular';
  quest : string = "";
  url : string = environment.localhost + "/spellsolver";

  constructor(private questService : QuestService) {  }
  
  ngOnInit(): void {
    this.questService.getProblems().subscribe(data => {
      this.quest = data;
      this.questService.postProblems(data).subscribe();
    });
  }

}
