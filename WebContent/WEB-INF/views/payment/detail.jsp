<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>费用详情</title>
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

				<input type="hidden" id="houseId" value="${houseFull.houseId}" />
				<div class="weui-panel weui-panel_access">
					<div class="weui-media-box weui-media-box_text">
						<h4 class="weui-media-box__title">${houseFull.estateName}
							${houseFull.buildingName}${houseFull.ladderName}${houseFull.roomName}</h4>
						<p class="weui-msg__desc">
							时间：${billingDate } <br />
							<c:forEach items="${billingList }" var="billing"> 							
									${billing.item}: ${billing.amount}元 <br />
							</c:forEach>
							------------ <br /> 总共：${totalAmount }元 <br /> 缴纳状态：
							<c:if test="${billingList.get(0).isPay=='N'}">
								<span style="color: red">待缴 </span>
							</c:if>
							<c:if test="${billingList.get(0).isPay=='Y'}">
								<span style="color: green">已缴 </span>
							</c:if>
						</p>
						<c:if test="${billingList.get(0).isPay=='N'}">
							<span class="right-button">
							<a id="submitPay" class="weui-btn weui-btn_mini weui-btn_plain-primary">立即缴费</a> </span>
						</c:if>

					</div>
				</div>
			</div>
		</div>
	</div>
	
<input type="hidden" id="timestamp" value="${timestamp}" />
<input type="hidden" id="signature" value="${signature}" />
<input type="hidden" id="message" value="${message}" />
<input type="hidden" name="noncestr"  id="nonceStr" value="${noncestr}" />
	
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="/GLF/js/payment.js?v08"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
wx.config({
    debug: false, 
    appId: 'wx1d90dfa8aef1ea1f', 
    timestamp: $('#timestamp').val(), 
    nonceStr: $('#nonceStr').val(), 
    signature: $('#signature').val(),
    jsApiList: ['chooseWXPay'] 
});
</script>
</body>
</html>