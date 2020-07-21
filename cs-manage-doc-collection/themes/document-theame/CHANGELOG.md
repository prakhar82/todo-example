# Changelog
All notable changes to this project will be documented in this file.

This project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

More info: https://keepachangelog.com/en/1.0.0/

## [1.58.0]

### Added
- centered `bb-button-bar`
- `bb-state-container__footer` class

### Changed
- removing `display-block` from `bb-bock`
- removing `display: inline-block` from `bb-avatar-ui`
- removing `display-block` from `bb-bock`
- updated `bb-dropdown-panel-ui` to cleaner BEM css keeping compatibility to theme 2
## [1.57.3]

### Fixed
- Fixed extra padding that was added from account selector for `_bb-payment-request-widget` and `_bb-consent-request-widget` [ENTI-8197] [HAD-274]
- Fixed extra padding to `_bb-consent-details-widget-ang` [ENTI-8197]
- Broken truncating text `bb-ellipsis-ui`(MAINT-9170)
- Aligned `bb-state-container` styles with theme 2.0
- Added missing Theme 2.0 styling for muliple accounts selector [MAINT-9158]
- Fixed jumping of placeholder when interacting with dropdown in Chrome [MAINT-9158]
- Fix unwanted whitespace in horizontal version of Page Layout Container (MAINT-9150)


## [1.57.2]

### Added
- `bb-input-radio-group` added theme 2.0 compatibility ATOM-540
- `bb-inline-edit` compatibility with theme 2 migration ATOM-543
- `bb-subheader--regular` styles
- Added `bb-stack` alignment classes when screen resizing [ID-1904]
- Added `card-section--highlight` style to highlight active card sections [ID-1903]
- Added screen size dependent alignment for `bb-text-align-*` [ID-1903]
- Add Theme 2.0 style for `bb-select-items-modal` from accessgroup-ui [ENTI-8055]
- Added `bb-list--density-sm` to `bb-list` for design needs [ENTI-8055]
- `bb-input-radio-group` added theme 2.0 compatibility [ATOM-540]
- `bb-inline-edit` compatibility with theme 2 migration [ATOM-543]
- Added `bb-stack` alignment classes when screen resizing [ID-1904]
- Added `card-section--highlight` style to highlight active card sections [ID-1903]
- Added screen size dependent alignment for `bb-text-align-*` [ID-1903]
- Added unlock user styles for `bb-approval-log-details` widget [ENTI-8107]
- Added validation classes to `bb-textarea-ui`
- Added `bb-input-password` compatibility with theme 2
- `bb-user-permissions-item` styles (ENTI-8022)
- `bb-table-ui` cleared utility classes from template
- Added class `bb-select-items__state-container` to `bb-select-items-modal` for vertical stretch [ENTI-8055]
- Added margin bottom for `bb-fieldset-ui` [DBSA-5549]
- `bb-stack` added `--center` modifier in order to be able to center stack items
- `bb-transaction-list-widget` added theme 2.0 compatibility [TRANS-2701]
- Added `bb-stack` responsive `--push-right` and `--push-left` classes [HAD-274]
- `bb-dropdown-menu` utility classes were cleaned up for theme 2 (migration compatibility)

### Fixed
- `layout-container-ang` - added separated scroll for sidebar and main content (MAINT-8979)
- `bb-a2a-external-account-manager` widget styles
- `bb-accessgroup-header-ui` padding fixed (nojira)
- `bb-*` prefix to `item-log` component
- Removed `bb-admin-user-details-overview-widget-ang` specific styling [ID-1904]
- Removed Admin User Details and Overview specific styling [ID-1903]
- Fix styles after update on design for `bb-consent-request-widget` [ENTI-8098] , [HAD-274]
- Fix styles after update on design for `bb-payment-request-widget` [ENTI-8098] , [HAD-274]
- Fix specificity for `bb-consent-request-product-selector__item-button` in `bb-consent-request-widget` [ENTI-8098]
- Removed unused class name from `bb-payment-card-ui` [TRANS-3471]
- `bb-fx-rates-trade-order-modal` - fixed currency input issues after migration to theme v2 [HAD-335]
- Fixed typo in class and removed unnecessary classes for `bb-user-permissions-item` (ENTI-8022)
- `bb-initiate-payment` fix overlap issue on beneficiary selector [PAYM-262]
- `bb-list`: removed padding added to last item (aligned with Theme 2.0)
- Removed unnecessary classes for `bb-user-permissions-item` (ENTI-8022)
- `bb-notification-ui` fix close button
- `bb-stack` fixed `bb-stack__break--xs` and `bb-stack--fill-xs` class generation [HAD-274]

## [1.57.1]

### Added
- `bb-approval-log-list-widget` styles [nojira]
- Add Theme 2.0 style for `bb-attachment-ui`
- Added `bb-business-function-privileges-name` styles (ENTI-8020)

## [1.57.0]

### Added
- `bb-currency-input-ui`: added compatibility with migration to theme 2
- Added `bb-block--fill`
- Added missing style for `card-section` [ENTI-8003]
- `bb-dropdown-multi-select-ui` styles from theme 2
- `bb-consent-details-widget-ang` styles (ENTI-7838)
- Add Theme 2.0 style for `bb-approval-log-details-widget` (ENTI-7911)
- `bb-user-permissions-item` styles (ENTI-8022)
- Added `bb-stack` break spacing classes [ID-1904]
- Added `bb-stack` fill classes when screen resizing [ID-1904]
- `bb-privilege-indicator-list-component` styles (ENTI-8017)

### Fixed
- `bb-page-layout-ui`: moved margin for heading from whole component to the main layout (MAINT-8916)
- `bb-search-box-ui`: fixed search button display after changing it's container from `div` to `label` tag [HAD-356]
- `bb-select-context`: fixed clipping of inner elements due to overflow hidden style (CB-4442)
- Add `min-width` style in `main-content` container to enable flex shrink (WM-1803).
- Add Theme 2.0 styles for `payord-upcoming-payments-widget` (DBSA-5430)
- `bb-common-error-state` styles
- Remove unsued class names in *bb-consent-details-widget* [ENTI-8003]
- Fix `margin-right` in `bb-stack--density-sm` to be `sm` [ENTI-8003]
- Remove double shadow added to *bb-consent-details-permissions* class in *_bb-consent-details-widget* [ENTI-8014]
- Changed class *session-timeout-component__modal* to *bb-session-timeout-modal* (nojira)
- `bb-fx-rates-trade-order-modal`: making full width for button inside bb-load-button-ui [HAD-339]
- `bb-highlight`: fixed font weight

