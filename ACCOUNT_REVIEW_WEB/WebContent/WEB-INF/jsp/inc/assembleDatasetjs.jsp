<jsp:include page="js.jsp" />
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>

//Angular.js
var ilmApp = angular.module('ilmApp', []);

ilmApp.config(['$httpProvider', function ($httpProvider) {    
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
}]);

ilmApp.controller('AssembleDatasetCtrl', function($scope, $http) {

	var vm = $scope;
	
	vm.currentRules = [];
	
	vm.dataSources1 = [];   
	vm.dataSources2 = [];
	
	vm.currentRules = []; // todo: retrieve from backend
	
	// load rule blocks when document ready
	angular.element(document).ready(function () {
		   console.log('Hello World');
		   $http.post('/account/regu/loadRuleBlock.html').
	        then(function(response) {
	        	var ruleBlocks = response.data;
	        	var rb;
	        	for (rb of ruleBlocks) {
	        		vm.dataSources1.push({id: rb.id, name: rb.name});
	        		vm.dataSources2.push({id: rb.id, name: rb.name});
	        	}
	        	
	        }, function(response) {
	          $scope.data = response.data || "Request failed";
	          $scope.status = response.status;
	      });
	});
	
	vm.addRule = function() {
		vm.currentRules.push({
			source1: vm.source1,
			source2: vm.source2,
			oper: vm.oper,
			ruleChainName: vm.ruleChainName
		});
	};
	
   vm.delRule = function(index) {
        vm.currentRules.splice(index, 1);
    };
    
    vm.run = function(index) {
    	$http.post('/account/regu/runRuleChain.html', 'ruleId=' + index).
        then(function(response) {
          $scope.status = response.status;
          $scope.data = response.data;
        }, function(response) {
          $scope.data = response.data || "Request failed";
          $scope.status = response.status;
      });
    };
});
	
</script>