<link href="${(project.staticDomain!)}/css/cas/pagecss/user/detail.css"
	rel="stylesheet" type="text/css" />
<link
	href="${(project.staticDomain!)}/libs/bootstrap-datetimepicker/css/datetimepicker.css"
	rel="stylesheet" type="text/css" />
<style>
.box{
	border-top: 1px solid #d2d6de;
}
.scrollspy-example {
height: 700px;
overflow: auto;
position: relative;
}
.form_check>span {
    color: red;
}
label {
    display: inline;
    margin-bottom: 5px;
    max-width: 100%;
}
.form-control {
	display: inline;
 	 width: 70%;
  }
  label {
    display: inline;
    margin-bottom: 5px;
    max-width: 100%;
    font-weight: normal;
}
</style>
<script type="text/javascript">
	var id="${(authorization.id)!}";
</script>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/cas/pagejs/authorization/detail.js" />
<script type="text/javascript" src="${ (project.staticDomain)! }/libs/My97DatePicker/WdatePicker.js"></script>
<div class="content-wrapper min-height494">

    <section class="content-header">
		<h1>
			<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/cas"><i class="fa fa-dashboard"></i> 首页</a></li>
			<!-- <li><a href="#">权限管理</a></li> -->
			<li class="active">权限添加</li>
		</ol>
	</section>

	<section class="content">

		<div class="row">
		<div class="col-md-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">权限添加</h3>
				</div>
				<form class="form-horizontal" id="form" name="form" method="post">
					<#if (authorization.id)??> <input type="hidden" name="id" id="id"
						value="${(authorization.id)!}" /> </#if>
						<input type="hidden" name="effectiveDate" id="effectiveDate" />
						<input type="hidden" name="expireDate" id="expireDateStr"/>
					<div class="box-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">权限名称</label>
							<div class="col-sm-6 form_check" >
								<input type="text" class="form-control" placeholder="权限名称"
									name="name" id="name" value="${(authorization.name)!}"><span>*</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">权限内容</label>
							<div class="col-sm-6 form_check">
								<input type="text" class="form-control" placeholder="权限内容"
									name="content" id="content" value="${(authorization.content)!}"><span>*</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">权限描述</label>
							<div class="col-sm-6 form_check">
								<input type="text" class="form-control" placeholder="权限描述"
									name="description" id="description"
									value="${(authorization.description)!}">
									 <span></span>
							</div>
						</div>
						<div class="form-group">
		                      <label class="col-sm-2 control-label">所属系统</label>
		                      <div class="col-sm-6 form_check">
			                      <select class="form-control " name="categoryId">
			                      		<option value="">请选择</option>
					                   <#list authorizationTypes as authorizationType>
										<option value="${(authorizationType.id)!}" <#if
											(authorization.category.id)?? &&
											authorization.category.id=authorizationType.id>selected</#if>>${(authorizationType.name)!}</option>
										</#list>
			                      </select>
			                      <span></span>
	                    	</div>
	                    </div>
					</div>
					<div class="box-footer">
						<button type="button" class="btn btn-default btn-sm" id="toListBtn">返回</button>
						<button type="button" class="btn btn-primary  pull-right btn-sm" id="saveBtn">保存</button>
					</div>
				</form>
			</div>
		</div>
		</div>
		<input type="hidden" id="systemAudit" value="${systemAudit?string('yes', 'no')}">
	</section>
</div>
