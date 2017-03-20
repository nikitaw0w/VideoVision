package bazhenov.nikita.petrsu.videocam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kovalchuk Denis
 * @version 1.0
 */

public class VideoCamLab {
    private static final VideoCamLab ourInstance = new VideoCamLab();

    private List<VideoCamera> videoCameraList;

    public static VideoCamLab getInstance() {
        return ourInstance;
    }

    private VideoCamLab() {
        videoCameraList = new ArrayList<>();
    }

    public void addCamera(VideoCamera videoCamera) {
        videoCameraList.add(videoCamera);
    }
}
