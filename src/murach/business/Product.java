package murach.business;

/*
* Date: November 10th, 2019
* @author Drake Greeott
* Description: add a notes field to the Product Manager application and display the first 30
characters of the note after the price column. Make sure the Product table has been create in the MySQL
database.
*/

import java.text.NumberFormat;

public class Product {
    private long id;
    private String code;
    private String description;
    private double price;
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getPriceFormatted() {
        NumberFormat currencyFormatter =
                NumberFormat.getCurrencyInstance();
        return currencyFormatter.format(getPrice());
    }   
    
    public String getNote(){
        return note;
    }
    
    public void setNote(String note){
        this.note = note;
    }

}