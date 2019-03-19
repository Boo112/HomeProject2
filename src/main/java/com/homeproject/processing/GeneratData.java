package com.homeproject.processing;

import com.homeproject.helper.PathToFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GeneratData {

    Random r = new Random();

    // метод для получения имен из файла(с учетом случаной выборки из мужских и женских имен)
    public String[] getNamesFromFile(String filename1, String filename2) {

        String[] shuffledArray = new String[60];

        try {
            //Для чтения мужского имени
            BufferedReader reader = new BufferedReader(new FileReader(filename1));
            //Для чтения Ж имени
            BufferedReader reader1 = new BufferedReader(new FileReader(filename2));
            //Для чтения М фамилий
            String line1, line2;

            List<String> lines = new ArrayList<String>();

            while ((line1 = reader.readLine()) != null) {
                lines.add(line1);

            }
            while ((line2 = reader1.readLine()) != null) {
                lines.add(line2);
            }
            //Смешиваем женские имена и мужские в один массив

            Collections.shuffle(lines);
            shuffledArray = lines.toArray(new String[0]); // Формируем массив Имен в произвольном порядке
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shuffledArray;
    }

    // Генерация индекса случайным образом от 100000 до 2000000
    public int getIndex() {

        return 100000 + r.nextInt(100000);
    }

    // Генерация даты рождения
    public Date getDataBirth() {

        Date dt;
        long ms;

        ms = -946771200000L + (Math.abs(r.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        dt = new Date(ms);

        return dt;
    }

    // Генерация валидного ИНН
    public String getInn() {
        String inn;

        int[] koeff1 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int[] koeff2 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};

        //ИНН= Код субьекта(1-2)-Номер налоговой инспеции(2 цифры)-4 - 10 - номер налоговой записи налогоплательщика-11 и 12 - контрольные цифры
        // Код региона Москва
        String subject = "77";

        // Налоговая инспецция В москве их 51
        String numberNI = Integer.toString(1 + r.nextInt(51));
        if (numberNI.length() < 2) {
            numberNI = '0' + numberNI; // 5=05
        }

        // Номер налоговой записи генерируются в произвольном порядке от 000000 до 999999
        String numberNR = "";
        for (int i = 0; i < 6; i++) {
            int k = r.nextInt(9);
            numberNR = numberNR + k;
        }

        //Промежуточный ИНН без двух последних цифр
        inn = subject + numberNI + numberNR;

        int n11; // предпоследнее число инн
        int n12; // последнее

        // разбиение ИНН на цифры для получение предпоследней цифры n11
        char[] myChar = inn.toCharArray();
        int arr = 0;

        for (int i = 0; i < inn.length(); i++) {

            int t = Character.digit(myChar[i], 10);

            arr = arr + t * koeff1[i];
        }

        n11 = arr % 11;
        //Если остаток от деления равен 10 то обнуляем его
        if (n11 == 10) {
            n11 = 0;
        }

        inn = inn + n11;

        // разбиение числа на цифры для получение последней цифры n12
        char[] myChar1 = inn.toCharArray();
        int arr1 = 0;

        for (int i = 0; i < inn.length(); i++) {

            int tt = Character.digit(myChar1[i], 10);

            arr1 = arr1 + tt * koeff2[i];
        }

        n12 = arr1 % 11;

        //Если остаток от деления равен 10 то обнуляем его
        if (n12 == 10) {
            n12 = 0;
        }

        // Получаем корректный ИНН
        inn = inn + n12;


        return inn;
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
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new PathToFiles().fileNameMan));


            String line1;
            String[] array = new String[30];
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



