package com.homeproject;


import com.homeproject.helper.PathToFiles;
import com.homeproject.helper.StorageUsers;
import com.homeproject.worker.ExcelWorker;
import com.homeproject.worker.PdfWorker;

public class Main {

    public static void main(String[] args) {

        PathToFiles fPath=new PathToFiles();

        StorageUsers storageUsers = new StorageUsers();
        ExcelWorker excelWorker =new ExcelWorker();
        storageUsers.populate();

        excelWorker.createExcelFile(fPath.fileNameXls);

        for (User user : storageUsers.get()) {

            excelWorker.reWriteExcel(fPath.fileNameXls,user);// добавляем юзера в эксель
        }

        new PdfWorker().writePDF(fPath.filenamePdf, storageUsers.get()); // создаем pdf
    }
}