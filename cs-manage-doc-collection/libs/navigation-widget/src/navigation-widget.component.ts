import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { RoutableContainer } from '@backbase/foundation-ang/core';
import { Router } from '@angular/router';
@RoutableContainer()
@Component({
  selector: 'bb-navigation-widget',
  templateUrl: './navigation-widget.component.html',
  styles: []
})
export class NavigationWidgetComponent implements OnInit {


/**
* Output  of navigation widget component send to Personal Document Widget
*/
@Output() sendFlagtoPersonalDoc = new EventEmitter<boolean>();
/**
* Output  of navigation widget component send to Document Library widget
*/
@Output() sendFlagtoDocumentLibrary = new EventEmitter<boolean>();
constructor(private router: Router){}

  ngOnInit() {
  }
  /**
   * Opens personal doc widget
   */
  openPersonalDoc(){
    this.sendFlagtoPersonalDoc.emit(true);
    this.sendFlagtoDocumentLibrary.emit(false);
  }

  /**
   * Opens document library
   */
  openDocumentLibrary(){
    this.sendFlagtoDocumentLibrary.emit(true);
    this.sendFlagtoPersonalDoc.emit(false);
  }
}
