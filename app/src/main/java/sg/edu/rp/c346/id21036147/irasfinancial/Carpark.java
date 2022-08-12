package sg.edu.rp.c346.id21036147.irasfinancial;

public class Carpark {

    private String total;
    private String type;
    private String available;

    public Carpark(String total, String type, String available) {
        this.total = total;
        this.type = type;
        this.available = available;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Carpark{" +
                "total='" + total + '\'' +
                ", type='" + type + '\'' +
                ", available='" + available + '\'' +
                '}';
    }
}
