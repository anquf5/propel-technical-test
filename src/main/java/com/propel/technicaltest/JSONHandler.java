package com.propel.technicaltest;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {

    private IOHandler ioHandler;

    private List<User> users;


    public JSONHandler(IOHandler ioHandler) throws IOException {
        this.ioHandler = ioHandler;
        this.users = new ArrayList<>(ioHandler.IOReader());
    }

    // List all users
    public String listAllUsers(){
        Gson gson = new Gson();
        return gson.toJson(users);
    }

    // View one user
    public String viewUser(int id){
        Gson gson = new Gson();
        return gson.toJson(users.get(id));
    }

    // Add
    public void addUser(String first_name, String last_name, String phone, String email) throws IOException {
        User user = new User(first_name, last_name, phone, email);
        users.add(user);
        ioHandler.IOWriter(users);
    }

    public void addUser(User user) throws IOException {
        users.add(user);
    }


    // Edit
    public void editUser(int id, String first_name, String last_name, String phone, String email) throws IOException {
        User user = users.get(id);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setPhone(phone);
        user.setEmail(email);
        ioHandler.IOWriter(users);
    }

    // Delete
    public void deleteUser(int id) throws IOException {
        users.remove(id);
        ioHandler.IOWriter(users);
    }
}
