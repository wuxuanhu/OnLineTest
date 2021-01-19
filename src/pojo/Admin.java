package pojo;

public class Admin {
    private String acc;
    private String pwd;
    private String email;
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Admin() {
    }

    public Admin(String acc, String pwd) {
        this.acc = acc;
        this.pwd = pwd;
    }

    public Admin(String acc, String pwd, String email, String phone) {
        this.acc = acc;
        this.pwd = pwd;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "acc='" + acc + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
