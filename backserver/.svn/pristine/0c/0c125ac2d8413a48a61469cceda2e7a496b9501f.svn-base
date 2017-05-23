<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="../js/systemsetting.js"></script>
<title>系统设置</title>

<script type="text/javascript">
	 $(document).ready(function() {
		 $('#cq-key').change(function(){ 
			if ($('#cq-key').val() == "3"){
				$("#btn_dw").attr("disabled",true);
				$("#btn_bk").attr("disabled",true);
				$("#btn_tm").attr("disabled",true);
			}else{
				$("#btn_dw").attr("disabled",false);
				$("#btn_bk").attr("disabled",false);
				$("#btn_tm").attr("disabled",false);
			}
		});
	}); 

</script>
<style type="text/css">
		*{font-family:'Microsoft Yahei';}
		
.conListH TD {
	padding-bottom: 5px;
	background-color: #addaf9;
	padding-left: 5px;
	padding-right: 5px;
	color: #000000;
	font-size: 14px;
	font-weight: bold;
	padding-top: 5px;
	padding-bottom: 5px;
	margin-top: 4px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
    <!-- view_source_begin -->
    &nbsp;&nbsp;&nbsp;&nbsp;<select style="font-size:16px;" id="cq-key">
		            		<option value="9">全部载入</option>
		            		<option value="1">系统配置</option>
		            		<option value="2">仅MongoDB配置</option>
		            		<option value="3">仅异网短信配置</option>
		            		<option value="4">仅常量</option>
		            	</select>
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="btn_dw" onclick="systemsetting.dosetting('qinzaina');" value="&nbsp;重新装载亲在哪设置&nbsp;" />
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="btn_tm" onclick="systemsetting.dosetting('timer');" value="&nbsp;重新装载Timer设置&nbsp;" />
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="btn_gdmc" onclick="systemsetting.dosetting('gdmc');"  value="&nbsp;重新装载GDMC设置&nbsp;" />
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="btn_bk" onclick="systemsetting.dosetting('backup');"  value="&nbsp;重新装载后台系统设置&nbsp;" />
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="btn_dvc" onclick="systemsetting.dosetting('device');"  value="&nbsp;重新装载亲在哪硬件版设置&nbsp;" />
	<br>
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="btn_sms" onclick="systemsetting.dosetting('sms');"  value="&nbsp;短信余量查看&nbsp;" />
    <!-- view_source_end -->   
</body>
</html>