## [1.56.4]

### Added
- `bb-block` styles for margin `spacer` from theme 2
-  Added `bb-stack` to work with `row` from theme
- `bb-request-request-widget` add Theme 2.0 style for (ENTI-7889)

### Fixed
- `navigation-link-widget` - sidebar height is 100% and fully visible in IE (MAINT-8920)
- `navigation-link-widget` - fix scrolling issue when sidebar height is larger than page (MAINT-8921)
- Add Theme 2.0 styles for `bb-payment-card-ui` (TRANS-2861)
-  Added `bb-stack` to work with `row` from theme 2
- `bb-checkbox-ui` theme2 compatibility [ATOM-539]

## [1.56.3]

### Added
- `bb-accessgroup-header-ui` styles
- `bb-stack` Added with 100%
- `bb-notification` updated from theme2
- `bb-text-bold` styles
- `typography` styles from theme 2

### Fixed
- `bb-modal-ui` Fixed close buttons wrong margins [MAINT-8925]
- `bb-product-summary-balance-history-widget` Deleted `width` from `account-balance-history__date` class (PRSUM-5654)
- `bb-consent-request-widget` add Theme 2.0 style for (ENTI-7888)

## [1.56.2]

### Added
- `bb-form-field` Added field fixed width classes.
- `bb-button-bar` Added `bb-button-bar__button--across` for reverse bar
- `bb-stack` Added left and right alignment of stack items based on screen size
- `bb-block` Added `bb-block--no-margin` alternate

### Fixed
- `bb-button-bar` Removed block display on buttons which disrupts icon alignment in buttons
- `bb-button-bar` Removed margin-top from button bar. This is now applied via `bb-block`
- `bb-button-bar` Moved all reverse bar button stylings inside small screen breakpoint
- `bb-notification-ui` added styles for classes that are missing after update to theme 2 [MAINT-8898]
- `bb-account-selector-ui` - fix placeholder/arrow alignment with min-height and flex for IE [HAD-235]
- `bb-account-selector-ui` - fix product item wrapping on mobile [MAINT-8899]
- `bb-dropdown` added styles to fix chevron alignment issues (ATOM-550)
- `bb-badge` fix styles for `abbr` to remove text underline (DBSA-5507)
- `popover`: remove @extend of optional class (MAINT-8758)
- `bb-currency-input-ui` - fix issue with IE11 [WEB2-264]

### Added

### Fixed

## [1.56.2]

## [Unreleased]

### Added
- Theme 2.0 support for `bb-accessgroup-header-ui`

## [1.56.1]

### Added
- Add Theme 2.0 style for `bb-consent-list-widget` (ENTI-7825)
- Add bb-stack and bb-block [ATOM-534]
- Add classes for `bb-label` [MAINT-8831]
- `bb-rich-text-editor-ui` added styles for disabled state, added classes for Theme 2.0 (DBSK-4630)

### Fixed
- `modal`: allow modal to be used without a header and/or footer

- `label`: add font-weight
- `layout`: deleted flex-box for `.account-details__info`

### Fixed
* `bb-dropdown-multi-select-ui` FormControl marked as INVALID even when VALID(MAINT-8623) (CB-4495)

## [1.56.0]

### Added
* Add `bb-toolbar`
* `bb-turnovers-widget` adding missing classes and aligned styles after migration to theme-v2 (WEB2-191)
* Add Theme 2.0 style for `bb_third-party-provider` from consent-ui (ENTI-7811)
* Add classes for `bb-portfolio-summary-allocation-widget` (WM-1925).
* Add Theme 2.0 styles and BEM classes for `bb-switch-ui` component (TRANS-2862)

### Fixed
* Change heading class name for `bb-fieldset-ui` (CB-4501)
* `bb-load-button-ui` - adapted some theme 2 changes

## [1.55.0]

### Added
* Add `bb-char-counter-ui` styles (NO-JIRA)
* Add `pending` category icon for `pending-transactions` (TRANS-2911)
* Add Theme 2.0 styles for `bb-dropdown-single-select-ui` component (ATOM-507)
* Add Theme 2.0 styles for `bb-fieldset-ui` component (CB-4501)

### Fixed

* `bb-product-item` fixed color of product number, for pass accessibility contrast (NO-JIRA)
* `bb-alert-ui`: fix missing margin between icon and heading (ATOM-488)

## [1.54.1]
## [1.54.1]

### Fixed
* `Pending Approvals` - Approve and reject buttons are very close to each other (CB-4476)
* `Contact Manager - Pending Approvals` - Approve and reject buttons have a different style (CB-4477)
* `bb-table` Internet Explorer 11 - Header borders are not displayed properly with unset (CB-4478)
* `Contact Manager - My Requests` - Internet Explorer 11 - Extra line on the top (CB-4486)
*  `Batch Manager` Internet Explorer 11 - Broken layout on the search bar (CB-4471)

## [1.54.0]

### Added
* Add backwards compatible class for `contacts-pending-approvals-widget` after upgrading to theme V2 (CB-4451)

## [1.54.0]

### Added
* Add the class to handle long sweep names in `bb-cash-management-sweep-list-widget-ang` (CME-321)
* `bb-session-timeout-modal`: added zindex class to move modal to top (ID-1736)
* Add Theme 2.0 styles for `bb-badge-counter-ui` component
* Add Theme 2.0 styles for `bb-empty-state-ui` component (ATOM-410)

## Fixed
* Fix border and box shadow for card on `bb-device-information-widget-ang` (DV-497)
* Fix status label and contrast of text fields on `bb-device-information-widget-ang` (DV-497)
* Maintain margin bottom on headers even on small screens for `user-details__heading` (DV-497)
* Fix `bb-product-item-basic-account-ui` by adding missing classes and aligned styles after migration to theme-v2 (WEB2-180)
* Fix modal buttons on small screens in `bb-device-information-widget-ang` (DV-566)

