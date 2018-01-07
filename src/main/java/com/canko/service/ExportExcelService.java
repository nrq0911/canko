package com.canko.service;

import com.canko.common.ExcelData;

import javax.servlet.http.HttpServletResponse;

public interface ExportExcelService {

    void exportOrderList(HttpServletResponse response, String fileName, String startTime, String endTime) throws Exception;

}
