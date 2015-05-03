/**
 * Main AngularJS Web Application
 */
var logTailerApp = angular.module('angularjs', ['ngRoute']);
 
logTailerApp.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    .when("/directroy-list", {
    	templateUrl: "partials/directory-list.html", 
    	controller: "DirectoryListCtrl"
     })
    .when("/directroy-contents/:folder_name", {
    	templateUrl: "partials/directory-contents.html", 
    	controller: "DirectoryContentsCtrl"
     })     
    .otherwise({
    	redirectTo: '/directroy-list'
    });
}]);


logTailerApp.controller('DirectoryListCtrl', function ($scope, $location, $http) {
    $scope.selectedRow = null; 
    
    var responsePromise = $http.get("/FancyLogTail/resources/LogTailerImpl/getDirectoriesList");
    responsePromise.success(function(data, status, headers, config) {
    	$scope.directoryList = data.logFile;
    });
    responsePromise.error(function(data, status, headers, config) {
        console.log("Request failed!");
    });     
    
    $scope.onHoverFolder  = function(index){ 
    	$scope.selectedRow = index;
    };
    
    $scope.onClickFolder = function(index){
    	$location.path("/directroy-contents/"+this.directory.logFileName);
    };
    
});

logTailerApp.controller('DirectoryContentsCtrl', function ($scope,$http,$routeParams,$window) {
	$scope.foldedName = $routeParams.folder_name;
    var responsePromise = $http.get("/FancyLogTail/resources/LogTailerImpl/getDirectoryContent/"+$routeParams.folder_name);
    responsePromise.success(function(data, status, headers, config) {
    	$scope.directoryContents = data.logFile;
    });
    responsePromise.error(function(data, status, headers, config) {
        console.log("Request failed!");
    }); 	
    
    $scope.onClickOpenIcon = function(index){
    	console.log("Open Icon clicked!");
    };

    $scope.onClickDownloadIcon = function(index){ 	
    	$window.open('/FancyLogTail/resources/LogTailerImpl/downloadLogFile?logFileAbsolutePath='+this.file.logFileAbsolutePath+"&logFileName="+this.file.logFileName);
    };    
});
