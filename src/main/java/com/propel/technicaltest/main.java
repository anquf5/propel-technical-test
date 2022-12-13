package com.propel.technicaltest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        IOHandler io = new IOHandler();
       JSONHandler jh = new JSONHandler(io);

        System.out.println("list: " + jh.listAllUsers());
        User user = new User("aaaa","bbbb","cccc","dddd");
        jh.addUser(user);
        System.out.println("list2: " + jh.listAllUsers());

    }
}
