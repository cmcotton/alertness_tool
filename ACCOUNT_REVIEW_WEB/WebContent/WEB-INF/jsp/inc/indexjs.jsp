<jsp:include page="js.jsp" />


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

	$.post('/account/regu/getAllReguEntity.html').done(
		function(json) {
			console.log(json);

			var size = json.length || 0, applicableRegus = [], nonapplicableRegus = [], passRegus = [], nonexecRegus = [], violateRegus = [];

			for (var i = 0; i < size; i++) {
				var reguEntity = $.parseJSON(json[i]);

				if (reguEntity.applicable == 1) {
					applicableRegus.push(reguEntity.no + " "
							+ reguEntity.name + "<br>");
					
					// pass
	                if (reguEntity.pass == 1) {
	                    passRegus.push(reguEntity.no + " "
	                            + reguEntity.name + "<br>");
	                } else if (reguEntity.nonexec == 1) { // nonexec
	                    nonexecRegus.push(reguEntity.no + " "
	                            + reguEntity.name + "<br>");
	                } else if (reguEntity.violate == 1) { // violate
	                    violateRegus.push(reguEntity.no + " "
	                            + reguEntity.name + "<br>");
	                }
					
				} else {
					nonapplicableRegus.push(reguEntity.no + " "
							+ reguEntity.name + "<br>");
				}

				
			}

			indexObj.applicableRegusSize = applicableRegus.length;
			indexObj.passRegusSize = passRegus.length;
			
			$('#regu-applicable').html(applicableRegus.length);
			$('#regu-pass').html(passRegus.length);
			$('#regu-nonexec').html(nonexecRegus.length);
			$('#regu-violate').html(violateRegus.length);

			var reguList = '', passList = '', nonexecList = '', violateList = '';

			applicableRegus.forEach(function(element, index) {
				reguList += element;
			});

			reguList += "<br><br>不適用<br>";

			nonapplicableRegus.forEach(function(element, index) {
				reguList += element;
			});

			$('#regu-applicable-list').html(reguList);

			// 符合項目數detail
			passRegus.forEach(function(element, index) {
				passList += element;
			});
			$('#regu-pass-list').html(passList);

			// 未執行項目數detail
			nonexecRegus.forEach(function(element, index) {
				nonexecList += element;
			});
			$('#regu-nonexec-list').html(nonexecList);

			// 違反項目數detail
			violateRegus.forEach(function(element, index) {
				violateList += element;
			});
			$('#regu-violate-list').html(violateList);
			
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

	
</script>
