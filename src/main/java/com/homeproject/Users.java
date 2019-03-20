package com.homeproject;

import com.homeproject.processing.Parser;
import com.homeproject.worker.HttpWorker;

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

            StringBuffer httpResponse = new HttpWorker().getResponse();

            if (httpResponse != null) {
                 users.add(parser.getUserFromJSON(httpResponse,i));// добавляем из randomuser/api
            } else {
                 users.add(parser.getUserFromLocalDatabase(i)); // добавляем из локальной базы пользователя
            }
        }
    }

    public List<User> get(){
        return users;
    }
}
