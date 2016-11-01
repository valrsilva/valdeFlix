angular.module('todoApp', []).controller('TodoListController', function($scope, $http) {
	
	var myApp = this;
	
	myApp.isLoading=false;
	myApp.userName = "";
	myApp.password = "";
		
    myApp.login = function() {
    	
    	if(!myApp.userName || !myApp.password){
    		Materialize.toast('Please fill the Login Name and the Password', 2000, 'rounded');
    		return false;
    	}
    	
    	myApp.isLoading = true;
    	
    	$http.post("http://localhost:8080/login",{
			"username":  myApp.userName,
			"password": myApp.password
		},{
			headers: {
				'Content-Type': 'application/json'
			}
		}) .success(function(data, status) {
			
			myApp.isLoading = false;
			
            if (data.coderro > 0) {
                alert(data.descerro);
            } else {
            	
            	if(data.msg === "notFound"){
            		Materialize.toast('Login fails. Invalid username or password.', 2000, 'rounded')
            	}else if(data.msg === "emptyPlan"){
            		window.location.href = "/plans.html";
            	}else{
            		window.location.href = "/list.html";
            	}
            	
            }
        });
    	
    };
	
});