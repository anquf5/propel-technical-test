package com.propel.technicaltest;

import com.google.gson.*;
import org.springframework.context.annotation.Configuration;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Configuration
public class IOHandler{
    private final String filename = System.getProperty("user.dir") + "\\src\\main\\java\\users.json";

    private Gson gson = new Gson();

    public IOHandler() {}

    // Read Json from File
    /*
        * @param []
        * @return User
        */
    public List<User> IOReader() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<User> users = Arrays.asList(gson.fromJson(br, User[].class));
        br.close();
        return users;
    }

    // Write Json to File
    /*
        * @param [users]
        * @return void
        */
    public void IOWriter(List<User> users) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(gson.toJson(users));
        bw.flush();
        bw.close();
    }
}
