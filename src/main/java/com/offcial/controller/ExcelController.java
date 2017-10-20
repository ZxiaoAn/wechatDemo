package com.offcial.controller;

import com.offcial.dao.HouseMessageDao;
import com.offcial.dto.HouseMessageDto;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * 通用excel表格
 *
 * @author Zza
 * @date 2017-10-18 16:50
 */
@Controller
@RequestMapping("/")
public class ExcelController {

    @Autowired
    private HouseMessageDao houseMessageDao;

    @RequestMapping("/Export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse resp, @RequestParam("type") String type)
            throws UnsupportedEncodingException {
        HSSFWorkbook wb = new HSSFWorkbook();
        request.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/x-download");

        String fileName = type + ".xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        HSSFSheet sheet = wb.createSheet("微信一周信息汇总记录");
        sheet.setDefaultRowHeight((short) (2 * 256));
        sheet.setColumnWidth(0, 50 * 160);
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        HSSFRow row = sheet.createRow(0);
        sheet.createRow(1);
        sheet.createRow(2);
        sheet.createRow(3);
        sheet.createRow(4);
        HSSFCellStyle style = wb.createCellStyle();

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("编号 ");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("信息");
        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("时间");
        cell = row.createCell(4);
        cell.setCellStyle(style);
        // 判断要下载的类型文件
        String timeS = LocalDate.now().minusWeeks(1).toString();
        String timeE = LocalDate.now().toString();
        //房屋
        if ("house".equals(type)) {
            List<HouseMessageDto> houseInfo = houseMessageDao.findAllByTimeIsBetweenOrderByTimeDesc(timeS, timeE);
            for (int i = 0; i < houseInfo.size(); i++) {
                HSSFRow row1 = sheet.createRow(i + 1);
                HouseMessageDto hmd = houseInfo.get(i);
                row1.createCell(0).setCellValue(i + 1);
                // 用户
                row1.createCell(1).setCellValue(hmd.getUser_name());
                // 信息
                row1.createCell(2).setCellValue(hmd.getInfo());
                // 时间
                row1.createCell(3).setCellValue(hmd.getInfo_time());
            }
        }
        try {
            OutputStream out = resp.getOutputStream();
            wb.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
