// 应用于#top的背景图像


// #w_city 城市id(如:杭州)
var w_city;
// 邮政id(如:330100),不需要显示
var w_adcode;
// 区域id,如(101210101),不需要显示
var w_cid;
// #w_temp 气温id(如:25℃)
var w_temp;
// #w_temp_low 气温id(如:20℃)
var w_temp_low;
// #w_temp_high 气温id(如:27℃)
var w_temp_high;
// #w_weather_code 天气id(如:100)
var w_weather_code;
// #w_weather 天气(如:晴)
var w_weather;
// #wind_dir 风向
var wind_dir;
// #wind_dir 风力
var wind_sc;


//先请求到城市地址
$.ajax({
	url:"https://restapi.amap.com/v3/ip?key=f11845e043e81879739e937a449b3707&s=rsv3",
	success:function(data){
		w_adcode = data['adcode'];
		loadCID();
	}
});
//城市地址->地域ID
function loadCID(){
	$.ajax({
		url:"https://search.heweather.com/find?key=0e6b2177d7f3421d8495e805eef57c73&lang=cn&location="+w_adcode,
		success:function(data){
			w_cid = data["HeWeather6"][0]["basic"][0]['cid'];
			w_cid = w_cid.substring(2,w_cid.length);

			w_city = data["HeWeather6"][0]["basic"][0]['location'];
			loadWeatherData();
		}
	})
}
//地域ID->天气
function loadWeatherData(){
	$.ajax({
		url:"https://apip.weatherdt.com/v2/plugin/data/h5?key=HagrkpUbkG&location="+w_cid+"&lang=cn",
		success:function(data){
			var now = data['now'];
			w_temp = now['tmp'];
			w_temp_high = data['daily_forecast'][0]['tmp_max'];
			w_temp_low = data['daily_forecast'][0]['tmp_min'];
			w_weather_code = now['cond_code'];
			w_weather = now['cond_txt'];
			wind_dir = now['wind_dir'];
			wind_sc = now['wind_sc'];
			w_apply();
		}
	})
}
function w_apply() {
	$("#w_city").html(w_city);
	$("#w_temp").html(w_temp);
	$("#w_temp_low").html(w_temp_low);
	$("#w_temp_high").html(w_temp_high);
	$("#w_weather_code").html(w_weather_code);
	$("#w_weather").html(w_weather);
	$("#w_wind_dir").html(wind_dir);
	$("#w_wind_sc").html(wind_sc);
	$("#w_weather_img").attr({"src":"https://apip.weatherdt.com/20200701/bg/h5/"+w_weather_code+"d.png"});
}