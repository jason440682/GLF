<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>Owner</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
	<link rel="stylesheet" href="../css/owner.css">
</head>
<body>
	<div class="weui-cells__title">选择小区</div>
    <div class="weui-cells">
    	<div class="weui-cell weui-cell_select">
        	<div class="weui-cell__bd">
            	<select class="weui-select" name="estate" id="estate">
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
            	<select class="weui-select" name="select1">
                	<c:forEach items="${estateList }" var="estate">
   						<option value="${estate.housingEstateId}"> ${estate.estateName}</option> 
					</c:forEach>
                </select>
            </div>
        </div>           
	</div>
	<div class="weui-cells__title">选择房号</div>
    <div class="weui-cells">
    	<div class="weui-cell weui-cell_select">
        	<div class="weui-cell__bd">
            	<select class="weui-select" name="select1">
                	<c:forEach items="${estateList }" var="estate">
   						<option value="${estate.housingEstateId}"> ${estate.estateName}</option> 
					</c:forEach>
                </select>
            </div>
        </div>           
	</div>
	
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	
	<script src="../js/add_house.js"></script>
</body>
</html>