## [1.53.0]

### Added
* Add styles for `bb-period-selector-ui` component (WEB2-178)
* Added BEM classes for `bb-product-sumary-products-list-widget` (PRSUM-5225)

### Fixed
* Fix `bb-icon-ui` add greyer color for delivery tracker ui (TRANS-3151)
* Fix `bb-currency-input-ui` fixing alignment of validation message (PXS-3255)
* Fix `bb-currency-input-ui` by adding missing classes and aligned styles after migration to theme-v2 (WEB2-179)
* Fix styles for `bb-a2a-external-account-manager` widget
* Fix action buttons layout for manage batches on small screens (CB-4362)
* Fix background color for `bb-a2a-details-header` close button
* Fix centering on loading indicator ATOM-442
* `bb-loading-indicator-ui`: fixed loading indicator centering and text width (ATOM-442)
* `bb-textarea-ui` added BEM class for char-counter element (HAD-117)
* `product-item` title becomes white when element is clicked in Safari (NO-JIRA)
* `notifications-badge` - broken badge view on zoom 200% (DLRN-1073)
* Fix `bb-currency-input-ui` styles
* Fix `bb-period-selector-ui` by adding missing classes and aligned styles after migration to theme-v2 (WEB2-178)
* Fix `bb-period-selector-ui` icon's color (WEB2-178)
* Fix styles for `bb-stepper-ui`
* Fix `bb-currency-input-ui` hiding the placeholder when ng-select is filtering
* Fix `bb-mega-menu-navigatopn-container-ui` fixed icons on mobile view
* Fix color of disabled items on `bb-device-information-widget-ang` (DV-497)
* Fix spacing in upload batch modal title and content (CB-4403)
* Fix the currency input ui to support having labels
## [1.52.1]

### Fixed
* `bb-loading-indicator-ui`: fixed loading indicator centering and text width (ATOM-442)
* `bb-textarea-ui` added BEM class for char-counter element (HAD-117)
* `bb-currency-input-ui`: style fixes [WEB2-179]
* `bb-period-selector-ui`: style fixes [WEB2-178]
* `bb-period-selector-ui`: fix icon's color [WEB2-178]
* Fix styles for input-password

## [1.52.0]

### Added
* Added styles for new `bb-fx-rates-currency-pair` component (HAD-29)
* Added styles for `bb-fx-rates-trade-order-modal` component (HAD-29)
* Added styles for new `bb-fx-rates-changed-modal` component (HAD-63)
* Added styles for new `bb-cash-flow-cash-in` widget (CME-358)
* Added styles for new `bb-cash-flow-cash-out` widget (CME-359)
* Added invalid state for account selector for `bb-fx-rates-trade-order-modal` component (HAD-45)
* Added styles for `bb-notifications-list-item` component (DLRN-759)
* Added styles for `bb-fx-rates-spot-order-modal` component (HAD-70)
* Added styles for `mega-menu-navigation-container-ang` universal container
* Added styles for `bb-fx-rates-pair-details` component (HAD-79)
* Added styles for `bb-fx-rates-pair-details` component (HAD-82)
* Added styles for `alert` for Theme 2.0 forwards compatibility (ATOM-349)
* Added active styles for bid/ask in `bb-fx-rates-trade-order-modal` component (HAD-41)
* Added `muted` color for `bb-icon`
* Added styles for new `bb-load-button-ui` (TRANS-2859)
* Added `inverse` modifier for background color in `bb-icon`
* Added background color per product kind to `bb-product-item-card` when card is favorite
* Added styles for `bb-activate-account-form` (PAYM-12)
* Added styles for `bb-list` (CB-4310)
* Added styles for `bb-device-information-widget-ang` (DV-528)
* Added styles for `bb-stop-checks-list-widget` (DBSA-4875)

### Fixed
* Fix Timepicker selector and remove height
* Fix width .bb-fx-rates-rate in `bb-fx-rates-trade-order-modal` component (HAD-63)
* Safari: header color of notification item when clicking on notification in list `bb-notifications-list-item` (DLRN-764)
* Fix styles, classes names for `bb-loading-indicator-ui` component. (WEB2-161)
* Fix `$user-context-dropdown-height` value
* Fix styles, classes names for `bb-stepper-ui` component. (WEB2-162)
* Fix border-radius for `bb-search-ui` (CB-3961)
* Fix overwriting default ng-selected styles in `bb-account-selector-ui` (HAD-67)
* Fix action button spacing for manage batches (CB-4310)
* Suppress deprecate warnings of Bootstrap, since these are fixed in Theme 2.0 (ATOM-432)
* `bb-notifications-badge` fixed active state for notifications badge button (DLRN-1062)
* Fix validation message to hide icon that bootstrap introduced in the latest update (PXS-3245)

## [1.51.1]

### Fixed
* Fix width of first and last columns of Portfolio Summary Positions widget
* Fix Timepicker selector, and remove height
* Fix font size for `bb-a2a-link-account-info`
* Fix font size and border for `bb-a2a-account-manager` widget
* Fix background color for `bb-a2a-account-details-modal` close button
* Fix the height and color of `user-context-dropdown__selector`, `bb-select-context__subheader` respectively
* Fix `bb-avatar-ui` styles in user-context-widget
* Add `btn-unstyled` variant

## [1.51.0]

### Added
* Added styles for new `bb-avatar-ui` component (ATOM-218)
* Added styles for `fx-rates-table` widget (CME-335)
* Added styles for new `bb-input-timepicker-ui` component (ENTI-6918)
* Added styles `bb-modal-ui` component
* Added styles `.bb-button-bar`
* Added support for `bb-icon-ui` component BEM classes (ATOM-258)
* Added styles for `bb-fx-rates-trade-order-modal` component (HAD-15)
* Fixed columns width of `bb-portfolio-summary-positions`
* Added BEM classes for `bb-product-sumary-accounts-overview-widget` (ATOM-180)
* Added BEM styles for user context widget (ATOM-142)
* Added styles for `bb-a2a-external-account-manager` component


