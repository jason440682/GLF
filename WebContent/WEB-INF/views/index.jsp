<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
	<link rel="stylesheet" href="../css/home.css">
	<title>微屋</title>
</head>
<body>
<div class='header'>
	<img src="../icon/user2.png" class="header-icon"  alt="" />
	<div class="header-title"><p >${nickName}</p></div>
</div>
<div class="weui-cells">
 	<a class="weui-cell weui-cell_access" href="../payment/index">
 		<div class="weui-cell__hd"><img src="../icon/Yuan.png" class="icon" alt=""></div>
		<div class="weui-cell__bd"><p>物业缴费</p></div>
        <div class="weui-cell__ft"></div>
    </a>            
</div>
<div class="weui-cells__title">我是业主</div>
	<div class="weui-cells">
		<a class="weui-cell weui-cell_access" href="../owner/home">
			<div class="weui-cell__hd"><img src="../icon/Home.png"  class="icon" alt="" /></div>
			<div class="weui-cell__bd"><p>我的物业</p></div>
            <div class="weui-cell__ft"></div>
        </a>
        <a class="weui-cell weui-cell_access" href="../owner/renter">
        	<div class="weui-cell__hd"><img src="../icon/Key.png"  class="icon" alt=""></div>
        	<div class="weui-cell__bd"><p>租户管理</p></div>
            <div class="weui-cell__ft"></div>
        </a>
</div>
<div class="weui-cells__title">我是租客</div>
	<div class="weui-cells">
		<a class="weui-cell weui-cell_access" href="javascript:;">
			<div class="weui-cell__hd"><img class="icon" src="../icon/Ok.png" alt=""></div>
			<div class="weui-cell__bd"><p>代缴验证</p></div>
            <div class="weui-cell__ft"></div>
        </a>        
</div>
<div class="weui-cells__title">我是中介</div>
	<div class="weui-cells">
		<a class="weui-cell weui-cell_access" href="/GLF/payConfirm/">
			<div class="weui-cell__hd"><img class="icon" src="../icon/Contact-Refresh.png" alt=""></div>
			<div class="weui-cell__bd"><p>委托管理</p></div>
            <div class="weui-cell__ft"></div>
        </a>        
</div>
</body>
</html>