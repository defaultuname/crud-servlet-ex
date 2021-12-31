<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Смартфон</title>
    <link href="info.css" rel="stylesheet"/>
</head>
<body>
<h3>Информация о смартфоне</h3>
<table>
    <tr>
        <th>ID</th>
        <th>Модель</th>
        <th>Цена</th>
    </tr>
    <tr>
        <td>${requestScope.oneSmartphone.id}</td>
        <td>${requestScope.oneSmartphone.model}</td>
        <td>${requestScope.oneSmartphone.price} $</td>
    </tr>
</table>

<button class="main" role="button" type="submit" onclick="window.location.href='index'">На главную
</button>
</body>
</html>
