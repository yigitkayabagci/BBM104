public class Decoration {
    private int price;
    private String dectype;
    private String decname;
    private int permeter;

    public void setPrice(int price) {
        this.price = price;
    }
    public void setDecType(String dectype) {
        this.dectype = dectype;
    }
    public int getPrice() {
        return price;
    }
    public String getDecType() {
        return dectype;
    }
    public Decoration(String decname,String dectype,int price ) {
        this.decname = decname;
        this.price = price;
        this.dectype = dectype;
    }
    public void setDecName(String decname) {
        this.decname = decname;
    }
    public String getDecName() {
        return decname;
    }
    public int getPermeter() {
        return permeter;
    }
    public void setPermeter(int permeter) {
        this.permeter = permeter;
    }

}
