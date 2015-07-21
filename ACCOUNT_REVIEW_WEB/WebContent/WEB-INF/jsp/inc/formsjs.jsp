<jsp:include page="js.jsp" />
<Script>
    
    function loadContent() {
	    var selVal = $('#report-name').val(),
	       option = '';
	    
	    if (selVal == '登出入軌跡紀錄') {
	    	option = 'getLogin.html';
	    } else if (selVal == '異常帳號登入稽核') {
	        option = 'getLogSelectAbnormalLogin.html';
	    } else if (selVal == '特定關係人查詢稽核') {
	        option = 'getLogSelectRelative.html';
	    }
	    
	    
	    $('#dataTables-example').DataTable({
	    	retrieve: true,
	        "ajax" : option,
	        responsive : true
	    });
	}

	$('#submitLogSelect').click(function() {
		loadContent();
	});

</Script> 