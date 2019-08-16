<%@ taglib
        prefix="c"
        uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CK
  Date: 2019/8/14
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<html>
<head>
    <title>修改商品详情页面</title>
    <script type="text/javascript">
        function onResetImg(_obj) {
            var td = _obj.parentElement;
            var content = '<img id="image" src="upload/${gs.goodsInfoPic}" style="width: 100px" align="center">'
            content += '<input id="file" type="file" name="goodsInfoPic" onchange="loadImageFile(event)"/>';
            content += '<input type="button" value="取消上传" onclick="onCancelImg(this)">';
            td.innerHTML = content;
        }
        function loadImageFile(event)
        {
            var image = document.getElementById('image');
            image.src = URL.createObjectURL(event.target.files[0]);
        }
        function onCancelImg(_obj) {
            var td= _obj.parentElement;
            var content='<img src="upload/${gs.goodsInfoPic}" width="100px" />';
            content+='<input type="button" value="重新上传" onclick="onResetImg(this)">';
            content+='<input type="hidden" name="goodsInfoPic" value="${gs.goodsInfoPic}">';
            td.innerHTML = content;
        }

    </script>
    <style type="text/css">
        body{
            background-image: url("/images/蓝.jpg");
        }
        .right{
            text-indent: 10px;
        }
    </style>
</head>
<body>
<div style="width: 300px;margin: auto;padding-top: 100px">
        <span style="color: #ffffff">${user.username}用户：您好 <br/>
        <a href="loginServlet?opr=out" onclick="return confirm('确认退出登录么？')">退出登录</a><br/>
        </span>
<form action="goodServlet?opr=update&id=${gs.id}&name=${name}" method="post" enctype="multipart/form-data" >

    <input type="hidden" name="id" value="${gs.id}">
    <table border="1px" width="100%" cellspacing="0">
        <tr>
            <td>
                商品名称
            </td>
            <td>
                <input type="text" name="goodsInfoName" value="${gs.goodsInfoName}" style="width: 100%;" class="right">
            </td>
        </tr>
        <tr>
            <td>
                商品图片
            </td>
            <td>
                <c:choose>
                <c:when test="${gs.goodsInfoPic!=null && gs.goodsInfoPic!= ''}">
                    <a href="downloadServlet?fileName=${gs.goodsInfoPic}">
                        <img src="upload/${gs.goodsInfoPic}" width="100px" >
                    </a><br>
                    <input type="button" value="重新上传" onclick="onResetImg(this)">
                    <div id="result"></div>
                    <input type="hidden" name="goodsInfoPic" value="${gs.goodsInfoPic}">
                </c:when>
                <c:otherwise>
                <!-- 上传控件是不能通过脚本去设置value值 -->
                <input type="file" name="goodsInfoPic"></td>
            </c:otherwise>
            </c:choose>
            </td>
        </tr>
        <tr>
            <td>
                商品价格
            </td>
            <td>
                <input type="text" name="goodsInfoPrice" value="${gs.goodsInfoPrice}" style="width: 100%" class="right">
            </td>
        </tr>

        <c:if test="${name == 'informationUpdate'}">
        <tr>
            <td>
                商品简介
            </td>
            <td>
                <input type="textarea" name="goodsInfoDescription" value="${gs.goodsInfoDescription}" style="width: 100%;height: 40px;" class="right">
            </td>
        </tr>
        <tr>
            <td>
                商品库存
            </td>
            <td>
                <input type="text" name="goodsStock" value="${gs.goodsStock}" style="width: 100%" class="right">
            </td>
        </tr>
        <tr>
            <td>
                状态值
            </td>
            <td>
                <c:if test="${gs.flag == '激活'}">
                <input type="radio" name="flag" value="激活" checked>激活
                <input type="radio" name="flag" value="禁用">禁用
                </c:if>
                <c:if test="${gs.flag == '禁用'}">
                    <input type="radio" name="flag" value="激活">激活
                    <input type="radio" name="flag" value="禁用" checked>禁用
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                创建人
            </td>
            <td>
                <input type="text" name="created" value="${gs.created}" style="width: 100%" class="right">
            </td>
        </tr>
        </c:if>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="保存" style="margin-right: 30px;outline: none;border: 0px; background-color: rgba(0, 0, 0, 0)" >
                <input type="button" value="返回" onclick="history.back()" style="outline: none;border: 0px; background-color: rgba(0, 0, 0, 0)">
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
