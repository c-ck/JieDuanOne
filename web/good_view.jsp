<%@ taglib
        prefix="c"
        uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CK
  Date: 2019/8/13
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<html>
<head>
    <title>单个商品信息</title>
    <style type="text/css">
        body{
            background-image: url("/images/蓝.jpg");
        }
        .left{
            width: 80px;
            text-align: right;
            padding-right: 20px;
        }
    </style>
</head>
<body>
<div style="width: 400px;margin: auto;padding-top: 50px">
    <span style="color: #ffffff">${user.username}用户：您好 <br/>
        <a href="loginServlet?opr=out" onclick="return confirm('确认退出登录么？')">退出登录</a><br/>
        </span>
    <table border="1px" cellspacing="0">
        <caption style="font-size: 20px">商品信息</caption>
        <tr>
            <td class="left">商品编号</td>
            <td>${gs.id}</td>
        </tr>
        <tr>
            <td class="left">
                商品名称
            </td>
            <td>
                ${gs.goodsInfoName}
            </td>
        </tr>
        <tr>
            <td class="left">
                商品图片
            </td>
            <td>
                <c:choose>
                    <c:when test="${gs.goodsInfoPic!=null && gs.goodsInfoPic!= ''}">
                        <a href="goodServlet?opr=downloadServlet&fileName=${gs.goodsInfoPic}">
                            <img src="upload/${gs.goodsInfoPic}" width="100px" >
                        </a>
                    </c:when>
                    <c:otherwise>
                        <!-- 上传控件是不能通过脚本去设置value值 -->
                        无图片
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td class="left">
                商品价格
            </td>
            <td>
                ${gs.goodsInfoPrice}
            </td>
        </tr>
        <tr>
            <td class="left">
                商品简介
            </td>
            <td>
                ${gs.goodsInfoDescription}
            </td>
        </tr>
        <tr>
            <td class="left">
                商品库存
            </td>
            <td>
                ${gs.goodsStock}
            </td>
        </tr>
        <tr>
            <td class="left">
                状态值
            </td>
            <td>
                ${gs.flag}
            </td>
        </tr>
        <tr>
            <td class="left">
                创建人
            </td>
            <td>
                ${gs.created}
            </td>
        </tr>
        <tr>
            <td class="left">
                创建时间
            </td>
            <td>
                ${gs.createdDate}
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <a href="goodServlet?opr=loadData&id=${gs.id}&name=informationUpdate" onclick="return confirm('确定要修改吗？')" style="margin-right: 30px">修改</a>
                <a href="goodServlet" onclick="history.back()">返回列表</a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
