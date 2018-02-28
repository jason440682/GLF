<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>我的物业</title>
<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
<link rel="stylesheet" 	href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css">
<link rel="stylesheet" href="/GLF/css/owner.css?v=07">
</head>
<body>
<header data-am-widget="header" class="am-header am-header-default"
		style="background-color:#3cc51f">
	<div class="am-header-left am-header-nav">
		<a href="#" onClick="javascript :history.back(-1);" > <i
			class="am-header-icon am-icon-arrow-left"></i> <span
			class="am-header-nav-title">返回 </span>
		</a>
	</div>

	<h1 class="am-header-title">物业缴费</h1>
	</header>

	<div class='advert'>
		<h1 class="advert-title">广告位</h1>
	</div>

	<div class="weui-tab">

		<div class="weui-tab__bd">
			<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">				
				<div class="weui-panel weui-panel_access">
					<div class="weui-panel__bd">
						<c:forEach items="${houseFullList }" var="houseFull">
   							<div class="weui-media-box weui-media-box_text">
								<h4 class="weui-media-box__title">${houseFull.estateName} ${houseFull.buildingName}${houseFull.ladderName}${houseFull.roomName}</h4>
								<p class="weui-msg__desc">
									地区：广州市番禺区 <br />  
									状态：<c:if test="${houseFull.status=='R'}">已出租  </c:if>  									
										 <c:if test="${houseFull.status=='S'}">  自住  </c:if>
								</p>
							</div>

							<div class="button_sp_area">				
								<span class="left-button"><a class="weui-btn weui-btn_mini weui-btn_plain-primary">分享</a></span>
								<c:if test="${houseFull.status=='R'}">
									<a class="weui-btn weui-btn_mini weui-btn_plain-primary" href="renterManage/${houseFull.houseId}">租客管理</a> 
								</c:if>  		
								<span class="right-button"><img src="../icon/Remove.png"> </span>
							</div>
						</c:forEach>													
					</div>
				</div>
				<div class="buttom-icon">					
					<a href="addHousing"><img class="add-button" src="../icon/Plus.png" alt=""></a>
				</div>

			</div>
			
		</div>
	</div>

	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
</body>
</html>