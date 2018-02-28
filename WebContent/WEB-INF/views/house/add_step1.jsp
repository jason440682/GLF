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
<link rel="stylesheet" href="/GLF/css/example.css?v=07">
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

	<h1 class="am-header-title">新增物业</h1>
</header>
	
	<div class="weui-tab">
		<div class="weui-cells__title">第一步：选择物业属性</div>
        <div class="weui-cells weui-cells_radio">
            <div class="weui-cell" type="M">
                <div class="weui-cell__bd">
                    <p>自己的房</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" checked="true" checked="true"  name="houseType" id="M"/>
                    <span class="weui-icon-checked"></span>
                </div>
            </div>
            <div class="weui-cell" type="R">

                <div class="weui-cell__bd">
                    <p>租的房</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" name="houseType"   id="R" />
                    <span class="weui-icon-checked" ></span>
                </div>
            </div>
            <div class="weui-cell" type="A">
                <div class="weui-cell__bd">
                    <p>代理的房</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" name="houseType"   id="A" />
                    <span class="weui-icon-checked"></span>
                </div>
            </div>
		</div>
		<div class="weui-cells__title">第二步：选择新增操作</div>
        <div class="weui-cells">
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__bd">
                    <p>扫一扫</p>
                </div>
                <div class="weui-cell__ft">
                </div>
            </a>
            <a class="weui-cell weui-cell_access" href="/GLF/house/addHousing">
                <div class="weui-cell__bd">
                    <p>手动选择</p>
                </div>
                <div class="weui-cell__ft">
                </div>
            </a>
        </div>
	</div>

	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
	
</body>
</html>