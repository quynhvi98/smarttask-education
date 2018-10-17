DROP DATABASE IF EXISTS smarttask_education;
CREATE DATABASE IF NOT EXISTS smarttask_education;

USE smarttask_education;

DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS config;
DROP TABLE IF EXISTS phong_hoc;
DROP TABLE IF EXISTS khoa_vien;
DROP TABLE IF EXISTS bo_mon;
DROP TABLE IF EXISTS ki_hoc;
DROP TABLE IF EXISTS mon_hoc;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS phan_quyen;
DROP TABLE IF EXISTS giao_vien;
DROP TABLE IF EXISTS lop_hoc;
DROP TABLE IF EXISTS sinh_vien;
DROP TABLE IF EXISTS lop_sinhvien;
DROP TABLE IF EXISTS bai_tap;
DROP TABLE IF EXISTS thong_bao;
DROP TABLE IF EXISTS bai_dang;
DROP TABLE IF EXISTS binh_luan;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS diem_sinhvien;
DROP TABLE IF EXISTS tai_lieu;
DROP TABLE IF EXISTS tin_tuc;
DROP TABLE IF EXISTS system_log;
DROP TABLE IF EXISTS bai_tap_lon;


CREATE TABLE IF NOT EXISTS role (
  ma_quyen VARCHAR(255),
  ten_quyen VARCHAR(255),
  PRIMARY KEY (ma_quyen)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS config (
  id BIGINT NOT NULL auto_increment,
  config_type VARCHAR(255),
  config_name VARCHAR(255),
  PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS phong_hoc (
  id BIGINT NOT NULL auto_increment,
  ten_phong varchar(255),
   PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS khoa_vien (
  ma_vien VARCHAR(255),
  ten_vien VARCHAR(255),
  trang_thai varchar(255),
  PRIMARY KEY (ma_vien)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS bo_mon (
  ma_nganh VARCHAR(255),
  ten_nganh VARCHAR(255),
  trang_thai varchar(255),
  ma_vien VARCHAR(255),
  PRIMARY KEY (ma_nganh),
  CONSTRAINT FK_ma_vien FOREIGN KEY (ma_vien) REFERENCES khoa_vien(ma_vien)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS ki_hoc (
  id BIGINT NOT NULL auto_increment,
  ki VARCHAR(255),
  PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS mon_hoc (
  ma_mon_hoc VARCHAR(255),
  ten_mon_hoc VARCHAR(255),
  trang_thai varchar(255),
  tin_chi int,
  ma_nganh VARCHAR(255),
  ma_ki BIGINT,
  PRIMARY KEY (ma_mon_hoc),
  CONSTRAINT FK_ma_nganh FOREIGN KEY (ma_nganh) REFERENCES bo_mon(ma_nganh),
  CONSTRAINT FK_ma_ki FOREIGN KEY (ma_ki) REFERENCES ki_hoc(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS user (
  user_name varchar(255),
  user_password varchar(255),
  full_name VARCHAR(255),
  user_email VARCHAR(255),
  user_phone VARCHAR(255),
  user_avatar VARCHAR(255),
  user_address VARCHAR(255),
  user_gender VARCHAR(255),
  user_dob DATE,
  PRIMARY KEY (user_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS phan_quyen (
  id BIGINT NOT NULL auto_increment,
  user_name varchar(255),
  ma_quyen VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_quyen FOREIGN KEY (ma_quyen) REFERENCES role(ma_quyen),
  CONSTRAINT FK_user_name_quyen FOREIGN KEY (user_name) REFERENCES user(user_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS giao_vien (
  ma_giao_vien VARCHAR(255),
  hoc_ham VARCHAR(255),
  mo_ta VARCHAR(255),
  user_name varchar(255),
  ma_nganh VARCHAR(255),
  PRIMARY KEY (ma_giao_vien),
  CONSTRAINT FK_ma_nganh_gv FOREIGN KEY (ma_nganh) REFERENCES bo_mon(ma_nganh),
  CONSTRAINT FK_user_name FOREIGN KEY (user_name) REFERENCES user(user_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS lop_hoc (
  ma_lop VARCHAR(255),
  mo_ta VARCHAR(255),
  ngay_tao datetime,
  phong_hoc BIGINT,
  ngay_bat_dau datetime,
  ngay_ket_thuc datetime,
  ngay_hoc varchar(255), -- Ngày học để dạng json có thứ(thứ hai, thứ ba,..và tiết học< tiết 1(7h->8h), ...)
  ca_hoc varchar(255),
  trang_thai varchar(255),
  ma_giao_vien VARCHAR(255),
  ma_mon_hoc VARCHAR(255),
  PRIMARY KEY (ma_lop),
  CONSTRAINT FK_ma_giao_vien FOREIGN KEY (ma_giao_vien) REFERENCES giao_vien(ma_giao_vien),
  CONSTRAINT FK_ma_mon_hoc FOREIGN KEY (ma_mon_hoc) REFERENCES mon_hoc(ma_mon_hoc),
  CONSTRAINT FK_ma_phong_hoc FOREIGN KEY (phong_hoc) REFERENCES phong_hoc(id)

) engine=InnoDB;

CREATE TABLE IF NOT EXISTS sinh_vien (
  ma_sinh_vien VARCHAR(255),
  user_name varchar(255),
  ma_vien VARCHAR(255),
  ngay_nhap_hoc date,
  ma_nganh varchar (255),
  PRIMARY KEY (ma_sinh_vien),
  CONSTRAINT FK_ma_vien_sv FOREIGN KEY (ma_vien) REFERENCES khoa_vien(ma_vien),
  CONSTRAINT FK_ma_nganh_sv FOREIGN KEY (ma_nganh) REFERENCES bo_mon(ma_nganh)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS lop_sinhvien (
  id BIGINT NOT NULL auto_increment,
  ma_lop VARCHAR(255),
  ma_sinh_vien VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_lop FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop),
  CONSTRAINT FK_ma_sinh_vien FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS bai_tap(
  id BIGINT NOT NULL auto_increment,
  ngay_tao datetime,
  file_name VARCHAR(255),
  file_real_name varchar(255),
  ma_sinh_vien VARCHAR(255),
  ma_lop VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_sinh_vien_bt FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien),
  CONSTRAINT FK_ma_lop_bt FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS thong_bao(
  id BIGINT NOT NULL auto_increment,
  content text,
  status VARCHAR(255),
  sender VARCHAR(255),
  title VARCHAR(255),
  time datetime,
  ma_giao_vien VARCHAR(255),
  ma_sinh_vien VARCHAR(255),
  ma_lop VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_giao_vien_thong_bao FOREIGN KEY (ma_giao_vien) REFERENCES giao_vien(ma_giao_vien),
  CONSTRAINT FK_ma_sinh_vien_thong_bao FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien),
  CONSTRAINT FK_ma_lop_hoc_thong_bao FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS bai_dang
(
    postid bigint PRIMARY KEY AUTO_INCREMENT,
    content longtext NOT NULL,
    file_name varchar(255),
    file_real_name varchar(255),
    user_name varchar(255),
    status int DEFAULT 1 NOT NULL,
    time datetime,
    lop_hoc varchar(255),
    CONSTRAINT ma_lop_fk FOREIGN KEY (lop_hoc) REFERENCES lop_hoc (ma_lop),
    CONSTRAINT user_name_fk FOREIGN KEY (user_name) REFERENCES user (user_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS binh_luan
(
    commentid bigint PRIMARY KEY AUTO_INCREMENT,
    content longtext NOT NULL,
    postid bigint,
    user_name varchar(255),
    time datetime,
    CONSTRAINT binh_luan_postid_fk FOREIGN KEY (postid) REFERENCES bai_dang (postid),
    CONSTRAINT binh_luan_user_name_fk FOREIGN KEY (user_name) REFERENCES user (user_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS likes
(
    likeid bigint PRIMARY KEY AUTO_INCREMENT,
    postid bigint,
    user_name varchar(255),
    CONSTRAINT like_bai_dang_postid_fk FOREIGN KEY (postid) REFERENCES bai_dang (postid),
    CONSTRAINT like_user_user_name_fk FOREIGN KEY (user_name) REFERENCES user (user_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS diem_sinhvien (
  id BIGINT NOT NULL auto_increment,
  diem_ly_thuyet double ,
  diem_thuc_hanh double,
  diem_cuoi_ki double,
  ma_sinh_vien VARCHAR(255),
  ma_giao_vien VARCHAR(255),
  ma_mon_hoc VARCHAR(255),
  ma_lop VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_sinh_vien FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien),
  CONSTRAINT FK_giao_vien FOREIGN KEY (ma_giao_vien) REFERENCES giao_vien(ma_giao_vien),
  CONSTRAINT FK_mon_hoc FOREIGN KEY (ma_mon_hoc) REFERENCES mon_hoc(ma_mon_hoc),
  CONSTRAINT FK_lop_hoc FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS tai_lieu
(
    ma_tai_lieu bigint PRIMARY KEY AUTO_INCREMENT,
    mo_ta varchar(255),
    ma_lop varchar(255),
    file_name varchar(255),
    file_real_name varchar(255),
     time datetime,
    CONSTRAINT tai_lieu_lop_hoc_ma_lop_fk FOREIGN KEY (ma_lop) REFERENCES lop_hoc (ma_lop)
);

CREATE TABLE IF NOT EXISTS tin_tuc
(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    title text,
    content text,
    time datetime,
    status int
);

CREATE TABLE IF NOT EXISTS system_log
(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    content text,
    time datetime
);

CREATE TABLE IF NOT EXISTS bai_tap_lon
(
  id bigint PRIMARY KEY AUTO_INCREMENT,
  noi_dung text,
  ngay_bat_dau datetime,
  han_nop datetime,
  ma_lop varchar(255),
  ma_giao_vien varchar(255),
  status varchar(255),
  CONSTRAINT FK_bt_lop FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop),
  CONSTRAINT FK_bt_giao_vien FOREIGN KEY (ma_giao_vien) REFERENCES giao_vien(ma_giao_vien)
);

#role
INSERT INTO smarttask_education.role (ma_quyen, ten_quyen) VALUES ('ad01', 'ROLE_ADMIN');
INSERT INTO smarttask_education.role (ma_quyen, ten_quyen) VALUES ('gv01', 'ROLE_GIANGVIEN');
INSERT INTO smarttask_education.role (ma_quyen, ten_quyen) VALUES ('sv01', 'ROLE_SINHVIEN');
#phonghoc
INSERT INTO smarttask_education.phong_hoc (id, ten_phong) VALUES (5, 'TÒA A/Dãy1/B1');
INSERT INTO smarttask_education.phong_hoc (id, ten_phong) VALUES (6, 'TÒA B/Dãy2/B2');
INSERT INTO smarttask_education.phong_hoc (id, ten_phong) VALUES (7, 'TÒA C/Dãy3/B3');
INSERT INTO smarttask_education.phong_hoc (id, ten_phong) VALUES (8, 'TÒA D/Dãy4/B4');
#kihoc
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (1, '1');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (2, '2');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (3, '3');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (4, '4');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (5, '5');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (6, '6');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (7, '7');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (8, '8');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (9, '9');
INSERT INTO smarttask_education.ki_hoc (id, ki) VALUES (10, '10');
#khoavien
INSERT INTO smarttask_education.khoa_vien (ma_vien, ten_vien, trang_thai) VALUES ('IT', 'Công nghệ thông tin', '1');
INSERT INTO smarttask_education.khoa_vien (ma_vien, ten_vien, trang_thai) VALUES ('TDH', 'Tự động hóa', '1');
#bomon

INSERT INTO smarttask_education.bo_mon (ma_nganh, ten_nganh, trang_thai, ma_vien) VALUES ('IT-CNTT', 'Công nghệ thông tin', '1', 'IT');
INSERT INTO smarttask_education.bo_mon (ma_nganh, ten_nganh, trang_thai, ma_vien) VALUES ('IT-CVN', 'CNTT Việt Nhật', '1', 'IT');
INSERT INTO smarttask_education.bo_mon (ma_nganh, ten_nganh, trang_thai, ma_vien) VALUES ('IT-KHMT', 'Khoa học máy tính', '1', 'IT');
INSERT INTO smarttask_education.bo_mon (ma_nganh, ten_nganh, trang_thai, ma_vien) VALUES ('IT-KTMT', 'Kỹ thuật máy tính', '1', 'IT');
INSERT INTO smarttask_education.bo_mon (ma_nganh, ten_nganh, trang_thai, ma_vien) VALUES ('TDH-KT', 'Kỹ thuật', '1', 'TDH');
INSERT INTO smarttask_education.bo_mon (ma_nganh, ten_nganh, trang_thai, ma_vien) VALUES ('TDH-KTDK', 'Kỹ thuật điều khiển', '1', 'TDH');
#monhoc
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-1', 'Kỹ thuật lập trình', '1', 2, 'IT-CNTT', 1);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-10', 'Tin học đại cương', '1', 3, 'IT-CNTT', 1);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-11', 'Trí tuệ nhân tạo', '1', 3, 'IT-CNTT', 2);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-12', 'Công nghệ Web và dịch vụ trực tuyến', '1', 3, 'IT-CNTT', 2);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-13', 'Phân tích và thiết kế HTTT', '1', 3, 'IT-CNTT', 2);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-14', 'Xử lí ngôn ngữ tự nhiên', '1', 3, 'IT-CNTT', 2);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-15', 'Ngôn ngữ và phương pháp dịch', '1', 3, 'IT-CNTT', 2);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-2', 'Làm quen với ngôn ngữ lập trình', '1', 3, 'IT-CNTT', 1);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-3', 'Tin học đại cương', '1', 3, 'IT-CNTT', 1);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-4', 'Thuật toán', '1', 3, 'IT-CNTT', 3);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-5', 'Cấu trúc dữ liệu và giải thuật', '1', 2, 'IT-CNTT', 1);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-6', 'Hướng đối tượng', '1', 3, 'IT-CNTT', 1);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-7', 'Hướng cấu trúc', '1', 3, 'IT-CNTT', 1);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-8', 'Kĩ thuật phần mềm', '1', 3, 'IT-CNTT', 1);
INSERT INTO smarttask_education.mon_hoc (ma_mon_hoc, ten_mon_hoc, trang_thai, tin_chi, ma_nganh, ma_ki) VALUES ('IT-CNTT-9', 'Lập trình C', '1', 3, 'IT-CNTT', 1);

INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (1, '10:54', 'crontime');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (2, 'Lớp của bạn đã đủ điều kiện khai giảng : [tenlop] - [mon] - thời gian [thoigian]', 'contentKhaiGiang');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (3, 'Thông báo khai giảng', 'titleKhaiGiang');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (4, 'Lớp mới: [tenlop] - trong kì [kihoc] - đã được tạo ', 'contentTaoLop');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (5, 'Thông lớp mới', 'titleTaoLop');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (6, 'Lớp : [tenlop] - môn: [mon] - thời gian [thoigian] - đã bị hủy vì số lượng sinh viên đăng kí chưa đủ', 'contentHuyLop');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (7, 'Thông báo hủy lớp', 'titleHuyLop');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (8, 'Thông báo bài tập', 'titleBaiTap');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (9, 'Lớp : [tenlop] có bài tập: [baitap] , ngày bắt đầu: [ngaybatdau] hạn nộp bài [hannop]', 'contentBaiTap');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (10, 'Thông báo sửa bài tập', 'titleSuaBaiTap');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (11, 'Lớp : [tenlop] sửa bài tập: [baitap] thành bài tập: [baitapmoi] , ngày bắt đầu: [ngaybatdau] hạn nộp bài [hannop]', 'contentSuaBaiTap');
INSERT INTO smarttask_education.config (id, config_type, config_name) VALUES (12, '4,10', 'soLuongSV');

INSERT INTO smarttask_education.tin_tuc (id, title, content, time, status) VALUES (1, 'THÔNG BÁO VỀ HỌC PHÍ CHO GIAI ĐOẠN 2018-2020 ĐỐI VỚI SINH VIÊN ĐẠI HỌC CHÍNH QUY K63', '<p>

</p><p><strong>THÔNG BÁO VỀ HỌC PHÍ CHO GIAI ĐOẠN 2018-2020 ĐỐI VỚI SINH VIÊN ĐẠI HỌC CHÍNH QUY K63</strong></p><p>Trường Đại học Bách khoa Hà Nội công bố mức học phí cho giai đoạn 2018-2020 đối với sinh viên đại học hệ chính quy Khóa 63 theo 4 nhóm ngành đào tạo và mức học phí của các chương trình đào tạo đặc biệt trong bảng dưới đây.</p><p><em>Đơn vị: nghìn đồng/một TCHP</em></p>



<p>Học phí của các Chương trình đào tạo quốc tế do Trường ĐH Bách khoa cấp bằng nằm trong khoảng 40-50 triệu đồng/năm học 2018-2019 tùy theo chương trình, chi tiết tại <u><a target="_blank" rel="nofollow" href="http://sie.hust.edu.vn./">http://sie.hust.edu.vn.</a></u></p><p>Trên quan điểm coi sinh viên là chủ thể và trung tâm của các hoạt động trong Trường, Trường Đại học Bách khoa Hà Nội cam kết sử dụng nguồn thu học phí với hiệu quả cao nhất nhằm nâng cao chất lượng đào tạo, đồng thời thực hiện đầy đủ chế độ miễn, giảm học phí theo quy định của Nhà nước. Toàn bộ phần tăng thu học phí hàng năm sẽ được sử dụng để đầu tư tăng cường cơ sở vật chất, phòng học và phòng thí nghiệm, cải thiện các điều kiện và môi trường học tập cho sinh viên.</p>

<br><p></p><p><br></p>', '2018-10-13 00:24:51', 1);
INSERT INTO smarttask_education.tin_tuc (id, title, content, time, status) VALUES (3, 'Mức học phí đại học và chính sách miễn giảm và chính sách học bổng mới năm 2017 (K62)', '<p>

</p><div><strong>Mức học phí đại học đối với khóa nhập học năm 2017, chế độ miễn giảm học phí và chính sách học bổng mới</strong></div><div><br><strong>Mức học phí đối với khóa sinh viên nhập học năm 2017 (K62) </strong><br>Trường Đại học Bách khoa Hà Nội công bố mức học phí cho giai đoạn 2017-2020 đối với sinh viên đại học hệ chính quy sẽ nhập học năm 2017 (Khóa 62) theo 4 nhóm ngành đào tạo và mức học phí của các chương trình đào tạo đặc biệt trong bảng dưới đây.<br>Học phí được tính theo số Tín chỉ học phí (TCHP) của các học phần được sinh viên đăng ký học ở mỗi học kỳ. Đối với sinh viên học theo tiến độ bình thường, một năm học tương đương khoảng 50 TCHP đối với các ngành kỹ thuật-công nghệ, khoảng 40 TCHP đối với các ngành kinh tế - quản lý và ngôn ngữ Anh. Như vậy, mức học phí đối với các ngành đào tạo đại trà đều thấp hơn mức trần do Chính phủ quy định từ 20% đến 40%; mức học phí bình quân cũng nằm dưới mức dự kiến Nhà trường đã công bố trong đề án được Chính phủ phê duyệt (14,4 triệu đồng so với dự kiến 16 triệu đồng đối với năm học 2017-2018).</div>



Trên quan điểm coi sinh viên là chủ thể và trung tâm của mọi hoạt động, Trường Đại học Bách khoa Hà Nội cam kết sử dụng nguồn thu học phí với hiệu quả cao nhất nhằm mục đích nâng cao chất lượng đào tạo và cơ hội việc làm cho sinh viên. Toàn bộ phần tăng thu học phí hàng năm sẽ được sử dụng để đầu tư tăng cường cơ sở vật chất, phòng học và phòng thí nghiệm, cải thiện các điều kiện và môi trường học tập cho sinh viên.<br><br><strong>Chế độ </strong><strong>miễn giảm học phí</strong><br>Nhà trường thực hiện đầy đủ chế độ miễn, giảm học phí cho sinh viên thuộc diện được hưởng chính sách miễn, giảm học phí theo quy định của Nhà nước. Đồng thời, Nhà trường hỗ trợ toàn bộ phần chênh lệch giữa mức học phí đào tạo đại học đại trà của Trường với mức học phí được miễn, giảm theo quy định của Nhà nước.<br>&nbsp;<br>&nbsp;<br><strong>Chính sách học bổng mới</strong><br>Nhằm tạo điều kiện cho những sinh viên có hoàn cảnh khó khăn có cơ hội học tập tại Trường, từ năm học 2017-2018 Nhà trường áp dụng chính sách cấp <em>Học bổng hỗ trợ học tập</em>&nbsp;với hai mức: <em>Học bổng toàn phần</em>&nbsp;có trị giá tương đương 100% học phí chương trình đại trà và <em>Học bổng bán phần</em>&nbsp;ở mức 50% tương ứng. Đối với sinh viên khóa mới, điều kiện được xét cấp học bổng là gia đình có hoàn cảnh khó khăn và các em nằm trong tốp 30% thí sinh trúng tuyển có kết quả cao nhất theo từng nhóm ngành. Nhà trường sẽ có hướng dẫn chi tiết để các em đã đăng ký nguyện vọng xét tuyển vào Trường có thể nộp hồ sơ đề nghị cấp học bổng ngay từ tháng 5/2017.<br>Bên cạnh đó, Nhà trường xét tặng <em>Học bổng khuyến khích tài năng</em>&nbsp;cho những sinh viên đạt thành tích học tập xuất sắc nhất hằng năm. Đặc biệt, các tân sinh viên K62 đạt thành tích cao trong các kỳ thi Olympic quốc tế, kỳ thi chọn học sinh giỏi THPT cấp quốc gia, các kỳ thi khoa học kỹ thuật, thể thao, văn hóa, văn nghệ cấp quốc gia sẽ được xét tặng Học bổng khuyến khích tài năng để động viên, khuyến khích các em phát huy năng lực học tập và tài năng cá nhân tại Trường Đại học Bách khoa Hà Nội.

<br><p></p>', '2018-10-13 00:25:35', 1);
INSERT INTO smarttask_education.tin_tuc (id, title, content, time, status) VALUES (5, 'Quy định về chuẩn trình độ và chuẩn tiếng anh đầu ra đối với sinh viên đại học hệ chính quy K62', '<p>

</p><div><strong>QUY ĐỊNH</strong><br><strong>Về chuẩn </strong><strong>tiếng Anh</strong><strong>&nbsp;theo trình độ và chuẩn tiếng Anh đầu ra đối với sinh viên</strong><strong>&nbsp;đại học </strong><strong>hệ </strong><strong>chính quy</strong><strong>&nbsp;Khóa 62</strong></div><div><em>(Ban hành theo Quyết định số &nbsp;148 &nbsp;/QĐ-ĐHBK-ĐTĐH ngày 5 tháng 9 &nbsp;năm 2017 của Hiệu trưởng Trường Đại học Bách khoa Hà Nội)</em></div>&nbsp;<br><br><strong>Điều 1. Phạm vi và đối tượng áp dụng</strong><div><br>1. Văn bản này quy định về chuẩn tiếng Anh theo trình độ và chuẩn tiếng Anh đầu ra (gọi tắt là chuẩn tiếng Anh); phương thức thi, đánh giá và công nhận trình độ tiếng Anh đối với sinh viên đại học hệ chính quy Khóa 62 (sau đây gọi tắt là sinh viên) tại Trường Đại học Bách khoa Hà Nội (sau đây gọi tắt là Trường).<br>2. Quy định này không áp dụng cho sinh viên thuộc ngành Ngôn ngữ Anh, sinh viên thuộc chương trình ELITECH (có quy định riêng) và sinh viên có quốc tịch nước ngoài của Trường.<br>&nbsp;</div><strong>Điều 2.</strong>&nbsp;<strong>Học phần tiếng Anh cơ bản và Chứng chỉ tiếng Anh nội bộ</strong><br><br><div>1. Trường phân loại trình độ Tiếng Anh của sinh viên căn cứ trên kết quả thi môn Tiếng Anh trong kỳ thi THPT Quốc gia năm 2017 để tổ chức các lớp phần tiếng Anh cơ bản. Các sinh viên đạt yêu cầu về điểm thi hoặc có chứng chỉ tiếng Anh quốc tế được miễn học và được công nhận đã hoàn thành 2 học phần tiếng Anh cơ bản. Số còn lại phải học một học phần tiếng Anh cơ bản (FL1101) hoặc học cả 2 học phần tiếng Anh cơ bản (FL1100, FL1101).<br>2. Điểm thi kết thúc các học phần tiếng Anh cơ bản là điểm điều kiện để công nhận sinh viên hoàn thành chương trình tiếng Anh cơ bản. Điểm thi kết thúc các học phần tiếng Anh cơ bản không tính vào điểm trung bình học kỳ, điểm trung bình tích lũy và điểm trung bình tốt nghiệp. Sinh viên chưa hoàn thành chương trình tiếng Anh cơ bản không được phép đăng ký tham dự kỳ thi cấp chứng chỉ tiếng Anh nội bộ của Trường. Sinh viên không hoàn thành học phần nào thì phải đăng ký học lại học phần đó.<br>3. Sinh viên được tham dự các kỳ thi cấp chứng chỉ tiếng Anh nội bộ của Trường nhiều lần và được công nhận điểm thi cao nhất trong các lần thi. Kết quả thi có giá trị trong thời gian 2 năm kể từ ngày thi. Sinh viên đạt điểm TOEIC 500 trở lên được cấp Chứng chỉ tiếng Anh nội bộ của Trường. Chứng chỉ này có giá trị trong 2 năm kể từ ngày cấp.</div>

<br><p></p>', '2018-10-13 00:26:02', 1);
INSERT INTO smarttask_education.tin_tuc (id, title, content, time, status) VALUES (7, 'Quy định về chuẩn ngoại ngữ đầu ra đối với các chương trình đặc biệt, chất lượng cao', '<p>

</p><p><strong>Quy trình đào tạo theo tín chỉ</strong></p><p>Từ năm học 2007-2008 Trường ĐHBK Hà Nội áp dụng quy trình đào tạo theo học chế tín chỉ. Sinh viên được chủ động lập kế hoạch và đăng ký học tập, tích lũy từng phần kiến thức theo tiến độ phù hợp với điều kiện và năng lực của bản thân. Với sự hỗ trợ của cố vấn học tập, sinh viên chọn đăng ký môn học, lớp học thuận lợi nhất cho kế hoạch học tập của mình. Mọi quy trình thực hiện thuận lợi, dễ dàng qua mạng. Quy chế đào tạo theo tín chỉ của Trường ĐHBK Hà Nội có thể xem và tải về tại đây.</p><p><strong>Học chương trình song ngành</strong></p><p>Học chế tín chỉ và chương trình đào tạo mới của ĐHBK Hà Nội cho phép sinh viên tự chọn học thêm một ngành thứ hai thuộc cùng khối thi theo chương trình song ngành. Khi tốt nghiệp sinh viên được cấp một bằng ghi hai ngành học (ngành kép). Sinh viên có thể chọn ngành thứ hai trong cùng nhóm hoặc khác nhóm với ngành thứ nhất. Đặc biệt, các ngành thuộc nhóm 02 và 06 (Bảng 1) thường dễ kết hợp với các ngành khác nhất để tạo ra một chương trình thiết thực cho công việc sau này. Tùy theo mức độ khác giữa hai ngành học, thời gian toàn khóa có thể kéo dài hơn bình thường từ 1-3 học kỳ.</p><p><strong>Học chương trình song bằng</strong></p><p>Bên cạnh chương trình song ngành, sinh viên cũng có thể lựa chọn học thêm một ngành thứ hai (ngành học không cần cùng khối thi với ngành của chương trình thứ nhất) để được cấp thêm một bằng tốt nghiệp đại học (cử nhân hoặc kỹ sư). Tùy theo mức độ khác nhau giữa hai ngành học, thời gian toàn khoá có thể kéo dài từ 2-5 học kỳ so với bình thường. Tuy nhiên với học chế tín chỉ (cùng với học kỳ hè), những sinh viên giỏi có thể học vượt để rút ngắn thời gian học toàn khóa.</p><p>Bên cạnh chương trình đại trà, sinh viên còn có nhiều cơ hội để tham dự các chương trình đào tạo đặc biệt. Mỗi chương trình đào tạo đặc biệt được thiết kế với mục tiêu và đặc điểm riêng, phù hợp với khả năng và nguyện vọng khác nhau của sinh viên.<br><strong>Quy định về chuẩn tiếng anh đầu ra đối với sinh viên các chương trình đặc biệt và chất lượng cao:</strong></p>

<br><p></p>', '2018-10-13 00:27:00', 1);

INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('giangvien', '$2a$10$z9Bj3z0JcsAop0ce8Y7Oj.wEr5r8e7ovOlqUcq2KkHdX9P2J/bnC.', 'Trần Giảng Viên', 'giangvien@gmail.com', '0123456789', null, 'Hà Nội', 'Nam', '1989-02-07');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('giangviencntt', '$2a$10$U..od756pY7ImxhCdHxeMuDjYYq6a3sH6IFSYhlOFPxxpc7nSilea', 'Nguyễn Giảng Viên CNTT', 'giangviencntt@gmail.com', '0989978987', null, 'Hà Nội', 'Nữ', '1984-02-08');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('gv', '$2a$10$ull/SkWVnbq.ImNT.PUCvew2L2pX7UN63CM44pvpNXxWmhetI1O0a', 'Bùi Giảng Viên', 'gv@gmail.com', '0123456789', null, 'Hà Giang', 'Nam', '1954-06-09');

INSERT INTO smarttask_education.giao_vien (ma_giao_vien, hoc_ham, mo_ta, user_name, ma_nganh) VALUES ('GVIT-1', 'Tiến sĩ', '2 năm', 'giangvien', 'IT-CNTT');
INSERT INTO smarttask_education.giao_vien (ma_giao_vien, hoc_ham, mo_ta, user_name, ma_nganh) VALUES ('GVIT-2', 'Tiến sĩ', '4 năm', 'giangviencntt', 'IT-CNTT');
INSERT INTO smarttask_education.giao_vien (ma_giao_vien, hoc_ham, mo_ta, user_name, ma_nganh) VALUES ('GVIT-3', 'P.GS', '10 năm', 'gv', 'IT-CNTT');

INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD001', '$2a$10$4OMOcR9j8RZ/2zTu5kwuTu3oste7LGl5YE3qoOdV6GxtLEBcwhWmy', 'Nguyễn Ngọc Ánh', 'info@lacco.com.vn', '090 8 381 686', null, 'Hà Nội', 'Nữ', '1998-08-21');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD0017', '$2a$10$ZKm0l5bh36swZC0HqW.neuPpGNlhgvUVmXb47pn670kN6UlssNxEu', 'Nguyễn Thế Vinh', 'hnc_duchoa@yahoo.com.vn', '08 38 243 800', null, 'Đồng Nai', 'Nam', '1998-06-18');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD002', '$2a$10$FnJzYPyoEnDEt7hq.O5CHObvYNigxt3G4huLU2mITX3aRtIcnH2yi', 'Nguyễn Thị Anh', 'info@kllc.com.vn', '091 3 805 217', null, 'Hồ Chí Minh', 'Nữ', '1997-09-25');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD003', '$2a$10$uutG7Y7AsU8cG.pgzdEvkuhWV8IjI06SozOKYZMqPGbkDhnmMK1JW', 'Nguyễn Văn Bỉn', 'nhatthienhuonglogistics@gmail.com', '091 3 805 217', null, 'Hà Nội', 'Nam', '1998-06-30');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD004', '$2a$10$mnvfkUiVJ71d9KijaROhPOrmIaK2uL/xy5rPS2jt58M09OK/NIap2', 'Trần Văn Cường', 'vijaigroupsg@gmail.com', '5 143 032', null, 'Vũng Tàu', 'Nam', '1998-07-31');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD005', '$2a$10$Tofel.S8Z/TkpaZMzIO05exo75167h3cZ2FqSuHwyzJj6bOMb3tq6', 'Lõ Văn Cường', 'haphuc.8410@gmail.com', '5 143 032', null, 'Hà Nội', 'Nam', '1998-01-15');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD006', '$2a$10$bmuO8dyjQrp8RbW7PrKoF.uyz9PP3RqOdw1PPE89Gv6J/NxPTITU6', 'Lưu Minh Đức', 'thucnu@idstransport.net', '090 3 719 300', null, 'Thanh Hóa', 'Nam', '1998-02-16');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD009', '$2a$10$rAvLFWOGudaqzvN1puwFMOhpjNKPS8Ox308MU2ohxhaj6f8gQsp0G', 'Ng Thi Van Yen', 'Vanht@ppcvietnam.com', '0903812785', null, 'Vũng Tàu', 'Nữ', '1998-06-25');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD007', '$2a$10$siQNY/ixpRmHSkQcXA51sutXemeqRiK2We5C3ej6i88URSKrjZhPm', 'Nguyễn Minh Đức', 'info@nguyenngoc.vn', '090 3 719 300', null, 'Hồ Chí Minh', 'Nam', '1998-03-14');
INSERT INTO smarttask_education.user (user_name, user_password, full_name, user_email, user_phone, user_avatar, user_address, user_gender, user_dob) VALUES ('SVD008', '$2a$10$llfneOe.oC5SuN/K.CzovOQEVnw9yyWN9tkh0lluURNp/.HS4WSw2', 'Nguyễn Văn Bỉn', 'csoadong@gmail.com', '090 9 542 630', null, 'Hà Nội', 'Nam', '1998-06-25');

INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D001', 'SVD001', 'IT', '2018-09-23', 'IT-CNTT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D0017', 'SVD0017', 'IT', '2018-01-23', 'IT-KHMT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D002', 'SVD002', 'IT', '2018-09-30', 'IT-CNTT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D003', 'SVD003', 'IT', '2018-09-23', 'IT-KTMT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D004', 'SVD004', 'IT', '2018-08-23', 'IT-KTMT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D005', 'SVD005', 'IT', '2018-07-23', 'IT-KTMT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D006', 'SVD006', 'IT', '2018-08-23', 'IT-KTMT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D007', 'SVD007', 'IT', '2018-09-30', 'IT-KTMT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D008', 'SVD008', 'IT', '2018-08-23', 'IT-KTMT');
INSERT INTO smarttask_education.sinh_vien (ma_sinh_vien, user_name, ma_vien, ngay_nhap_hoc, ma_nganh) VALUES ('D009', 'SVD009', 'IT', '2018-09-23', 'IT-KTMT');

INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (1, 'SVD001', 'sv01');
INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (2, 'SVD002', 'sv01');
INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (3, 'SVD003', 'sv01');
INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (4, 'SVD004', 'sv01');
INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (5, 'SVD005', 'sv01');
INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (17, 'SVD0017', 'sv01');

INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (70, 'giangvien', 'gv01');
INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (71, 'giangviencntt', 'gv01');
INSERT INTO smarttask_education.phan_quyen (id, user_name, ma_quyen) VALUES (72, 'gv', 'gv01');

INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-1-1539365302541', 'Môn công nghệ thông tin', '2018-10-13 00:32:53', 5, '2018-10-31 00:00:00', '2019-05-01 00:00:00', '2,4,', '2,1,', '1', 'GVIT-1', 'IT-CNTT-1');
INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-10-1539365576097', 'Môn học', '2018-10-13 00:35:51', 5, '2017-10-31 00:00:00', '2017-05-01 00:00:00', '5,6,', '2,4,', '1', 'GVIT-2', 'IT-CNTT-10');
INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-10-1539368737566', 'Lớp mới', '2018-10-13 01:26:24', 5, '2018-10-31 00:00:00', '2019-05-01 00:00:00', '3,', '2,', '1', 'GVIT-3', 'IT-CNTT-10');
INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-2-1539365754205', 'Lớp mới', '2018-10-13 00:36:34', 5, '2018-10-31 00:00:00', '2019-05-01 00:00:00', '2,', '1,', '1', 'GVIT-3', 'IT-CNTT-2');
INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-5-1539368786912', 'Lớp mới', '2018-10-13 01:27:01', 5, '2018-10-31 00:00:00', '2019-05-01 00:00:00', '5,', '1,', '1', 'GVIT-1', 'IT-CNTT-5');
INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-6-1539368824400', 'Lớp mới', '2018-10-13 01:27:44', 5, '2018-10-31 00:00:00', '2019-05-01 00:00:00', '4,', '1,', '1', 'GVIT-2', 'IT-CNTT-6');
INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-8-1539369285696', 'Lớp mới', '2018-10-13 01:27:44', 5, '2018-10-31 00:00:00', '2019-05-01 00:00:00', '3,', '4,', '1', 'GVIT-1', 'IT-CNTT-8');
INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-9-1539368867349', 'Lóp mới', '2018-10-13 01:29:26', 5, '2018-10-31 00:00:00', '2019-05-01 00:00:00', '6,', '6,', '1', 'GVIT-1', 'IT-CNTT-9');
INSERT INTO smarttask_education.lop_hoc (ma_lop, mo_ta, ngay_tao, phong_hoc, ngay_bat_dau, ngay_ket_thuc, ngay_hoc, ca_hoc, trang_thai, ma_giao_vien, ma_mon_hoc) VALUES ('IT-CNTT-1-1539397543283', 'Lop moi', '2018-10-13 09:26:23', 5, '2018-10-31 00:00:00', '2019-05-01 00:00:00', '2,4,', '2,1,', '0', 'GVIT-3', 'IT-CNTT-2');

INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (2, 'IT-CNTT-10-1539365576097', 'D0017');
INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (3, 'IT-CNTT-2-1539365754205', 'D0017');
INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (4, 'IT-CNTT-5-1539368786912', 'D0017');
INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (5, 'IT-CNTT-9-1539368867349', 'D0017');
INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (6, 'IT-CNTT-8-1539369285696', 'D0017');
INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (7, 'IT-CNTT-1-1539365302541', 'D001');
INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (8, 'IT-CNTT-1-1539365302541', 'D002');
INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (9, 'IT-CNTT-1-1539365302541', 'D003');
INSERT INTO smarttask_education.lop_sinhvien (id, ma_lop, ma_sinh_vien) VALUES (11, 'IT-CNTT-1-1539365302541', 'D0017');

INSERT INTO smarttask_education.diem_sinhvien (id, diem_ly_thuyet, diem_thuc_hanh, diem_cuoi_ki, ma_sinh_vien, ma_giao_vien, ma_mon_hoc, ma_lop) VALUES (1, 8, 8, 8, 'D0017', 'GVIT-1', 'IT-CNTT-1', 'IT-CNTT-1-1539365302541');
INSERT INTO smarttask_education.diem_sinhvien (id, diem_ly_thuyet, diem_thuc_hanh, diem_cuoi_ki, ma_sinh_vien, ma_giao_vien, ma_mon_hoc, ma_lop) VALUES (2, 8, 8, 8, 'D0017', 'GVIT-2', 'IT-CNTT-10', 'IT-CNTT-10-1539365576097');
INSERT INTO smarttask_education.diem_sinhvien (id, diem_ly_thuyet, diem_thuc_hanh, diem_cuoi_ki, ma_sinh_vien, ma_giao_vien, ma_mon_hoc, ma_lop) VALUES (3, 8, 8, 8, 'D0017', 'GVIT-3', 'IT-CNTT-2', 'IT-CNTT-2-1539365754205');
INSERT INTO smarttask_education.diem_sinhvien (id, diem_ly_thuyet, diem_thuc_hanh, diem_cuoi_ki, ma_sinh_vien, ma_giao_vien, ma_mon_hoc, ma_lop) VALUES (4, 8, 8, 8, 'D0017', 'GVIT-3', 'IT-CNTT-9', 'IT-CNTT-9-1539368867349');

INSERT INTO smarttask_education.bai_dang (postid, content, file_name, file_real_name, user_name, status, time, lop_hoc) VALUES (1, 'Các em cùng thầy giải câu bài tập này', 'Screenshot_1.png', '1539372194301.png', 'giangvien', 1, '2018-10-13 02:23:14', 'IT-CNTT-1-1539365302541');

INSERT INTO smarttask_education.binh_luan (commentid, content, postid, user_name, time) VALUES (1, 'Bạn nào giải nhanh nhất, sớm nhất, thầy cho 50k nhé', 1, 'giangvien', '2018-10-13 02:24:28');
INSERT INTO smarttask_education.binh_luan (commentid, content, postid, user_name, time) VALUES (2, 'Em gửi bài cho thầy rồi đấy ạ, thầy check đi ạ :D', 1, 'SVD001', '2018-10-13 02:26:30');

INSERT INTO smarttask_education.likes (likeid, postid, user_name) VALUES (15, 1, 'giangvien');
INSERT INTO smarttask_education.likes (likeid, postid, user_name) VALUES (16, 1, 'SVD001');
