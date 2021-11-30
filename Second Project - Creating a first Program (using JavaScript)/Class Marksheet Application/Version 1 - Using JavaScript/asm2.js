let data = new Array();

function addTestScore() {
    var fname = document.forms["myForm"]["fname"].value;
    var math = document.forms["myForm"]["math"].value;
    var physics = document.forms["myForm"]["physics"].value;
    var chemistry = document.forms["myForm"]["chemistry"].value;

    if (fname=="" || math=="" || physics=="" || chemistry=="") {
        alert("Không được bỏ trống phần này");
        return false;
    } else if ((isNaN(math)||math<0||math>10) || 
    (isNaN(physics)||physics<0||physics>10) || 
    (isNaN(chemistry)||chemistry<0||chemistry>10)) {
        alert("Yêu cầu nhập lại điểm");
        return false;
    } else {
        var testScore = {
            fname: document.getElementById("fname").value,
            math: document.getElementById("math").value,
            physics: document.getElementById("physics").value,
            chemistry: document.getElementById("chemistry").value
        }
        data.push(testScore);
        var table = document.getElementById("table");
        var row = table.insertRow(data.length);
        var cel0 = row.insertCell(0);
        var cel1 = row.insertCell(1);
        var cel2 = row.insertCell(2);
        var cel3 = row.insertCell(3);
        var cel4 = row.insertCell(4);
        var cel5 = row.insertCell(5);
        cel0.innerHTML = data.length;
        cel1.innerHTML = testScore.fname;
        cel2.innerHTML = testScore.math;
        cel3.innerHTML = testScore.physics;
        cel4.innerHTML = testScore.chemistry;
        cel5.innerHTML = "?"
    }
}

function resetTestScore() {
    document.getElementById("fname").value="";
    document.getElementById("math").value="";
    document.getElementById("physics").value="";
    document.getElementById("chemistry").value="";
}

function avgTestScore() {
    for (var i=0; i<data.length; i++) {
        var avgScore = (parseFloat(data[i].math) + parseFloat(data[i].physics) + parseFloat(data[i].chemistry))/3;
        document.getElementById("table").rows[i+1].cells[5].innerHTML = avgScore.toFixed(1);
    }
}

function checkGoodStudent() {
    for (var i=0; i<data.length; i++) {
        var avgScore = (parseFloat(data[i].math) + parseFloat(data[i].physics) + parseFloat(data[i].chemistry))/3;
        if (avgScore > 8) {
            document.getElementById("table").rows[i+1].style.backgroundColor="yellow";
        }
    }
}