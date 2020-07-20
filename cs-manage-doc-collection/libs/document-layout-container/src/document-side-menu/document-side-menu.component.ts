import { Component, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { RouterService } from '@backbase/foundation-ang/core';


@Component({
  // tslint:disable-next-line: component-selector
  selector: 'document-side-menu',
  templateUrl: './document-side-menu.component.html'
})
export class DocumentSideMenuComponent implements OnInit, OnChanges {
  clicked: any;
  @Input() documentTabs: any;
  
  constructor(private router: RouterService) { }

  ngOnInit() {}
    

  ngOnChanges(changes: SimpleChanges) {
    if (changes.documentTabs) {
      this.documentTabs = this.documentTabs.filter((tab:any) => {
        return tab.createSideMenu === 'true';
      });
      
    }
  }
  /**
   * Navigates to
   * @param id
   */
  navigateTo(route: string) {
    localStorage.setItem("Route", route);
   // this.responseFrmSubMenu = route;

    this.router.navigate([route]);
  //  this.beneficiaryService.setStartPosition();
  }
}
