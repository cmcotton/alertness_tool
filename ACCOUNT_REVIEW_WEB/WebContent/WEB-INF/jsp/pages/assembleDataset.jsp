
<div id="page-wrapper" ng-controller="AssembleDatasetCtrl">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">自組規則管理</h1>
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
							<div class="form-group col-lg-5">
							
								<label>資料來源1</label>                               
								<select class="form-control" ng-model="source1"
                                        data-ng-options="a.name for a in dataSources1">                
                                </select>

							</div>
							
							<div class="form-group col-lg-2">
                            
                                <label>比較</label>                               
                                <select class="form-control" ng-model="oper">
                                    <option value="in">IN</option>
                                    <option value="notIn">NOT IN</option>
                                </select>
                            </div>
                            
							<div class="form-group col-lg-5">
							
								<label>資料來源2</label>                               
                                <select class="form-control" ng-model="source2"
                                        data-ng-options="a.name for a in dataSources2">                
                                </select>
                            </div>
						</form>

						<div class="form-group col-lg-12">
							<button type="button" class="btn btn-primary" id="submit" ng-click="addRule()">加入</button>
						</div>
					</div>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
						  <tr>
						      <th>項次</th>
						      <th>資料來源1</th>
						      <th class="sorting">比較</th>
						      <th>資料來源2</th>
						      <th></th>
						  </tr>
							<tr ng-repeat="x in currentRules">
							    <td>{{$index + 1}}</td>
                                <td>{{x.source1.id + ': ' + x.source1.name}}</td>
                                <td>{{x.oper}}</td>
                                <td>{{x.source2.id + ': ' + x.source2.name}}</td>
                                <td><button type="button" class="btn btn-primary" id="delete" ng-click="delRule($index)">刪除</button></td>
                            </tr>
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
