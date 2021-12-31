<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Изменение</title>
    <link href="forms.css" rel="stylesheet"/>
</head>
<body>
<h3>Изменение сущности</h3>
<form method="post">
    <input type="hidden" value="${requestScope.smartphone.id}" name="id"/>
    <label>Модель</label><br>
    <label>
        <input name="model" value="${requestScope.smartphone.model}" required/>
    </label><br><br>
    <label>Цена</label><br>
    <label>
        <input name="price" value="${requestScope.smartphone.price}" type="number" min="0" step=".01" required/>
    </label><br><br>
    <input type="submit" value="Сохранить"/>
</form>
</body>
</html>