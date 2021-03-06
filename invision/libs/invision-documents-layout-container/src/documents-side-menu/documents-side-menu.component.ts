import { Component, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { RouterService } from '@backbase/foundation-ang/core';


@Component({
  // tslint:disable-next-line: component-selector
  selector: 'documents-side-menu',
  templateUrl: './documents-side-menu.component.html'
})
export class DocumentsSideMenuComponent implements OnInit, OnChanges {
  transferTab: any;
  clicked: any;
  @Input() transferTabs: any;
  
  constructor(private router: RouterService) { }

  ngOnInit() {}
    

  ngOnChanges(changes: SimpleChanges) {
    if (changes.transferTabs) {
      this.transferTab = this.transferTabs.filter((tab:any) => {
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
