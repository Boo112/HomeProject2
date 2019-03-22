package com.homeproject.processing;

import com.homeproject.helper.PathToFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateData {

    Random r = new Random();

    // Генерация индекса случайным образом от 100000 до 2000000
    public int getIndex() {
        return 100000 + r.nextInt(100000);
    }

    // Генерация даты рождения
    public Date getDataBirth() {
        long ms = -946771200000L + (Math.abs(r.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

    // Считаем возраст
    public int getAge(Date dt) {
        Date dnow = new Date();

        String pattern = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String l = simpleDateFormat.format(dt);
        String lnow = simpleDateFormat.format(dnow);

        int age = Integer.parseInt(lnow) - Integer.parseInt(l);

        return age;
    }

    public String[] getNameFromFile(String fileName) {
        String name[] = new String[30];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            List<String> lines = new ArrayList<String>();

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

        boolean a = false;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new PathToFiles().fileNameMan));

            String line1;
            String[] array;
            List<String> lines = new ArrayList<String>();

            // получаем мужские имена из файла
            while ((line1 = reader.readLine()) != null) {
                lines.add(line1);
            }
            array = lines.toArray(new String[0]);

            // Проверяем содержится ли имя в файле с мужскими именами , если да то считаем что это М
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(name)) {
                    a = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

}



