function notify_sucess(title,msg) {
    Lobibox.notify('success', {
        size: 'mini',
        position: "top right",
        sound: false,
        delay: 2000,
        title: title,
        msg: msg+' thành công'
    });
    setTimeout("location.reload(true);", 2000);
}

function notify_error(title,msg) {
    Lobibox.notify('error', {
        size: 'mini',
        position: "top right",
        sound: false,
        delay: 2000,
        title: title,
        msg: msg+' thất bại'
    });
    setTimeout("location.reload(true);", 2000);
}