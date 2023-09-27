package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Order {

    private int order_Id;
    private LocalDateTime order_date;
    private int customerId;
    private int table_Number;

    public Order(String fileLine){
    String [] elemArray=fileLine.split(";");
    this.order_Id=Integer.parseInt(elemArray[0]);
    this.order_date= LocalDateTime.parse(elemArray[1]);
    this.customerId=Integer.parseInt(elemArray[2]);
    this.table_Number=Integer.parseInt(elemArray[3]);
    }

    public String toStringTextFile(){
    return order_Id+";"+order_date+";"
            +customerId+";"+table_Number;
    }




}
