package com.homeproject;

import com.homeproject.processing.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Users {

    private Random r=new Random();
    private List<User> users = new ArrayList<User>();
    Parser parser=new Parser();

    int count= 1+r.nextInt(30);

    public void populate() {

        for(int i=0;i<count;i++) {
                 users.add(parser.getUserFromLocalDatabase(i)); // добавляем из локальной базы пользователя
        }
    }

    public List<User> get(){
        return users;
    }
}
