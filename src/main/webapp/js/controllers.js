'use strict';

/* Controllers */

var itemcatControllers = angular.module('itemcatControllers', []);

itemcatControllers.controller('ItemListCtrl', [ '$scope', 'ItemService',
		function($scope, ItemService) {
			$scope.items = ItemService.query();
			$scope.orderProp = 'age';
		} ]);

itemcatControllers.controller('ItemDetailCtrl', [ '$scope', '$routeParams',
		'ItemService', function($scope, $routeParams, ItemService) {
			$scope.item = ItemService.get({
				itemId : $routeParams.itemId
			}, function(item) {
				$scope.mainImageUrl = item.images[0];
			});

			$scope.setImage = function(imageUrl) {
				$scope.mainImageUrl = imageUrl;
			};
		} ]);

itemcatControllers.controller('ItemDetailEditCtrl', [ '$scope', '$routeParams',
		'ItemService', function($scope, $routeParams, ItemService) {
			$scope.item = ItemService.edit({
				itemId : $routeParams.itemId
			}, function(item) {
				$scope.mainImageUrl = item.images[0];
			});

			$scope.setImage = function(imageUrl) {
				$scope.mainImageUrl = imageUrl;
			};
			
			$scope.uploadImage = function(imageUrl) {
				$scope.mainImageUrl = imageUrl;
			};
			
			$scope.save = function() {
				$scope.success = ItemService.post({
					itemId : $routeParams.itemId,
				},$scope.item);
			};			
		} ]);

itemcatControllers.controller('UploadController',[ '$scope', 'fileReader',
		function ($scope, fileReader) {
    		console.log(fileReader)
    		$scope.getFile = function () {
    			$scope.progress = 0;
    			fileReader.readAsDataUrl($scope.file, $scope)
                     .then(function(result) {
                         $scope.imageSrc = result;
                     });
    		};
    		$scope.$on("fileProgress", function(e, progress) {
    			$scope.progress = progress.loaded / progress.total;
    		});
}]);