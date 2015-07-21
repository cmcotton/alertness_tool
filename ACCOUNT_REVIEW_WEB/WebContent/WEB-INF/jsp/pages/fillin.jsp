
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">自行填報作業</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Basic Form Elements</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>報表類型</label> <select class="form-control">
										<option>應用系統</option>
										<option>主機/伺服器</option>
									</select>
								</div>
								<div class="form-group">
									<label>報表名稱</label> <select class="form-control"
										id="report-name">
										<option>個人PC自檢表</option>
									</select>
								</div>
								<div class="form-group">
									<label>開始時間</label>
									<div class='input-group date' id='datetimepicker1'>
										<input type='text' class="form-control" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
									<p class="help-block">區間起始日</p>
								</div>
								<div class="form-group">
									<label>結束時間</label>
									<div class='input-group date' id='datetimepicker1'>
										<input type='text' class="form-control" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
									<p class="help-block">區間起始日</p>
								</div>
								<div class="form-group">
									<label>File input</label> <input type="file">
								</div>
							</form>

                            <button type="button" class="btn btn-info btn-circle btn-xl"><i class="fa fa-check"></i>
                            </button>
						</div>

					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

