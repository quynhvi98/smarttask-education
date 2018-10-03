package com.fpt.services.user.impl;

import com.fpt.entity.GiaoVien;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.repositories.giangvien.GiangVienDao;
import com.fpt.repositories.sinhvien.SinhVienDao;
import com.fpt.repositories.user.UserDao;
import com.fpt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    SinhVienDao sinhVienDao;
    @Autowired
    GiangVienDao giangVienDao;

    @Transactional
    @Override
    public void createStudentAccount(User user, SinhVien sinhVien) {
        User persist = userDao.save(user);
        sinhVien.setUser(persist);
        sinhVienDao.save(sinhVien);
    }

    @Override
    public void createAdminAccount(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void createTeacherAccount(User user, GiaoVien giaoVien) {
        User persist = userDao.save(user);
        giaoVien.setUser(persist);
        giangVienDao.save(giaoVien);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public void saveLstSV(List<User> lstUser) {
        lstUser.forEach(user -> userDao.save(user));
    }

    @Override
    public List<User> search(String userName, String maThanhVien,String fullName) {
        return userDao.search(userName,maThanhVien,fullName);
    }
}
