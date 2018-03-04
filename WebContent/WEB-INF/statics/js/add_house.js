
//选取 id 为 estate,  当发生  change 事件时, 执行 以下函数
//在本例中，estate 是一个下拉选择的输入框。  当选择任意一个选项时会触发
$("#estate").on("change", function() {
    
	$("#building").empty(); 
	$("#ladder").empty(); 
	$("#room").empty(); 
	
	var id = $("option:selected", this).val();
	//得到所选择小区的 id
	
	//发起get 请求  其实就是访问了类似这样的网址 http://localhost：8080/GLF/owner/getBuilding?id=10001
	// 你可以直接在浏览器上访问，看返回的数据是怎样的
	//得到该小区 的楼宇信息， 
	$.get("getBuilding", { id: id }, function(data){
		var json = eval(data);    //返回的数据是json格式的
		  // 将 楼宇的下拉输入框清空
		
		//这个一个循环， 把拿到的信息放到楼宇的下拉输入框  中
        $.each(json, function (i, item) {  	
        	var option = $("<option>").val(json[i].buildingId).text(json[i].buildingName);
        	$("#building").append(option);
        });
	});
});


$("#building").on("change", function() {
	
	$("#ladder").empty(); 
	$("#room").empty(); 
	
	var id = $("option:selected", this).val();
	$.get("getLadders", { id: id }, function(data){
		var json = eval(data);   
		 
		
		if(json.length == 0){
			$('#ladder').attr("disabled","disabled");
			getRoom(id,'BU');
		}else{
			$('#ladder').removeAttr("disabled");			
			$.each(json, function (i, item) {  	
	        	var option = $("<option>").val(json[i].ladderId).text(json[i].ladderName);
	        	$("#ladder").append(option);
	        });
		}    
	});
});


$("#ladder").on("change", function() {
	$("#room").empty(); 
	var id = $("option:selected", this).val();
	getRoom(id,'LA');
});


$("#vcodeBtn").on("click", function() {	
	var time = 60;  //倒计时60秒 
    
    var phoneNum = $("#phone").val();
    var id = $("#room").val();
	$.get("vcode", { houseId: id , phone: phoneNum}, function(data){
		if(data =='Phone not match'){
			alert("与业主预留号码不相同")
		}else{
			 var timer = setInterval(fun1, 1000);  //设置定时器
		}
	})	    
	
    function fun1() { 
      time--; 
      if(time>=0) { 
    	$("#vcodeBtn").html(time + "s后重新发送");
      }else{ 
    	$("#vcodeBtn").html("重新发送验证码"); 
    	$('#vcodeBtn').removeAttr("disabled");
        clearTimeout(timer);  //清除定时器 
        time =60;  //设置循环重新开始条件 
      } 
    } 
	
});

function getRoom(id,getBy){
	$.get("getRooms", { id: id,getBy:getBy}, function(data){
		
		var json = eval(data);   		
		$.each(json, function (i, item) {  	
	        var option = $("<option>").val(json[i].houseId).text(json[i].roomName);
	        $("#room").append(option);
	    });
		    
	});
}

