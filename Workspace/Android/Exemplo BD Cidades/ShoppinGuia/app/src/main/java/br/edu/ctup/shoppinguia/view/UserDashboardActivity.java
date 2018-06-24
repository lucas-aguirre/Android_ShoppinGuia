package br.edu.ctup.shoppinguia.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.edu.ctup.shoppinguia.R;
import br.edu.ctup.shoppinguia.model.User;

public class UserDashboardActivity extends AppCompatActivity {

    private TextView txtName, txtEmail, txtPassword, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtPhone = (TextView) findViewById(R.id.txtPhone);

        User user = (User) getIntent().getSerializableExtra("User");

        txtName.setText(user.getName().toString());
        txtEmail.setText(user.getEmail().toString());
        txtPhone.setText(user.getPhone().toString());
        txtPassword.setText(user.getPassword().toString());
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
