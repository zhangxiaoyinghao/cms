<style>
	.content {
  min-height: 900px;
}
</style>
<div class="content-wrapper">

	<section class="content-header">
		<h1>
			<small></small>
		</h1>
          <ol class="breadcrumb ">
            <li><a href="/cas"><i class="fa fa-dashboard"></i> 首页</a></li>
            <!-- <li><a href="#">权限管理</a></li> -->
            <li class="active">权限管理</li>
          </ol>

    </section>
	<section class="content" >
		<div class="row marginbottom10">
			<div class="col-xs-12">
				<div class="pull-right">
					<button data_auth="cas_authorization_add" type="button" class="btn btn-primary btn-sm" id="savePageBtn">新增</button>
					<button data_auth="cas_authorization_delete" type="button" class="btn btn-danger btn-sm" id="deletePageBtn">删除</button>
				</div>
			</div>
		</div>
		<div align="center">
				<div  id="overlayed" class="overlay" style="transform: translate(-50%,-10%);position: absolute;top: 33%;left: 55%; width: 200px">
					<div class="fa fa-spinner fa-pulse fa-3x"></div>
					<!-- <img style="width:50px;height:54px;" src="${ (project.staticDomain)! }/images/loading_circle.gif"> -->
					<div style="padding-right:5px;padding-top:10px"> <font size="4">Loading......</font></div>
				</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<table id="example" style="display: none;"  class="table table-striped table-bordered "
								cellspacing="0" width="100%" >
					<thead>
						<tr class="bg-LTE">
							<th><input type="checkbox" id="chkAll" /></th>
							<th>序号</th>
							<th>权限名称</th>
							<th>权限内容</th>
							<th>权限描述</th>
							<th>所属系统</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<#list authorizations as authorization>
						<tr>
							<td><input type="checkbox" name="chk_authorizationId" value="${(authorization.id)!}" /></td>
							<td>${authorization_index+1}</td>
							<td>${(authorization.name)!}</td>
							<td>${(authorization.content)!}</td>
							<td>${(authorization.description)!}</td>
							<td>${(authorization.category.name)!}</td>
							
							<td>
								<button data_auth="cas_authorization_update" class="btn btn-primary btn-sm"
									id='updatePageBtn_${ (authorization.id)! }' name='updatePageBtn'
									value="${ (authorization.id)! }">修改</button>
								<button data_auth="cas_authorization_delete" class="btn btn-danger btn-sm"
									id='deleteSingleBtn_${ (authorization.id)! }'
									name='deleteSingleBtn' value="${ (authorization.id)! }">删除</button>
							</td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
		<input type="hidden" id="systemAudit" value="${systemAudit?string('yes', 'no')}">
	</section>
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/cas/pagejs/authorization/list.js">
</script>