<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <style type="text/css">
        body {
            color: #555;
        }

        .login-box {
            width: 450px;
            border: 1px solid #ccc;
            margin: 0 auto;
            margin-top: 150px;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 2px 2px 5px #ccc;
        }

        .login-title {
            text-align: center;
        }

        .login-row {
            padding: 10px 0px 10px 0px;
        }

        .login-text {
            width: 50%;
            border: 1px solid #ccc;
            height: 30px;
            padding-left: 10px;
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
            if (!password.value) {
                alert("密码不能为空！");
                return false;
            }
        }
    </script>
</head>
<body>
<div class="login-box">
    <h1 class="login-title">欢迎登录</h1>
    <form action="loginServlet?opr=in" method="post" onsubmit="return onCheck()">
        <div class="login-row" style="margin-left: 80px">
            账号：<input type="text" class="login-text" name="username" id="username" value="${username}" style="border-radius: 8px"/>
        </div>
        <div class="login-row" style="margin-left: 80px">
            密码：<input type="password" class="login-text" name="password" id="password" value="${password}" style="border-radius: 8px"/>
        </div>
        <div class="login-row" style="text-align: center;">
            <button type="submit" style="margin-right: 30px">登录</button>
            <button type="reset">重置</button>
        </div>
        <div>
            没有账号？<a href="register.jsp" style="text-decoration: none;color: blue">马上注册</a>
        </div>
    </form>
</div>
</body>
</html>
