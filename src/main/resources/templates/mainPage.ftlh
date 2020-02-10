<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta charset="UTF-8">
    <title>StadiumsSystem</title>
</head>
<body>
    <div>Hello, user</div>
    <div>
        <form th:action="@{/logout}" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
    </div>
    <div><a href="/eventTypes">Перейти к списку видов мероприятий</a></div>
    <div><a href="/managers">Перейти к списку организаторов</a></div>
    <div><a href="/stadiums">Перейти к списку стадионов</a></div>
    <div><a href="/events">Перейти к списку мероприятий</a></div>
    <div><a href="/sectors">Перейти к списку секторов</a></div>
    <div><a href="/tickets">Перейти к списку билетов</a></div>
</body>
</html>