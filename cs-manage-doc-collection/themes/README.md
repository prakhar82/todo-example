## Generate Themes

#####Install sass using below command
````
npm i -D node-sass
````
#####Run below command on root folder
````
npm run ng -- g theme --name=document-theme --projectName=cs-manage-doc-collection-app --themeVersion=1.58.0
````
#####Create document-global folder inside ./themes/document-theme/scss

#####Create _custom-style.scss file and add below 
```
.personalinfo_nav ul li a span{
  margin:0px 7px;
  }
  .personalinfo_nav{
  height:60px;
  }
  .personalinfo_nav ul li a {

    margin:0px 10px;

  }
  .personalinfo_nav .navbar-nav .nav-link {
      color:#fff;
  }
  .navbar_light{
    background-color:#fff;
  }
  .navbar_light .navbar-brand{
    font-size: 1rem;
      font-weight: bold;
  }
  .navbar_light ul{
    margin-left:13%;
  }
  .navbar_light ul li a {

    margin:0px 16px;

  }

  .img_section img{
    width:100%;
    height:180px;
  }
  .side_navigation .list-group-item{
    border:unset;
    color:#212529;
    text-decoration:none;
  }


  .table_section table td span{
    margin:0px 5px;

  }
  .filter_section .btn{
    border-radius: 0px
  }
  .pagination_section .pagination .page-link{

    background-color:unset;
    border:0px;
  }
  .pagination_section .pagination{
    border-radius:0px;
  }

  .p-none{
    margin-top: 2.0rem!important;
    margin-left: 3.5rem!important;
  }

  .p-none .list-group-item{
    border:unset;
    
  }
  .pagination_section .pagination .page_link{
    right:0;
    position: absolute;
    margin-top: -15px;
  }
  .p-none .list-group-item:hover, .p-none .list-group-item:active {
    font-weight: bold;
  }
  .filter_section .fa-filter{
    top: 1px;
    margin-left: -26px;
    position: absolute;
  }
  .pagination_section .pagination .page-item:nth-child(2){
    text-decoration: underline;
  }
.navbar_light .fa-user{
  top: 8px;
    position: absolute;
    margin-left: -15px;
}

```
##### Open ./themes/document-theme/scss/main.scss and add below line at the end of file
````
@import "document-global/custom-style";
````
 
[<<Back](../README.md)
