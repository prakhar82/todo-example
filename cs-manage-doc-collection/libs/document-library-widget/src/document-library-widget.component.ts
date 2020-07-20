import { Component, OnInit } from '@angular/core';
import { RoutableContainer } from '@backbase/foundation-ang/core';

@RoutableContainer()
@Component({
  selector: 'bb-document-library-widget',
  templateUrl: './document-library-widget.component.html'
})
export class DocumentLibraryWidgetComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
