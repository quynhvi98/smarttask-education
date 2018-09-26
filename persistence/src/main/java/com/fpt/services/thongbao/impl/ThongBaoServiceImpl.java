package com.fpt.services.thongbao.impl;

import com.fpt.entity.SinhVien;
import com.fpt.entity.ThongBao;
import com.fpt.repositories.sinhvien.SinhVienDao;
import com.fpt.repositories.thongbao.ThongBaoDao;
import com.fpt.services.thongbao.ThongBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongBaoServiceImpl implements ThongBaoService {
    @Autowired
    private ThongBaoDao thongBaoDao;


    @Override
    public ThongBao themThongBao(ThongBao thongBao) {
        return thongBaoDao.save(thongBao);
    }

    @Override
    public List<ThongBao> listThongBaoGv(String username) {
        return thongBaoDao.listThongBaoGv(username);
    }

    @Override
    public List<ThongBao> listThongBaoSV(String username) {
        return thongBaoDao.listThongBaoSV(username);
    }

    @Override
    public ThongBao findById(String id) {
        return thongBaoDao.thongBaoById(id);
    }

    @Override
    public List<ThongBao> thongBaoMoiNhatGV(String ma_gv) {
        return thongBaoDao.thongBaoMoiNhatGV(ma_gv);
    }

    @Override
    public List<ThongBao> thongBaoMoiNhatSV(String ma_sv) {
        return thongBaoDao.thongBaoMoiNhatSV(ma_sv);
    }

    @Override
    public int soLuongTbChuaXemGV(String ma_gv) {
        return thongBaoDao.soLuongTbChuaXemGV(ma_gv).size();
    }

    @Override
    public int soLuongTbChuaXemSV(String ma_sv) {
        return thongBaoDao.soLuongTbChuaXemSV(ma_sv).size();
    }
}
