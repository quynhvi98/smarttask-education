$(function() {
  $('[data-toggle="tooltip"]').tooltip({
    container: 'body'
  })

  var randomAvatarClolorAray = ['avatar-blue', 'avatar-indigo', 'avatar-purple', 'avatar-pink','avatar-red', 'avatar-orange', 'avatar-yellow', 'avatar-green', 'avatar-info', 'avatar-danger', 'avatar-warning'];
$('.table .avatar ').each(function() {
  var randomClolor = randomAvatarClolorAray[Math.floor(Math.random() * randomAvatarClolorAray.length)];
  $(this).addClass(randomClolor);
});
  // var randomClolorAray = ['yellow-bg', 'green-bg', 'teal-bg', 'cyan-bg',
	// 'primary-bg', 'success-bg'];
  var randomClolorAray = ['green-bg', 'teal-bg', 'cyan-bg', 'primary-bg'];
  $('.random-clolor-avatar a').each(function() {
    var randomClolor = randomClolorAray[Math.floor(Math.random() * randomClolorAray.length)];
    $(this).prepend('<span class="avatar-text ' + randomClolor + '">' + $(this).text().trim().substring(0, 1) + '</span>');
  });

  if ($('.content-wrapper').find('.chat-mobile').length > 0) {
    $('.content-wrapper').addClass('sectionchat');
    if ($(document).width() < 768) {
      $('.direct-chat').addClass('collapsed-box').find('[data-widget="collapse"] .fa-minus').addClass('fa-plus').removeClass('fa-minus');
    }
  }
  $('[data-chatshow]').on('click', function() {
    var dataWidget = $("#" + $(this).attr('data-chatshow'));
    dataWidget.toggleClass('chatshow').find('[data-widget="collapse"]').click();
    dataWidget.siblings().each(function() {
      if ($(this).is('.chatshow')) {
        $(this).toggleClass('chatshow').find('[data-widget="collapse"]').click();
      }
    });
  });


  $('.thoat').click(function() {
    $(this).parents('.collapse').collapse('hide');
  });
  $('.bar').each(function() {
    var phantramcongviec = $(this).prev('label').find('.phantramcongviec');
    if(phantramcongviec.length < 1){
      phantramcongviec = $(this).find('.phantramcongviec');
    }
    var  tinhphantram = parseInt(phantramcongviec.text());
console.log(tinhphantram);
    // var hoanthanh = parseInt($(this).find('.hoanthanh-congviec').text());
    // var tongcongviec = parseInt($(this).find('.tong-congviec').text());
    $(this).find('.complete')
      .css('width', phantramcongviec.text());
    // phantramcongviec.text('(' + Math.ceil(hoanthanh / tongcongviec * 100) + '%)');

    if (tinhphantram <= 30) {
      $(this).find('.complete').removeClass('warning success').addClass('danger');
      phantramcongviec.removeClass('warning-color success-color').addClass('danger-color');
    } else if (tinhphantram > 30 && tinhphantram < 50) {
      $(this).find('.complete').removeClass('success danger').addClass('warning');
      phantramcongviec.removeClass('success-color danger-color').addClass('warning-color');
    } else if (tinhphantram > 50) {
      $(this).find('.complete').removeClass('danger warning').addClass('success');
      phantramcongviec.removeClass('danger-color warning-color').addClass('success-color');
    }
  });

  // dropdowncuttom ẩn hiện nội dung..........................................
  // $('.title-dropdowncuttom').first().addClass("-collapsed").next('.table').slideDown(300);
  $('.title-dropdowncuttom').click(function(a) {
    if (!$(a.target).is('a') && !$(a.target).is('a *')) {
      var $parent = $(this).closest('.dropdowncuttom');
      if ($parent.hasClass('-collapsed')) {
        $parent.removeClass("-collapsed");
        $('.table', $parent).slideDown(300);
      } else {
        $parent.addClass("-collapsed");
        $('.table', $parent).slideUp(300);
      }
    }
  });

  // mở select2 khi được
	// focus....................................................
  $(document).on('focus', '.select2', function(e) {
    if (e.originalEvent) {
      $(this).siblings('select').select2('open');
    }
  });
  // add tooltip cho các icon trong dropdown có icon 3 dấu
	// chấm......................
  $('.bacham .dropdown-menu.dropdown-menu-right li a i').each(function() {
    $(this).attr({
      "data-toggle": "tooltip",
      "data-placement": "top",
      "title": $(this).parent().text()
    });
    $(this).tooltip({
      container: 'body'
    })
  });

  // cuttom
	// checkbox...........................................................
  $('input[type="radio"]').each(function() {
    if ($(this).parent().is('label')) {
      if (!$(this).parent().is('.radio-check')) {
        $(this).parent().addClass('radio-check');
      }
    } else {
      if (!$(this).parent().is('.radio-check')) {
        $(this).wrap('<label class="radio-check"></label>');
      }
    }

    var input = $(this);
    if (input.is(':checked')) {
      $(this).parent().addClass('tick');
    } else {
      $(this).parent().removeClass('tick');
    }
  });
  $('input[type="radio"]').change(function() {
    $('[name="' + $(this).attr('name') + '"]').each(function() {
      if ($(this).is(':checked')) {
        $(this).parent().addClass('tick');
        if ($(this).attr('data-show') != "") {
          var divShow = $(this).attr('data-show');
          $(divShow).addClass('show');
        }
      } else {
        $(this).parent().removeClass('tick');
        if ($(this).attr('data-show') != "") {
          var divShow = $(this).attr('data-show');
          $(divShow).removeClass('show');
        }
      }
    });

  });

  $('input[type="checkbox"]').each(function() {
    if ($(this).parent().is('label')) {
      if (!$(this).parent().is('.check')) {
        $(this).parent().addClass('check');
      }
    } else {
      if (!$(this).parent().is('.check')) {
        $(this).wrap('<label class="check"></label>');
      }
    }
    var input = $(this);
    if (input.is(':checked')) {
      $(this).parent().addClass('tick');
    } else {
      $(this).parent().removeClass('tick');
    }
  });

  $('body').on('click', '.check', function() {
      if (!$(this).find('input[type="checkbox"]').is(':checked')) {
        $(this).addClass('tick').find('span').addClass('xong');
        $(this).find('input[type="checkbox"]').prop("checked", true);
      } else {
        $(this).removeClass('tick').find('span').removeClass('xong');
        $(this).find('input[type="checkbox"]').prop("checked", false);
      }
  });

  // hiệu ứng khi check vào input check trên th của table thì check hết các
	// input của td
  $('th .check input').change(function() {
    if ($(this).is(':checked')) {
      $(this).parents('table').find('[type="checkbox"]').prop('checked', true).parent().addClass('tick');
    } else {
      $(this).parents('table').find('[type="checkbox"]').prop('checked', false).parent().removeClass('tick');
    }
  });

  // khi check vào input type='checkbox' trên td tieend hành kiểm tra xem đã
	// check hết chưa để lên th check hay xóa check
  $('td .check input').on('change', function() {
    if ($(this).is(':checked')) {
      if ($(this).parents('table').find('tbody').find('.check [type="checkbox"]:checked').length == $(this).parents('table').find('tbody').find('.check [type="checkbox"]').length) {
        $(this).parents('table').find('th .check [type="checkbox"]').prop('checked', true).parent().addClass('tick');
      }
    } else {
      $(this).parents('table').find('th .check [type="checkbox"]').prop('checked', false).parent().removeClass('tick');
    }
  });
  // bị loại bỏ do thay đổi style web ko dùng đến nữa...............
  // sử dụng cho khung input trên cùng có cha là class .khung
  // mục đích cha width theo số mình nhập trong data-width="số bất kỳ"...
  //
  // $('[data-width]').each(function() {
  // var thisDataWidth = $(this);
  // var dataWidth = $('[data-width]').attr('data-width');
  // if ($(this).find('.submit').length < 1) {
  // $(this).addClass('fix-khung');
  // $(this).find('.input').css('width', 100 / dataWidth - 1 + "%");
  // $($(this).find('.input')).each(function() {
  // if ($(this).attr('data-chiemkhoang') != '') {
  // $(this).css('width', ((100 / dataWidth) *
	// $(this).attr('data-chiemkhoang')) - 1 + "%");
  // }
  // $(this).on('focus', 'input, .select2', function() {
  // $(this).parents('.input').find('.baoloi').css({
  // 'display': 'none',
  // // 'top': $(this).offset().top + $(this).outerHeight() + 3,
  // // 'left': $(this).offset().left
  // });
  // })
  // });
  // } else
  // if (dataWidth <= 5 && $(this).find('.submit').length > 0) {
  // $(this).find('.input').css('width', 100 / dataWidth - 1 + "%");
  // $(this).find('.hai-phan').css('width', (100 / dataWidth - 0.5) * 2 +
	// "%");
  // } else {
  // $(this).addClass('fix-khung');
  // $(this).find('.input').css('width', 100 / 5 - 1 + "%");
  // $(this).find('.hai-phan').css('width', (100 / 5 - 0.5) * 2 + "%");
  // }
  // });
  //
  // $('[data-width] textarea').each(function() {
  // $(this).val($(this).val().trim()).css({
  // 'height': "",
  // 'overflow-y': 'hidden'
  // });
  // });
  // // $(this).val().trim()
  // $('[data-width] textarea').on('keydown', function() {
  // if (this.scrollHeight > $(this).height() && 46 < this.scrollHeight &&
	// this.scrollHeight < 400) {
  // $(this).addClass('border-textarea').css({
  // 'height': this.scrollHeight + "px",
  // 'overflow-y': 'hidden'
  // }).closest('.input').css({
  // 'overflow': 'visible',
  // 'z-index': '9999'
  // });
  // } else if (this.scrollHeight > 400) {
  // $(this).addClass('border-textarea').css({
  // 'height': '400px',
  // 'overflow-y': 'scroll'
  // }).closest('.input').css({
  // 'overflow': 'visible',
  // 'z-index': '9999'
  // });
  // }
  // }).focus(function() {
  // if (this.scrollHeight > $(this).height() && 46 < this.scrollHeight &&
	// this.scrollHeight < 400) {
  // $(this).addClass('border-textarea').css({
  // 'height': this.scrollHeight + "px",
  // 'overflow-y': 'hidden'
  // }).closest('.input').css({
  // 'overflow': 'visible',
  // 'z-index': '9999'
  // });
  // } else if (this.scrollHeight > 400) {
  // $(this).addClass('border-textarea').css({
  // 'height': '400px',
  // 'overflow-y': 'scroll'
  // }).closest('.input').css({
  // 'overflow': 'visible',
  // 'z-index': '9999'
  // });
  // }
  // }).change(function() {
  // if ($(this).val() != "") {
  // $(this).addClass('co-noidung');
  // } else {
  // $(this).removeClass('co-noidung');
  // }
  // }).on('blur', function() {
  // $(this).scrollTop(0).removeClass('border-textarea').css({
  // 'height': "",
  // 'overflow-y': 'hidden'
  // }).closest('.input').css({
  // 'overflow': '',
  // 'z-index': ''
  // });
  // });
  // cuttom lại style của input có
	// type="file".................................

  $('input[type="file"]').each(function() {
    if ($(this).next('label').attr('for') == $(this).attr('id')) {
      $(this).next('label').addClass('labelFile').prev('label').addClass('labelFiletext');
      var labelFile = $(this).next('label').text();
      $(this).on('change',function() {
    	  var fileName = $(this).get(0).files;

        if (fileName.length != 0) {
          $(this).next('label').text($(this).val().split('\\').pop()).css('padding-right', '25px').addClass('btn-success');
          $(this).prev('.label').addClass('trang');
        } else {
          $(this).next('label').text(labelFile).css('padding-right', '').removeClass('btn-success');
          $(this).prev('.label').removeClass('trang');
        }
      });

    }
  });
  var fileName;
  $('input[type="file"]').on('click', function(){
	  fileName = $(this).get(0).files;

  });
  $('input[type="file"]').on('change',function(e) {
    if ($(this).get(0).files.length != 0) {
      $('<i class="fa fa-paperclip" aria-hidden="true"></i>').prependTo($(this).next('.labelFile'));
      $('<i class="fa fa-times-circle icon-right" aria-hidden="true"></i>').appendTo($(this).next('.labelFile'));
    } else {
    	$(this).get(0).files = fileName;
        if($(this).parents('.direct-chat').length >0){
            $('<i class="fa fa-file" aria-hidden="true"></i>').prependTo($(this).next('.labelFile'));
        } else {
//            $('<i class="fa fa-paperclip" aria-hidden="true"></i>').prependTo($(this).next('.labelFile'));
        }
    }
  });
  $('label').on('click', '.icon-right', function(e) {

    e.preventDefault();
    var xxx = $(this).parents('.labelFile');
    fileName = $(this).parents('.form-group-tepdinhkem').find('input[type="file"]').get(0).files;
    $(this).parent().prev().val('').change();
    $('<i class="fa fa-paperclip" aria-hidden="true"></i>').prependTo(xxx);
  });
  // cuttom lại style của selectbox...........................................
  $('.checkselect').select2({
    // minimumResultsForSearch: -1,
    containerCssClass: "containercheckselect",
    dropdownCssClass: "checkselect"
  });

  // ví dụ về popup .........................................................
  $('.doimatkhau').click(function() {
    swal({
        title: "Cảnh báo",
        text: "Bạn có chắc muốn thiết lập lại mật khẩu",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-danger",
        confirmButtonText: "Đồng ý",
        cancelButtonText: "Thoát!",
        // closeOnConfirm: false
      },
      function() {
        var matkau = "@xyzzz123";
        setClipboard(matkau);
        Lobibox.notify('success', {
          size: 'mini',
          position: "top right",
          sound: false,
          delay: 4000,
          title: 'Thiết lập thành công!',
          msg: 'Mật khẩu mới là: <span class="label label-success">' + matkau + '</span> mật khẩu đã dc sao chép (ctr + v) để paste'
        });
      });
  });
  $('.xoa').click(function() {
    swal({
        title: "Cảnh báo",
        text: "Bạn có chắc muốn xóa nhân sự này!",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-danger",
        confirmButtonText: "Đồng ý",
        cancelButtonText: "Thoát!",
        // closeOnConfirm: false
        // closeOnCancel: false
      },
      function(isConfirm) {
        if (isConfirm) {
          Lobibox.notify('success', {
            size: 'mini',
            position: "top right",
            sound: false,
            delay: 4000,
            title: 'Xóa nhân sự',
            msg: 'Nhân sự <span class="label label-success">xxx</span> đã dc xóa khỏi danh mục'
          });
        } else {
          Lobibox.notify('warning', {
            size: 'mini',
            position: "top right",
            sound: false,
            delay: 4000,
            title: 'Xóa nhân sự',
            msg: 'Xóa nhân sự không thành công Bạn đã hủy hành động',
          });
        }
      });
  });
  $('.remove-tr').click(function() {
    var thistr = $(this);
    var thistrText = thistr.closest('tr').find('td').first().text();
    if (thistr.closest('tr').find('.tieudethongbao').length > 0) {
      thistrText = thistr.closest('tr').find('.tieudethongbao').text();
    }
    swal({
        title: "Cảnh báo",
        text: "Bạn có chắc muốn xóa: <span class='red'>" + thistrText + "</span> khỏi bảng hiện tai?",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-danger",
        confirmButtonText: "Đồng ý",
        cancelButtonText: "Thoát!",
        html: true,
      },
      function() {
        thistr.closest('tr').remove();
        Lobibox.notify('success', {
          size: 'mini',
          position: "top right",
          sound: false,
          delay: 4000,
          title: 'xóa <span class="red">' + thistrText + '</span> thành công!',
        });
      });

  })
  // hàm copy 1 đoạn text................................................
  function setClipboard(value) {
    var tempInput = document.createElement("input");
    tempInput.style = "position: absolute; left: -1000px; top: -1000px";
    tempInput.value = value;
    document.body.appendChild(tempInput);
    tempInput.select();
    document.execCommand("copy");
    document.body.removeChild(tempInput);
  }
  // vì khi modal chưa show sẽ ko lấy dc width lên phải chạy lại hàm
	// fixThead()
  // tạm thời tắt tính năng này vì chưa bắt hết các trường hợp
  // $('.modal').on('shown.bs.modal', function(e) {
    // fixThead();
  // });

  slimScroll2();
  select2Mobile();
  hidemodal();
  // resizeDataTable();
  // daterangepickerFixModal();
  borderCollapsed();
// tạm thời tắt tính năng này vì chưa bắt hết các trường hợp
  fixThead();

  $('#select2Modal').on('show.bs.modal', function(e) {
    $(this).removeAttr('tabindex');
    if($(this).find('.select2-search__field').length ==0 && $(this).find('#select2-modal-input').length ==0){
      $(this).find('.modal-header').append('<div class="select2-search-modal-input"><input type="text" class="form-control" id="select2-modal-input"></div>');
    }
    $('#select2-modal-input').on('keyup', function(){
      $('input.select2-search__field').val($(this).val()).keyup()
    });
    $(this).find('.select2-search__field').focus();
  }).on('hidden.bs.modal', function(e) {
    $('#select2-modal-input').remove();
  });
});
$(window).resize(function() {
  select2Mobile();
  // tạm thời tắt tính năng này vì chưa bắt hết các trường hợp
  fixThead();
});
// hàm kiểm tra xem modal đã đóng hết chưa? nếu chưa thì phải add lại class cho
// body
function hidemodal() {
  $('.modal').on('hidden.bs.modal', function(e) {
    $('.modal').each(function() {
      if ($(this).css('display') == 'block') {
        $('body').addClass('modal-open');
      }
    });
  });
}

