package br.com.example.crud_firebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.example.crud_firebase.models.User;

public class SignUpActivity extends AppCompatActivity {
    Button sgn_button;
    EditText email, name, password;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_signup);
        super.onCreate(savedInstanceState);

        email = findViewById(R.id.sgnUp_email);
        password = findViewById(R.id.sgnUp_password);
        name = findViewById(R.id.sgnUp_name);
        sgn_button = findViewById(R.id.sgnUp_button);

        statFirebase();

        sgn_button.setOnClickListener(v -> {

            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setName(name.getText().toString());

            databaseReference.child("User").child(user.getId()).setValue(user);
            clear();
        });
    }

    private void clear() {

        email.setText("");
        name.setText("");
        password.setText("");
    }

    private void statFirebase() {
        FirebaseApp.initializeApp(SignUpActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
