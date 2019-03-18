package com.homeproject;

public class Main {

    public static void main(String[] args) {

        PathToFiles fPath=new PathToFiles();

        Users users = new Users();
        ExcelWorking excelWorking=new ExcelWorking();
        users.populate();

        excelWorking.createExcelFile(fPath.fileNameXls);

        for (User user : users.get()) {

            excelWorking.reWriteExcel(
                    fPath.fileNameXls,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getAge(),
                    user.getGender(),
                    user.getDateOfBorn(),
                    user.getInn(),
                    user.getIndex(),
                    user.getCountry(),
                    user.getState(),
                    user.getCity(),
                    user.getStreet(),
                    user.getHouse(),
                    user.getFlat());

        }

        new PdfWorker().writePDF(fPath.filenamePdf, users.get()); // создаем pdf
    }
}