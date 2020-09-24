// 应用于#top的背景图像
// w_locate 地域id
// #w_temp 气温id
// #w_weather 
var w_city;

var w_adcode;

var w_cid;

$.ajax({
	url:"https://restapi.amap.com/v3/ip?key=f11845e043e81879739e937a449b3707&s=rsv3",
	sucess:function(data){
		w_city = data['city'];
		w_adcode = data['adcode'];
		loadcid();
	}
});
function loadcid(){
	$.ajax({
		url:"https://search.heweather.com/find?key=0e6b2177d7f3421d8495e805eef57c73&lang=cn&location="+w_adcode,
		sucess:function(data){
			w_cid = data["HeWeather6"][0]["basic"][0]['cid'];
			w_cid = w_cid.substring(2,w_cid.length);
			loadwdata();
		}
	})
}
function loadwdata(){
	$.ajax({
		url:"https://apip.weatherdt.com/v2/plugin/data/h5?key=HagrkpUbkG&location="+w_cid+"&lang=cn",
		sucess:function(data){
			
		}
	})
}