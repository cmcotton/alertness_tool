<jsp:include page="js.jsp" />
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>

//Angular.js
var ilmApp = angular.module('ilmApp', []);

ilmApp.controller('AssembleDatasetCtrl', function($scope, $http) {
	var vm = $scope;
	
	vm.currentRules = [];
	
	vm.engineer = {
	        name: "Dani",
	        currentActivity: {
	            id: 3,
	            type: "Work",
	            name: "Fixing bugs"
	        }
	    };
	vm.dataSources1 =	 
	    [
	        { id: 1, name: "登入紀錄" },	        
	        { id: 2, name: "帳號清冊" }	        
	    ];   
	vm.dataSources2 =    
        [
            { id: 1, name: "登入紀錄" },            
            { id: 2, name: "帳號清冊" }         
        ];
	
	vm.currentRules = [];
	
	vm.addRule = function() {
		vm.currentRules.push({
			source1: vm.source1,
			source2: vm.source2,
			oper: vm.oper
		});
	};
	
   vm.delRule = function(index) {
        vm.currentRules.splice(index, 1);
    };
});
	
</script>