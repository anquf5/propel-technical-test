package com.propel.technicaltest;
import com.google.gson.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JSONHandler {

    private IOHandler ioHandler;
    private List<User> users;
    private Gson gson = new Gson();

    public JSONHandler(IOHandler ioHandler) throws IOException {
        this.ioHandler = ioHandler;
        this.users = new ArrayList<>(ioHandler.IOReader());
    }

    // List all users
    /*
        * @param []
        * @return User
        */
    public List<User> listAllUsers(){
        return users;
    }

    // View one user
    /*
        * @param [id]
        * @return JsonObject
        */
    public JsonObject viewUser(int id){
        JsonObject response = new JsonObject();
        try {
            User user = users.get(id);
            response = gson.toJsonTree(user).getAsJsonObject();
        }catch (Exception e){
            if (!users.contains(id)){
                response.addProperty("msg", gson.toJson("Can't find this user!"));
            }
            else{
                response.addProperty("msg", gson.toJson(e.getMessage()));
            }
        }
        finally {
            return response;
        }
    }


    // Add
    /*
        * @param User
        * @return JsonObject
        */
    public JsonObject addUser(User user) throws IOException {
        JsonObject response = new JsonObject();
        try {
            users.add(user);
            response.addProperty("msg", gson.toJson("Successed"));
            ioHandler.IOWriter(users);
        }catch (Exception e){
            response.addProperty("msg", gson.toJson(e.getMessage()));
        }finally {
            return response;
        }
    }

    // Edit
    /*
        * @param [id, first_name, last_name, phone, email]
        * @return JsonObject
        */
    public JsonObject editUser(int id, String first_name, String last_name, String phone, String email) throws IOException {
        JsonObject response = new JsonObject();
        try{
            User user = users.get(id);
            if (first_name != null){
                user.setFirst_name(first_name);
            }
            if (last_name != null) {
                user.setLast_name(last_name);
            }
            if (phone != null) {
                user.setPhone(phone);
            }
            if (email != null) {
                user.setEmail(email);
            }
            response.addProperty("msg", gson.toJson("Successed"));
            ioHandler.IOWriter(users);
        }catch (Exception e){
            if (!users.contains(id)){
                response.addProperty("msg", gson.toJson("Can't find this user!"));
            }
            else{
                response.addProperty("msg", gson.toJson(e.getMessage()));
            }
        }finally {
            return response;
        }
    }

    // Delete
    /*
        * @param [id]
        * @return JsonObject
        */
    public JsonObject deleteUser(int id) throws IOException {
        JsonObject response = new JsonObject();
        try {
            users.remove(id);
            response.addProperty("msg", gson.toJson("Successed"));
            ioHandler.IOWriter(users);
        }catch (Exception e){
            if (!users.contains(id)){
                response.addProperty("msg", gson.toJson("Can't find this user!"));
            }
            else{
                response.addProperty("msg", gson.toJson(e.getMessage()));
            }
        }finally {
            return response;
        }
    }
}
