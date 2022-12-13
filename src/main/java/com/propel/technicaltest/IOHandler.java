package com.propel.technicaltest;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOHandler{
    private static final File file = new File(IOHandler.class.getClassLoader().getResource("test.json").getFile());

    public IOHandler() {
    }

    public List<User> IOReader() throws IOException {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<User> users = Arrays.asList(gson.fromJson(reader, User[].class));
        reader.close();
        return users;
    }

    public void IOWriter(List<User> users) throws IOException{
        Gson gson = new Gson();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(gson.toJson(users));
        bw.flush();
        bw.close();
    }
}