### Updated
* Updated Bootstrap to v.4.3.1 (PXS-3105)

### Fixed
* Fixed responsive styles of `fx-rates-table` widget (WM-1918)
* Fixed width of `fx-rates-search` widget on tablet resolution (WM-1921)
* Removed custom bootstrap overrides from scss/vendor/bootstrap
* Fixed styles of `bb-navigation-link-widget-ang`
* Fixed columns width of `bb-portfolio-summary-positions`
* Fix the space added on top of the navigation to be on large screens only

## [1.50.0]

### Added
* Added styles for new `bb-item-log-ui` component (CB-3905)

### Fixed
* Added max-width for context-switch name and description (ENTI-6744)
* Removed Backbase styling for `.btn-info` and `.btn-warning`, fall back to higher contrast Bootstrap styles (PXS-2907)
* Add box-shadow to `bb-payment-card-ui` to be complaint with the design (TRANS-2580)

## [1.49.0]

### Added
* Added `.bb-input-radio-group-label` styles for `_bb-input-radio-ui` component (WEB3-37)
* Added `pdf`, `img` and `unknown` file type icons (DBSK-3986)
* Added scrolling support for `navigation-link-widget` (CB-4000).

### Fixed
* Fixed `navigation-link-widget` ie11 rendering issue (CB-3993)

## [1.48.0]

### Added
* Added default theme vendor styles for `ng-select` component (PRSUM-4764)
* Added `.bb-empty-state-container` styles for `_bb-empty-state-ui` component (CB-3762)
* Added `_bb-dropdown-panel-ui` styles (CME-186)
* Added `_bb-dropdown-multi-select-ui` styles (CME-186)
* Added `_bb-search-box` styles, backported from theme 2.0 (ATOM-109)
* Added `_bb-input-password` styles.
* Added `bb-navigation-horizontal-widget` sticky style (MAINT-7421)

### Fixed
* Fixed narrow `.rb-content` for page-layout on navigation container (NOJIRA)
* Fixed styles for `.bb-notifications-list-item-content__box-orient` for `_ext-bb-notification-badge` component (nojira fix to compile prefixed rule to css)
* Fixed styles for `bb-payment-card-icon-dialo` and `bb-payment-card-back` (TRANS-2280)
* Fixed styles for `bb-input-checkbox-ui` (MAINT-7643)
* Fixed right positioning for `.bb-notifications-badge` to left (CB-3869)
* Fixed alignment issue for `.bb-schedule-ui` (NEOP-330)
* Fixed `bb-notification-ui` width (MAINT-7716)

## [1.47.0]

### Added
* Added `_bb-account-selector-ui` styles (PRSUM-4698)
* Added `_bb-payment-card-activation-ui` for payment cards activation [TRANS-2225]
* Added `map-deep-set` function to set a value in nested maps[MAINT-7184]
* Added `bb-stepper` added class when there's a label available (BAC-2632)
* Added `_bb-currency-input-ui` added class 'currency-input-container' (CME-153)
* Added `_bb-notification-details` styles (CME-211)
* Added styles for notifications list and notifications list item in `_bb-notification-ui` (CME-209)
* Added `_bb-currency-input-ui` added class 'currency-selector' (CME-153)
* Added `_bb-cash-management-sweeps-search` styles (CME-71)
* Added `_bb-cash-management-manage-sweeps-list` added class 'bb-cash-management-sweep-list-container' (CME-71)

### Fixed
* Fixed misalignment of `right-top-navbar` on IE11 (BAC-2670)
* Fixed `_ui-bb-dropdown-select-ng` to have max height and scrollbars [OBPAY-1675]
* Fix displaying of red border around `bb-currency-input-ui` inner fields if there's an invalid value entered [CME-153]
* Moved `_variables.scss` comments to `base.scss` in order to make better example for quickstart scenarios, improving documentation on how to change colors [MAINT-7184]
* Fix upward collapsing of product selector [MAINT-7510]
* Fix positioning of searchbox append button on Windows [MAINT-7517]
* Fix chevron-right wc2 icon mapping [ATOM-41]
* Fix transactions icons to match the designs [MAINT-7553]
* Fix `bb-input-checkbox-ui` color to match the designs [PXS-2327]
* Fix `card` bottom border color to match the designs

## [1.46.0]

### Added
* Added `_bb-user-context-ui` for AccessGroup WC3 (ENTI-6085)
* added `applyAdjacentLines` mixing to create lines between elements
* `bg-greyer` background color
* Added `bb-delivery-tracker` delivery tracking to cards management [TRANS-2017]
* Added `delivery-tracker-${name}` icons to bicon mapping [TRANS-2017]
* Added Service Agreement Icon Ui (ENTI-6039)
* Added `.fa-bars` extension to fix wc2 icon problem (BAC-2476)
* Added equivalent bicon icons to fontawesome `fa-percent` and `fa-money-bill-alt` to fix wc2 wealth. (BAC-2475)
* `bb-switch-ui` now has box-shadow when focused (BAC-2560)
* Added `bb-infinite-scroll-ui` styles (ENTI-6038)
* Added `bb-dropdown-menu-ui` styles (BAC-2431)(BAC-2626)
* Added `contact-search-container` style (CB-3409)
* Added `bb-chart legend-label-color` styles (LF-4052)
* Added equivalent bicon icons to fontawesome `fa-arrow-left` and `fa-plus` for WC2 (BAC-2552)
* Added `bb-cash-management-manage-sweeps-list` style for responsive view (CME-70)
* Added `table-row-*` backgroud styles for table row (CB-3481)
* Added `bb-notifications-badge` styles for showing badge in the top right corner and for removing underline from notifications button (CME-207)
* Added `bb-notifications-badge` removing text underline from notifications badge btn (CME-207)

### Removed
* Removed `chart-tooltip` breakpoint styles (LF-4052)
* The `!important` property from text alignment block of td for responsive view of the table

