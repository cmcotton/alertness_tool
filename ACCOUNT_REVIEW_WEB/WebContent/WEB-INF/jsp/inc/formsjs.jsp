<jsp:include page="js.jsp" />
<Script>
var forms = {
	    ruleSel: function() {
	        var rule = $("#report-name").find(":selected").val();
	        return  rule;
	        },
	    getRule: function() {
	        $("#report-name").find('option').remove();
	        
	        $.post('/account/regu/getRuleToRegu.html', {'reguNo':$("#regu-select").find(":selected").val()}).done(function(json) {
	                console.log(json);
	                var size = json.data.length;

	                for (var i = 0; i < size; i++) {
	                    // var rule = $.parseJSON(json[i]);
	                    // if (rule.property == 'manual') {
	                        $("#report-name").append(
	                                $("<option></option>").attr("value", json.data[i][0])
	                                        .text(json.data[i][0] + ": " + json.data[i][1]));
	                    // }
	                }
	            });
	    },
	    getTable: function() {
	    	return $('#dataTables-example');
	    },
	    postPass: function(rule) {
	        $.post('fillinRule.html', {'oper':'pass', 'rule':rule , 'attach':$('#file-upload').val()}).done(function(json) {
	        });
	    },
	    postViolate: function(rule) {
	        $.post('fillinRule.html', {'oper':'violate', 'rule':rule , 'attach':$('#file-upload').val()}).done(function(json) {
	        });
	    }
};
	
$("#regu-select").change(function() {
	forms.getRule();
});

$.post('/account/regu/getAllReguEntity.html').done(
        function(res) {
            console.log(res);
            var size = res.length;
            for (var i = 0; i < size; i++) {
                var json = $.parseJSON(res[i]);
                
                if (json.applicable == '1') {
                    $("#regu-select").append(
                        $("<option></option>").attr("value", json.no).text(json.no + ": " + json.name));
                }
            }

        });
        
	$.post('/account/regu/getAllRuleEntity.html').done(function(json) {
		console.log(json);
		var size = json.length;

		for (var i = 0; i < size; i++) {
			var rule = $.parseJSON(json[i]);
			if (rule.property == 'manual') {
				$("#report-name").append(
						$("<option></option>").attr("value", rule.no)
								.text(rule.no + ": " + rule.name));
			}
		}
				
		forms.getRule();
	});

	function loadContent() {
		var selVal = forms.ruleSel(), 
		   option = 'postGridData';
		
		//$('#dataTables-example').DataTable().clear();
		
		$.post('/account/audit/getReportColumn.html', {'rule': selVal}).done(function(col) {
			columns = col;
			
			$.post('/account/audit/postGridData.html', {'rule': selVal}).done(function(json) {
				
				if (json.error) {
					alert(json.error);
				}
				
				var data = [];
	            
	            for (var i = 0; i < json.length; i++) {
	                var arr = [];
	                arr.push(json[i].userid);
	                arr.push(json[i].datetime);
	                // arr.push('重複登入');
	                arr.push(json[i].action);
	                arr.push(long2ip(json[i].ip));
	                data.push(arr);
	            }
	            
	            var table = $('#dataTables-example').DataTable({
                    "columns": columns,
                       retrieve : true,
                //       "data" : data,
                       responsive : true
               });
	            
	            table.clear();                 
                table.draw();
                data.forEach(function(element) {
                    table.row.add([element[0], element[1], element[2], element[3], element[4]]).draw();
                });

                
	            
	        });			
		});

		
		

	}

	function long2ip(ip) {
		  //  discuss at: http://phpjs.org/functions/long2ip/
		  // original by: Waldo Malqui Silva
		  //   example 1: long2ip( 3221234342 );
		  //   returns 1: '192.0.34.166'

		  if (!isFinite(ip))
		    return false;

		  return [ip >>> 24, ip >>> 16 & 0xFF, ip >>> 8 & 0xFF, ip & 0xFF].join('.');
		}
	
	$('#submitLogSelect').click(function() {
		loadContent();
	});
</Script>
