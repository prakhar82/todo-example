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
  // @Input() pendingCount: number;
  // responseFrmSubMenu: string;

  constructor(private router: RouterService) { }

  ngOnInit() {
    // this.transferService.getResponseFromSubMenu().subscribe(res => {
    //   this.responseFrmSubMenu = res.toString().split("/")[1];
    //   if (this.responseFrmSubMenu) {
    //     localStorage.setItem("Route", this.responseFrmSubMenu);
    //   }
    // })
    // this.responseFrmSubMenu = localStorage.getItem("Route");
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.transferTabs) {
      this.transferTab = this.transferTabs.filter((tab:any) => {
        return tab.createSideMenu === 'true';
      });
      // To show/hide Add Beneficary button on New payment->External if Beneficiary profile is not retrieved
      // this.transferTabs.find(tab => {
      //   if (tab.route === 'beneficiary') {
      //     return this.transferService.setShowHideAddBeneficiary(true);
      //   }
      // });
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