// resizeDataTable hàm này để xóa và chạy lại DataTable rồi add scroll với
// slimscroll thiên về fix cho mobile nhiều hơn
// "ordering": false, thuộc tính để ẩn icon sắp theo thứ tự
// function resizeDataTable() {
  var orderingDataTable = $('.table.ordering table').DataTable({
    "ordering": false,
    // "scrollX": true,
    "scrollY": true,
      scroller:    true,
      // "fnDrawCallback": function() {
      //   $('.dataTables_scrollBody').slimScroll({
      //     width: '',
      //     height: '',
      //     axis: "both",
      //     wheelStep: 10,
      //     railVisible: true,
      //     railColor: colorScroll,
      //     allowPageScroll: true,
      //     color: colorScroll,
      //     touchScrollStep: 75
      //   });
      // }
  });

  var resizeDataTable = $('div.table:not(".tree-dot, .ordering , .no-datatable") table:not(".tree-table")').DataTable({
    // "scrollX": true,
    "scrollY": true,
    scroller:    true,
      // "fnDrawCallback": function() {
      //   $('.dataTables_scrollBody').slimScroll({
      //     width: '',
      //     height: '',
      //     axis: "both",
      //     wheelStep: 10,
      //     railVisible: true,
      //     railColor: colorScroll,
      //     allowPageScroll: true,
      //     color: colorScroll,
      //     touchScrollStep: 75
      //   });
      // }
  });
  // if ($(document).width() < 768) {
  //   // resizeDataTable.destroy();
  //   $('div.table:not(".tree-dot"), .box .direct-chat-messages').slimscroll({
  //     width: '',
  //     height: '',
  //     axis: "both",
  //   });
  // }
