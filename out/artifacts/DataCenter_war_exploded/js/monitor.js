let socket;
function init(){
    socket = new WebSocket("ws://localhost:8080/DataCenter/device-monitor");
    socket.onmessage = function (event) {
        const msg = JSON.parse(event.data);
        switch (msg['type']) {
            case "":

                break;
            case "":

                break;
            case "":
        }
    }

}

