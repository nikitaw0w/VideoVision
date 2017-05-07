package bazhenov.nikita.petrsu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bazhenov.nikita.petrsu.videocam.VideoCamLab;
import bazhenov.nikita.petrsu.videocam.VideoCamera;

public class EditCameraActivity extends AppCompatActivity {
    public final static String EXTRA_ID = "id_extra";
    private EditText name;
    private EditText id;
    private EditText user;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_camera);

        setTitle("Edit camera");

        final VideoCamLab videoCameraLab = VideoCamLab.getInstance(getApplicationContext());

        String cameraId = getIntent().getStringExtra(EXTRA_ID);
        final VideoCamera camera = videoCameraLab.getCamera(cameraId);

        name = (EditText) findViewById(R.id.camera_name_edit);
        name.setText(camera.getName());
        id = (EditText) findViewById(R.id.camera_id_edit);
        id.setText(camera.getIdCam());
        user = (EditText) findViewById(R.id.camera_user_edit);
        user.setText(camera.getUser());
        password = (EditText) findViewById(R.id.camera_password_edit);
        password.setText(camera.getPassword());
        Button save = (Button) findViewById(R.id.save_camera);
        save.setOnClickListener(new View.OnClickListener() {
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
                    videoCameraLab.updateCamera(camera);
                    Toast.makeText(getApplicationContext(),
                            "Камера обнавлена", Toast.LENGTH_LONG).show();
                    onBackPressed();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Заполните все поля", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button remove = (Button) findViewById(R.id.remove_camera);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoCameraLab.removeCamera(camera);
                onBackPressed();
            }
        });
    }
}