// }

function slimScroll2() {
  $(".box .direct-chat-messages").slimScroll({
    width: '',
    height: '',
    railVisible:true,
    axis: "y"
  });
  // $('.tree-table').parent().slimScroll({
  //   width: '',
  //   height: '',
  //   railVisible:true,
  //   axis: "both",
  // });
  // if($(".box .direct-chat-messages").outerHeight() > 350){
  // $(".box .direct-chat-messages").slimscroll({
  // width: '',
  // height: '',
  // axis: "both",
  // });
  // } else{
  // $(".box .direct-chat-messages").slimScroll({destroy: true});
  // }
}
// cuttom lại giao diện cho select trong hàm này bao gồm tạo sơ đồ cây cho
// selectbox và hiển thị dưới dạng popup khi trên mobile
function select2Mobile() {
  // Modal select2Modal
  var select2Modal = '<div class="modal fade" id="select2Modal"  role="dialog" aria-labelledby="select2ModalLabel"> \
     <div class="modal-dialog" role="document"> \
       <div class="modal-content"> \
         <div class="modal-header"> \
           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> \
           <h4 class="modal-title" id="select2ModalLabel">Lựa chọn</h4> \
         </div> \
         <div class="modal-body" id="select2show"> \
         </div> \
       </div> \
     </div> \
   </div>';
  if ($(document).width() < 768) {
    if ($('#select2Modal').length < 1) {
      $(select2Modal).appendTo('body');
    }
    if ($('.select2-container').length > 0) {
      $('select.select2').select2('destroy');
    }
    $('.select2').select2({
      dropdownParent: $('#select2show')
    });
    $('select').on('select2:open', function(e) {
      $('#select2Modal').modal('show').find('.modal-title').text($(e.target).attr('title'));
      var thisdataClass = $(this);
      // dataClassSelect2(thisdataClass);
      var containerClass = "";
      if (thisdataClass.find("option[class]").length > 0) {
        containerClass = "containerselect2tree";
      }
      dataClassSelect2(thisdataClass, containerClass);
    }).on('select2:close', function(e) {
      $('#select2Modal').modal('hide');
    });
  } else {
    // if ($('.select2-container').length > 0) {
    // $('select.select2').select2('destroy');
    // }
    // if(){
    // select2-container
    // }
    $('#select2Modal').remove();
    $('.select2').select2({
      containerCssClass: "containerselect2",
      dropdownAutoWidth: true,
      dropdownAutoHeight: true
    });
    $('select').on('select2:open, select2:opening', function(e) {
      var thisdataClass = $(this);
      var containerClass = "";
      if (thisdataClass.find("option[class]").length > 0) {
        containerClass = "containerselect2tree";
      }
      dataClassSelect2(thisdataClass, containerClass);
    });
  }
}
// tạo tree cho selectbox.................................................
function dataClassSelect2(thisdataClass, containerClass) {
  var findThis = thisdataClass;
  var dataClassSelect2 = findThis.attr('data-class');
  var select2this = findThis;
  var select2Ul = $(findThis.data('select2').$results);
  select2Ul.closest('.select2-container').addClass(containerClass);
  setTimeout(function() {
    eachThis();
  }, 10);
  $(document).on("keyup", ".select2-search__field", function(e) {
    var dataClassSelect2 = $(this).attr('data-class');
    if ($(this).val() != '') {
      $(select2Ul).find('li').addClass('optionlv1');
    } else {
      eachThis();
    }
  });

  function eachThis() {
    var lvtruoc = 1;
    var lvsau = 1;
    var lvLength = 1;
    select2this.children().each(function() {
      if ($(this).attr('class') != '' || $(this).attr('class') != undefined) {
        $(select2Ul).addClass(dataClassSelect2);
        $(select2Ul).find('li').eq($(this).index()).addClass($(this).attr('class'));

        if ($(this).attr('class') != undefined) {
          lvsau = parseInt($(this).attr('class').replace(/([a-zA-Z ])/g, ""));
        }
        if (lvtruoc == lvsau) {
          lvLength++;
        } else if (lvtruoc > lvsau) {
          var cungleve = ((100 * lvLength) + 50);
          var dataClass = '[data-top="-' + cungleve + '"]:before';
          $(select2Ul).find('li').eq($(this).index()).attr('data-top', "-" + cungleve).append($('<style>' + dataClass + '{top:-' + cungleve + '% !important}</style>'));
          lvtruoc = lvsau;
        } else if (lvtruoc < lvsau) {
          lvtruoc = lvsau;
          lvLength = 1;
        }
      }
    });
  }
}
// fixThead tồn tại chủ yếu cho giao diện mobile nó có tác dụng cố định thead
// của table để khi table có nội dung dài sẽ dễ nhìn hơn
function fixThead() {
  $('.fix-w').each(function() {
    // var widththat = $(this).parents('table').css('position','fixed').outerWidth();
    var widththat = $(this).parents('table').outerWidth();
    // $(this).parents('table').css('position','');
    if($(this).parents('table').is('.full-table')){
      $(this).parents('table').css('width', widththat);
    }

    $(this).find('.uutien').each(function() {
      $(this).css('width', ((($(this).outerWidth() * 2) / widththat) * 100) + '%');
    });
    $(this).find('th').each(function() {
      $(this).css('width', ($(this).outerWidth() / widththat) * 100 + '%');
    });
  });
  $('div.table:not(".tree-dot")').each(function() {
    if ($(this).parents('.slimScrollDiv').find('.table1').length > 0) {
      $(this).parents('.slimScrollDiv').find('.table1').remove();
    }
    if ($(this).parents('.slimScrollDiv').length > 0) {
      var tableW = $(this).find('table').width();
      var thead = $('<table class="tree-table" style="width:' + tableW + 'px"></table>').append($(this).find('thead').clone());
      thead = $('<div class="table1"></div>').append($('<div class="table2"></div>').append(thead)).insertBefore($(this));
      $(this).bind('slimscrollingX', function(e, pos) {
        $(this).parents('.slimScrollDiv').find('.table2').scrollLeft(pos);
      });
    }

  });
}

