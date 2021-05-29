package com.example.madpropertypal.Constructors;

public class PropertyModelClass {
    Integer id;
    String nameNumber;
    String location;
    String size;
    String price;
    String propertyType;
    String leaseType;
    String constructionYear;
    String floorsNumber;
    String description;
    String localAmenities;
    String bedroomsNumber;
    String bathroomsNumber;

    //Generating constructors
    public PropertyModelClass(String nameNumber, String location, String size, String price,
                              String propertyType, String leaseType, String constructionYear,
                              String floorsNumber, String description, String localAmenities,
                              String bedroomsNumber, String bathroomsNumber) {
        this.nameNumber = nameNumber;
        this.location = location;
        this.size = size;
        this.price = price;
        this.propertyType = propertyType;
        this.leaseType = leaseType;
        this.constructionYear = constructionYear;
        this.floorsNumber = floorsNumber;
        this.description = description;
        this.localAmenities = localAmenities;
        this.bedroomsNumber = bedroomsNumber;
        this.bathroomsNumber = bathroomsNumber;
    }

    public PropertyModelClass(Integer id, String nameNumber, String location, String size, String price,
                              String propertyType, String leaseType, String constructionYear, String floorsNumber,
                              String description, String localAmenities, String bedroomsNumber, String bathroomsNumber) {
        this.id = id;
        this.nameNumber = nameNumber;
        this.location = location;
        this.size = size;
        this.price = price;
        this.propertyType = propertyType;
        this.leaseType = leaseType;
        this.constructionYear = constructionYear;
        this.floorsNumber = floorsNumber;
        this.description = description;
        this.localAmenities = localAmenities;
        this.bedroomsNumber = bedroomsNumber;
        this.bathroomsNumber = bathroomsNumber;
    }

    //Generating Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameNumber() {
        return nameNumber;
    }

    public void setNameNumber(String nameNumber) {
        this.nameNumber = nameNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getFloorsNumber() {
        return floorsNumber;
    }

    public void setFloorsNumber(String floorsNumber) {
        this.floorsNumber = floorsNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalAmenities() {
        return localAmenities;
    }

    public void setLocalAmenities(String localAmenities) {
        this.localAmenities = localAmenities;
    }

    public String getBedroomsNumber() {
        return bedroomsNumber;
    }

    public void setBedroomsNumber(String bedroomsNumber) {
        this.bedroomsNumber = bedroomsNumber;
    }

    public String getBathroomsNumber() {
        return bathroomsNumber;
    }

    public void setBathroomsNumber(String bathroomsNumber) {
        this.bathroomsNumber = bathroomsNumber;
    }
}
