package com.niazi.academy.Model_class;

import android.os.Parcelable;

public class Std_Detail_Model {
    String name,image,email,address,phoneno;

    public Std_Detail_Model() {
    }

    public Std_Detail_Model(String name, String image, String email, String address, String phoneno) {
        this.name = name;
        this.image = image;
        this.email = email;
        this.address = address;
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
