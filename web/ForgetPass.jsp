<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="_meta.jsp"/>
        <jsp:include page="_header1.jsp"/>
        <style>
            /* Định nghĩa các quy tắc CSS */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .forgetpass-form {
                max-width: 300px;
                margin: 50px auto;
                padding: 20px;
                background: #fff;
                border-radius: 5px;
                box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.1);
            }

            .forgetpass-form h1 {
                text-align: center;
            }

            .forgetpass-form input[type="email"],
            .forgetpass-form input[type="submit"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            .forgetpass-form input[type="submit"] {
                background-color: #007bff;
                color: #fff;
                cursor: pointer;
            }

            .forgetpass-form input[type="submit"]:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <form class="forgetpass-form" action="forgetpass" method="get">
            <h1>Khôi Phục Mật Khẩu</h1>
            <h3 style="color: red; font-size: 20px; text-align: center; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);"> ${requestScope.mess} </h3>

            <input type="email" name="email" placeholder="Nhập Email"><br>
            <input type="submit" value="Gửi yêu cầu">
        </form>
        <jsp:include page="_footer.jsp"/>
    </body>
</html>
