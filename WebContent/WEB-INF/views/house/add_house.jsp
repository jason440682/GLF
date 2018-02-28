<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>新增物业</title>
<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
<link rel="stylesheet" 	href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css">
<link rel="stylesheet" href="/GLF/css/owner.css?v=07">
<link rel="stylesheet" href="/GLF/css/example.css?v=07">
<link rel="stylesheet" href="/GLF/css/addHouse.css?v=07">
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
	<div class="weui-cells__title">选择小区</div>
    <div class="weui-cells">
    	<div class="weui-cell weui-cell_select">
        	<div class="weui-cell__bd">
            	<select class="weui-select" name="estate" id="estate" >
            		<option value="" disabled="disabled" selected="true">请选择</option>
                	<c:forEach items="${estateList }" var="estate">
   						<option value="${estate.housingEstateId}"> ${estate.estateName}</option> 
					</c:forEach>
                </select>
            </div>
        </div>           
	</div>
	<div class="weui-cells__title">选择楼宇</div>
    <div class="weui-cells">
    	<div class="weui-cell weui-cell_select">
        	<div class="weui-cell__bd">
            	<select class="weui-select" name="building" id="building">
                	
                </select>
            </div>
        </div>           
	</div>
	<div class="weui-cells__title">选择梯号</div>
    <div class="weui-cells">
    	<div class="weui-cell weui-cell_select">
        	<div class="weui-cell__bd">
            	<select class="weui-select" name="ladder" id="ladder">
                	
                </select>
            </div>
        </div>           
	</div>
	<div class="weui-cells__title">选择房号</div>
    <div class="weui-cells">
    	<div class="weui-cell weui-cell_select">
        	<div class="weui-cell__bd">
            	<select class="weui-select" name="room" id="room">
                	
                </select>
            </div>
        </div>           
	</div>
	
	<div class="weui-cell weui-cell_vcode">
    	<div class="weui-cell__hd">
        	<label class="weui-label">手机号</label>
        </div>
        <div class="weui-cell__bd">
        	<input class="weui-input" type="number" pattern="[0-9]*"  placeholder="请输入手机号"/>
        </div>
        <div class="weui-cell__ft">
        	<button class="weui-vcode-btn" id="vcodeBtn" >获取验证码</button>
        </div>
    </div>
    
     <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="submit">确定</a>
     </div>
	
	
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	
	<script src="/GLF/js/add_house.js?v=07"></script>
</body>
</html>