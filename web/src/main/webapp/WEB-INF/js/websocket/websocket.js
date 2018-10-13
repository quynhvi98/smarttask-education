'use strict';
var stompClient = null;
var username = document.getElementById('username').value;
var socket = new SockJS('http://localhost:8080/web/ws');
stompClient = Stomp.over(socket);
stompClient.connect({}, onConnected);

function onConnected() {
    stompClient.subscribe('/topic/public-'+username, onMessageReceived);
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    function onError() {
        console.log("error " + error);
    }

    function onMessageReceived(payload) {
        playSound();
        var message = JSON.parse(payload.body);
        var a = '<li> <ul class="menu"> <li> <a style="height: 39px;"  href="/web/mailbox/read-mail?id='+message.id+'> <i  style="float: left; class="fa fa-users text-aqua"></i>' +'<p style="margin-top: -15px; margin-left: 24px; font-size: 15px;">'+ message.title+' - ('+message.sender+')'+'</p></a></li></ul></li>';
        var node = document.getElementById('chuong');
        var txt = parseInt(node.textContent || node.innerText);
        document.getElementById('chuong').innerHTML = txt + 1;
        document.getElementById('tb').innerHTML = a + document.getElementById('tb').innerHTML;

    }
}

window.onload = init;
var sound;

function init() {
    sound = new Howl({
        urls: ['https://notificationsounds.com/soundfiles/15de21c670ae7c3f6f3f1f37029303c9/file-sounds-1085-definite.mp3'],
        onload: function () {
            console.log("Loaded asset ");
            button.disabled = false; // enable the play sound button
        }
    });
}

function playSound() {
    sound.play();
}
