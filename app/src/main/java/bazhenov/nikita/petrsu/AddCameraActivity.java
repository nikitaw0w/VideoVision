package bazhenov.nikita.petrsu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bazhenov.nikita.petrsu.videocam.VideoCamLab;
import bazhenov.nikita.petrsu.videocam.VideoCamera;

public class AddCameraActivity extends AppCompatActivity {
    private EditText name;
    private EditText id;
    private EditText user;
    private EditText password;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_camera);

        setTitle("Add camera");

        final VideoCamLab videoCameraLab = VideoCamLab.getInstance(getApplicationContext());
        final VideoCamera camera = new VideoCamera();

        name = (EditText) findViewById(R.id.camera_name_edit);
        id = (EditText) findViewById(R.id.camera_id_edit);
        user = (EditText) findViewById(R.id.camera_user_edit);
        password = (EditText) findViewById(R.id.camera_password_edit);
        add = (Button) findViewById(R.id.add_camera);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName = name.getText().toString();
                String sId = id.getText().toString();
                String sUser = user.getText().toString();
                String sPassword = password.getText().toString();

                if (!sName.isEmpty() && !sId.isEmpty() && !sUser.isEmpty() && !sPassword.isEmpty()) {
                    camera.setName(sName);
                    camera.setIdCam(sId);
                    camera.setUser(sUser);
                    camera.setPasswrord(sPassword);
                    videoCameraLab.addCamera(camera);
                    Toast.makeText(getApplicationContext(),
                            "Камера добавлена", Toast.LENGTH_LONG).show();
                    onBackPressed();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Заполните все поля", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
