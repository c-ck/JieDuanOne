<%--
  Created by IntelliJ IDEA.
  User: CK
  Date: 2019/8/12
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品列表</title>
    <style type="text/css">
        body{
            background-image: url("/images/蓝.jpg");
        }
    </style>
</head>
<body>
    <div style="width: 800px;margin: auto">
        <span style="color: #ffffff">${user.username}用户：您好 <br/>
        <a href="loginServlet?opr=out" onclick="return confirm('确认退出登录么？')">退出登录</a><br/>
        </span>
        <a href="good_save.jsp">添加</a>
        <table border="1" width="100%" cellspacing="0">
            <caption style="font-size: 30px">商品列表查询</caption>
            <thead>
            <tr>
                <th>
                    商品名字
                </th>
                <th>
                    商品图片
                </th>
                <th>
                    商品价格
                </th>
                <th>
                    操作
                </th>
            </tr>
            </thead>
            <tbody style="text-align: center">
                <!-- 遍历商品集合 -->
                <c:forEach items="${list}" var="gs" varStatus="index">
                    <tr class="w">
                        <td> ${gs.goodsInfoName}</td>
                        <td>
                            <!--
                    如果goodsInfoPic不为空就显示图片<img>
                    如果为空就显示<input type="file">
                    -->
                            <c:choose>
                                <c:when test="${gs.goodsInfoPic!=null && gs.goodsInfoPic!=''}">
                                    <a href="downloadServlet?fileName=${gs.goodsInfoPic}">
                                        <img src="upload/${gs.goodsInfoPic}" width="85px" height="90px">
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <!-- 上传控件是不能通过脚本去设置value值 -->
                                    无图片
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${gs.goodsInfoPrice}元</td>
                        <td>
                            <a href="goodServlet?opr=loadData&id=${gs.id}&name=update" onclick="return confirm('确定要修改吗？')">修改</a>
                            <a href="goodServlet?opr=delete&id=${gs.id}" onclick="return confirm('确定要删除吗？')">删除</a>
                            <a href="goodServlet?opr=loadData&id=${gs.id}&name=information">详情</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
