package com.homeproject.worker;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Row;
import com.homeproject.User;
import com.homeproject.helper.PathToFiles;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PdfWorker {

    public void writePDF(String filename, List<User> users) {

        PDPage myPage = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        File pdf = new File(filename);

        try(  PDDocument mainDocument = new PDDocument())
        {

        float margin = 10;
        float yStartNewPage = myPage.getMediaBox().getHeight() - (2 * margin);
        float tableWidth = myPage.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float bottomMargin = 70;
        float yPosition = 10;

        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, mainDocument, myPage, true, drawContent);

        PDFont font3 = PDType0Font.load(mainDocument, new File(new PathToFiles().fileFont));

        Row<PDPage> headerRow = table.createRow(5);
        headerRow.createCell(3, "№").setFont(font3);
        headerRow.createCell(7, "Имя").setFont(font3);
        headerRow.createCell(7, "Фамилия").setFont(font3);
        headerRow.createCell(10, "Отчество").setFont(font3);
        headerRow.createCell(4, "Возраст").setFont(font3);
        headerRow.createCell(4, "Пол").setFont(font3);
        headerRow.createCell(7, "Дата рождения").setFont(font3);
        headerRow.createCell(8, "ИНН").setFont(font3);
        headerRow.createCell(7, "Почтовый индекс").setFont(font3);
        headerRow.createCell(7, "Страна").setFont(font3);
        headerRow.createCell(7, "Область").setFont(font3);
        headerRow.createCell(7, "Город").setFont(font3);
        headerRow.createCell(7, "Улица").setFont(font3);
        headerRow.createCell(4, "Дом").setFont(font3);
        headerRow.createCell(5, "Квартира").setFont(font3);
        table.addHeaderRow(headerRow);

        int i = 1;
        for (User us : users) {

            Row<PDPage> row = table.createRow(5);

            row.createCell(3, Integer.toString(i++)).setFont(font3);
            row.createCell(7, us.getFirstName()).setFont(font3);
            row.createCell(7, us.getLastName()).setFont(font3);
            row.createCell(10, us.getPatronymic()).setFont(font3);
            row.createCell(4, us.getAge()).setFont(font3);
            row.createCell(4, us.getGender()).setFont(font3);
            row.createCell(7, us.getDateOfBorn()).setFont(font3);
            row.createCell(8, us.getInn()).setFont(font3);
            row.createCell(7, us.getIndex()).setFont(font3);
            row.createCell(7, us.getCountry()).setFont(font3);
            row.createCell(7, us.getState()).setFont(font3);
            row.createCell(7, us.getCity()).setFont(font3);
            row.createCell(7, us.getStreet()).setFont(font3);
            row.createCell(4, us.getHouse()).setFont(font3);
            row.createCell(5, us.getFlat()).setFont(font3);

        }
        table.draw();

            mainDocument.addPage(myPage);
            mainDocument.save(pdf);
            mainDocument.close();

            System.out.println("\n" + "Файл создан. " + "Путь: " + pdf.getAbsolutePath());

        } catch (IOException e) {

            System.out.println(e);
        }

    }
}
