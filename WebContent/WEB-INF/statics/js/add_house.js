$("#estate").on("change", function() {

	var id = $("option:selected", this).val();
	
	$.get("getBuilding", { id: id }, function(data){
		var json = eval(data); 
		$("#building").empty();
        $.each(json, function (i, item) {  	
        	var option = $("<option>").val(json[i].buildingId).text(json[i].buildingName);
        	$("#building").append(option);
        });
	});
});