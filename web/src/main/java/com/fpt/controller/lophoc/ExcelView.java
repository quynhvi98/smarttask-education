package com.fpt.controller.lophoc;

import com.fpt.entity.SinhVien;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {

    @Override
    public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub

        String maLop = (String) model.get("maLop");
        String value="attachment; filename=\""+maLop+".xlsx"+"\"";
        // change the file name
        response.setHeader("Content-Disposition", value);

        @SuppressWarnings("unchecked")
        List<SinhVien> sinhViens= (List<SinhVien>) model.get("sinhViens");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Users Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("STT");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Mã SV");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Tên SV");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Email");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Điểm thực hành");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Điểm lý thuyết");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("Điểm cuối kì");
        header.getCell(6).setCellStyle(style);
        int rowCount = 1;
        int stt=0;

        for(SinhVien  sv: sinhViens){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(stt);
            userRow.createCell(1).setCellValue(sv.getMaSinhVien());
            userRow.createCell(2).setCellValue(sv.getUser().getFullName());
            userRow.createCell(3).setCellValue(sv.getUser().getUserEmail());
            stt++;
        }

    }
}