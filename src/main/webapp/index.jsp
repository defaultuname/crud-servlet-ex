<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Смартфоны</title>
    <link href="index.css" rel="stylesheet"/>
</head>
<body>

<h2>Список смартфонов</h2>

<button class="create" role="button" type="submit" onclick="window.location.href='create'">Добавить</button>
<hr>

<c:if test="${requestScope.smartphones.size() > 0}">
    <h3>Записей в базе данных: ${requestScope.smartphones.size()}</h3>

    <table>
        <tr class="model">
            <th>Модель</th>
            <th>Цена</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="smart" items="${requestScope.smartphones}">
            <tr>
                <td><a href="info?id=<c:out value='${smart.id}' />">${smart.model}</a></td>
                <td class="price">${smart.price} $</td>
                <td>
                    <form method="GET" action='<c:url value="/edit"/>' style="display:inline;">
                        <input type="hidden" name="id" value="${smart.id}">
                        <input type="submit" value="Изменить">
                    </form>
                    <form method="POST" action='<c:url value="/delete"/>' style="display:inline;">
                        <input type="hidden" name="id" value="${smart.id}">
                        <input type="submit" value="Удалить">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>
<c:if test="${requestScope.smartphones.size() == 0}">
    <h3>База данных пуста</h3>
</c:if>

<a href="https://github.com/defaultuname" class="link">github</a>

</body>
</html>
