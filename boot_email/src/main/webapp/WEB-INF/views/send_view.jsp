<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <table width = "500" border="1">
        <form method="post" action="send_email">
            <tr>
                <td>이메일</td>
                <td>
                    <input type="text" name="to" size="50">
                </td>
            </tr>
            <tr>
                <td>제목</td>
                <td>
                    <input type="text" name="subject" size="50">
                </td>
            </tr>
            <tr>
                <td>내용</td>
                <td>
                    <textarea rows="10" name="message"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="전송">
                </td>
            </tr>
        </form>
    </table>
</body>
</html>