var k_food = document.getElementById("k_food").innerText;
var j_food = document.getElementById("j_food").innerText;
var c_food = document.getElementById("c_food").innerText;
var w_food = document.getElementById("w_food").innerText;
var cafe = document.getElementById("cafe").innerText;
var late_food = document.getElementById("late_food").innerText;
var etc = document.getElementById("etc").innerText;

function allBtn() {
    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(true);
    }
}

function k_foodBtn() {
    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == k_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function j_foodBtn() {
    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == j_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function c_foodBtn() {
    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == c_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function w_foodBtn() {
    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == w_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function cafeBtn() {
    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == cafe) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function late_foodBtn() {
    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == late_food) {
            boardMarkers[i].setVisible(true);
        }
    }
}

function etcBtn() {
    for (var i=0; i<boardMarkers.length; i++) {
        boardMarkers[i].setVisible(false);
        if(boardInfo[i].storeType == etc) {
            boardMarkers[i].setVisible(true);
        }
    }
}

