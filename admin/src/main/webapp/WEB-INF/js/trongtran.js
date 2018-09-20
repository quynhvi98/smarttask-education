function notify_sucess(title, msg) {
    Lobibox.notify('success', {
        size: 'mini',
        position: "top right",
        sound: false,
        delay: 2000,
        title: title,
        msg: msg + ' thành công!'
    });
    setTimeout("location.reload(true);", 2000);
}

function notify_error(title, msg) {
    Lobibox.notify('error', {
        size: 'mini',
        position: "top right",
        sound: false,
        delay: 2000,
        title: title,
        msg: msg + ' thất bại!'
    });
    setTimeout("location.reload(true);", 2000);
}

function notify_reset_success(matkhau) {
    Lobibox.notify('success', {
        size: 'mini',
        position: "top right",
        sound: false,
        delay: 2000,
        title: 'Thiết lập thành công!',
        msg: "Mật khẩu mới là: <span class='label label-success'>"+matkhau+"</span> mật khẩu đã dc sao chép (ctr + v) để paste"
    });
}

function setClipboard(value) {
    var tempInput = document.createElement("input");
    tempInput.style = "position: absolute; left: -1000px; top: -1000px";
    tempInput.value = value;
    document.body.appendChild(tempInput);
    tempInput.select();
    document.execCommand("copy");
    document.body.removeChild(tempInput);
}