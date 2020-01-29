<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Managers</title>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Manager's name</th>
    </tr>
    <#list managers as manager>
        <tr>
            <td>${manager.id}</td>
            <td>${manager.managerName}</td>
            <td><a href="/manager-update/{id} (id=${manager.id})">Edit</a></td>
            <td><a href="/manager-delete/{id} (id=${manager.id})">Delete</a></td>
        </tr>
    </#list>
</table>
<div><a href="/manager-create">Создать нового организатора</a></div>
</body>
</html>