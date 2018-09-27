$(function(){

	//在 HTML DOM 內 li 才有兄弟 
	$("#MENU li").hover(function(){
		$(this).stop(true,false).animate({marginTop:0},800,"easeOutBounce");
		$(this).next().stop(true,false).animate({marginTop:70},900,"easeOutBack");
		$(this).prev().stop(true,false).animate({marginTop:70},900,"easeOutBack");	
	},function(){
		$(this).stop(true,false).animate({marginTop:100},800,"easeOutBounce");	
		$(this).next().stop(true,false).animate({marginTop:100},700,"easeOutBounce");
		$(this).prev().stop(true,false).animate({marginTop:100},700,"easeOutBounce");
	});
	
})