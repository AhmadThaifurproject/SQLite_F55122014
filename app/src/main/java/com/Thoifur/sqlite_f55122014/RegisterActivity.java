package com.Thoifur.sqlite_f55122014;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.Thoifur.sqlite_f55122014.databinding.ActivityRegisterBinding;
import utils.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.btnLogin.setOnClickListener(view -> {
            Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(loginIntent);
        });

        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.edtUsername.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();
            String confPass = binding.edtConfirm.getText().toString().trim();

            if (password.equals(confPass)) {
                long result = databaseHelper.addUser(username, password);

                if (result > 0) {
                    Toast.makeText(RegisterActivity.this, "You have registered",
                            Toast.LENGTH_SHORT).show();

                    Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(moveToLogin);
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Password does not match",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
