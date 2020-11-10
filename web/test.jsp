<%--
  Created by IntelliJ IDEA.
  User: soy
  Date: 2020/11/9
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="https://cdn.staticfile.org/jquery/3.5.1/jquery.js"></script>
    <script src="js/weather.js"></script>
</head>
<body>
系统核心数量:
<%=Runtime.getRuntime().availableProcessors()%>

当前城市:<span id="w_city"></span><br/>
当前温度:<span id="w_temp"></span><br/>
最低温度:<span id="w_temp_low"></span><br/>
最高温度:<span id="w_temp_high"></span><br/>
天气:<span id="w_weather"></span><img height="30px" width="30px" id="w_weather_ico"/>
</body>
</html>
