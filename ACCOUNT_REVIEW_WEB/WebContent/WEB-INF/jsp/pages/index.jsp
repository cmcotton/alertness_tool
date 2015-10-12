  
<div id="page-wrapper" ng-controller="IndexCtrl"> {{test}}
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">儀表板</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-comments fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge" id="regu-applicable">{{applicableRegusSize}}</div>
							<div>適用項目數</div>
						</div>
					</div>
				</div>
				<a href="#myModalFeasible" data-toggle="modal">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>

				<!-- Modal HTML -->
				<div id="myModalFeasible" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Confirmation</h4>
							</div>
							<div class="modal-body" id="regu-applicable-list">
								<table>
								 <tr ng-repeat="x in applicableRegus">
	                                 <td>{{x}}</td>
	                             </tr>                             
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-green">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-tasks fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge" id="regu-pass">{{passRegusSize}}</div>
							<div>符合項目數</div>
						</div>
					</div>
				</div>
				<a href="#myModalPass" data-toggle="modal">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>

				<!-- Modal HTML -->
				<div id="myModalPass" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">符合項目列表</h4>
							</div>
							<div class="modal-body" id="regu-pass-list"><table>
                                 <tr ng-repeat="x in passRegus">
                                     <td>{{x}}</td>
                                 </tr>                             
                                </table></div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-yellow">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-shopping-cart fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge" id="regu-nonexec">{{nonexecRegusSize}}</div>
							<div>未執行項目數</div>
						</div>
					</div>
				</div>
				<a href="#myModalNotExec" data-toggle="modal">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>

				<!-- Modal HTML -->
				<div id="myModalNotExec" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">未執行項目列表</h4>
							</div>
							<div class="modal-body" id="regu-nonexec-list"><table>
                                 <tr ng-repeat="x in nonexecRegus">
                                     <td>{{x}}</td>
                                 </tr>                             
                                </table></div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-red">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-support fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge" id="regu-violate">{{violateRegusSize}}</div>
							<div>違反規範項目數</div>
						</div>
					</div>
				</div>
				<a href="#myModalViolated" data-toggle="modal">
					<div class="panel-footer">
						<span class="pull-left">View Details</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>

				<!-- Modal HTML -->
				<div id="myModalViolated" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">違反規範項目列表</h4>
							</div>
							<div class="modal-body" id="regu-violate-list"><table>
                                 <tr ng-repeat="x in violateRegus">
                                     <td>{{x}}</td>
                                 </tr>                             
                                </table></div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-8">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-bar-chart-o fa-fw"></i>過去12個月符合性檢視統計趨勢
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div id="morris-area-chart"></div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->

		</div>
		<!-- /.col-lg-8 -->
		<div class="col-lg-4">

			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-bar-chart-o fa-fw"></i> 符合比例
				</div>
				<div class="panel-body">
					<div id="morris-donut-chart"></div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->


		</div>
		<!-- /.col-lg-4 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

