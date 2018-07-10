package org.quickstart.json.jackson.v1.pojo;

public class User {
    public enum Gender {
        MALE, FEMALE
    };

    private Gender _gender;
    private Name _name;
    private boolean _isVerified;
    private byte[] _userImage;

    public Name getName() {
        return _name;
    }

    public boolean isVerified() {
        return _isVerified;
    }

    public Gender getGender() {
        return _gender;
    }

    public byte[] getUserImage() {
        return _userImage;
    }

    public void setName(Name n) {
        _name = n;
    }

    public void setVerified(boolean b) {
        _isVerified = b;
    }

    public void setGender(Gender g) {
        _gender = g;
    }

    public void setUserImage(byte[] b) {
        _userImage = b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("gender: " + _gender + ",");
        sb.append("name: " + _name + ",");
        sb.append("isVerified: " + _isVerified + ",");
        sb.append("userImage: " + new String(_userImage) + ".");
        return sb.toString();
    }
}
