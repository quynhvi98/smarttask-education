// This is included with the Parsley library itself,
// thus there is no use in adding it to your project.


Parsley.addMessages('vi', {
  defaultMessage: "Giá trị không hợp lệ.",
  type: {
    email:        "Giá trị không phải là email.",
    url:          "Giá trị không phải là url.",
    number:       "Giá trị không phải là số.",
    integer:      "Giá trị không phải là số nguyên.",
    digits:       "Giá trị này không phải là chữ số.",
    alphanum:     "Giá trị không phải là chữ."
  },
  notblank:       "Giá trị không được để trống.",
  required:       "Giá trị này là bắt buộc.",
  pattern:        "Giá trị này không hợp lệ.",
  min:            "Giá trị này phải lớn hơn hoặc bằng %s.",
  max:            "Giá trị này phải nhỏ hơn hoặc bằng %s.",
  range:          "Giá trị này phải trong khoảng %s đến %s.",
  minlength:      "Giá trị này quá ngắn. Phải có %s ký tự hoặc dài hơn.",
  maxlength:      "Giá trị này quá dài. Tối đa %s ký tự hoặc ít hơn.",
  length:         "Độ dài ký tự không đúng. Phải nằm trong khoảng từ %s đến %s ký tự.",
  mincheck:       "Chọn tối thiếu %s lựa chọn hoặc nhiều hơn.",
  maxcheck:       "Chọn tối đa %s lựa chọn hoặc ít hơn.",
  check:          "Phải chọn từ %s đến %s lựa chọn.",
  equalto:        "Giá trị không giống nhau."
});

Parsley.setLocale('vi');
