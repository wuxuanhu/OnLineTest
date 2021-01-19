package pojo;

public class ChangeClassify {
    private String oldname;
    private String newname;

    public ChangeClassify() {
    }

    public ChangeClassify(String oldname, String newname) {
        this.oldname = oldname;
        this.newname = newname;
    }

    public String getOldname() {
        return oldname;
    }

    public void setOldname(String oldname) {
        this.oldname = oldname;
    }

    public String getNewname() {
        return newname;
    }

    public void setNewname(String newname) {
        this.newname = newname;
    }
}
