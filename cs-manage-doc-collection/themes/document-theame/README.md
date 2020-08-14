![Pixies Power](https://backbase.atlassian.net/wiki/download/thumbnails/522027682/sticker.png?version=1&modificationDate=1529315648939&cacheVersion=1&api=v2&width=150&height=150)

# Backbase Theme
   
The Official Theme for Backbase Widget Collections, which includes both WC2 and WC3 styles:


## Table of Contents:

When developing the theme, keep in mind that the backbase layer is laid on top of bootstrap 4 source. Please view the following topics on how to effectively and efficiently use the theme in your UIs and/or Widgets.

  * [File Structure Tree: Conventions](#theme-file-conventions)
  * [File Structure Tree: Load-order](#theme-file-loadorder)
  * [Using the Color Palette](#theme-colors)
  * [Using Typography: Icons & Texts](#theme-typography)
  * [New Variables Checklist](#theme-var-checklist)
  * [Debugging tips](#theme-debugging)

## Theme File Structure: Conventions: <a name="theme-file-conventions"></a>


### Top-level Structure: Project Theme Context
```
├── assets        <== images, audio, and other static media content
│   ├── fonts
│   │   └── Roboto
│   └── images
│       ├── bb-card-vendor-ui
│       └── bb-payment-card-ui
└── scss
    ├── base
    │   └── base.scss
    ├── components
    │   ├── _ui-bb-example-component-ng.scss
    │   └── components.scss
    ├── fonts                        <== @font-face Typography Definitions
    │   ├── _fontawesome.scss
    │   ├── _roboto.scss
    │   └── fonts.scss
    ├── helpers
    │   ├── _functions.scss
    │   ├── _mixins.scss
    │   ├── _variables.scss
    │   └── helpers.scss
    ├── main.scss
    ├── templates
    │   ├── _ext-bb-example-extension-ng.scss
    │   └── templates.scss
    └── vendor
        ├── @material
        ├── angular-ui-tree-wc2
        ├── backbase                 <== Main theme layer 
        ├── backbase-wc2             <== Widget Collections 2 Only layer
        ├── bootstrap
        ├── font-awesome
        ├── material-design-icons
        └── ng2-dragula
```

### Low-level Structure: Backbase Theme Core
```
scss/vendor/backbase
├── backbase.scss
├── base                <== [*Core Sass helpers* -- mainly consists of primitive values and extends atop of Bootstrap helpers (no-css-output)]
│   ├── _alerts.scss
│   ├── _animations.scss
│   ├── _background.scss
│   ├── _badges.scss
│   ├── _buttons.scss
│   ├── _card-vendor.scss
│   ├── _cards.scss
│   ├── _functions.scss
│   ├── ...
│   └── base.scss
├── helpers           <== [*Non-Core Capability-specific Sass Helpers* -- consume/reference `base` layer helpers (no-css-output)]
│   ├── _exports.scss   <== exported css-variables for widget-level consumption
│   ├── _mixins.scss
│   ├── helpers.scss
│   └── variables
│       ├── _accounts.scss
│       ├── _avatars.scss
│       ├── _backgrounds.scss
│       ├── _borders.scss
│       ├── _buttons.scss
│       ├── _cards.scss
│       ├── ...
│       └── variables.scss
├── layouts           <== [*Universal Collection, Page, Containers Styles* -- (outputs actual css)]
│   ├── _box.scss
│   ├── _global.scss
│   ├── _page.scss
│   ├── ...
│   └── layouts.scss
├── components         <== [*UI Collection Component Styles* -- (outputs actual css)]
│   ├── bootstrap        <== [*Bootstrap Component Class Overrides* -- (outputs actual css)]
│   │   ├── _alert-variants.scss
│   │   ├── _background-variants.scss
│   │   ├── _badge-variants.scss
│   │   ├── _button-variants.scss
│   │   ├── _cards.scss
│   │   ├── ...
│   │   └── bootstrap-components.scss
│   ├── _avatars.scss
│   ├── _bb-badge-ui.scss
│   ├── _bb-button-ui.scss
│   ├── _bb-card-vendor-ui.scss
│   ├── ...
│   └── components.scss
└── templates         <== [*Widget Extension Slot Template styles* -- (outputs actual css)]
    ├── _ext-bb-budget.scss
    ├── _ext-bb-transactions.scss
    └── templates.scss
```

### For WC3 Projects only
To keep your theme more light weight, remove/comment-out the importing of v2 styles
```scss
//Inside main.scss

//==================================
// Backbase Theme Loader
//==================================
@import 'vendor/backbase/backbase';

//Encapsulated WC2 styles <-- Comment/Remove Block
[class$="-ng"][class^="widget-bb-"] {
  @import "vendor/backbase-wc2/base/base";
  @import "vendor/backbase-wc2/helpers/helpers";
  @import 'vendor/backbase-wc2/backbase-wc2';
}

```
As a result, your theme will be v3 only. 



## File Structure Tree: Load-order <a name="theme-file-loadorder"></a>

* Sass files are loaded on a *last-in-first-out* basis. So the latest style statements will win over the same transpiled css-selected instructions defined earlier in the CSS bundle runtime. 

* By convention, we keep our sass partial stylesheets organized in a folder context that semantically represents its specificity in regards to Web-based Backbase Products, listed below from global-to-local spectrum.
    1.  Base -- where you'd want to make shared changes across Backbase Products
    2.  Helpers
    3.  Layouts
    4.  Components
    5.  Templates -- where you'd want to single-out one template for styles that don't necessarily want to be shared

* Stylesheets should be scoped inside their belonging *footprint* or *range of impact* folders (see above list)

* It is recommended to always check if the new styles introduced to the theme can be shared with other styles first, before writing one-off stylesheet partials that are hard to update in a DRY and efficient manner (when in doubt, always defer to the single-source-of-truth designs provided by the design system)


## Using the Color Palette <a name="theme-colors"></a>

Atop of Bootstrap 4's color system, the Backbase Theme provides a number of helpful APIs that could make working with colors easier.

A few caveats on widget theming -- READ THIS BEFOREHAND:
*   Never introduce/modify colors unless they are prescribed by Sketch Design Kit on Abstract
*   Always consult the public API sections, located in `backbase/base/_colors`
*   If a style block is marked \[PRIVATE\], do not use those variables elsewhere as those interfaces are reserved for baseline `backbase/base/**` usage only  
*   Do not directly use bootstrap helpers (variables, functions, mixins) as those can hijack the intended theme cascade and lead to unintended and hard-to-debug effects. 

### Backbase Theme Helpers API

#### @function color($colorProps...)

When referencing thematic colors, using this function allows you to make accurate and semantic calls to the $color-palette base variable. This allows for an schema-like representation of the colors in the theme, at any time.

*   Usage Example:
```scss
//SASS Helper Code
$table-row-active-color: color("actions", "selected", "default")

//CSS Output Code
.my-product-item {
  background-color: color("actions", "primary", "dark");
  &:hover {
    background-color: color("selected"); //shorthand for color("actions", "selected", "default")
  }
}
```

*   Supported Shorthand for 'Default' Colors: (primary|secondary|danger|success|warning|info|selected)


#### @variable $color-palette

*   The under-the-hood base color object used mainly for 'extending' over the bootstrap defaults.

*   The colors defined here are referenced in iterable overrides of core mixins and functions. 

```scss
$primaryMap: (
  lighter: $blue-lighter,
  light: $blue-light, 
  default: $blue,
  dark: $blue-dark,
  darker: $blue-darker,
  darkest: $blue-darkest
);

//...
//...
//... other ${*}Map variables

$color-palette: (
  actions: (
    primary: $primaryMap,
    secondary: $secondaryMap,
    selected: $selectedMap
  ),
  support: (
    danger: $dangerMap,
    warning: $warningMap,
    success: $successMap,
    info: $infoMap,
  ),
  neutrals: $neutralsMap,
  extends: $colors
); 
```

####  @function map-deep-get(nestedProps...)

*   Since Sass doesn't have a native method to access nested map values, a map-deep-get method was introduced to perform such operations. 

*   Usage Example -- Equivalent of above color()
```scss
//SASS Helper Code
$table-row-active-color: map-deep-get($color-palette, "actions", "selected", "default"); // notice the verboseness

//CSS Output Code
.my-product-item {
  background-color: map-deep-get($color-palette, "actions", "primary", "dark");
  &:hover {
    background-color: map-deep-get($color-palette, "actions", "selected", "default"); //not a shorthand anymore :-(
  }
}
```

####  @function color-yiq($color-value)

*   One of the few exceptions to the default Bootstrap API that will help you in providing the correct accessibility for color-contrast. 
*   Especially useful in retrieving colors for readable text and icons that are contrasted correctly on different UI color states. 


## Using Typography: Icons & Texts <a name="theme-typography"></a>

For Typography, which affects both readable text and icons, there has been a slight, but global modification that affects all theming.

### Typography Guidelines & API
A Few Caveats for Widget Typography: PLEASE READ BEFOREHAND
*   Always consult the font base sections, located in `backbase/base/_typography`
*   Always attempt to first use utility classes from bootstrap/backbase that can be directly applied inside your templates.
*   Only in cases where, due to technical complexity or new designs/functional-contexts, will there be a warrant for introducing new theme helpers (e.g. table styles, drag-and-drop-lists, etc)
*   HTML elements should be semantic to the content shown. Just because a `<h2>` tag has the correct `font-size` doesn't mean that selector is semantic for the content!

### Font API

#### Font Family 
The main text font used is `Roboto`, with 3 defined variants
   1. Regular: `font-weight: 400 (i.e. normal)`  
   2. Thin: `font-weight: 300`  
   3. Medium: `font-weight: 500 (i.e. bold)`  

Template Usage
   ```html
   <p class="font-weight-medium"></p>
   <p class="font-weight-regular"></p>
   <p class="font-weight-light"></p>
   ```
Sass Usage
   ```scss
   $title-text: $font-weight-medium;
   $normal-text: $font-weight-regular;
   $subtitle-text: $font-weight-light;
   ```

#### Font Sizing
*   The base font is designated default at `16px` 
*   Header tags have been modified to adopt differing `font-weight`s: 

*   Default HTML Selector
    ```scss
    //Designated Font-weight per Heading group 
    h1 { font-weight: $font-weight-light; }
    h2 { font-weight: $font-weight-regular; }
    h3 { font-weight: $font-weight-medium; }
    h4 { font-weight: $font-weight-medium; }
    h5 { font-weight: $font-weight-medium; }
    h6 { font-weight: $font-weight-medium; }
    ```

*   Utility Classes for Template Usage
    ```html
    <!--14px-->
    <p class="text-small"></p>
    <!--20px-->
    <p class="text-large"></p>
    ```

### Icon API
*   Requires the usage of component `bb-icon-ui`, a ui-ang component
*   `bb-icon-ui` is mapped interfaced to render sized icons defined in `backbase/base/_icons.scss`, where a configureable `$icon-map` resides for key:val mappings to `:before { content: $icon_map_value }`
*   If you are wondering where the content values are defined for Material-Icons, check the `material-design-icons/codepoints` manifest file
    

## New Variables Checklist  <a name="theme-var-checklist"></a>
Below is a set of conditions you can use when thinking about adding new variables
1.  There is a new design that doesn't share styles from the pre-existing theme
2.  There is an unique functional/semantic context that should be de-coupled and have its own separation-of-concern (e.g. product-selector vs. dropdown), due to its reuse coverage size, widget-agnostic qualities


## Debugging tips <a name="theme-debugging"></a>

### For checking your sass code validity
```bash
# For WC2 (bb-build-sass-plugin)
bb-package -b sass .

# For WC3, angular's webpack sass-loader handles it
```

### For checking sass-to-css values
*   Especially useful when trying to pinpoint why your theme changes aren't reflected on your project
*   When in doubt or lost, use the following to log a few touchpoints inside `main.scss` to assess if it's a load-order issue, or a css-selector issue.

```scss
@debug $my-variable-value;
@warn "Warning: there is something wrong with #{$my-variable-value}";
@error "Error: the value fails because of condition #{$my-variable-value < 0}";
```