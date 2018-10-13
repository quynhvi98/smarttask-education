package com.fpt.services.phonghoc.impl;

import com.fpt.entity.LopHoc;
import com.fpt.entity.PhongHoc;
import com.fpt.repositories.phonghoc.PhongHocDao;
import com.fpt.services.phonghoc.PhongHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhongHocServiceImpl implements PhongHocService {
    @Autowired
    private PhongHocDao phongHocDao;

    @Override
    public List<PhongHoc> getAll() {
        return (List<PhongHoc>) phongHocDao.findAll();
    }

    @Override
    public List<PhongHoc> getAvailableClass(String[] ngayHoc, String[] caHoc) {
        List<PhongHoc> lstPhongHoc = (List<PhongHoc>) phongHocDao.findAll();
        List<PhongHoc> result = new ArrayList<>();
        int checkExist = 0;
        for (int i = 0; i < lstPhongHoc.size(); i++) {
            if (lstPhongHoc.get(i).getLopHocs().size() > 0) {
                for (LopHoc lopHoc : lstPhongHoc.get(i).getLopHocs()) {
                    String[] ngayHocOfLop = lopHoc.getNgayHoc().split(",");
                    String[] caHocOfLop = lopHoc.getCaHoc().split(",");
                    for (int j = 0; j < ngayHoc.length; j++) {
                        for (int z = 0; z < ngayHocOfLop.length; z++) {
                            if (ngayHoc[j].equals(ngayHocOfLop[z]) && caHoc[j].equals(caHocOfLop[z])) {
                                checkExist++;
                            }
                        }
                    }
                    if (checkExist == 0) {
                        result.add(lstPhongHoc.get(i)) ;
                    }else {
                        checkExist = 0;
                    }
                }
            }else {
                result.add(lstPhongHoc.get(i)) ;
            }
        }
        result.forEach(phongHoc -> phongHoc.setLopHocs(null));
        return result;
    }

    @Override
    public PhongHoc findById(String phongHoc) {
        return phongHocDao.findOne(Integer.parseInt(phongHoc));
    }
}
