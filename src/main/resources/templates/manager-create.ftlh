<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create manager</title>
</head>
<body>
    <form action="/manager-create" method="post">
        <label for="managerName">Manager name</label>
        <input type="text" field="${manager.managerName}" id="managerName" placeholder="Write name for Manager">
        <input type="submit" value="Create Manager">
    </form>
</body>
</html>