<%@ include file="header.jsp" %>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Tables</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">DataTables Advanced Tables</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">

								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th data-field="name">檔名</th>
											<th data-field="lastModified">產製時間</th>
											<th data-field="forks_count">Forks</th>
											<th data-field="description">Description</th>
										</tr>
									</thead>									
								</table>
							</div>

						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<!-- /.row -->

			<!-- /.row -->

			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

<%@ include file="footer.jsp" %>