var com = {};
com.prototype = new Object();

com.submit = function(serviceId, requestParam, callbackFn){
	var request = $.ajax({
		 url : "/board/"+serviceId
		,method: "post" 
	    ,datatype : "json"
	    ,data : requestParam
	});
	
	request.done(function(data, textStatus, jqXHR ){
		alert("data : "+data);
		
		callbackFn(data);
	});
	
	request.fail(function(jqXHR, textStatus, errorThrown ){
		alert("errorThrown : "+errorThrown);
	});
};