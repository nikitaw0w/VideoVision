package bazhenov.nikita.petrsu.videocam;

/**
 * @author Kovalchuk Denis
 * @version 1.0
 */

public class VideoCamera {
    private String name;
    private String id;
    private String user;
    private String password;

    public VideoCamera(String name, String id, String user, String password) {
        this.name = name;
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswrorld(String password) {
        this.password = password;
    }
}
