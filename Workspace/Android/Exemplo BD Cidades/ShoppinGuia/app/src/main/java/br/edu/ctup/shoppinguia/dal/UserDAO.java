package br.edu.ctup.shoppinguia.dal;

import android.content.Context;

import br.edu.ctup.shoppinguia.model.User;

public class UserDAO {

    public static boolean registerUser(User user, Context context){
        Database d = new Database(context);

        return d.userRegister(user);
    }

    public static User userLogin(String login, String password, Context context){
        Database d = new Database(context);

        return d.userLogin(login, password);
    }
}
