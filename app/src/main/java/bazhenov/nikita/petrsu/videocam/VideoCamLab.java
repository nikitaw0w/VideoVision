package bazhenov.nikita.petrsu.videocam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bazhenov.nikita.petrsu.database.CameraCursorWrapper;
import bazhenov.nikita.petrsu.database.CameraDbHelper;
import bazhenov.nikita.petrsu.database.CameraDbSchema.CameraTable;

public class VideoCamLab {
    private static VideoCamLab ourInstance;
    private Context contextApp;
    private SQLiteDatabase database;

    private List<VideoCamera> videoCameraList;

    public static VideoCamLab getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new VideoCamLab(context);
        }
        return ourInstance;
    }

    private VideoCamLab(Context context) {
        contextApp = context.getApplicationContext();
        database = new CameraDbHelper(contextApp)
                .getWritableDatabase();
        videoCameraList = new ArrayList<>();
    }

    private static ContentValues getContentValues(VideoCamera camera) {
        ContentValues values = new ContentValues();
        values.put(CameraTable.Cols.UUID, camera.getId().toString());
        values.put(CameraTable.Cols.NAME, camera.getName());
        values.put(CameraTable.Cols.ID, camera.getIdCam());
        values.put(CameraTable.Cols.USER, camera.getUser());
        values.put(CameraTable.Cols.PASSWORD, camera.getPassword());

        return values;
    }

    public List<VideoCamera> getCameras() {
        List<VideoCamera> Cameras = new ArrayList<>();
        CameraCursorWrapper cursor = queryCameras(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Cameras.add(cursor.getCamera());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Cameras;
    }

    public VideoCamera getCamera(UUID id) {
        CameraCursorWrapper cursor = queryCameras(
                CameraTable.Cols.UUID + " = ?",
                new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCamera();
        } finally {
            cursor.close();
        }
    }

    public void addCamera(VideoCamera Camera) {
        ContentValues values = getContentValues(Camera);
        database.insert(CameraTable.NAME, null, values);
    }

    private CameraCursorWrapper queryCameras(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                CameraTable.NAME,
                null, // Columns - null выбирает все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new CameraCursorWrapper(cursor);
    }

    public void updateCamera(VideoCamera Camera) {
        String uuidString = Camera.getId().toString();
        ContentValues values = getContentValues(Camera);
        database.update(CameraTable.NAME, values,
                CameraTable.Cols.UUID + " = ?",
                new String[] { uuidString } );
    }

    public void removeCamera(VideoCamera Camera) {
        String uuidString = Camera.getId().toString();
        database.delete(CameraTable.NAME, CameraTable.Cols.UUID +
                " = ?", new String[] { uuidString } );
    }

    public VideoCamera getCamera(final int position) {
        return videoCameraList.get(position);
    }

    public VideoCamera getCamera(final String id) {
        for (VideoCamera vc : getCameras()) {
            if (vc.getIdCam().equals(id)) {
                return vc;
            }
        }
        return null;
    }

    public List<VideoCamera> getList() {
        return videoCameraList;
    }
}
