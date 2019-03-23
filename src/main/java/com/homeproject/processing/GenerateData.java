package com.homeproject.processing;

import com.homeproject.helper.DateHelper;
import com.homeproject.helper.PathToFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class GenerateData {

    Random r = new Random();
    DateHelper dateHelper=new DateHelper();

    // Генерация индекса случайным образом от 100000 до 2000000
    public int getIndex() {
        return 100000 + r.nextInt(100000);
    }

    // Генерация даты рождения
    public LocalDate getDataBirth() {
        int year=1930+r.nextInt(85);
        int month=1+r.nextInt(12);
        int day=1+r.nextInt(30);

        if(month==2){
            if(!dateHelper.checkLeapYear(year,month,day)) {
                day = 1 + r.nextInt(28);
            }else  day = 1 + r.nextInt(29);
        }

        return LocalDate.of(year, month, day);
    }

    // Считаем возраст
    public int getAge(LocalDate dt) {
        return Period.between(dt,LocalDate.now()).getYears();
    }

    public String[] getNameFromFile(String fileName) {
        String name[] = new String[30];

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String> lines = new ArrayList();

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            Collections.shuffle(lines);
            name = lines.toArray(new String[0]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    // Получаем пол юзера
    public boolean getGender(String name) {

        boolean itsMan = false;
        try(BufferedReader reader = new BufferedReader(new FileReader(new PathToFiles().fileNameMan))) {

            String[] arrayNames;
            String line;
            List<String> lines = new ArrayList();

            // получаем мужские имена из файла
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            arrayNames = lines.toArray(new String[0]);

            // Проверяем содержится ли имя в файле с мужскими именами , если да то считаем что это М
            int count=arrayNames.length;
            for (int i = 0; i < count; i++) {
                if (arrayNames[i].equals(name)) {
                    itsMan = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itsMan;
    }


}



