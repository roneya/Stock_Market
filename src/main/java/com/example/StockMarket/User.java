package com.example.StockMarket;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document(collection = "users")
public class User {



    private String first_name;
    private String last_name;
    @Id
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String phone_number;
    @JsonIgnore
    private String password;

    @Transient
    private String plainPassword;

    private String encryptedPassword;

    public User(){



    }




    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return encryptedPassword;
    }

    public void setPassword(String password) {

        this.plainPassword = password;
        this.encryptedPassword =  new BCryptPasswordEncoder().encode(password);
    }
}
