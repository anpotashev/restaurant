restaurants_list = function () {
    var restaurants = [];
    $.getJSON("/restaurants",
        function (json) {
            for (var i = 0; i < json.length; i++) {
                var item = {};
                item.id = json[i].id;
                item.name = json[i].name;
                restaurants.push(item);
            }
        });
    return restaurants;
}

restaurants_menu = function (restaurantId) {
    var menu = [];
    $.getJSON("/restaurants/" + restaurantId + "/prices",
        function (json) {
            for (var i = 0; i < json.length; i++) {
                var item = {};
                var dish = json[i].dish;
                var price = json[i].price;
                item.id = dish.id;
                item.name = dish.name;
                item.price = price;
                menu.push(item);
            }
        });
    return menu;
}

restaurants_menu_for_date = function (restaurantId, date) {
    var menu = [];
    $.getJSON("/restaurants/" + restaurantId + "/prices/" + date,
        function (json) {
            for (var i = 0; i < json.length; i++) {
                var item = {};
                var dish = json[i].dish;
                var price = json[i].price;
                item.id = dish.id;
                item.name = dish.name;
                item.price = price;
                menu.push(item);
            }
        });
    return menu;
}

get_json = function (url) {
    var req = new XMLHttpRequest();
    req.open("GET", url, false);
    req.send(null);
    return JSON.parse(req.responseText);
}

del_send = function (url) {
    var req = new XMLHttpRequest();
    req.open("DELETE", url, false);
    req.send(null);
}

post_new_restaurant = function () {
    var rest = {};
    rest.name = "test";
    rest.id = null;
    var req = new XMLHttpRequest();
    req.open("POST", "/restaurants", true);
    req.setRequestHeader("Content-type", "application/json");
    req.send(JSON.stringify(rest));
}

edit_restaurant = function () {
    var rest = {};
    rest.name = "Боброфф";
    rest.id = null;
    var req = new XMLHttpRequest();
    req.open("PUT", "/restaurants/100024", true);
    req.setRequestHeader("Content-type", "application/json");
    req.send(JSON.stringify(rest));
}


new_price = function () {
    var price = {};
    price.dishId = 100003;
    price.price = 10;
    var req = new XMLHttpRequest();
    req.open("POST", "/restaurants/100024/prices/2017-12-16", true);
    req.setRequestHeader("Content-type", "application/json");
    req.send(JSON.stringify(price));
}

new_price2 = function () {
    var price = {};
    price.dishId = 100003;
    price.price = 12.22345;
    $.ajax({
        url: '/restaurants/100024/prices/2017-12-16',
        dataType: 'json',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(price),
        success: function (data) {
            console.log(data.id);
            console.log(data.dishName);
            console.log(data.price);
        }
    });
}

log_new_price_result = function (data) {
    console.log(data.id);
    console.log(data.name);
    console.log(data.price);
}

new_prices = function () {
    var prices = [];
    var price1 = {};
    var price2 = {};
    var price3 = {};
    var price4 = {};
    price1.dishId = 100003;
    price1.price = 10;
    prices.push(price1);
    price2.dishId = 100002;
    price2.price = 0.2;
    prices.push(price2);
    price3.dishId = 100001;
    price3.price = 22.3;
    prices.push(price3);
    price4.dishId = 100000;
    price4.price = 11.77;
    prices.push(price4);
    var req = new XMLHttpRequest();
    req.open("POST", "/restaurants/100024/prices/2017-12-16", true);
    req.setRequestHeader("Content-type", "application/json");
    return req.send(JSON.stringify(prices));
}