### Fixed
* Fixed padding for small viewports for `bb-user-context-ui` (ENTI-6156)
* Rename background -> background-size for `bb-user-context-icon-ui`
* Fix position of adjacent lines for `bb-delivery-tracker` [TRANS-2220]
* Fix map height to fully occupy the available height
* Fix progress bar radius to match design guidelines (BAC-2395)
* Fix `calendar-today` and `bar-char` missing icons.
* `bb-switch-ui` size now reflects what's on design (BAC-2561)
* `bb-icon-ui` now has pointer-events: none to overcome the flickering tooltip issue on hover (BAC-2435)
* Fix `bb-navigation-horizontal-widget` height to reflect designs (MAINT-7426)
* Fix `bb-input-radio-ui` selected and border color to reflect designs (BAC-2544)
* Implement active state for "more-btn" and hover state for "all-items" in navigation-horizontal-widget. [MAINT-7347]
* Fix `bb-notification-ui` long text out of the notification field in IE (CME-158)
* Fix displaying of red border around `bb-currency-input-ui` inner fields if there's an invalid value entered [CME-153]
* Fix active state in navigation-horizontal-widget. [MAINT-7347]
* Fix split button in IE for `bb-cash-management-create-sweep-modal` (CME-98)
* `.rb-sidebar` anchor elements now have $base-padding-height as padding-left instead of $small-padding-height (MAINT-7339)

## [1.45.0]

### Added
* Horizontal Navigation styles (BAC-983)
* Add `bb-transactions-filter-ui` for background to transactions filter table form [TRANS-1944]

### Fixed
* ```bb-input-radio-ui``` - radio button center aligned due to label content height(DLRN-152)
* Fixing the '.main-content-btn-anchor' class by removing the width property.
* Sticky columns in the table for Safari (WM-1823)
* Hidden only radio input tag with using custom label template (DLRN-150)
* Fix text-dark to be neutral-9 (darker) instead of $neutral-8 (muted)

## [1.44.7]
### Fixed
* Fix Insight Charts for WC2 (LF-4204)

## [1.44.6]
### Fixed
* Fix rb-sidebar height for WC2 (CB-3511)
* Fix Budget category icons display issue on WC2 `.bb-transaction-category-*` classes (BAC-2558)

## [1.44.5]
### Fixed
* Fix main content on mobile for retail, by utilizing media breakpoint `lg` and above (BAC-2554)

## [1.44.4]
### Fixed
* Fix content mapping of `category-*` classes for bicons/bb-icon-ui (BAC-2549)

## [1.44.3]
### Fixed
* Issues when adding custom account as favorite (PRSUM-4543)
* Fix navigation layout for Universal Collection Handlebars (BAC-2536)

## [1.44.2]

### Fixed
* Sticky columns in table for Safari (WM-1823)
* Fix cursor for Transaction item head rows (WM-1548)

## [1.44.1]

### Fixed
* Fix icon @each iteration of bicon-sizing bug for `.bicons` (NOJIRA)
* Fix category icons @each iteration of bicon-category-* coloring `.bicons` (NOJIRA)

## [1.44.0]

### Added
* Two new exported chart colors (`chart-neutral-color` and `chart-alert-color`)
* Positioning of budget card control button
* Styles to show `bb-badge-ui` inside navigation-spa-widget if sidebar is collapsed
* Support for `div.bb-dropdown-btn-group-wrapper` element under `.btn-group` used in `bb-dropdown-menu-ui` component (BAC-2431)
* Wrapper class for `bb-curency-input-ui` to group labels and inputs (NOJIRA)

### Fixed
* Fixing budget-ng that was displays the category name one character at a time
* Transaction category icon background IE11 issue
* Add `bb-notification-foreground` class for notifications to be in front of a modal window (CME-94)
* `bb-badge-ui` paddings according to design
* Fix stylings for WC2 budgets/insights/transaction (BAC-2485)

## [1.43.0]

### Added
* Add support for `bb-icon-ui` to display category-${icon} via new bicon category classes (`.bicon-category-home`) (BAC-2432)

### Fixed
* Changed border-bottom from greyest to greyer (BAC-2466)
* Add media query to break-points for padding bottom spacing percentage.
* Included backtrack patches, with commits from 1.42.1

## [1.42.1]

### Fixed
* Included hotfixes from previous 1.41.7 Release

## [1.42.0]

### Added
* Include `bar-chart` material icon
* `bb-payment-card-state` class updated according to latest designs
* Introduce `.container-small` and `.container-medium` utility classes for layout-container convenience
* Update Bootstrap default `.container` class gutter paddings from `15px` to `16px` to match design system grids & layouts (see `$grid-gutter-width`)
* `bb-icon-ui` added ability for small (sm) icons when circle

### Fixed
* Fix `.bb-input-radio` styles for content inside label tag(BAC-2394)
* Fix font size of Sweep Item labels on Sweep summary page (CME-97)
* Fix `bb-switch-ui` file name and import it to the components.
* Fix `bb-search-box` clear button not showing properly in modals (BAC-2424)

## [1.41.[5,6,7]] -- patch range (5 to 7)

### Fixed -- backtrack patch, including commits from 1.42.0 (up to prelease 'cr.8')
* Fix `bb-switch-ui` file name and import it to the components.
* Fix `bb-search-box` clear button not showing properly in modals (BAC-2424)
* Fix `headings-widget` with removal of negative margins from `@extend .row` (BAC-2416)

## [1.41.4]
* Fix empty-state icon color
* Fix popover show opacity
* Fix more missing icons from FontAwesome
* Fix notification-stripe to match wc3 alert-variants
* Fix shadow to account-card, budget-card, transaction-item
* Fix headings-widget, content-widget background and padding
* Add missing background color for main-content on CX (next to side-navigation bar)

## [1.41.3]

### Fixed
* Fix imports for business widgets [WC2]
* Fix accounts-overview [WC2] --BAC-2386, BAC-2407
* Fix manage-payments [WC2] --BAC-2408
* Fix contact-manager [WC2] --BAC-2409
* Fix more missing font-awesome shims [WC2] --BAC-2111
* Fix profile-widget [WC2] --BAC-2405


## [1.41.2]

### Fixed
* Fix box-shadow for wc2 cards [WC2]
* Fix notification button misaligned display position [WC2] --BAC-2402
* Fix headings widget styling (background color) [WC2] --BAC-2401
* Fix favorite accounts missing colors [WC2] --BAC-2386
* Fix navigation quick actions (create-new) [WC2] -- BAC-2406
* Applied workaround fix for `.bicon-*` classes without default `.bicon` [WC2]
* Re-imported wc2 extension templates [WC2]

