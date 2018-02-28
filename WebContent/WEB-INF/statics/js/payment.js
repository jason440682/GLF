
$("#submitPay").click(function(){
	
	var houseId = document.getElementById('houseId').value;
	
	$.ajax({
		   contentType: "application/json; charset=utf-8",
		   url: "/GLF/payment/submit",
		   data:{"houseId": houseId},
		   dataType: "json",
		   traditional: true,
		   async:false,
		   success:function(result){
           		alert(result.pack);
           		alert(result.paySign);
           		
           	wx.chooseWXPay({
           	    timestamp: result.timeStamp, 
           	    nonceStr: result.nonceStr,
           	    package: result.pack, 
           	    signType: 'MD5', 
           	    paySign: result.paySign, 
           	    success: function (res) {
           	    	alert("支付成功");
           	    },
           	    fail: function (res){
           	    	alert("失败");
           	    	alert(JSON.stringify(res));
           	    }
           	});
               
           }
		  });
});