function borderCollapsed() {
  $('.btn-link[data-toggle="collapse"]').each(function() {
    if ($(this).attr('aria-expanded') == 'false') {
      $(this).parents('.form-group.mb-0').addClass('bordercollapsed');
    } else {
      $(this).parents('.form-group.mb-0').removeClass('bordercollapsed');
    }
  });
}
$('.collapse ').on('shown.bs.collapse', function() {
  borderCollapsed();
});
$('.collapse ').on('hidden.bs.collapse', function() {
  borderCollapsed();
});

(function() {
  function success() {}

  function failure() {
    var attributesSrc = this.attributes['src'];
    $('[src="' + attributesSrc.value + '"]').attr('src', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANwAAADcCAIAAACUOFjWAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NkRBOTg1OTk2QTA2MTFFMUE4Qjg4MUM1MzNBQUMzNkQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NkRBOTg1OUE2QTA2MTFFMUE4Qjg4MUM1MzNBQUMzNkQiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo2REE5ODU5NzZBMDYxMUUxQThCODgxQzUzM0FBQzM2RCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo2REE5ODU5ODZBMDYxMUUxQThCODgxQzUzM0FBQzM2RCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Po8bhO4AABZqSURBVHja7F3bVhtJ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');
  }

  // this fails
  $('img').each(function() {
    var thisimg = $(this).attr('src');
    checkImage(thisimg, success, failure);
  });
})();

$(document).ready(function() {
  var stickyNavTop = $('.navbar-static-top').offset().top;
  var stickyNav = function() {
    var scrollTop = $(window).scrollTop();

    if (scrollTop > stickyNavTop) {
      $('.navbar-static-top').addClass('sticky');
    } else {
      $('.navbar-static-top').removeClass('sticky');
    }
  };
  stickyNav();
  $(window).scroll(function() {
    stickyNav();
  });
  // SHOWMOER
  var showChar = 60;
  var ellipsestext = "...";
  var moretext = "";
  var lesstext = "";
  $('[data-sokytu]').each(function() {
    showChar = $(this).attr('data-sokytu');
    var content = $(this).html().trim();
    if (content.length > showChar) {
      var c = content.substr(0, showChar);
      var h = content.substr(showChar - 1, content.length - showChar);
      var html = c + '<span class="moreelipses">' + ellipsestext + '</span>&nbsp;<span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';
      $(this).html(html);
    }
  });
  $(".morelink").click(function() {
    if ($(this).hasClass("less")) {
      $(this).removeClass("less");
      $(this).html(moretext);
    } else {
      $(this).addClass("less");
      $(this).html(lesstext);
    }
    $(this).parent().prev().toggle();
    $(this).prev().toggle();
    return false;
  });
  $('.update-trangthai').click(function() {
    $(this).parents('.input-group').addClass('ok');
  });
  var oldval;
  $('.select2').on("select2:open", function(e) {
    oldval = $(this).val();
  });
  $('.select2').change(function() {
    if (oldval != $(this).val()) {
      $(this).parents('.input-group').removeClass('ok');
    }
  });

  $('.them-checklist').each(function(){
    var checkboxValue = 1;
    var checkboxName = 'checklist_' + checkboxValue;
    $(this).find('li').each(function(){
      $(this).find('[type="checkbox"]').val(checkboxValue).attr('name', 'checklist_' + checkboxValue);
      checkboxValue ++;
    });
    $(this).on('click', function(e){
      e.preventDefault();
      var thisClick = $(e.target);
      var themChecklist = $(this);
      if(thisClick.is('.capnhat') && thisClick.parents('li').length < 1){
        checkboxValue ++;
      } else if(thisClick.is('.xoali')){
        checkboxValue = 1;
        themChecklist.find('li').each(function(){
          $(this).find('[type="checkbox"]').val(checkboxValue).attr('name', 'checklist_' + checkboxValue);
          checkboxValue ++;
        });
      }
      checkboxName = 'checklist_' + checkboxValue;
      themChecklistRun(thisClick, themChecklist, checkboxName, checkboxValue);
      });
  });
});
function themChecklistRun(thisClick, themChecklist, checkboxName, checkboxValue){
  if(thisClick.is('.capnhat')){
    var capnhatButton = thisClick;
    var checkboxName1 = checkboxName;
    var checkboxValue1 = checkboxValue;
    checklist(capnhatButton, checkboxName1, checkboxValue1);
  } else if (thisClick.is('.dong')) {
    themChecklist.removeClass('show-checklist');
    if (thisClick.parents('li').length > 0) {
      thisClick.parents('li').removeClass('hidden-label');
      themChecklist.find('.editable-controls').remove();
    } else {
      themChecklist.find('.editable-controls').remove();
    }
  } else if (thisClick.is('.xoali')) {
    themChecklist.removeClass('show-checklist');
    thisClick.parents('li').remove();
  } else if (thisClick.is('.checklist')) {
    themChecklist.addClass('show-checklist');
    var editableControlsAddTo = thisClick.parent();
    editableControls(editableControlsAddTo);
  } else if (thisClick.is('li')) {
      thisClick.addClass('hidden-label');
      themChecklist.addClass('show-checklist');
      var editableControlsAddTo = thisClick;
      editableControls(editableControlsAddTo);
  }
}
function checklist(capnhatButton, checkboxName1, checkboxValue1) {
  var Name = checkboxName1;
  var Value = checkboxValue1;
  var Button = capnhatButton;
  if ($(Button).parents('.editable-controls').find('input').val() != '') {
    var li = '<li>\
                <label class="check" for="">\
                  <input type="checkbox" name="' + Name + '" value="' + Value + '">\
                  <span>' + $(Button).parents('.editable-controls').find('input').val() + '</span>\
                </label>\
              </li>';
    if ($(Button).parents('li').length > 0) {
      $(Button).parents('li').find('label').find('span').text($(Button).parents('.editable-controls').find('input').val());
    } else {
      $(Button).parents('.them-checklist').find('.cacbuocthuhien').append(li);
    }

    $(Button).parents('.editable-controls').find('input').val('');
    $(Button).parents('.them-checklist').removeClass('show-checklist');
    $(Button).parents('li').removeClass('hidden-label');
    $('.editable-controls').remove();
  }
}

function editableControls(editableControlsAddTo) {
  var addEditableControls = editableControlsAddTo;
  var a = '<div class="editable-controls">\
    <div class="col-md-12">\
      <div class="input-group">\
        <span class="input-group-addon">\
          <i class="fa fa-pencil" aria-hidden="true"></i>\
        </span>\
        <input type="text" class="form-control" placeholder="text">\
      </div>\
    </div>\
    <div class="col-md-12">\
      <button type="button" class="btn btn-primary btn-sm capnhat">Sửa</button>\
      <button type="button" class="btn btn-default btn-sm dong">Đóng</button>\
      <button type="button" class="btn btn-danger btn-sm pull-right xoali">xóa</button>\
    </div>\
  </div>';
  if ($(addEditableControls).parents('.them-checklist').find('.editable-controls').length > 0) {
    $(addEditableControls).siblings().removeClass('hidden-label').find('.editable-controls').remove();
  }
  if ($(addEditableControls).find('.editable-controls').length < 1) {
    $(addEditableControls).append(a);
    if ($(addEditableControls).is('li')) {
      $(addEditableControls).find('input').val($(addEditableControls).find('label').text().trim());
    }
  }
}

// function daterangepickerFixModal() {
  var daterangepickerRun = function(thisDaterangepicker, bodys, singleDatePickerChek, giahanden){
  // console.log('aaaaaaaaaaaaa');
  var giahanden1 = giahanden;
  var body = bodys;
  var singleDatePickerChek1 = singleDatePickerChek;
  var thisDaterangepicker1 = thisDaterangepicker;
  // console.log(thisDaterangepicker1);
    $(thisDaterangepicker1).daterangepicker({
        separator: ' to ',
        singleDatePicker: singleDatePickerChek1,
        // minDate: new Date(2017, 10 - 1, 25),
        // maxDate: new Date(),
        locale: {
          separator: " → ",
          applyLabel: 'Đồng ý',
          cancelLabel: 'Hủy',
          fromLabel: 'Từ ngày',
          toLabel: 'Đến ngày',
          daysOfWeek: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
          monthNames: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
          firstDay: 1
        },
        parentEl: body
      },
      function(start, end) {
        if(giahanden1 != ''){
          giahanden1.removeClass('btn-default').addClass('btn-primary').find('span').html('<b>Gia hạn đến: </b>' + start.format('DD/MM/YYYY'))

        }
      });
  }

  $('.lich-btn1, .mot-lich').each(function() {
    var singleDatePickerChek = true;
    var giahanden = $(this);
    if($(this).is('.mot-lich')){
      giahanden = '';
    }
    var bodys = $('body');
    var thisDaterangepicker = $(this);
    daterangepickerRun(thisDaterangepicker, bodys, singleDatePickerChek, giahanden);
  });

  $('.hai-lich').each(function() {
    var singleDatePickerChek = false;
    var giahanden = '';
    var bodys = $('body');
    var thisDaterangepicker = $(this);
    daterangepickerRun(thisDaterangepicker, bodys, singleDatePickerChek, giahanden);
  });

  $('.modal').on('show.bs.modal', function(e) {
    var bodys = $(this).find('.modal-body');
    $('.modal-body .lich-btn1').each(function() {
      var singleDatePickerChek = true;
      var giahanden = $(this);
      var thisDaterangepicker = $(this);
      daterangepickerRun(thisDaterangepicker, bodys, singleDatePickerChek, giahanden);
    });
    // Date range picker
    $('.modal-body .hai-lich').each(function() {
      var singleDatePickerChek = false;
      var giahanden = '';
      var thisDaterangepicker = $(this);
      daterangepickerRun(thisDaterangepicker, bodys, singleDatePickerChek, giahanden);
    });
  });

$(document)
.bind('ajaxStart', function() {
   $(".bg-loading").show();
});
$(document).bind("ajaxComplete", function(){
     $(".bg-loading").hide();
});
// $('textarea.form-control').on('keydown', function(e){
// if(isNaN(parseInt(e.key))){e.preventDefault();}
// })
// thêm dòng tree

var thisTRthemdong, nextAllTr, prevAllTr, dataId, datalevel, trFist;
$('body').on('click', '.themdong-thuong', function(e){
    e.preventDefault();
    datalevel = parseInt($(this).parents('tr').attr('data-level')) + 1;
    nextAllTr = $(this).parents('tr').nextAll();
    dataId = parseInt($(this).parents('tr').nextAll().last().attr('data-id'));
    var stt = 0;
    nextAllTr.each(function(){
        if(parseInt($(this).attr('data-level')) == datalevel){
            stt++;
            $(this).find('td').first().text(stt);
            thisTRthemdong = $(this);
            if(parseInt(thisTRthemdong.attr('data-id')) > dataId){
                dataId = parseInt(thisTRthemdong.attr('data-id'));
            }
        }else {
            return false;
        }
    });

    thisTRthemdong
        .find('select').select2('destroy');
    var tr = thisTRthemdong.clone();
    thisTRthemdong.find('select').select2();
    if(thisTRthemdong.css('display') !='none'){
        tr.insertAfter(thisTRthemdong);
        tr.find('select').select2();
        tr
            .attr('data-id', dataId + 1)
            .find('td').first().text(stt+1);
    } else {
      thisTRthemdong.find('input').val('');
        thisTRthemdong.show();

    }
});
$('body').on('click', '.xoathuong', function(e){
    e.preventDefault();
    if($(this).parents('tr').next().find('.xoathuong').length>0 || $(this).parents('tr').prev().find('.xoathuong').length>0){
        var stt = 0;
        prevAllTr = $(this).parents('tr').prevAll();
        prevAllTr.each(function(){
            if(parseInt($(this).attr('data-level')) == datalevel){
                trFist = $(this).prev();
            }else {
                return false;
            }
        });
        $(this).parents('tr').remove();
        trFist.nextAll().each(function(){
            if(parseInt($(this).attr('data-level')) == datalevel){
                stt++;
                $(this).find('td').first().text(stt);
            }else {
                return false;
            }
        });
    } else {
        $(this).parents('tr').find('input').val('-1');
        $(this).parents('tr').hide();
    }
});
// end thêm dòng tree
function runDatable(againDatable) {
    againDatable.DataTable().destroy();
    againDatable.DataTable({
      "scrollX": true,
      "scrollY": true,
        // "initComplete": function() {
        //     // dataTables_scrollBody
        //     $(this).parent().slimscroll({
        //         width: '',
        //         height: 300,
        //         axis: "both",
        //         wheelStep: 10,
        //         railVisible:true,
        //         touchScrollStep: 75
        //     });
        // }
    });
}
function rangeDate(setLich) {
    setLich.daterangepicker({
        separator: ' to ',
        singleDatePicker: true,
        // minDate: new Date(2017, 10 - 1, 25),
        maxDate: new Date(),
        locale: {
            separator: " → ",
            applyLabel: 'Đồng ý',
            cancelLabel: 'Hủy',
            fromLabel: 'Từ ngày',
            toLabel: 'Đến ngày',
            daysOfWeek: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
            monthNames: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
            firstDay: 1
        },
        parentEl: $('body')
    })
}


// DataTableShowAll($('.tree-table'));
function DataTableShowAll(All){
  All.DataTable().destroy();
  All.DataTable({
    "scrollX": true,
    "scrollY": true,
    // scroller:    true,
      "pageLength": -1,
      "bLengthChange": false,
      "bPaginate": false,
    // "fnDrawCallback": function() {
    //   $('.dataTables_scrollBody').slimScroll({
    //     width: '',
    //     height: '',
    //     axis: "both",
    //     wheelStep: 100,
    //     railVisible: true,
    //     railColor: colorScroll,
    //     allowPageScroll: true,
    //     color: colorScroll,
    //     touchScrollStep: 75
    //   });
    // }
  });
}
// DataTableShowAll($('.tree-table'));
// treeview active
var pathname = window.location.pathname;
$('[href="' + pathname + '"]')
  .addClass('active')
  .parents('.treeview')
  .addClass('active')
  .siblings()
  .removeClass('active');
