package pooja.jadhav.login;
public class CustomClass {
    private String name, rollno, email, phone, location;
     private int img_src;

    public CustomClass(String name, String rollno, String email, String phone, String location, int img_src) {
        this.name = name;
        this.rollno = rollno;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.img_src = img_src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getImg_src() {
        return img_src;
    }

    public void setImg_src(int img_src) {
        this.img_src = img_src;
    }

}