## [1.41.1]

### Added
* Added styles for progress tracker widget

## [1.41.0]

### Fixed
* Fix bug when long text overflows Sweep Rule buttons in IE (CME-65)
* Update page layout to have fixed height for mobile and desktop screens based on the design

## [1.40.1]

### Added
* Added styles for `.bb-table`

## [1.40.0]

### Added
* Added class `.header-label` for bolder labels, use it instead of `.font-weight-bold` to make labels customizable

### Fixed
* Fixed broken styles in ```bb-input-datepicker-ui```
* Update style for `bb-switch` for wc3
* Revert back design updates for `bb-switch` wc2

## [1.39.0]

### Added
* Added styles for `bb-stepper-ui`
* Added class for `bb-input-inline-edit-ui` for rounded buttons
* Added class `chart-bar` that defines minimum height for bar charts

### Fixed
* Use `.active` class instead of `.current` and `.checked` in `bb-stepper-ui`
* Fixed issue where datepicker showed circles around calendar dates on Firefox
* Modal: placement of close button fixed
* Fixed styles for long step's name in `bb-stepper-ui` component
* Update switcher design

## [1.38.1]
* Added styles for `bb-input-inline-edit-ui`

## [1.38.0]

### Added
* Added quickstart guidelines via inline comments in project-level variables.scss
* Added styles for `bb-map-ui`

### Fixed
* Highlight of invalid input component in case its input is grouped with button (has input-group wrapper)
* Added `bb-payment-card-component` styling and correct background classes

## [1.37.0]

### Added
* Added payment card svg images for dynamic background based on card name (TRANS-1718)

### Fixed
* Fixed background style for heading-widget via `.heading-wrapper`
* Added animation support for `.bicon` icons with `.animate` clase
* Fixed background style for heading-widget via `.heading-wrapper`
* Added margin-bottom in `bb-rich-text-editor-ui` between editable container and counter (DBSK-2936)
* Added `.bicon-file-xls`, `.bicon-file-csv`, `.bicon-file-blk`, `.bicon-file-mts`, `.bicon-bb-sepa` styles for file icons.

## [1.36.0]

### Added
* Added active state in `_bb-product-item-ui` styles for all product summary cards in ui-ang (PRSUM-4192)

### Fixed
* `ui-bb-list-ng` elements under `.modal` now use display: block (BAC-2126)

## [1.35.0]

### Added
* Added `bb-message-ui` styles
* Added `text-support` class with text's color #55585A

### Fixed
* Based on Usage, Ported font-awesome `.fa, .fas, .far` font classes to point towards bicons (material-icons)
* Fix variable `$light` to be correctly set as `$neutral-4`
* Updated list group border color

### Removed
* vendor/font-awesome fonts removed from theme

## [1.34.0]

### Added
* Added opt-in bgd color class .heading-area
* Added `bb-search-box-ui` classes for search and clear icon buttons
* (BAC-1759): Introduce `bb-list-multiple-select-ui` component
* (BAC-1759): Introduce `bb-checkbox-group-ui` component
* (BAC-1759): Add indeterminate state for `bb-input-checkbox` component
* Added `.table` and `.table-hover` styling
* Added `bb-paginator-ui` component styling

### Fixed
* Removed model-bank's mw-1140 class from heading-widget

## [1.33.0]

### Added

* Add initial sassdocrc.json configuration for SassDocs, a sass documentation tool
* Add sassdoc npm script command
* Add initial sassdoc poster blocks for (color, icons, typography)
* `bb-input-datepicker-ui` component class and classes for date range support. Component's redesign
- Add --chart-group-bar-color variable for group bar chart
- Add background for forecast group bar in turnovers charts
* Add `.icon-circle` (to be used in conjunction with `.bicon`) for circled, avatar-like icons
* `bg-lighter` background color

### Fixed
* Remap the transaction categories icon colors to the colors comming from the desing
* Miscellaneous backwards compatibility issues with WC2 widgets
* Fixed class `.text-dark` from not properly applying the right color
* Page Layout sidebar resizing issue on smaller screens
* Remove Tabs background color from Tabs Layout
* Modal dialog paddings (header/body/footer) updated to match designs
* Badge left and right padding updated to match design but still needs definition if fixed width is going to be used
* Single select dropdown top padding increased
* Checkbox component checked icon line height set to 1
* Reordered neutral colors from white to black
* Improve styles for sticky columns for Portfolio Summary Positions widget.
* Bigger row height variable for positions widget of PortfolioSummary
* Make all the cells of Positions table of PositionsWidget (Portfolio Summary) with fixed height (WM-1642)

## [1.32.4]

### Fixed

* Fix `.rb-sidebar` width from navigationLayout
* Fix `ui-bb-stepper-ng` stepper number for circular look
* Fix `uib-modal-ng` shims to have it properly display
* Fix misaligned coexistence of `@extend .bicon` and `<i class="fa">` resolutions from ui-ng
* Fix btn-outline-light colors for wc2
* Expose `switcher`, `uib-*` from `backbase-wc2` to main root

## [1.32.3]

### Fixed

* Highlighting of items on tabbing through the list [BAC-2034]

## [1.32.2]

### Fixed

* Enable chart styles for WC3 as well as WC2
* Highlighting of items on tabbing through the list [BAC-2034]

## [1.32.1]

### Fixed

* Fix set-locale-widget flag url paths

## [1.32.0]

### Added

* (BAC-2030): US, ES, FR, NL flags added.
* New `bb-input-radio-ui` component
* (BAC-1639): New README with API for typography, color, file-structure
* (BAC-1639): Added mixin `appearance-toggle()` -- to apply form-control `appearance: none`
* (BAC-1639): Set native BS4 `$enable-caret` to false for disabling `.dropdown-toggle`'s rendered caret
* (BAC-1639): Set native BS4 `$enable-shadows`; to false;
* (BAC-1639): Add future `.shadow` util classes (introduced in BS4.1) with multiple levels (`*-sm`, `*-md`, `*-lg`)
* (BAC-1639): Added pagination variables; Added datepicker variables
* (BAC-1639): Introduce `.font-weight-medium` utility class (extend bs4 equiv.)
* (BAC-1639): Introduce `base/set-defaults.scss` for nullifying Native BS4 pre-default'ed variables

