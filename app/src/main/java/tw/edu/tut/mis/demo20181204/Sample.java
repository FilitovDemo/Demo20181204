package tw.edu.tut.mis.demo20181204;

public class Sample {
    //資料欄位
    private String Name, TEL, Address;

    //建構式
    Sample(){
        Name = "沒有名字";
        TEL = "";
        Address = "";
    }

    Sample(String name, String tel, String address){
        Name = name;
        TEL = tel;
        Address = address;
    }

    //存取
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
