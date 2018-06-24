package br.edu.ctup.shoppinguia.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ctup.shoppinguia.R;
import br.edu.ctup.shoppinguia.dal.UserDAO;
import br.edu.ctup.shoppinguia.model.User;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister, btnGoogle, btnFacebook;
    private EditText edtLogin, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnGoogle = (Button) findViewById(R.id.btnGoogle);
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
    }

    public void btnRegister(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

    public void btnLogin(View view){

        if (! edtLogin.getText().toString().equals("") && ! edtPassword.getText().toString().equals("")) {
            User u = new User();

            u = UserDAO.userLogin(edtLogin.getText().toString(), edtPassword.getText().toString(), this);

            if (u.getName() != null){
                String activity;

                Intent intent = null;

                if (u.getRole_id() == 1){
                    intent = new Intent(this, AdminDashboardActivity.class);
                } else if(u.getRole_id() == 2){
                    intent = new Intent(this, MallUserDashboardActivity.class);
                } else if(u.getRole_id() == 3){
                    intent = new Intent(this, ShopUserDashboardActivity.class);
                } else if(u.getRole_id() == 4){
                    intent = new Intent(this, UserDashboardActivity.class);
                }

                if (intent != null){
                    intent.putExtra("User", u);
                    startActivity(intent);

                    finish();
                } else {
                    Toast.makeText(this, "Erro ao inicializar", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Login ou Senha incorretos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
        }
    }
}
