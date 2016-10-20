angular.module('todoApp', []).controller('TodoListController', function($scope, $http) {
	
	var myApp = this;
	
	myApp.title="";
	myApp.userName = "";
	myApp.password = "";
	myApp.listItems = [];
	myApp.selectedPlan = 0;
	
	myApp.registerInfo = {
		"name": "",
		"username": "",
		"password": "",
		"email": ""
	};
	
	myApp.subscribeInfo = {
		"cardNumber": "",
		"cardDigit": "",
		"user": {
			"id": 1
		},
		"plan": {
			"id": 0
		}
	};
	
	myApp.subscribeInfoValidation = {
		"cardNumber": {
			"invalid" : false,
			"message": ""
		},
		"cardDigit": {
			"invalid" : false,
			"message": ""
		}
	};
	
	myApp.carregarDados = function() {
    	
		/*$http({
			method: "GET", 
			url: "http://localhost:8080/media?idUser=1",
			responseType:'json',
			headers: {
				"Content-Type":"application/json",
	            "Accept" : "application/json"
			}}).
	        then(function(data) {
	        	if (data.coderro > 0) {
	                alert(data.descerro);
	            } else {
	            	myApp.listItems = data;
	            }
	        }, function(data) {
	        	alert('Request failed');
	      });*/
		
    	$http.get("http://localhost:8080/media?idUser=1",{
    		headers: {
    			'Content-type': 'application/json'
    		}
    	}).success(function(data, status) {
            if (data.coderro > 0) {
                alert(data.descerro);
            } else {
            	myApp.listItems = data;
            }
        });
    	
    };
    
    myApp.login = function() {
    	
    	$http.post("http://localhost:8080/login",{
			"username":  myApp.userName,
			"password": myApp.password
		},{
			headers: {
				'Content-Type': 'application/json'
			}
		}) .success(function(data, status) {
            if (data.coderro > 0) {
                alert(data.descerro);
            } else {
            	
            	if(data.msg === "notFound"){
            		alert("Login fails. Invalid username or password.")
            	}else if(data.msg === "emptyPlan"){
            		window.location.href = "/plans.html";
            	}else{
            		window.location.href = "/list.html";
            	}
            	
            }
        });
    	
    };
    
    myApp.register = function() {
    	
    	$http.post("http://localhost:8080/register", myApp.registerInfo)
    		.success(function(data, status) {
	            if (data.coderro > 0) {
	                alert(data.descerro);
	            } else {
	            	window.location.href = "/index.html";
	            }
	        });
    	
    }
    
    myApp.subscribe = function() {
    	
    	if(!myApp.subscribeInfo.cardNumber){
    		myApp.subscribeInfoValidation.cardNumber.invalid=true;
    		myApp.subscribeInfoValidation.cardNumber.message="Please fill this field with your card number";
    	}
    	if(!myApp.subscribeInfo.cardDigit){
    		myApp.subscribeInfoValidation.cardDigit.invalid=true;
    		myApp.subscribeInfoValidation.cardDigit.message="Please fill this field with your card digit";
    	}
    	
    	if(!myApp.subscribeInfo.cardNumber || !myApp.subscribeInfo.cardDigit){
    		return false;
    	}
    	
    	if(!myApp.subscribeInfo.plan.id){
    		alert("Please select a plan.");
    		return false
    	}
    	
    	$http.post("http://localhost:8080/subscription", myApp.subscribeInfo)
    		.success(function(data, status) {
	            if (data.coderro > 0) {
	                alert(data.descerro);
	            } else {
	            	window.location.href = "/list.html";
	            }
	        });
    	
    }
    
    myApp.watch = function(objCard) {
    	
    	window.location.href = "/player.html";
    	
    }
    
    myApp.selectPlan = function(idPlan){
    	
    	myApp.selectedPlan = idPlan;
    	myApp.subscribeInfo.plan.id = idPlan;
    	
    }
    
    myApp.carregarDados();
	
});