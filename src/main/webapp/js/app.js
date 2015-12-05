'use strict';

/* App Module */

var itemcatApp = angular.module('itemcatApp', [
  'ngRoute',
  'itemcatAnimations',
  'itemcatControllers',
  'phonecatFilters',
  'itemcatServices',
  'termProjectCustomDirectives'
]);

itemcatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/items', {
        templateUrl: 'partials/item-list.html',
        controller: 'ItemListCtrl'
      }).
      when('/item/:itemId', {
        templateUrl: 'partials/item-detail.html',
        controller: 'ItemDetailCtrl'
      }).
      when('/item-edit/:itemId', {
          templateUrl: 'partials/item-detail-edit.html',
          controller: 'ItemDetailEditCtrl'
        }).
      otherwise({
        redirectTo: '/items'
      });
  }]);
