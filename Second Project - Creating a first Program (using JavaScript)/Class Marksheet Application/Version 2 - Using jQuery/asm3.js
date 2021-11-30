var list = []; //tạo biến lưu học sinh//

$(document).ready(function()
{
    $("#btn1").click(function() {
        addTestScore();
    })

    $("#btn2").click(function() {
        showAvgScore();
    })

    $("#btn3").click(function() {
        showGoodStudent();
    })
})

//Tạo hàm nhập và kiểm tra điểm đầu vào//
function addTestScore() {
    var fname = $("#fname").val();
    var math = $("#math").val();
    var physics = $("#physics").val();
    var chemistry = $("#chemistry").val();

    if (fname == "" || math == "" || physics == "" || chemistry == "") {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return false;
    } else if (
        (isNaN(math) || math < 0 || math > 10) ||
        (isNaN(physics) || physics < 0 || physics > 10) ||
        (isNaN(chemistry) || chemistry < 0 || chemistry > 10)
    ) {
        alert("Vui lòng nhập lại điểm");
        return false;
    } else {

    addNew();
    $("input").val("");
    }

}

function addNew() {
    var fname = $("#fname").val();
    var math = $("#math").val();
    var physics = $("#physics").val();
    var chemistry = $("#chemistry").val();

    var testScore = new Student(fname, math, physics, chemistry);
    list.push(testScore);

    addRow(testScore, list.length, false);
}

function addRow(testScore, orderNum, avg) {
    var row = $("<tr></tr>").attr("id", orderNum);
    $("tbody").append(row);

    $("#" + orderNum).append($("<td></td>").text(orderNum));
    $("#" + orderNum).append($("<td></td>").text(testScore.fname));
    $("#" + orderNum).append($("<td></td>").text(testScore.math));
    $("#" + orderNum).append($("<td></td>").text(testScore.physics));
    $("#" + orderNum).append($("<td></td>").text(testScore.chemistry));
    
    if (avg == true) {
        $("#" + orderNum).append($("<td></td>").text(testScore.avgScore));
    } else {
        $("#" + orderNum).append($("<td></td>").text("?"))
    }
}

class Student {
    constructor(fname, math, physics, chemistry) {
        this.fname = fname;
        this.math = parseFloat(math);
        this.physics = parseFloat(physics);
        this.chemistry = parseFloat(chemistry);
        this.avgScore = parseFloat((this.math + this.physics + this.chemistry) / 3).toFixed(1);
    }
}

//Tạo hàm hiển thị điểm trung bình//
function showAvgScore() {
    $("tbody").empty();

    for (var i = 0; i < list.length; i++) {
        addRow(list[i], i+1, true);
    }
}

//Tạo hàm hiển thị học sinh giỏi//
function showGoodStudent() {
    for (var i = 0; i < list.length; i++) {
        if (list[i].avgScore >= 8.0) {
            $("#" + (i+1)).addClass("good")
        }
    }
}