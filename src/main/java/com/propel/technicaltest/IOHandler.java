package com.propel.technicaltest;

import com.google.gson.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class IOHandler{
    private static final String filename = System.getProperty("user.dir") + "\\src\\main\\json\\test.json";

    public IOHandler() {}

    public List<User> IOReader() throws IOException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<User> users = Arrays.asList(gson.fromJson(br, User[].class));
        br.close();
        return users;
    }

    public void IOWriter(List<User> users) throws IOException{
        Gson gson = new Gson();
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        System.out.println(gson.toJson(users));
        bw.write(gson.toJson(users));
        bw.flush();
        bw.close();
    }
}
