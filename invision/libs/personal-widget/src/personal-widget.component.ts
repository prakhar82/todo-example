import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable,BehaviorSubject  } from 'rxjs';
import { map, pluck } from 'rxjs/operators';
import { RoutableContainer } from '@backbase/foundation-ang/core';
import { switchMap, filter } from 'rxjs/operators';

@RoutableContainer()
@Component({
  selector: 'bb-personal-widget',
  templateUrl: './personal-widget.component.html',
  styles: [],
})
export class PersonalWidgetComponent implements OnInit {


   constructor(private route: ActivatedRoute) {}



  ngOnInit() {

  }
}
