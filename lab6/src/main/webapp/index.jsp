<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <title>Температура</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>Данные о температуре</h1>
<table>
    <tr>
        <td>Среднемесячная температура:</td>
        <td>${averageTemperature}</td>
    </tr>
    <tr>
        <td>Количество дней, когда температура была выше среднемесячной:</td>
        <td>${daysAboveAverage}</td>
    </tr>
    <tr>
        <td>Количество дней, когда температура была ниже среднемесячной:</td>
        <td>${daysBelowAverage}</td>
    </tr>
    <tr>
        <td>Топ-3 температуры:</td>
        <td>${topTemperatures}</td>
    </tr>
    <tr>
        <td>Загрузить данные из файла:</td>
        <td>
            <form action="temperature" method="POST" enctype="multipart/form-data">
                <input type="file" name="file">
                <input type="submit" value="Загрузить">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
