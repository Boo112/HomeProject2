package com.homeproject;

import com.homeproject.worker.HttpWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Users {

    private Random r=new Random();
    private List<User> users = new ArrayList<User>();

    int count= 1+r.nextInt(30);

    public void populate() {

        for(int i=0;i<count;i++) {

            StringBuffer httpResponse = new HttpWorker().getResponse();

            if (httpResponse != null) {
                 users.add(new Parser().getUserFromJSON(httpResponse));// добавляем из randomuser/api
            } else {
                 users.add(new Parser().getUserFromLocalDatabase(i)); // добавляем из локальной базы пользователя
            }
        }
    }

    public List<User> get(){
        return users;
    }
}
