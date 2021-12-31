<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Создание</title>
    <link href="forms.css" rel="stylesheet"/>
</head>
<body>
<h3>Добавление смартфона</h3>
<form method="post">
    <label>Модель</label><br>
    <label>
        <input name="model" required/>
    </label><br><br>
    <label>Цена</label><br>
    <label>
        <input name="price" type="number" step=".01" required min="0"/>
    </label><br><br>
    <input type="submit" value="Сохранить"/>
</form>
</body>
</html>