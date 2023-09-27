package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Customer {

private int idCustomer;
private  String name;
private String lastname;
private String email;
private String phonenumber;
private LocalDate birthdayDate;

public Customer(String fileLine){
    String[]elemArray=fileLine.split(";");
    this.idCustomer=Integer.parseInt(elemArray[0]);
    this.name=elemArray[1];
    this.lastname=elemArray[2];
    this.email=elemArray[3];
    this.phonenumber=elemArray[4];
    this.birthdayDate=LocalDate.parse(elemArray[5]);
}

public String toStringFile(){
    return idCustomer+";"+name+";"+lastname
            +";"+email+";"+phonenumber+";"+birthdayDate;

}



}
