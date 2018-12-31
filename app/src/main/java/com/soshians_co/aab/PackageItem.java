package com.soshians_co.aab;

/**
 * Created by yousef on 5/10/2016.
 */
public class PackageItem {
    private String ServiceName;
    private String ServicePrice;
    private String ProfileName;
    private String ServiceID;



    public PackageItem(String ServiceName, String ServicePrice, String ProfileName, String ServiceID) {
        super();
        this.ProfileName = ProfileName;
        this.ServiceName = ServiceName;
        this.ServiceID = ServiceID;
        this.ServicePrice = ServicePrice;


    }


    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String ProfileName) {
        this.ProfileName = ProfileName;
    }


    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }




    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String ServiceID) {
        this.ServiceID = ServiceID;
    }


    public void setServicePrice(String ServicePrice) {
        this.ServicePrice = ServicePrice;
    }
    public String getServicePrice() {
        return ServicePrice;
    }


}
