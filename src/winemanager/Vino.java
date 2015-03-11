/*
 * Vino.java
 *
 * Created on June 11, 2007, 12:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package winemanager;

import java.io.Serializable;

/**
 *
 * @author Luiso
 */
public class Vino implements Serializable{
  
    private String name;
    private String country;
    private String type;
    private int points;
    private double price;
    private String color;
    private int quantity;
    private String comment;
    private int year;
    private double totalAmount;
    private String region;
    private String UPC;
    static final long serialVersionUID = -4243769260304837740L;
    
    /** Creates a new instance of Vino */
    public Vino(String nombre, String pais, String c, double precio, int cantidad, int ano) {
        name = nombre;
        country = pais;
        color = c;
        price = precio;
        quantity = cantidad;
        year = ano;
        totalAmount = price * quantity;
    }
    
    public Vino(){
        name = "";
        price = 0;
        country = "";
        type = "";
        points = 0;
        color = "";
        quantity = 0;
        comment = "";
        year = 0;
        region = "";
        UPC = "";
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public String getName(){
        return name;
    }
    
    public String getCountry(){
        return country;
    }
    
    public String getColor(){
        return color;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public int getYear(){
        return year;
    }
    
    public String getType(){
        return type;
    }
    
    public int getPoints(){
        return points;
    }
    
    public String getComment(){
        return comment;
    }
    
    public double getTotalAmount(){
        return totalAmount;
    }
    
    public void addComment(String comentario){
        comment = comentario;
    }
    
    public void addPoints(int puntos){
        points = puntos;
    }
    
    public void addType(String tipo){
        type = tipo;
    }
    
    public void removeComment(){
        comment = "";
    }
        
    public String getRegion(){
        return region;
    }
    
    public void addRegion(String reg){
        region = reg;
    }
    
    public void setAgain(String nombre, String pais, String c, String tipo, int cantidad,
            int ano, String comentario, double precio, double precioTotal, int puntos, String reg){
        name = nombre;
        country = pais;
        color = c;
        type = tipo;
        price = precio;
        quantity = cantidad;
        year = ano;
        points = puntos;
        totalAmount = precio*cantidad;
        comment = comentario;
        region = reg;
    }
    
}
