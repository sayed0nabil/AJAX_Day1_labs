


let req;

function generateRequest(){
    req = null;
    if(window.XMLHttpRequest){
        req = new XMLHttpRequest();
    }else if(window.ActiveXObject){
        req = new ActiveXObject(Microsoft.XMLHTTP);
    }
}
function submitform1() {
    generateRequest();
    if(req){
        req.onreadystatechange = function(){
            if(req.readyState == 4){
                if(req.status == 200)
                    document.lab1.content.value = req.responseText;
                else
                    document.lab1.content.value = "Error Code: " + req.status;
                console.log("Resposne: " + req.responseText);
            }
        };
        req.open("GET", "content.txt?t=" + new Date().getTime(), true);
        req.send(null);
    }

}


function submitform2() {
    generateRequest();
    if(req){
        req.onreadystatechange = function () {
            if(req.readyState == 4){
                const result = document.getElementById("result");
                if(req.status == 200) {
                    result.textContent = "Valid";
                    result.style.color = "green";
                }
                else if(req.status == 404){
                    result.textContent = "Not Valid";
                    result.style.color = "red";
                }
            }
        };
        const username = document.getElementById("username").value;
        req.open("POST", "checkusername", true);
        req.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        req.send("username=" + username);
    }

}

document.body.onload = function () {
    generateRequest();
    if(req){
        req.onreadystatechange = function(){
            if(req.readyState == 4){
                if(req.status == 200) {
                    console.log(req.readyState);
                    document.getElementById("table-container").innerHTML = req.responseText;
                }
                else
                    document.getElementById("table-container").textContent = "Error Code: " + req.status;
            }
        };
        req.open("GET", "table.html?t=" + new Date().getTime(), true);
        req.send(null);
    }
}