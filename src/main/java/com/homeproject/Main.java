package com.homeproject;


import com.homeproject.helper.PathToFiles;
import com.homeproject.worker.ExcelWorker;
import com.homeproject.worker.PdfWorker;

public class Main {

    public static void main(String[] args) {

        PathToFiles fPath=new PathToFiles();

        Users users = new Users();
        ExcelWorker excelWorker =new ExcelWorker();
        users.populate();

        excelWorker.createExcelFile(fPath.fileNameXls);

        for (User user : users.get()) {

            excelWorker.reWriteExcel(fPath.fileNameXls,user);// добавляем юзера в эксель
        }

        new PdfWorker().writePDF(fPath.filenamePdf, users.get()); // создаем pdf
    }
}