var systemsetting = new function(){
	this.systemsettingUrl = defaultWebUrl + "user/dosetting.do";
};
/**
 * 初始化
 */
systemsetting.dosetting = function(systemname){	
	var systemsettingInfo = new jsonReq();
	systemsettingInfo.setEventListener("receive", systemsetting.loginupcheckCallback);
	systemsettingInfo.send(this.systemsettingUrl+"?systemname="+systemname+"&cq-key="+$("#cq-key").val());
};


systemsetting.loginupcheckCallback = function(data){
	if (data.systemname == "sms"){
		alert("余额:"+data.amount+"  "+"剩余条数:"+data.number+"  "+"冻结条数:"+data.freeze);
	}else{
		if (data.res == "sucess"){
			alert("重载成功！");
		}else{
			alert("重载失败！");
		}
	}
};