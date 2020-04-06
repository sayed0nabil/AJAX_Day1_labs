

let counter = 0;

function callback(resposneTxt, statusTxt){
    if(statusTxt == "success") {
        const msgs = JSON.parse(resposneTxt);
        for(; counter<msgs.length; counter++){
            const msgBox = document.getElementById("msgbox");
            const container = document.createElement("div");
            const username = document.createElement("strong");
            const msg = document.createElement("span");
            username.textContent = msgs[counter].username;
            msg.textContent = msgs[counter].msg;
            container.style.background = "#ff3c2d";
            container.style.color = "white";
            container.style.border = "1px solid blue";
            container.style.marginTop = "20px";
            username.style.padding = "20px";
            msg.style.padding = "20px";
            container.append(username, msg);
            msgBox.append(container);
        }
        document.getElementById("message").value = "";
    }
}
function getMsgs() {
        $.get("messenger?t=" + new Date().getTime(), callback);
}


document.body.onload = getMsgs;
setInterval(getMsgs, 5000);

function sendMessage() {
    $.post("messenger", {
        username: document.getElementById("username").value,
        message: document.getElementById("message").value
    }, callback);
}