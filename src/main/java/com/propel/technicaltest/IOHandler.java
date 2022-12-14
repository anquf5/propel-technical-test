package com.propel.technicaltest;

import com.google.gson.*;
import org.springframework.context.annotation.Configuration;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Configuration
public class IOHandler{
    public final String filename = System.getProperty("user.dir") + "\\src\\main\\java\\users.json";

    public IOHandler() {}

    // Read Json from File
    /*
        * @param []
        * @return User
        */
    public List<User> IOReader() throws IOException {
        Gson gson = new Gson();
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
        Gson gson = new Gson();
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        System.out.println(gson.toJson(users));
        bw.write(gson.toJson(users));
        bw.flush();
        bw.close();
    }
}
