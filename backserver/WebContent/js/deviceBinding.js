var deviceBinding = new function(){
    this.getDeviceBindingexURL = defaultWebUrl + "user/getDeviceBindingex.do";
    this.phoneNumber= null;
};
deviceBinding.deviceBinding = function(){
    var cond = new deviceBindingCond();
    cond.mobile = $("#mobile").val();
    cond.deviceCode = $("#deviceCode").val();
    if(cond.mobile != "" || cond.deviceCode != ""){
        if (cond.mobile != "") {
            if(!isMobilePhone(cond.mobile)){
                alert("输入的手机号码格式有误，请重新输入。");
                return ;
            } 
        }
        $('#grid').omGrid('setData', this.getDeviceBindingexURL+"?mobile="+cond.mobile+"&deviceCode="+cond.deviceCode+"&rule="+$("#curAdminarea").val());
        $('#grid').omGrid({
            onSuccess:function(data,testStatus,XMLHttpRequest,event){
                $('#devicefailsearch').html(data.dataStr);
                if (data.res != ""){
                    alert("登录名重复，请重新操作！");
                }
            },
           onError:function(XMLHttpRequest,textStatus,errorThrown,event){
               $('#infofailsearch').html('取得数据失败,请检查登录名是否重复，网络连接是否正常');
            }
        });
       if(data == "fail"){
           alert("修改失败！");
       } else {
           alert("修改成功！");
       }
    }
};

function deviceBindingCond() {
    this.mobile = null;
    this.deviceCode = null;
    this.seq = null;
    this.crDate = null;
};