### Fixed

* Fixed `.shadow` util class to use `$box-shadow-lg`
* Fixed product-item color left shadow to use 'selected' color
* (BAC-2023): Fixed `<a href></a>` to text-decorate with underline when hovered
* (BAC-2023): Fixed `font-size-base` syntax for unit `rem` (remove repeated unit in `$font-icon-*`)
* (BAC-2023): Fixed `.text-#{colors}` to use `$text-emphasis-palette`, which uses a darker secondary color
* (BAC-1639): Fixed budget capability donut styles (WC2)
* (BAC-1639): Set priority to resolve `font-awesome` when both `.bicon` and `.fa` are present
* (BAC-1639): Fixed budget capability donut styles (WC2)
* (BAC-1639): Set priority to resolve `font-awesome` when both `.bicon` and `.fa` are present
* Set button type "reset" to show no webkit appearence border
* `angular-cdk-drag-and-drop.scss` simple styling for Angular CDK drag and drop
* Fixed color for `bb-rich-text-editor-ui` component placeholder
* Make `bb-rich-text-editor-ui` outlined on focus

### Removed

* `ng2-dragula` styles. The according library is not used anymore

### Updated

* (BAC-1639): Updated BS4 `*-variants` mixins provisions of util classes: alerts-variants,
    btn-variants, bg-variants, dropdown, typography, badge-variants, icon sizing issue,
    payment-card, product-item
* (BAC-1639): Remapped native BS4 color helpers `$theme-colors`, `colors`
* (BAC-1639): Refactored color usage in select UI to use fn color()
* (BAC-1639): Updated main.scss entry point -- encapsulate WC2-specific styles
* (BAC-1639): Move `backbase/icons/icons` to `components/bb-icon-ui.scss`
* (BAC-1639): Moved `universal-3` from outer `vendor/*` into backbase layer (under `layouts`)
* (BAC-1639): Renamed `variables/bb-messages-ui` to `base/rich-text-editor`
* (BAC-1639): Updated default header font-weight, font-sizes
* (BAC-1639): Updated colors for the navigation sidebar

* Renamed `bb-message-ui` to `bb-rich-text-editor-ui`

## [1.31.1]

### Fixed

* Fixed active state and accessibility styling on Product Selector

## [1.31.0]

### Added

* Added styles for Product Item
* Enabled support for using box-shadows
* Updated default font-weight "bold" to 500
* Added styles for Product Item `bb-product-item-credit-card-ui`
* Added styles for Product Item `bb-product-item-savings-account-ui`
* Added styles for Product Item `bb-product-item-term-deposit-ui`
* Added styles for Product Item `bb-product-item-loan-ui`
* Added styles for Product Item `bb-product-item-investment-account-ui`
* Added styles for Product Item `bb-product-item-debit-card-ui`
* Added styles for Product Item `bb-product-item-current-account-ui`
* Added styles for Product Item `bb-product-item-basic-account-ui`
* Updated bb-icon-ui display to inline-flex from flex
* Added styles for Message component `bb-message-ui`

### Fixed

* Set opposite `border-radius` to 0 in `input-group-prepend` and `input-group-append` classes
* Removed media-breakpoint behaviors for `.rb-sidebar` that were collapses and truncates content in sidebar-web.soy
* Fixed width styling and `md` breakpoint behavior (compact mode) for newly introduced `.soy-template` class on page-layout `.rb-sidebar`, which originally causes overlaps on content area

## [1.30.2]

### Fixed

* Set `bb-badge-ui` component to flex
* Set `bb-button-ui` component to grow
* Set `bb-input-checkbox-ui` component to use $checkbox-outline for focus outline
* Set all buttons to show no webkit appearence border

## [1.30.1]

### Fixed

* Fix `statements` icon in bicon icon set

## [1.30.0]

