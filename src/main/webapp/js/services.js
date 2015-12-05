'use strict';

/* Services */

var itemcatServices = angular.module('itemcatServices', ['ngResource']);

itemcatServices.factory('ItemService', ['$resource',
  function($resource){
    return $resource('rest/:verb/:itemId', {}, {
      query:{method:'GET', params:{verb:'items'}, isArray:true},
      get:  {method:'GET', params:{verb:'item'}},
      post: {method:'POST', params:{verb:'item',itemId:'ax'},headers: {'Content-Type': 'application/json'},data: "message=atilla"},
      edit: {method:'GET', params:{verb:'item-edit'}}
    });
  }]);

