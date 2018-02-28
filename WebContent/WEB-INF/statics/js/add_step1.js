$('.weui-cell').click(function(){
	var val=$(this).attr("type");
	$('input:radio[name=houseType]').attr('checked',false);
	if(val == 'M'){
		
		alert($('input:radio[name=houseType]:nth(0)').attr('id'))
		$('input:radio[name=houseType]:nth(0)').attr('checked',true);
	}else if(val == 'R'){
		$('input:radio[name=houseType]:nth(1)').attr('checked',true);
	}else{
		$('input:radio[name=houseType]:nth(2)').attr('checked',true);
	}
	
});


