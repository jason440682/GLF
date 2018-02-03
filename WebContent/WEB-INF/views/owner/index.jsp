<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Owner</title>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
<link rel="stylesheet" href="../css/owner.css?v=02">
</head>
<body>
	<div class="weui-tab">

		<div class="weui-tab__bd">
			<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
				<header class='demos-header'>
				<h1 class="demos-title">我的物业</h1>
				</header>
				<div class="weui-panel weui-panel_access">
					<div class="weui-panel__bd">
						<div class="weui-media-box weui-media-box_text">
							<h4 class="weui-media-box__title">新杭花园A栋301房</h4>
							<p class="weui-msg__desc">
								地区：广州市番禺区 <br /> 本月费用：200元<br /> 缴纳状态 : 待缴
							</p>
						</div>

						<div class="button_sp_area">				
							<span class="left-button"><a class="weui-btn weui-btn_mini weui-btn_plain-default">详情</a></span>
							<span class="right-button"><a class="weui-btn weui-btn_mini weui-btn_plain-primary">立即缴款</a> </span>
						</div>

						<div class="weui-media-box weui-media-box_text">
							<h4 class="weui-media-box__title">新杭花园A栋801房</h4>
							<p class="weui-msg__desc">
								地区：广州市番禺区 <br /> 本月费用：200元<br /> 缴纳状态 : 已缴
							</p>
						</div>
						<div class="button_sp_area">				
							<span class="left-button"><a class="weui-btn weui-btn_mini weui-btn_plain-default">详情</a></span>
							
						</div>
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__bd"></div>
					<div class="weui-cell__ft">
						<img src="../icon/Plus.png" alt="">
					</div>
				</div>

			</div>
			<div id="tab2" class="weui-tab__bd-item">
				<h1>页面二</h1>
			</div>
			<div id="tab3" class="weui-tab__bd-item">
				<h1>页面三</h1>
			</div>
			<div id="tab4" class="weui-tab__bd-item">
				<h1>页面四</h1>
			</div>
		</div>

		<div class="weui-tabbar">
			<a href="#tab1" class="weui-tabbar__item weui-bar__item--on">
				<div class="weui-tabbar__icon">
					<img src="../icon/Home.png" alt="" />
				</div>
				<p class="weui-tabbar__label">物业</p>
			</a> <a href="#tab2" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<img src="../icon/Yuan.png" alt="">
				</div>
				<p class="weui-tabbar__label">缴费</p>
			</a> <a href="#tab3" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<img src="../icon/Key.png" alt="">
				</div>
				<p class="weui-tabbar__label">租户管理</p>
			</a> <a href="#tab4" class="weui-tabbar__item"> <span
				class="weui-badge"
				style="position: absolute; top: -.4em; right: 1em;">2</span>
				<div class="weui-tabbar__icon">
					<img src="../icon/Contact-Plus.png" alt="">
				</div>
				<p class="weui-tabbar__label">租户验证</p>
			</a>
		</div>
	</div>

	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
</body>
</html>