var k_food = document.getElementById("k_food").innerText;
var j_food = document.getElementById("j_food").innerText;
var c_food = document.getElementById("c_food").innerText;
var w_food = document.getElementById("w_food").innerText;
var cafe = document.getElementById("cafe").innerText;
var late_food = document.getElementById("late_food").innerText;
var etc = document.getElementById("etc").innerText;

var all_img =  document.getElementById("all_img");
var k_food_img =  document.getElementById("k_food_img");
var j_food_img =  document.getElementById("j_food_img");
var c_food_img =  document.getElementById("c_food_img");
var w_food_img =  document.getElementById("w_food_img");
var cafe_img =  document.getElementById("cafe_img");
var late_food_img =  document.getElementById("late_food_img");
var etc_img =  document.getElementById("etc_img");

function allBtn() {
    if(all_img.src.match("all")) {
        all_img.src = "./images/all_red.png"
    }

    k_food_img.src = "./images/k-food.png"
    j_food_img.src = "./images/j-food.png"
    c_food_img.src = "./images/c-food.png"
    w_food_img.src = "./images/w-food.png"
    cafe_img.src = "./images/cafe.png"
    late_food_img.src = "./images/late-food.png"
    etc_img.src = "./images/etc.png"

    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(true);
    }
}
//function allBtn() {
//    var all_img =  document.getElementById("all_img");
//    if(all_img.src.match("all")) {
//        all_img.src = "./images/all_red.png"
//    } else {
//        all_img.src = "./images/all.png"
//    }
//
//    for (var i=0; i<boardMarkers.length; i++) {
//        boardMarkers[i].setVisible(true);
//    }
//}


function k_foodBtn() {
        if(k_food_img.src.match("k-food")) {
            k_food_img.src = "./images/k-food_red.png"
        }

        all_img.src = "./images/all.png"
        j_food_img.src = "./images/j-food.png"
        c_food_img.src = "./images/c-food.png"
        w_food_img.src = "./images/w-food.png"
        cafe_img.src = "./images/cafe.png"
        late_food_img.src = "./images/late-food.png"
        etc_img.src = "./images/etc.png"

    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == k_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function j_foodBtn() {
    if(j_food_img.src.match("j-food")) {
        j_food_img.src = "./images/j-food_red.png"
    }

    all_img.src = "./images/all.png"
    k_food_img.src = "./images/k-food.png"
    c_food_img.src = "./images/c-food.png"
    w_food_img.src = "./images/w-food.png"
    cafe_img.src = "./images/cafe.png"
    late_food_img.src = "./images/late-food.png"
    etc_img.src = "./images/etc.png"

    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == j_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function c_foodBtn() {
    if(c_food_img.src.match("c-food")) {
        c_food_img.src = "./images/c-food_red.png"
    }

    all_img.src = "./images/all.png"
    k_food_img.src = "./images/k-food.png"
    j_food_img.src = "./images/j-food.png"
    w_food_img.src = "./images/w-food.png"
    cafe_img.src = "./images/cafe.png"
    late_food_img.src = "./images/late-food.png"
    etc_img.src = "./images/etc.png"

    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == c_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function w_foodBtn() {
    if(w_food_img.src.match("w-food")) {
        w_food_img.src = "./images/w-food_red.png"
    }

    all_img.src = "./images/all.png"
    k_food_img.src = "./images/k-food.png"
    j_food_img.src = "./images/j-food.png"
    c_food_img.src = "./images/c-food.png"
    cafe_img.src = "./images/cafe.png"
    late_food_img.src = "./images/late-food.png"
    etc_img.src = "./images/etc.png"

    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == w_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function cafeBtn() {
    if(cafe_img.src.match("cafe")) {
        cafe_img.src = "./images/cafe_red.png"
    }

    all_img.src = "./images/all.png"
    k_food_img.src = "./images/k-food.png"
    j_food_img.src = "./images/j-food.png"
    c_food_img.src = "./images/c-food.png"
    w_food_img.src = "./images/w-food.png"
    late_food_img.src = "./images/late-food.png"
    etc_img.src = "./images/etc.png"

    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == cafe) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function late_foodBtn() {
    if(late_food_img.src.match("late-food")) {
        late_food_img.src = "./images/late-food_red.png"
    }

    all_img.src = "./images/all.png"
    k_food_img.src = "./images/k-food.png"
    j_food_img.src = "./images/j-food.png"
    c_food_img.src = "./images/c-food.png"
    w_food_img.src = "./images/w-food.png"
    cafe_img.src = "./images/cafe.png"
    etc_img.src = "./images/etc.png"

    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == late_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function etcBtn() {
    if(etc_img.src.match("etc")) {
        etc_img.src = "./images/etc_red.png"
    }

    all_img.src = "./images/all.png"
    k_food_img.src = "./images/k-food.png"
    j_food_img.src = "./images/j-food.png"
    c_food_img.src = "./images/c-food.png"
    w_food_img.src = "./images/w-food.png"
    cafe_img.src = "./images/cafe.png"
    late_food_img.src = "./images/late-food.png"

    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == etc) {
            boardMarkers[i].setVisible(true);
        }
    }
}

