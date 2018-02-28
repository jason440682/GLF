<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"	content="width=device-width, initial-scale=1, user-scalable=no">
<title>租客管理</title>
<link rel="stylesheet"	href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
<link rel="stylesheet"	href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
<link rel="stylesheet" 	href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css">
<link rel="stylesheet" href="/GLF/css/owner.css?v=07">
</head>
<body>
	<header data-am-widget="header" class="am-header am-header-default"
		style="background-color:#3cc51f">
	<div class="am-header-left am-header-nav">
		<a href="#" onClick="javascript :history.back(-1);"> <i
			class="am-header-icon am-icon-arrow-left"></i> <span
			class="am-header-nav-title">返回 </span>
		</a>
	</div>

	<h1 class="am-header-title">租客管理</h1>
	</header>

	<div class='advert'>
		<h1 class="advert-title">广告位</h1>
	</div>

	<div class="weui-tab">

		<div class="weui-tab__bd">
			<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
				<div class="weui-panel weui-panel_access">
					<div class="weui-panel__bd">						
   						<div class="weui-media-box weui-media-box_text">
							<h4 class="weui-media-box__title">${houseFullInfo.estateName} ${houseFullInfo.buildingName}${houseFullInfo.ladderName}${houseFullInfo.roomName}</h4>
							<p class="weui-msg__desc">
								租户：${renter.name} <br />
								电话：${renter.phone} <br />
								代缴起始日期：${renter.startDate}  <br />
								代缴结束日期：${renter.endDate}  <br />
							</p>						
						</div>	
					
						<div class="weui-media-box weui-media-box_text">
							<h4 class="weui-media-box__title">代缴款项</h4>
							<p class="weui-msg__desc">
								<c:forEach items="${renterPayItems }" var="item">
									<c:if test="${item.isPayFor=='Y' }"><input type="checkbox" disabled="true" checked = "true"></c:if> 
									<c:if test="${item.isPayFor=='N' }"><input type="checkbox" disabled="true" ></c:if> 
								 	 ${item.item }  <br />
								</c:forEach>
								<div class="weui-loadmore weui-loadmore_line weui-loadmore_dot">
  <span class="weui-loadmore__tips"></span>
								</div>
								本月：<c:choose>
										<c:when test="${isPay == 'Y'}"><div color="green">已缴</div></c:when>
										<c:otherwise><span>未缴</span></c:otherwise>
									 </c:choose>
							</p>						
						</div>												
					</div>
				</div>
			</div>			
		</div>
	</div>

	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
</body>
</html>