### Added
* css variable for benchmark chart color
* override ngx-chart styles within `bb-portfolio-summary-charts' component
* ng-invalid with ng-touched parent sets child input and select borders to red

### Fixed

* For `bb-card-vendor-ui`, the VISA logo is changed to white because it is used
  on a dark background
* Hide default icon in IE for `bb-dropdown-single-select`
* Set outline on `bb-input-checkbox` focus to 2px.
* Default `bb-loading-indicator-path` dash array and offset for IE usage.
* Center `bb-icon-ui`
* For `bb-input-checkbox-ui`, allows resizing of checkbox based on parent component text size.
* Fix the height issue of `rb-sidebar` in ie11 and firefox
* Added missing icons to bicon map
    * accounts
    * transactions
    * credit-cards
    * debit-cards
    * quick-action
    * statements
    * unexpected

## [1.29.2]

### Fixed

* Removed border and icon color for `bb-dropdown-single-select` and `bb-dropdown-single-select-icon` classes

## [1.29.1]

### Added

* New vendor folder for universal WC3. 'backbase-universal-3'
* Add styles for layout flex container
* Add styles for navigation link tree widget
* Add styles for set locale widget.
* All the rules added for universal WC3 in:
  * vendor/backbase/layouts/\_navigation.scss
  * vendor/backbase/layouts/\_page.scss

### Removed

* Removed the only file related to a widget from universal WC3 as well as its reference.
  * vendor/backbase/templates/\_set-locale-widget.scss
  * vendor/backbase/templates/templates.scss

### Fixed

* Update background color of sidebar for `page-flex-layout`

## [1.29.0]

### Added

* Added `bb-loading-indicator` class
* Added `bb-dropdown-single-select` and `bb-dropdown-single-select-icon` classes
* Added `bb-input-checkbox` class
* Added `bicon-arrow-in` and `bicon-arrow-out` icons
* Added business banking categories definitions
* Expose spending/income categories colors
* Add `.pre-scrollable-typeahead` to manage scrollable typeahead dropdown menu

### Removed

* Added `bb-loading-indicator` class
* Added `bb-dropdown-single-select` and `bb-dropdown-single-select-icon` classes
* Added `bb-input-checkbox` class

### Fixed

* Created classes for icons and sizing of icons according to Backbase design system
* CSS variables with colors and for each transaction category (per category and combined)
* Donut chart container minimum height and increased size modifiers
* Add `.pre-scrollable-typeahead` to manage scrollable typeahead dropdown menu
* Added `bicon-arrow-in` and `bicon-arrow-out` icons
* Added business banking categories definitions
* Expose spending/income categories colors
* Expose spending/income categories icon content
* Expose chart's tooltip arrow size
* Badge styling fixed (uppercase transform)
* Overrided default hover color for `.heading-tabs`

## [1.28.0]

### Added

* Update README with basic usage of theme wc2 & wc3 stylings
* Separate wc2 & wc3 styling to allow for cleaner inclusion/exclusion inside `main.scss`
* Add lock icon and fallback for whitelabel
* Set of transaction category label classes `bb-transaction-category-label-{CATEGORY}`. Can be used to apply category's color to the element
* Material icons for `.bicon-user`, `.bicon-unexpected` and `.bicon-add-outline`
* Changes for Contact details modal
* `bb-logo-emblem` and `bb-logo-inverse` modifiers for the `bb-logo` class
* `bb-logo-responsive` modifier which makes it 100% width
* `bb-card-vendor` class with modifiers for mastercard, visa and visa-debit
* `bb-payment-card` class
* Font icon size xxl for biggest icons available
* Specific styles for `ext-bus-favorite-accounts-lite-ng` extension.
* Add scrolling for box layout `scrollable-box-content` and tab layout `scrollable-tab-content`
* Add styles for box layout to support fluid image content `fluid-image-box-layout`

### Fixed

* `nav-item` styles updated to match new designs
* Use backbase font size in input variables
* Replace error icon to use exclamation triangle instead of circle
* Override ngx-chart legend wrong width for turnovers, on safari browser
* Donut chart size is reduced. Chart's slice icons and values font size is increased
* Font icon size for xl has been reduced, there was too many gap between lg.

## [1.27.0]

### Added

* Added `npm run build` to transpile sass and minify output to css/ directory (for distribution of source and minified theme, together)
* Restructured theme file structure (removed /theme folder; un-nest /theme/styles as /scss at root level)
* Officially renamed package.json's project name to '@backbase/backbase-theme'
* Manual bump of package.json's project version (delegating 1.26.0 --> 1.27.0)
* Removed settings.gradle

## [1.25.2]

### Added

* override ngx-chart styles within `bb-turnovers-bar-chart` component

## [1.25.1]

### Added

* Material icons for `.bicon-loading` and `.bicon-ellipsis-h`

### Fixed

* Apply `navbar` link styles when parent is `.nav-item`
* Apply new styles for `nav-item` inactive, active and hover state

## [1.25.0]

### Changed

* `.bicon-download` icon content

### Fixed

* Remove vertical padding in account selector button

### Added

* `.button-toolbar` class to manage responsiveness on payment form buttons
* Selected dropdown item icon in dropdown list
* Added `.bicon-xl` icon class
* Added `.top-side-navigation-layout` namespace to add some styles only applicable within this namespace.

## [1.24.0]

### Added

* Added `.card-scrollable-x` class so account cards can be scrollable by horizontal
* Added `.ext-bus-favorite-accounts-ng` class for cards in ext-bus-favorite-accounts-ng and ext-bus-accounts-overview-ng
* Added `.button-toolbar` class to manage responsiveness on payment form buttons
* Selected dropdown item icon in dropdown list
* Added `.bicon-xl` icon class
* `.button-toolbar` class to manage responsiveness on payment form buttons
* Selected dropdown item icon in dropdown list
* Added `.bicon-autorenew` icon class

### Changed

* `.bicon-download` icon content
* Removed some specificity regarding navigation that added complexity to the system

### Fixed

* Remove vertical padding in account selector button
* Modified styling for switcher component

## [1.23.0]

### Added

* Added input-group customizations
* Added calendar popup component styles

## [1.22.0]

### Added

* Added `.bicon-lg` modifier class for large icons
* Added `.bicon-current-accounts`, `.bicon-savings-accounts`, `.bicon-term-deposits`, `.bicon-credit-cards`, `.bicon-debit-cards`, `.bicon-loans` and `.bicon-investment-accounts` icon classes for product kinds
* Added contextual classes for `ui-bb-account-card-ng` component (bb-account-card-primary, bb-account-card-success, ...)
* `.ext-bb-product-summary-overview-ng` styles are applied to `.ext-bb-product-summary-ng` as well

### Changed

* Removed styles from `.ext-bb-product-summary-overview-ng` that are applied directly to the account card component

## [1.21.0]

### Added

* Added `.page-heading-wrapped .heading-wrapper` class that allows adding some padding to the page heading content

## [1.20.2]

### Fixed

* Change active state to use .active instead of .focus for button group toggle

## [1.20.1]

### Fixed

* Budget card width

## Added

* Added `.bicon-download`, `.bicon-caret-up`, ,`bicon-caret-down` icon classes

## [1.15.0]

### Added

* Removed spacing class `.pb-100p` and `.pb-25p`
  (\_spacing.scss)
* Added loop to autogenerate class `.pb-5p` to `.pb-100p` with a step of 5
  (\_spacing.scss)
* Added `.bicon-edit` icon class

## [1.14.0]

### Added

* Added utility class .pb-25p to creating proportion for height to be 1/4 the width
* Added `.bicon-note-add`, `.bicon-edit`, `.bicon-print` and `.bicon-add-a-photo` icon classes

## [1.11.2]

### Added

* Added utility class .pb-100p for square layouts (\_spacing.scss)

## [1.9.1]

### Added

* CHANGELOG.md

### Changed

* Bumped version from 1.9.0 to 1.9.1 in package.json (model.xml auto-handled by jenkins)
* Updated under-the-hood whitelabel theme to latest 1.13.0
* Moved old variables to variables-legacy (imported within `vendor/backbase/helpers/helpers`)

### Fixed

* Improved custom theme load-order to correctly override vendor themes
