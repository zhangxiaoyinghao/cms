$(function() {
	// 初始化控件
	initCurrentComponents();
	// 监听所有event事件
	addCurrentEventListener();
});
/*
 * Init components. ==========================================
 */
function initCurrentComponents() {
	paging('');
}
/*
 * Add event listener. ==========================================
 */
function addCurrentEventListener() {
	// $('#checkAll').click(checkAll);
	$('#savePageBtn').click(savePage);
	$("#deletePageBtn").click(deletePageBtnClicked);
	$("#chkAll").click(chkAllClicked);
	initUpdatePageBtnListener();
	initDeleteSingleBtnListener();
}
var dataTables;
/*
 * 分页
 */
function paging(name){
	//分页
	$('#example').dataTable().fnDestroy();	
	$('#example').dataTable( {
         "language": {
             "lengthMenu": "每页 _MENU_ 条记录",
             "zeroRecords": "没有找到记录",
             "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
             "infoEmpty": "无记录",
             "infoFiltered": "(从 _MAX_ 条记录过滤)",
//             "search":name,
             "search":"",
             "searchPlaceholder":"搜索",
             "paginate":{
            	 "next":"下一页",
            	 "previous":"上一页"
             }
         },
         "bAutoWidth":false,
 	     "columnDefs": [
 	                    { "orderable": false, "targets": 5 },
 	                    { "width": "10%", "targets": 0 },
 	                    { "width": "10%", "targets": 1 },
 	                    { "width": "30%", "targets": 2 },
 	                    { "width": "15%", "targets": 3 },
 	                    { "width": "15%", "targets": 4 },
 	                    { "width": "10%", "targets": 5 },
 	                   ],
         "fnDrawCallback" :  drawCallback
     } );
	 $('#example').show();
	
	 $("#overlayed").hide();
	 
//		dataTables.api().search("cas").order([0,'desc']).page.len( 12 ).page(2).draw(false);
//		

}


/**
 * 初始化权限
 */
function drawCallback() {
	initAuthority();
//	if(dataTables){
//		console.info(dataTables.api().page());
//		console.info(dataTables.api().search());
//		console.info(dataTables.api().order());
//	}
}

// 跳转到新增页面
function savePage() {
	window.location.href = "savePage";
}

//全选
function chkAllClicked(){
	if($("#chkAll").is(":checked")){
		$("input[name='chk_authorizationId']").each(function(){
			$(this).prop('checked', true);
		});
	}else{
		$("input[name='chk_authorizationId']").each(function(){
			$(this).prop('checked', false);
		});
	}
}

//批量删除
function deletePageBtnClicked(){
	var authorizationName = $("input[type='search']").val();
	
	var objList = [];
	$("input[name='chk_authorizationId']").each(function(){
		if($(this).is(':checked')){
			var obj = {};
			obj['id']=$(this).val();
			obj['name']=authorizationName;
			objList.push(obj);
		}
	});
	
	if(objList.length<1){
		$.Zebra_Dialog("请选择要删除的内容！", {
			'type':     'information',
			'title':    '提示',
			'buttons':   ['确定','取消'],
		});
		return ;
	}
	
	 $.Zebra_Dialog("您确定要删除吗？", {
			'type':     'information',
			'title':    '提示',
			'buttons':   ['确定','取消'],
			'onClose':  function(caption){
				var option = (caption != '' ? '"' + caption + '"': 'nothing');
				if("\"确定\""== option){
					deleteBatch(objList);
				}else{
					return ;
				}
			}
	 });
}

function deleteBatch(objList){
	
	
	if($("#systemAudit").val()=="yes"){
		$.Zebra_Dialog("删除权限正在审核请稍后..", {
			'type':     'information',
			'title':    '提示',
			'buttons':  ["确定"],
			'onClose':  function(caption){
				toDelete(objList);
			}
		});
	}else{
		toDelete(objList);
	}
	
}

function toDelete(objList){
	var authorizationName = '';
	$.ajax({
		url:'deleteBatch',
		type:'post',
		dataType:'json',
		data:JSON.stringify(objList),
		contentType:'application/json;charset=UTF-8', 
		success:function(result){
			authorizationName = result.body.authorizationName;
			$.Zebra_Dialog(result.message, {
				'type':     'information',
				'title':    '提示',
				'buttons':   ['确定','取消'],
			});
			
			console.info("authorizationName:"+authorizationName);
//			paging(authorizationName);
			location.href = "list";
//			var table = $('#example').DataTable();
//			table.search( authorizationName.trim()).draw();
		}
		
	});
//	console.info("authorizationName:"+authorizationName);
//	$("input[type='search']").val(authorizationName);
	
}


// 初始化修改按钮监听事件
function initUpdatePageBtnListener() {
	$('#example').on('click',"[name = 'updatePageBtn']", function (index, element) {
		   var id = $(this).val();
		    window.location.href = "updatePage?id="
				+ id;
		} );
	
}
// 初始化删除按钮监听事件
function initDeleteSingleBtnListener() {
	$('#example').on('click',"[name = 'deleteSingleBtn']", function (index, element) {
		   var id = $(this).val();
		   
		   $.Zebra_Dialog("您确定要删除吗？", {
				'type':     'information',
				'title':    '提示',
				'buttons':   ['确定','取消'],
				'onClose':  function(caption){
					var option = (caption != '' ? '"' + caption + '"': 'nothing');
					if("\"确定\""== option){
						if($("#systemAudit").val()=="yes"){
							$.Zebra_Dialog("删除权限正在审核请稍后..", {
								'type':     'information',
								'title':    '提示',
								'buttons':  ["确定"],
								'onClose':  function(caption){
									window.location.href = "delete?id="
										+ id;
								}
							});
						}else{
							window.location.href = "delete?id="
								+ id;
						}
					}else{
					}
				}
			});
		} );
}
