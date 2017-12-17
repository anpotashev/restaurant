<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <script type="text/javascript" src="/resources/jquery.js"></script>
    <script type="text/javascript" src="/resources/jsonfunctions.js"></script>
    <%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>--%>
    <script>

        //        $(document).ready(function () {
        //            fill_restaurant_table();
        ////            $.getJSON("/restaurants",
        ////                function (json) {
        ////                    var tr;
        ////                    for (var i = 0; i < json.length; i++) {
        ////                        tr=$('<tr/>');
        ////                        tr.append("<td>" + json[i].id + "</td>");
        ////                        tr.append("<td>" + create_link(json[i].id, json[i].name) + "</td>");
        ////                        console.log(json[i].id + " - " + json[i].name);
        ////                        tr.append("</tr>");
        ////                        $("table#restaurant_table").append(tr);
        ////                    }
        ////
        ////                });
        //        });
        //
        //        fill_restaurant_table = function() {
        //            restauarants = get_restaurnts();
        //            var table = $("table#restauarant_table");
        //            table.empty();
        //            var tr;
        //            restauarants.forEach(function (t) {
        //                tr = $('<tr>');
        //                tr.append("<td>" + t.id + "</td>");
        ////                tr.append("<td>" + create_link(t.id, t.name) + "</td>");
        //                tr.append("<td>" + create_link(t.id, t.name) + "</td>");
        //                table.append(tr);
        //            });
        //        }
        //        get_restaurnts = function () {
        //            var restaurants = $.getJSON("/restaurants",
        //                function (json) {
        //                    var result = [];
        //                    for (var i = 0; i < json.length; i++) {
        //                        restaurants[i].id = json[i].id;
        //                        restaurants[i].name = json[i].name;
        //                    }
        //                    return result;
        //                });
        //            return restaurants;
        //        }
        //
        //        create_link = function (id, name) {
        //            return "<a href='#' onclick=load_menu(" + id + ")>"+name+"</a>";
        //        }
        //
        //        load_menu = function(id) {
        //            $.getJSON("/restaurants/"+id+"/prices", load_prices);
        //        }
        //        load_prices = function (json) {
        //            $("table#menu_table").empty();
        //
        //                for (var i = 0; i < json.length; i++) {
        //                    var tr = $('<tr>')
        //                    var dish = json[i].dish;
        //                    var price = json[i].price;
        //                    tr.append("<td>" + dish.id + "</td>")
        //                    tr.append("<td>" + dish.name + "</td>")
        //                    tr.append("<td>" + price.toFixed(2) + "</td>")
        //                    $("table#menu_table").append(tr);
        ////                    var dish = $.parseJSON(d);
        ////                    console.log(d.id);
        ////                    console.log(dish.name);
        ////                    console.log(json[i].date);
        ////                    console.log(json[i].price);
        //                }
        ////                console.log(table);
        ////                $("menu").append("HHH");
        //        }

    </script>
    <title>test js page</title>
</head>
<body>
<h2>Hello world!!!</h2>
<table id="restaurant_table">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
    </tr>
    </thead>
</table>
<div id="menu">
    <table id="menu_table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>