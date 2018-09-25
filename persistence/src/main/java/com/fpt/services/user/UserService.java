package com.fpt.services.user;

import com.fpt.entity.GiaoVien;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;

import java.util.List;

public interface UserService {
    void createStudentAccount(User user, SinhVien sinhVien);
    void createAdminAccount(User user);
    void createTeacherAccount(User user, GiaoVien giaoVien);
    User findUserByUserName(String username);
    List<User> findAll();

    void update(User user);

    void saveLstSV(List<User> lstUser);
}
