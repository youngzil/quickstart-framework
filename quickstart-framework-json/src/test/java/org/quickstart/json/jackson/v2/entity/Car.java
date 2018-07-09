package org.quickstart.json.jackson.v2.entity;

/**
 * Created by GatsbyNewton on 2016/4/15.
 */
public class Car {

    public static class Owner {
        private String first;
        private String last;

        public Owner(String first, String last) {
            this.first = first;
            this.last = last;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[Owner: \n").append("first = " + this.first + "\n").append("last = " + this.last + "]");
            return sb.toString();
        }
    }

    private String brand;
    private int doors;
    private Owner owner;
    private String[] component;

    public Car() {}

    public Car(String brand, int doors, Owner owner, String[] component) {
        this.brand = brand;
        this.doors = doors;
        this.owner = owner;
        this.component = component;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String[] getComponent() {
        return component;
    }

    public void setComponent(String[] component) {
        this.component = component;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Car: \n").append("brand = " + this.brand + "\n").append("doors = " + this.doors + "\n").append("owner = " + this.owner + "\n").append("component = " + this.component + "]");
        return sb.toString();
    }
}
