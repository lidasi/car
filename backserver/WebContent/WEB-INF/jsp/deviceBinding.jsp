<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/deviceBinding.js"></script>
<script type="text/javascript" src="../js/md5.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>设备绑定页</title>
<script type="text/javascript">

     $(document).ready(function() {
         $.validator.addMethod("phoneNumber", function(value) {
             return /^\d{11}$/.test(value);
          }, '请输入11位手机号');
        $('#grid').omGrid({
            height:510,
            colModel : [ {header : '手机号', name : 'mobile', width : 80, align : 'left',editor:{type:"text",editable:false}},
                         {header : '设备号', name : 'deviceCode', align : 'left', width : 80,editor:{type:"text",editable:false}},
                         {header : '创建时间', name : 'crDate', align : 'left', width : 80,editor:{type:"text",editable:false}}
                      ],
            dataSource : "",

        });
        
        function pwdedit(colValue, rowData, rowIndex){
           
        }
        function ruleRenderer(value){
            if(!value){
                return m_r[0].text;
            }
            for(var i = 0; i < m_r.length; i++) {
                if(m_r[i].value == value) {
                    return m_r[i].text;
                }
            }
        }

   /*      deviceBinding.deviceBinding(); */
       
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
    <table>
        <tr>
            <td>手机号:<input style="border-radius:5px;" id="mobile" type="text" size="22" maxlength='11' />
            <td>设备号:<input style="border-radius:5px;" id="deviceCode" type="text" name="deviceCode" size="22" maxlength='11' />
            <td><input type="button" onclick='deviceBinding.deviceBinding()' value="查询" />
        </tr>
    </table>
    <br>
    
    <div id="demo" >
        <TABLE class="conListH" border="0" cellSpacing="0" cellPadding="0"
        width="100%">
        <TBODY>
            <TR>
                <TD><div id='devicefailsearch'></div></TD>
            </TR>
        </TBODY>
    </TABLE>
        <table id="grid"></table>
    </div>
    
    <!-- view_source_end -->   
</body>
</html>