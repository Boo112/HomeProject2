package com.homeproject.processing;

import java.util.Random;

public class GenerateInn {

    Random r = new Random();
    int[] coefficientN2 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
    int[] coefficientN1 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};


    // Генерация валидного ИНН
    public String getInn() {
        String inn;

        //ИНН= Код субьекта(1-2)-Номер налоговой инспеции(2 цифры)-4 -
        // 10 - номер налоговой записи налогоплательщика-11 и 12 - контрольные цифры
        // Код региона Москва
        String subject = "77";

        // Налоговая инспецция в Москве их 51
        String numberTaxInspection = Integer.toString(1 + r.nextInt(51));
        if (numberTaxInspection.length() < 2) {
            numberTaxInspection = '0' + numberTaxInspection;
        }

        // Номер налоговой записи генерируются в произвольном порядке от 000000 до 999999
        String numberTaxRecord = "";
        for (int i = 0; i < 6; i++) {
            int k = r.nextInt(9);
            numberTaxRecord = numberTaxRecord + k;
        }

        //Промежуточный ИНН без двух последних цифр
        inn = subject + numberTaxInspection + numberTaxRecord;

        // К промежуточному ИНН добавляем предпоследнее число
        inn = inn + getDigit(inn,coefficientN2);

        // получаем полный валидный ИНН, добавляя последнее число
        return inn + getDigit(inn,coefficientN1);
    }

    //Получение последних цифр ИНН
    private int getDigit(String inn,int[] coefficient ){

        int lastDigit;
        int sumOfProductDigit = 0;
        int digitsInn;
        int innLength=inn.length();

        for (int i = 0; i < innLength; i++) {
            digitsInn = Character.digit(inn.toCharArray()[i], 10);
            sumOfProductDigit = sumOfProductDigit + digitsInn * coefficient[i];
        }
        lastDigit = sumOfProductDigit % 11;

        if (lastDigit == 10) {
            lastDigit = 0;
        }
        return lastDigit;
    }
}
