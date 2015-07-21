
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">報表下載</h1>
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

					<div class="col-lg-12">
						<form role="form">
							<div class="form-group col-lg-6">
								<label>報表類型</label> <select class="form-control">
									<option>應用系統</option>
									<option>主機/伺服器</option>
								</select>
							</div>
							<div class="form-group col-lg-6">
								<label>報表名稱</label> <select class="form-control"
									id="report-name">
									<option>登出入軌跡紀錄</option>
									<option>異常帳號登入稽核</option>
									<option>特定關係人查詢稽核</option>
								</select>
							</div>



							<div class="form-group col-lg-6">
								<label>開始時間</label>
								<div class='input-group date' id='datetimepicker1'>
									<input type='text' class="form-control" /> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								<p class="help-block">區間起始日</p>
							</div>

							<div class="form-group col-lg-6">
								<label>結束時間</label>
								<div class='input-group date' id='datetimepicker1'>
									<input type='text' class="form-control" /> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								<p class="help-block">區間起始日</p>
							</div>
							<div class="form-group col-lg-6">
								<label>使用者</label> <input class="form-control"
									placeholder="Enter user id">
							</div>

						</form>

						<div class="form-group col-lg-12">
							<button type="button" class="btn btn-primary"
								id="submit">送出</button>
							<button type="reset" class="btn btn-default">重新輸入</button>
						</div>
					</div>


					<div class="dataTable_wrapper">

						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th data-field="name">檔名</th>
									<th data-field="lastModified">產製時間</th>
									<th data-field="forks_count">下載</th>
									<th data-field="description">描述</th>
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
