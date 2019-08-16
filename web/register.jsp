<%@ taglib
        prefix="c"
        uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CK
  Date: 2019/8/14
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<html>
<head>
    <title>注册页面</title>

    <style>
        *{
            margin:0px;
            padding:0px;
            box-sizing: border-box;
        }

        body{
            background-image: url("/images/背景2.jpg");
            padding-top:20px;
        }

        .rg_layout{
            width:900px;
            height:600px;
            border:8px solid #A6A6A6;
            background-color: white;
            margin:auto;
            padding-top:30px;

        }

        .rg_left{
            /*border:1px solid red;*/
            float:left;
            padding:15px;
        }


        .rg_left>p:first-child{
            font-size:20px;
            color:#FFD026;
        }

        .rg_left>p:last-child{
            font-size:20px;
            color:#A6A6A6;
        }


        .rg_center{
            /*border:1px solid red;*/
            float:left;
            margin-left:80px;

        }


        .td_left{
            width:120px;
            height:45px;
            text-align: right;
            padding-right: 40px;
        }

        #username,#password,#email,#tel,#rePassword,#hobbys,#addrs{
            border:1px solid #A6A6A6;
            width:250px;
            height:30px;
            padding:8px;
            border-radius: 8px;
        }

        .rg_right{
            /*border:1px solid red;*/
            float:right;
            padding:15px;
        }

        .rg_right p{
            font-size:15px;
        }

        .rg_right p a{
            color:pink;
        }

        #btn_sub{
            width:120px;
            height:40px;
            background-color: #ffde65;
            border-color: #ffde65;
            font-size:20px;
        }
        #btn_sub2{
            width:120px;
            height:40px;
            background-color: #ffde65;
            border-color: #ffde65;
            font-size:20px;
        }

    </style>
    <script type="text/javascript">
        function onCheck() {
            var username = document.getElementById("username");
            if (!username.value) {
                alert("用户名不能为空！");
                return false;
            }
            var password = document.getElementById("password");
            if(!password.value){
                alert("密码不能为空！");
                return false;
            }
            var rePassword = document.getElementById("rePassword");
            if(!rePassword.value){
                alert("确认密码不能为空！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div class="rg_layout">

    <!--左边部分-->
    <div class="rg_left">
        <p>新用户注册</p>
        <p>USER REGISTER</p>
    </div>

    <!--中间部分-->
    <div class="rg_center">
        <div class="rg_form">
            <!--定义表单 form-->
            <form action="registerServlet" method="post" onsubmit="return onCheck()">
                <table>
                    <tr>
                        <td class="td_left"><label for="username">用户名</label></td>
                        <td class="td_right"><input type="text" name="username" id="username" placeholder="请输入用户名" value="${username}"></td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="password">密码</label></td>
                        <td class="td_right"><input type="password" name="password" id="password" placeholder="请输入密码" value="${password}">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="password">确认密码</label></td>
                        <td class="td_right"><input type="password" name="rePassword" id="rePassword" placeholder="请再次输入密码" value="${rePassword}">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label>性别</label></td>
                        <td class="td_right">
                            <input type="radio" name="sex" value="男"> 男
                            <input type="radio" name="sex" value="女" style="margin-left: 30px"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">爱好</td>
                        <td class="td_right">
                            <input type="text" name="hobbys" id="hobbys" placeholder="请输入爱好" value="${hobbys}"></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="tel">手机号码</label></td>
                        <td class="td_right"><input type="text" name="phone" id="tel" placeholder="请输入手机号" value="${phone}"></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="email">Email</label></td>
                        <td class="td_right"><input type="email" name="email" id="email" placeholder="请输入邮箱" value="${email}"></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="addrs">地址</label></td>
                        <td class="td_right"><input type="text" name="addrs" id="addrs" placeholder="请输入地址" value="${addrs}">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center" style="padding-top: 30px">
                            <input type="submit" value="注册" id="btn_sub">
                            <input type="reset" value="重置" id="btn_sub2">
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </div>


    <!--右边部分-->
    <div class="rg_right">

        <p>
            已有账号？<a href="login.jsp" style="text-decoration: none">立即登录</a>
        </p>

    </div>

</div>

</body>
</html>
