//验证js,即使删除了,也无法访问啊,后端没有数据给你...
$.ajax({
	async: false,
	url: "./verify",
	success: function(res){
		switch(res["code"]){
			case 0:break;
			case 1:
				window.location.replace("login.html")
				break;
			case -1:
				window.location.replace("init.html")
		}
	}
});