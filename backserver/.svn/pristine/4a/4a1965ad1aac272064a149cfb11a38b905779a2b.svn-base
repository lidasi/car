<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>测试jquery控制My97DatetimePicker格式</title>
<!-- <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script> -->
<!-- <script type="text/javascript" src="../js/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="../js/jquery.json-2.4.js"></script> -->
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.srcTime').bind('focus', function() {
				WdatePicker();
			});
			changeAttr3();
			timePicker.toDayPicker();
		});

		function changeAttr1() {
			$('#srcStartDate').unbind('focus');
			$('#srcStartDate').bind('focus', function() {
				WdatePicker({
					skin : 'whyGreen',
					dateFmt : 'yyyy'
				});
			});
			$('#srcEndDate').unbind('focus');
			$('#srcEndDate').bind('focus', function() {
				WdatePicker({
					skin : 'whyGreen',
					dateFmt : 'yyyy',
					minDate : '#F{$dp.$D(\'srcStartDate\')}'
				});
			});
		};
		function changeAttr2() {
			$('#srcStartDate').unbind('focus');
			$('#srcStartDate').bind('focus', function() {
				WdatePicker({
					skin : 'whyGreen',
					dateFmt : 'yyyy-MM'
				});
			});
			$('#srcEndDate').unbind('focus');
			$('#srcEndDate').bind('focus', function() {
				WdatePicker({
					skin : 'whyGreen',
					dateFmt : 'yyyy-MM',
					minDate : '#F{$dp.$D(\'srcStartDate\')}'
				});
			});
		};
		function changeAttr3() {
			$('#srcStartDate').unbind('focus');
			$('#srcStartDate').bind('focus', function() {
				WdatePicker({
					skin : 'whyGreen',
					dateFmt : 'yyyy-MM-dd'
				});
			});
			$('#srcEndDate').unbind('focus');
			$('#srcEndDate').bind('focus', function() {
				WdatePicker({
					skin : 'whyGreen',
					dateFmt : 'yyyy-MM-dd',
					minDate : '#F{$dp.$D(\'srcStartDate\')}'
				});
			});
		};

// 		function showperiodtype(value)
// 		{
// 			if (value == "y") {
// 				changeAttr1();
// 			}

// 			if (value == "m") {
// 				changeAttr2();
// 			}
// 			if (value == "d") {
// 				changeAttr3();
// 			}

// 		}
	</script>
	
<!-- 	<input id="1" type="button" value="变为年" onclick="changeAttr1()"> -->
<!-- 	<input id="2" type="button" value="变为月" onclick="changeAttr2()"> -->
<!-- 	<input id="3" type="button" value="变为日" onclick="changeAttr3()"> -->
<!-- 	<SELECT id="periodType" -->
<!-- 		onchange="showperiodtype(this.options[this.selectedIndex].value);" -->
<!-- 		name="periodType"> -->
<!-- 		<OPTION value="y">年</OPTION> -->
<!-- 		<OPTION value="m">月</OPTION> -->
<!-- 		<OPTION value="d">日</OPTION> -->
<!-- 	</SELECT> -->
开始时间：<input id="srcStartDate" class="srcTime" type="text" />
    结束时间：<input id="srcEndDate" class="srcTime" type="text" />
</body>
</html>
