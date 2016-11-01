angular.module('todoApp', []).controller('TodoListController', function($scope, $http) {
	
	var myApp = this;
	
	myApp.isLoading=false;
	myApp.mediaListNotAvailable = false;
	myApp.listItems = [];
		
	myApp.carregarDados = function() {
    	
		myApp.isLoading = true;
		
    	$http.get("http://localhost:8080/media?idUser=1",{
    		headers: {
    			'Content-type': 'application/json'
    		}
    	}).success(function(data, status) {
    		
    		myApp.isLoading = false;
    		
            if (data.coderro > 0) {
                alert(data.descerro);
            } else {
            	myApp.listItems = data;
            }
        }).error(function(data, status) {
        	
        	myApp.isLoading = false;
        	
        	if(data.message === 'No instances available for valdeflix-media'){
        		myApp.mediaListNotAvailable = true;
        	}
        });
    	
    };
    
    myApp.watch = function(objCard) {
    	
    	window.location.href = "/player.html";
    	
    }
    
    myApp.carregarDados();
	
});