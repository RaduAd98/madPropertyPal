package com.example.madpropertypal.Constructors;

public class ReportModelClass {
    String propertyID;
    String viewingDate;
    String interest;
    String offerPrice;
    String offerConditions;
    String offerExpiryDate;
    String viewingComments;


    public ReportModelClass(String propertyID, String viewingDate, String interest, String offerPrice, String offerConditions, String offerExpiryDate, String viewingComments) {
        this.propertyID = propertyID;
        this.viewingDate = viewingDate;
        this.interest = interest;
        this.offerPrice = offerPrice;
        this.offerConditions = offerConditions;
        this.offerExpiryDate = offerExpiryDate;
        this.viewingComments = viewingComments;

    }

    public String getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public String getViewingDate() {
        return viewingDate;
    }

    public void setViewingDate(String viewingDate) {
        this.viewingDate = viewingDate;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getOfferConditions() {
        return offerConditions;
    }

    public void setOfferConditions(String offerConditions) {
        this.offerConditions = offerConditions;
    }

    public String getOfferExpiryDate() {
        return offerExpiryDate;
    }

    public void setOfferExpiryDate(String offerExpiryDate) {
        this.offerExpiryDate = offerExpiryDate;
    }

    public String getViewingComments() {
        return viewingComments;
    }

    public void setViewingComments(String viewingComments) {
        this.viewingComments = viewingComments;
    }
}
