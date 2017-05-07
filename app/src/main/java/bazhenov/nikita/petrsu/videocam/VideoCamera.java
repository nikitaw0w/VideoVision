package bazhenov.nikita.petrsu.videocam;

import java.util.UUID;

public class VideoCamera {
    private UUID id;
    private String name;
    private String idCam;
    private String user;
    private String password;

    public VideoCamera() {
        this(UUID.randomUUID());
    }

    public VideoCamera(UUID id) {
        this.id = id;
    }

    public VideoCamera(UUID id, String name, String idCam, String user, String password) {
        this.id = id;
        this.name = name;
        this.idCam = idCam;
        this.user = user;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCam() {
        return idCam;
    }

    public void setIdCam(String id) {
        this.idCam = id;
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

    public void setPasswrord(String password) {
        this.password = password;
    }
}
