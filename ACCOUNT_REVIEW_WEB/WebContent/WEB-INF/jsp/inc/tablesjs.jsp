<jsp:include page="js.jsp"/>
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    
        $('#submit').click(function() {
            
            $('#dataTables-example').DataTable({
                "ajax" : 'getReportList.html',
                responsive : true
            });           
            
        });
    
    </script>