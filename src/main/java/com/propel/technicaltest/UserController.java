package com.propel.technicaltest;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    private final JSONHandler jsonHandler;

    public UserController(JSONHandler jsonHandler) {
        this.jsonHandler = jsonHandler;
    }

    // List all users
    @GetMapping("list")
    public List<User> listAllUsers(){
        return jsonHandler.listAllUsers();
    }

    // View user
    @GetMapping("view/{id}")
    public JsonObject viewUser(@PathVariable("id") Integer id){
        return jsonHandler.viewUser(id);
    }

    // Add new user
    @PostMapping("add")
    public JsonObject addUser(@RequestBody userRequest request) throws IOException {
        User user = new User(request.firstName, request.lastName, request.phone, request.email);
        return jsonHandler.addUser(user);
    }

    // Edit user
    @PutMapping("{id}")
    public JsonObject editUser(
            @PathVariable Integer id,
            @RequestBody userRequest request
    ) throws IOException {
        return jsonHandler.editUser(
                id,
                request.firstName,
                request.lastName,
                request.phone,
                request.email
        );
    }

    static record userRequest(
            String firstName,
            String lastName,
            String phone,
            String email
    ) {}

    // Delete user
    @DeleteMapping("{id}")
    public JsonObject deleteUser(@PathVariable Integer id) throws IOException {
        return jsonHandler.deleteUser(id);
    }

}
