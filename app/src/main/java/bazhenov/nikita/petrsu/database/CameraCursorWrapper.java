package bazhenov.nikita.petrsu.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import bazhenov.nikita.petrsu.database.CameraDbSchema.CameraTable;
import bazhenov.nikita.petrsu.videocam.VideoCamera;

public class CameraCursorWrapper extends CursorWrapper {
    public CameraCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public VideoCamera getCamera() {
        String uuidString = getString(getColumnIndex(CameraTable.Cols.UUID));
        String name = getString(getColumnIndex(CameraTable.Cols.NAME));
        String id = getString(getColumnIndex(CameraTable.Cols.ID));
        String user = getString(getColumnIndex(CameraTable.Cols.USER));
        String password = getString(getColumnIndex(CameraTable.Cols.PASSWORD));

        VideoCamera camera = new VideoCamera(UUID.fromString(uuidString));
        camera.setName(name);
        camera.setIdCam(id);
        camera.setUser(user);
        camera.setPasswrord(password);

        return camera;
    }
}