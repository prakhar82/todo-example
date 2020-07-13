import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map, pluck } from 'rxjs/operators';
import { RoutableContainer } from '@backbase/foundation-ang/core';

@RoutableContainer()
@Component({
  selector: 'bb-document-widget',
  templateUrl: './document-widget.component.html',
  styles: []
})
export class DocumentWidgetComponent implements OnInit {


  constructor() { }

  ngOnInit() {
    
  }

}
