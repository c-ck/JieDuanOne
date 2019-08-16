<%--
  Created by IntelliJ IDEA.
  User: CK
  Date: 2019/8/12
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<html>
<head>
    <title>添加页面</title>
    <%--<script type="text/javascript" src="js/upload.js" rel="stylesheet"></script>--%>
</head>
<body>
        <span style="color: darksalmon">${user.username}用户：您好 <br/>
        <a href="loginServlet?opr=out" onclick="return confirm('确认退出登录么？')">退出登录</a><br/>
        </span>
    <form action="goodServlet?opr=add" method="post" enctype="multipart/form-data">

        <table border="1px">
            <tr>
                <td>
                    商品名称
                </td>
                <td>
                    <input type="text" name="goodsInfoName">
                </td>
            </tr>
            <tr>
                <td>
                    商品图片
                </td>
                <td>
                    <input type="file" name="goodsInfoPic">
                </td>
            </tr>
            <tr>
                <td>
                    商品价格
                </td>
                <td>
                    <input type="text" name="goodsInfoPrice">
                </td>
            </tr>
            <tr>
                <td>
                    商品简介
                </td>
                <td>
                    <input type="text" name="goodsInfoDescription">
                </td>
            </tr>
            <tr>
                <td>
                    商品库存
                </td>
                <td>
                    <input type="text" name="goodsStock">
                </td>
            </tr>
            <tr>
                <td>
                    状态值
                </td>
                <td>
                    <input type="radio" name="flag" value="激活">激活
                    <input type="radio" name="flag" value="禁用">禁用
                </td>
            </tr>
            <tr>
                <td>
                    创建人
                </td>
                <td>
                    <input type="text" name="created">
                </td>
            </tr>
            <tr>
                <td>
                    创建时间
                </td>
                <td>
                    <input type="date" name="createdDate" >
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="保存">
                    <input type="button" value="返回" onclick="history.back()">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
