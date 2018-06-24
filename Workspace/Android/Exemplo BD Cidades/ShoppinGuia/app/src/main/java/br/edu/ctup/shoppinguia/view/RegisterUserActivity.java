package br.edu.ctup.shoppinguia.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.edu.ctup.shoppinguia.R;
import br.edu.ctup.shoppinguia.dal.UserDAO;
import br.edu.ctup.shoppinguia.model.User;

public class RegisterUserActivity extends AppCompatActivity {

    private Button btnRegister, btnBack;
    private EditText edtName, edtPhone, edtEmail, edtPassword;

    private static final DateFormat df = new SimpleDateFormat("dd-mm-yyyy, HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnBack = (Button) findViewById(R.id.btnBack);

        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }

    public void btnBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void btnRegister(View view){
        if(! edtName.getText().toString().equals("") && ! edtEmail.getText().toString().equals("") && ! edtPassword.getText().toString().equals("")) {
            //Exemplo Lista Avançada
            User u = new User();

            u.setName(edtName.getText().toString());
            u.setEmail(edtEmail.getText().toString());
            u.setPassword(edtPassword.getText().toString());
            if (! edtPhone.getText().toString().equals("")){
                u.setPhone(edtPhone.getText().toString());
            }
            u.setRole_id(4);
            u.setToken("E-mail");
//            u.setCreated_at(df.format(Calendar.getInstance().getTime()));

            if(UserDAO.registerUser(u, this)){
                Toast.makeText(this,
                        "Usuário cadastrado com sucesso!",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, UserDashboardActivity.class);
                intent.putExtra("User", u);
                startActivity(intent);

                finish();

            }else{
                Toast.makeText(this,
                        "Usuário não cadastrado.",
                        Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Favor preencher todos os campos.", Toast.LENGTH_SHORT).show();
        }
    }
}
