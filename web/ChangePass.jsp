<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Khôi Phục Mật Khẩu</title>
        <jsp:include page="_meta.jsp"/>
        <jsp:include page="_header1.jsp"/>
        <style>
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
        <div class="container" style="text-align: center; background-color: #f0f0f0; padding: 20px;">
            <h1 style="color: #333;">Khôi Phục Mật Khẩu</h1>
            <h3 style="color: red; font-size: 20px;display: flex; text-align: center; justify-content: center"> ${requestScope.mess} </h3>
            <div class="forgetpass-form" style="max-width: 300px; margin: 0 auto;">
                <form action="forgetpass" method="post" style="background-color: #fff; padding: 20px; border-radius: 5px;">
                    <div class="form-group" style="margin-bottom: 15px;">
                        <label for="email" style="display: flex; margin-bottom: 5px;">Email:</label>
                        <input type="email" id="email" name="email" value="${sessionScope.email}  " style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 3px;" readonly="">
                    </div>
                    <div class="form-group" style="margin-bottom: 15px;">
                        <label for="otp" style="display: flex; margin-bottom: 5px;">Nhập OTP:</label>
                        <input type="text" id="otp" name="otp" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 3px;" required="">
                    </div>
                    <div class="form-group" style="margin-bottom: 15px;">
                        <label for="pass" style="display: flex; margin-bottom: 5px;">Nhập mật khẩu:</label>
                        <input type="password" id="pass" name="pass" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 3px;" required="">
                    </div>
                    <div class="form-group" style="margin-bottom: 15px;">
                        <label for="repass" style="display: flex; margin-bottom: 5px;">Nhập lại mật khẩu:</label>
                        <input type="password" id="repass" name="repass" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 3px;" required="">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Gửi yêu cầu" style="width: 100%; padding: 10px; background-color: #007bff; color: #fff; border: none; border-radius: 3px; cursor: pointer;">
                    </div>
                </form>
            </div>
        </div>


    </body>
    <jsp:include page="_footer.jsp"/>
</html>
