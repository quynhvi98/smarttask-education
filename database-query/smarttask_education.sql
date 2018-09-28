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
DROP TABLE IF EXISTS loai_bai_viet;
DROP TABLE IF EXISTS bai_viet;
DROP TABLE IF EXISTS bai_tap;
DROP TABLE IF EXISTS nhom;
DROP TABLE IF EXISTS chi_tiet_nhom;
DROP TABLE IF EXISTS de_tai;
DROP TABLE IF EXISTS chi_tiet_nhom_de_tai;
DROP TABLE IF EXISTS nop_de_tai;
DROP TABLE IF EXISTS thong_bao;
DROP TABLE IF EXISTS phe_duyet;
DROP TABLE IF EXISTS bai_dang;
DROP TABLE IF EXISTS binh_luan;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS diem_sinhvien;


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
  PRIMARY KEY (ma_sinh_vien),
  CONSTRAINT FK_ma_vien_sv FOREIGN KEY (ma_vien) REFERENCES khoa_vien(ma_vien),
  CONSTRAINT FK_user_name_sv FOREIGN KEY (user_name) REFERENCES user(user_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS lop_sinhvien (
  id BIGINT NOT NULL auto_increment,
  ma_lop VARCHAR(255),
  ma_sinh_vien VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_lop FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop),
  CONSTRAINT FK_ma_sinh_vien FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS loai_bai_viet (
  ma_loai VARCHAR(255),
  ten_loai VARCHAR(255),
  PRIMARY KEY (ma_loai)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS bai_viet(
  ma_bai_viet VARCHAR(255),
  ten_bai_viet VARCHAR(255),
  noi_dung VARCHAR(255),
  trang_thai varchar (255),
  ngay_tao datetime,
  ma_lop VARCHAR(255),
  ma_loai VARCHAR(255),
  PRIMARY KEY (ma_bai_viet),
  CONSTRAINT FK_ma_lop_bv FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop),
  CONSTRAINT FK_ma_loai FOREIGN KEY (ma_loai) REFERENCES loai_bai_viet(ma_loai)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS bai_tap(
  id BIGINT NOT NULL auto_increment,
  ngay_tao datetime,
  path VARCHAR(255),
  ma_sinh_vien VARCHAR(255),
  ma_bai_viet VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_sinh_vien_bt FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien),
  CONSTRAINT FK_ma_bai_viet FOREIGN KEY (ma_bai_viet) REFERENCES bai_viet(ma_bai_viet)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS nhom(
  ma_nhom VARCHAR(255),
  ten_nhom VARCHAR(255),
  ngay_tao_nhom datetime,
  ma_lop VARCHAR(255),
  trang_thai varchar (255),
  PRIMARY KEY (ma_nhom),
  CONSTRAINT FK_ma_lop_nhom FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS chi_tiet_nhom(
  id BIGINT NOT NULL auto_increment,
  ma_nhom VARCHAR(255),
  ma_sinh_vien VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_nhom FOREIGN KEY (ma_nhom) REFERENCES nhom(ma_nhom),
  CONSTRAINT FK_ma_sinh_vien_nhom FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS de_tai(
  ma_de_tai VARCHAR(255),
  ten_de_tai VARCHAR(255),
  PRIMARY KEY (ma_de_tai)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS chi_tiet_nhom_de_tai(
  id BIGINT NOT NULL auto_increment,
  ma_nhom VARCHAR(255),
  ma_de_tai VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_nhom_de_tai FOREIGN KEY (ma_nhom) REFERENCES nhom(ma_nhom),
  CONSTRAINT FK_ma_de_tai FOREIGN KEY (ma_de_tai) REFERENCES de_tai(ma_de_tai)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS nop_de_tai(
  id BIGINT NOT NULL auto_increment,
  ma_de_tai VARCHAR(255),
  ngay_nop datetime,
  path VARCHAR(255),
  ma_nhom VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_nhom_nop_de_tai FOREIGN KEY (ma_nhom) REFERENCES nhom(ma_nhom),
   CONSTRAINT FK_ma_nop_de_tai FOREIGN KEY (ma_de_tai) REFERENCES de_tai(ma_de_tai)
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
  ma_nhom VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_giao_vien_thong_bao FOREIGN KEY (ma_giao_vien) REFERENCES giao_vien(ma_giao_vien),
  CONSTRAINT FK_ma_sinh_vien_thong_bao FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien),
  CONSTRAINT FK_ma_lop_hoc_thong_bao FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop),
  CONSTRAINT FK_ma_nhom_thong_bao FOREIGN KEY (ma_nhom) REFERENCES nhom(ma_nhom)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS phe_duyet (
  id BIGINT NOT NULL auto_increment,
  status VARCHAR(255),
  ma_giao_vien VARCHAR(255),
  ma_sinh_vien VARCHAR(255),
  ma_lop VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT FK_ma_giao_vien_phe_duyet FOREIGN KEY (ma_giao_vien) REFERENCES giao_vien(ma_giao_vien),
  CONSTRAINT FK_ma_sinh_vien_phe_duyet FOREIGN KEY (ma_sinh_vien) REFERENCES sinh_vien(ma_sinh_vien),
  CONSTRAINT FK_ma_lop_hoc_phe_duyet FOREIGN KEY (ma_lop) REFERENCES lop_hoc(ma_lop)
) engine=InnoDB;


CREATE TABLE IF NOT EXISTS bai_dang
(
    postid bigint PRIMARY KEY AUTO_INCREMENT,
    content longtext NOT NULL,
    file varchar(255),
    user_name varchar(255),
    status int DEFAULT 1 NOT NULL,
    time datetime,
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

