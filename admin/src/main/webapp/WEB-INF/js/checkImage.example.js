// Example Use
(function () {
  function success(){}
  function failure() {
    var attributesSrc = this.attributes['src'];
    $('[src="' + attributesSrc.value + '"]').attr('src', 'WEB-INF/images/no_image.png');
  }

  // this fails
$('img').each(function(){
    var thisimg = $(this).attr('src');
    checkImage(thisimg, success, failure);
  });
})();
