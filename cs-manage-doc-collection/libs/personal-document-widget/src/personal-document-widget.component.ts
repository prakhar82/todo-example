import { Component, OnInit } from '@angular/core';
import { RoutableContainer } from '@backbase/foundation-ang/core';
import { ActivatedRoute } from '@angular/router';

@RoutableContainer()
@Component({
  selector: 'bb-personal-document-widget',
templateUrl: './personal-document-widget.component.html'
})
export class PersonalDocumentWidgetComponent implements OnInit {

  constructor(private route: ActivatedRoute) {}



  ngOnInit() {

  }

}
