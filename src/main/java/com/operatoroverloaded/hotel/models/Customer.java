package com.operatoroverloaded.hotel.models;
import java.util.ArrayList;
import com.operatoroverloaded.hotel.stores.billstore.*;
abstract public class Customer {
    private int customerId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private double bill_amt;
    private double bill_payed;
    private double bill_left;
    private ArrayList<Integer> bills;
    DateTime reservedFrom, reservedTo;

//------------------------------------------------------------------Constructors----------------------------------------------------------------------------------------------

    public Customer(int id, String name, String email, String phone, String address, DateTime from, DateTime to){
        this.customerId = id; 
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bill_amt = 0;
        this.bill_left = 0;
        this.bill_payed = 0;
        this.bills = new ArrayList<Integer>();
        reservedFrom = from;
        reservedTo = to;
    }

    public Customer(String name, String email, String phone, String address, DateTime from, DateTime to){
        this.customerId = -1; 
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bill_amt = 0;
        this.bill_left = 0;
        this.bill_payed = 0;
        this.bills = new ArrayList<Integer>();
        reservedFrom = from;
        reservedTo = to;
    }



    public Customer() {
        this.customerId = -1;
        this.name = "";
        this.email = "";
        this.phone = "";
        this.address = "";
        this.bill_amt = 0;
        this.bill_left = 0;
        this.bill_payed = 0;
        this.bills = new ArrayList<Integer>();
        reservedFrom = new DateTime(0, 0, 0, 0, 0, 0);
        reservedTo = new DateTime(0, 0, 0, 0, 0, 0);
    }

//------------------------------------------------------------------Setter methods----------------------------------------------------------------------------------------------

    public void setCustomerId(int id){
        this.customerId = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String name){
        this.email = name;
    }
    public void setPhone(String ph){
        this.phone = ph;
    }
    public void setAddress(String name){
        this.address = name;
    }
    public void setBillLeft(double billleft){
        this.bill_left = billleft;
        this.bill_payed = this.bill_amt - billleft;
    }
    public void setBillPayed(double billpayed){
        this.bill_payed = billpayed;
        this.bill_left = this.bill_amt - billpayed;
    }
    private void setBillAmount(double billamt){ //when you add bill, it automatically sets the bill amount thats why it is a private method
        this.bill_amt = billamt;
        this.bill_left = billamt-this.bill_payed;
    }
    public void addBill(int billid){ //expects bill id
        for (int id : this.bills) if (id == billid) return; //if billid already exists simply return
        this.bills.add(billid);
        Bill bill = BillStore.getInstance().getBill(billid); //assuming that there is a database for bills called billStore that returns me the bill, if i pass a billId to it
        setBillAmount(bill_amt+bill.getAmount());
    }

    public void addBill(Bill bill){ //expects bill object
        for (int id : this.bills) if (id == bill.getBillId()) return; //if bill already exists simply return
        bills.add(bill.getBillId());
        setBillAmount(this.bill_amt+bill.getAmount());
    }

    public void deleteBill(int billid){ //expects bill id
        Bill b = BillStore.getInstance().getBill(billid);
        for (int id : bills) if (id == billid) {
            setBillAmount(bill_amt-b.getAmount());
        } 
        bills.remove(Integer.valueOf(billid));
    }

    public void setReservedFrom(DateTime from){
        this.reservedFrom = from;
    }
    
    public void setReservedTo(DateTime to){
        this.reservedTo = to;
    }

//------------------------------------------------------------------Getter methods----------------------------------------------------------------------------------------------

    public int getCustomerId(){
        return this.customerId;
    }
    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    public double getBillAmount(){
        return this.bill_amt;
    }
    public double getBillPayed(){
        return this.bill_payed;
    }
    public double getBillLeft(){
        return this.bill_left;
    }
    public ArrayList<Integer> getBills(){ //Returns an ArrayList of all the Billid's
        return this.bills;
    }
    public DateTime getReservedFrom(){
        return this.reservedFrom;
    }
    public DateTime getReservedTo(){
        return this.reservedTo;
    }

//-------------------------------------------------------------Other Constructors------------------------------------------------------------------------------------

    public Customer(int id, String name, String email, String phone, String address){
        this.customerId = id; 
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bill_amt = 0;
        this.bill_left = 0;
        this.bill_payed = 0;
        this.bills = new ArrayList<Integer>();
        reservedFrom = new DateTime(0,0,0,0,0,0);
        reservedTo = new DateTime(0,0,0,0,0,0);
    }

    public Customer(String name, String email, String phone, String address){
        this.customerId = -1; 
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bill_amt = 0;
        this.bill_left = 0;
        this.bill_payed = 0;
        this.bills = new ArrayList<Integer>();
        reservedFrom = new DateTime(0,0,0,0,0,0);
        reservedTo = new DateTime(0,0,0,0,0,0);
    }

    public Customer(int id, String name, String email, String phone, String address, DateTime from){
        this.customerId = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bill_amt = 0;
        this.bill_left = 0;
        this.bill_payed = 0;
        this.bills = new ArrayList<Integer>();
        reservedFrom = from;
        reservedTo = new DateTime(0,0,0,0,0,0);
    }

    public Customer(String name, String email, String phone, String address, DateTime from){
        this.customerId = -1;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bill_amt = 0;
        this.bill_left = 0;
        this.bill_payed = 0;
        this.bills = new ArrayList<Integer>();
        reservedFrom = from;
        reservedTo = new DateTime(0,0,0,0,0,0);
    }

    public Customer(String name, String email, String phone, String address, double bill_amt, double bill_payed, double bill_left, ArrayList<Integer> bills, DateTime reservedFrom, DateTime reservedTo){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bill_amt = bill_amt;
        this.bill_left = bill_left;
        this.bill_payed = bill_payed;
        this.bills = bills;
        this.reservedFrom = reservedFrom;
        this.reservedTo = reservedTo;
    }

//-------------------------------------------------------------display------------------------------------------------------------------------------------

    public String toString(){
        String str = "";
        str = str + "Name: " + name + "\nEmail: " + email + "\nPhone number: " + phone + "\nAddress: " + address + "\nTotal Bill amount: " + bill_amt + "\nBill payed: " + bill_payed + "\nBill left: " + bill_left + "\nReserved from: " + reservedFrom.toString();
        if (reservedTo.timeDifference(new DateTime(0,0,0,0,0,0)) != 0) str = str + "\nReserved to: " + reservedTo.toString();
        return str;
    }

    public String viewCustomerDetails(){
        return toString();
    }

}
