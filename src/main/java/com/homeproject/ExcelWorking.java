package com.homeproject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;

public class ExcelWorking {


    public void createExcelFile(String filename) {
        try {
            File file = new File(filename);
            FileOutputStream fileOut = new FileOutputStream(file);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Данные пользователей");

            // Создаем шапку документа эксель
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Имя");
            rowhead.createCell(1).setCellValue("Фамилия");
            rowhead.createCell(2).setCellValue("Отчество");
            rowhead.createCell(3).setCellValue("Возраст");
            rowhead.createCell(4).setCellValue("Пол");
            rowhead.createCell(5).setCellValue("Дата рождения");
            rowhead.createCell(6).setCellValue("ИНН");
            rowhead.createCell(7).setCellValue("Почтовый индекс");
            rowhead.createCell(8).setCellValue("Страна");
            rowhead.createCell(9).setCellValue("Область");
            rowhead.createCell(10).setCellValue("Город");
            rowhead.createCell(11).setCellValue("Улица");
            rowhead.createCell(12).setCellValue("Дом");
            rowhead.createCell(13).setCellValue("Квартира");

            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("\n" + "Файл создан. " + "Путь: " + file.getAbsolutePath());

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void reWriteExcel(String fileName, String firstName, String lastName, String patronymic,
                             String age, String gender, String dateOfBorn, String inn, String index,
                             String country, String state, String city, String street, String house,
                             String flat) {

        File myFile = new File(fileName);
        try (FileInputStream inputStream = new FileInputStream(myFile)){

            HSSFWorkbook workbook_ED = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook_ED.getSheetAt(0);

            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            //Дописываем данные
            row.createCell(0).setCellValue(firstName);
            row.createCell(1).setCellValue(lastName);
            row.createCell(2).setCellValue(patronymic);
            row.createCell(4).setCellValue(gender);
            row.createCell(3).setCellValue(age);
            row.createCell(5).setCellValue(dateOfBorn);
            row.createCell(6).setCellValue(inn);
            row.createCell(7).setCellValue(index);
            row.createCell(8).setCellValue(country);
            row.createCell(9).setCellValue(state);
            row.createCell(10).setCellValue(city);
            row.createCell(11).setCellValue(street);
            row.createCell(12).setCellValue(house);
            row.createCell(13).setCellValue(flat);

            for (int i = 0; i < row.getLastCellNum() - 1; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream fileOut = new FileOutputStream(myFile);
            workbook_ED.write(fileOut);

            fileOut.close();
            workbook_ED.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
