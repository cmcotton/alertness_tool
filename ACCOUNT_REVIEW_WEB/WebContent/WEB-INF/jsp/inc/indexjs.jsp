<jsp:include page="js.jsp" />

<!-- Angular.js-->
<script src="../js/angular.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="../bower_components/raphael/raphael-min.js"></script>
<script src="../bower_components/morrisjs/morris.min.js"></script>
<script src="../js/morris-data.js"></script>

<!-- Flot Charts JavaScript -->
<script src="../bower_components/flot/excanvas.min.js"></script>
<script src="../bower_components/flot/jquery.flot.js"></script>
<script src="../bower_components/flot/jquery.flot.pie.js"></script>
<script src="../bower_components/flot/jquery.flot.resize.js"></script>
<script src="../bower_components/flot/jquery.flot.time.js"></script>
<script
	src="../bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
<script src="../js/flot-data.js"></script>

<script>
	var indexObj = {};	
	 
	 // morris dount
	 Morris.Donut({
	 element : 'morris-donut-chart',
	 data : [ {
	 label : "符合項目",
	 value : (indexObj.passRegusSize / indexObj.applicableRegusSize)
	 }, {
	 label : "不符合項目",
	 value : (1 - (indexObj.passRegusSize / indexObj.applicableRegusSize)) 
	 } ],
	 colors : [ 'green', 'red' ],
	 formatter : function(y, data) {
	 return y.toPrecision(4) * 100  + '%';
	 }
	 });
	 
	 
	//Flot Pie Chart
	$(function() {

		var data = [ {
			label : "Series 0",
			data : 1
		}, {
			label : "Series 1",
			data : 3
		}, {
			label : "Series 2",
			data : 9
		}, {
			label : "Series 3",
			data : 20
		} ];

		var plotObj = $.plot($("#flot-pie-chart"), data, {
			series : {
				pie : {
					show : true
				}
			},
			grid : {
				hoverable : true
			},
			tooltip : true,
			tooltipOpts : {
				content : "%p.0%, %s", // show percentages, rounding to 2 decimal places
				shifts : {
					x : 20,
					y : 0
				},
				defaultTheme : false
			}
		});

	});

	// Angular.js
	var ilmApp = angular.module('ilmApp', []);

	ilmApp.controller('IndexCtrl', function($scope, $http) {
	    var vm = $scope;
		
		vm.applicableRegus = [];
		vm.nonapplicableRegus = [];
		vm.passRegus = [];
		vm.nonexecRegus = [];
		vm.violateRegus = [];
		
		vm.initReguInfo = function() {
			  var res = $http.post('/account/regu/getAllReguEntity.html');
			  
			  res.success(function(data, status, headers, config) {
				  var size = data.length || 0;

				     for (var i = 0; i < size; i++) {
					     var reguEntity = $.parseJSON(data[i]);
	
					     if (reguEntity.applicable == 1) {
					    	 vm.applicableRegus.push(reguEntity.no + " " + reguEntity.name);
						    
						     // pass
						     if (reguEntity.pass == 1) {
						    	 vm.passRegus.push(reguEntity.no + " " + reguEntity.name);
						     } else if (reguEntity.nonexec == 1) { // nonexec
						    	 vm.nonexecRegus.push(reguEntity.no + " " + reguEntity.name);
						     } else if (reguEntity.violate == 1) { // violate
						    	 vm.violateRegus.push(reguEntity.no + " " + reguEntity.name);
						     }
					    
					     } else {
					    	 vm.nonapplicableRegus.push(reguEntity.no + " " + reguEntity.name);
					     }
				     }

				     vm.applicableRegusSize = vm.applicableRegus.length;
				     vm.passRegusSize = vm.passRegus.length;
				     vm.nonexecRegusSize = vm.nonexecRegus.length;
				     vm.violateRegusSize = vm.violateRegus.length;
				     
				     vm.initReguDetail(); // run once
			});
		};
		
		vm.initReguDetail = function() {
			var reguList = '';
			
			vm.applicableRegus.forEach(function(element, index) {
			    reguList += element;
			});

			reguList += "<br><br>不適用<br>";

			vm.nonapplicableRegus.forEach(function(element, index) {
			    reguList += element;
			});

			vm.reguApplicableList = reguList;
			
			// 符合項目數detail
			passRegus.forEach(function(element, index) {
			    passList += element;
			});
			vm.passList = passList;
			
			// 未執行項目數detail
			nonexecRegus.forEach(function(element, index) {
			    nonexecList += element;
			});
			vm.nonexecList = nonexecList;
			
			// 違反項目數detail
			violateRegus.forEach(function(element, index) {
			    violateList += element;
			});
			vm.violateList = violateList; 
		};
	
		vm.initReguInfo(); // run once
		
	});
</script>
