$(document).ready(function() {

    $("#form").validate({
        rules: {
            search: "required",
            fromDate: "required",
            toDate: "required"
        },
        message: {
            search: "Please enter your keywords!",
            fromDate: "Please enter your date!",
            toDate: "Please enter your date!"
        },
        submitHandler: function(form) {
            form.submit();
        }
    })

    //Add loader when fetching data from Gnews API//
    $(document).ajaxStart(function() {
        $("#loader").show();
    });
    
    //Fetching data//
    var url = new URL ('https://gnews.io/api/v4/top-headlines?token=cb459c92b3b9916b7bf96bb54d0b4446'),
        params = {lang:"en"};
    Object.keys(params).forEach(key => url.searchParams.append(key, params[key])); //filter data with param//

    fetch(url)
    .then( (response) => {
        if (response.ok) {
            return response.json();
        } else {
            throw response;
        };
    })
    .then( (data) => {
        //Render data into html//
        let output = "";
        let latestNews = data.articles;

        for (var i in latestNews) {
            let timestamp = new Date(latestNews[i].publishedAt);
            let utcTimes = timestamp.toUTCString();
            output += `
                <div class="col-md-6 col-lg-4">
                    <img src="${latestNews[i].image}" class="img-fluid rounded mx-auto d-block" style="height: 300px;"/>
                    <h5><a href="${latestNews[i].url}" target="_blank">${latestNews[i].title}</a></h5>
                    <h6><em>Published on: ${utcTimes}</em></h6>
                    <p>${latestNews[i].description}</p>
                </div>
            `
        }

        if (output !== "") {
            $("#newsResult").append(output);
        }
    })
    .then (function() {
        $("#loader").hide();
    })
    .catch ((error) => {
        $("#loader").hide(0, function() {
            alert("Error: " + error.status);
        })
    })

    //Add datepicker//

    let dateFormat ="mm/dd/yy",
        fromDate = $("#fromDate")
        .datepicker({
            changeMonth: true,
        })
        .on("change", function() {
            toDate.datepicker("option", "minDate", getDate(this));
        }),
        toDate = $("#toDate")
        .datepicker({
            changeMonth: true,
        })
        .on("change", function() {
            fromDate.datepicker("option", "maxDate", getDate(this));
        });
    function getDate(e) {
        var date;
        try {
            date = $.datepicker.parseDate(dateFormat,e.value);
        } catch (error) {
            date = null;
        }
        return date;
    }

    //Add click event//
    $("#btn1").click(function(e) {

            e.preventDefault();

            if ($("#form").valid()) {

                let input = $("#search").val();
                let fromDate = new Date($("#fromDate").val()).toISOString().split('.')[0] + "Z";
                let toDate = new Date($("#toDate").val()).toISOString().split('.')[0] + "Z";
        
                if (input !== "") {
                
                let url = "https://gnews.io/api/v4/search?q="+input+"&lang=en"+"&from="+fromDate+"&to="+toDate+"&sortby=relevance"+"&token=cb459c92b3b9916b7bf96bb54d0b4446";
                
                fetch(url)
                .then ( (response) => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw response;
                    };
                })
                .then ( (data) => {
                    let output = "";
                    let latestNews = data.articles;

                    for (var i in latestNews) {
                        let timestamp = new Date(latestNews[i].publishedAt);
                        let utcTimes = timestamp.toUTCString();
                        output += `
                            <div class="col-md-6 col-lg-4">
                                <img src="${latestNews[i].image}" class="img-fluid rounded mx-auto d-block" style="height: 300px;" />
                                <h5><a href="${latestNews[i].url}" target="_blank">${latestNews[i].title}</a></h5>
                                <h6><em>Published on: ${utcTimes}</em></h6>
                                <p>${latestNews[i].description}</p>
                            </div>
                        `
                    }
                    
                    if (output !== "") {
                        $("#searchBox").modal("hide");
                        $("#newsResult").html("");
                        $("#newsResult").append(output);
                        $("input").val("");

                    } else {
                        let newsNotFound = "There is no articles. Try searching later!";
                        $("#searchBox").modal("hide");
                        $("#newsResult").html("");
                        $("#newsResult").append(newsNotFound);
                    }
                })
                .then ( function() {
                    $("#loader").hide();
                })
                .catch ( (error) => {
                    $("#loader").hide(0, function() {
                        alert("Error: " + error.status);
                    })
                })
                .then ( function() {
                    $("#searchBox").modal("hide");
                })
            } 
        }
    })
})