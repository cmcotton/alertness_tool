<jsp:include page="js.jsp"/>
<script>
$.post('getAllRuleEntity.html').done(function(json) {
	
	console.log(json);
	
	var data = [],
	   i = 0,
	   size = json.length;
	
	
	for (; i < size; i++) {
		var obj = $.parseJSON(json[i]);
		var row = [obj.no, obj.name, obj.desc];
		data.push(row);
	}
	
	
	$('#dataTables-example').DataTable({
        "data" : data,
        responsive : true
    });
});

	           

</script>