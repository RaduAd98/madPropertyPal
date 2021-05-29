package com.example.madpropertypal.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.madpropertypal.Constructors.PropertyModelClass;
import com.example.madpropertypal.Constructors.ReportModelClass;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper implements BaseColumns {

    //Property table columns
    public static final String NAME_NUMBER = "Name";
    public static final String LOCATION = "Location";
    public static final String SIZE = "Size";
    public static final String PRICE = "Price";
    public static final String PROPERTY_TYPE = "Property";
    public static final String LEASE_TYPE = "Lease";
    public static final String CONSTRUCTION_YEAR = "Construction";
    public static final String FLOORS_NUMBER = "Floors";
    public static final String DESCRIPTION = "Description";
    public static final String LOCAL_AMENITIES = "Amenities";
    public static final String BEDROOMS_NUMBER = "Bedrooms";
    public static final String BATHROOMS_NUMBER = "Bathrooms";
    //Report table columns
    public static final String PROPERTY_ID = "ID";
    public static final String VIEWING_DATE = "Date";
    public static final String INTEREST = "Interest";
    public static final String OFFER_PRICE = "Offer";
    public static final String OFFER_CONDITIONS = "Conditions";
    public static final String EXPIRY_DATE = "Expire";
    public static final String VIEWING_COMMENTS = "Comments";
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "MadPropertyPalDatabase";
    //Database table name
    private static final String TABLE_NAME = "MadPropertyPalTable";
    private static final String TABLE_NAME_REPORT = "MadPropertyPalReportTable";
    //Creating table query for Property table
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY ,"
            + NAME_NUMBER + " TEXT NOT NULL,"
            + LOCATION + " TEXT NOT NULL,"
            + SIZE + " TEXT NOT NULL,"
            + PRICE + " TEXT NOT NULL,"
            + PROPERTY_TYPE + " TEXT NOT NULL,"
            + LEASE_TYPE + " TEXT NOT NULL,"
            + CONSTRUCTION_YEAR + " TEXT,"
            + FLOORS_NUMBER + " TEXT,"
            + DESCRIPTION + " TEXT,"
            + LOCAL_AMENITIES + " TEXT,"
            + BEDROOMS_NUMBER + " TEXT NOT NULL,"
            + BATHROOMS_NUMBER + " TEXT NOT NULL);";
    //Creating table query for Report table
    private static final String CREATE_TABLE_REPORT = "CREATE TABLE " + TABLE_NAME_REPORT + "("
            + PROPERTY_ID + " TEXT NOT NULL,"
            + VIEWING_DATE + " TEXT NOT NULL,"
            + INTEREST + " TEXT NOT NULL,"
            + OFFER_PRICE + " TEXT,"
            + OFFER_CONDITIONS + " TEXT,"
            + EXPIRY_DATE + " TEXT,"
            + VIEWING_COMMENTS + " TEXT);";
    private SQLiteDatabase sqLiteDatabase;


    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_REPORT);
    }

    //Upgrading the databases when making changes to the schema
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REPORT);
        onCreate(db);
    }

    //Add property method
    public void addProperty(PropertyModelClass propertyModelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME_NUMBER, propertyModelClass.getNameNumber());
        contentValues.put(DatabaseHelperClass.LOCATION, propertyModelClass.getLocation());
        contentValues.put(DatabaseHelperClass.SIZE, propertyModelClass.getSize());
        contentValues.put(DatabaseHelperClass.PRICE, propertyModelClass.getPrice());
        contentValues.put(DatabaseHelperClass.PROPERTY_TYPE, propertyModelClass.getPropertyType());
        contentValues.put(DatabaseHelperClass.LEASE_TYPE, propertyModelClass.getLeaseType());
        contentValues.put(DatabaseHelperClass.CONSTRUCTION_YEAR, propertyModelClass.getConstructionYear());
        contentValues.put(DatabaseHelperClass.FLOORS_NUMBER, propertyModelClass.getFloorsNumber());
        contentValues.put(DatabaseHelperClass.DESCRIPTION, propertyModelClass.getDescription());
        contentValues.put(DatabaseHelperClass.LOCAL_AMENITIES, propertyModelClass.getLocalAmenities());
        contentValues.put(DatabaseHelperClass.BEDROOMS_NUMBER, propertyModelClass.getBedroomsNumber());
        contentValues.put(DatabaseHelperClass.BATHROOMS_NUMBER, propertyModelClass.getBathroomsNumber());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null, contentValues);

    }

    //Get property details
    public List<PropertyModelClass> getPropertyList() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<PropertyModelClass> storeProperty = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String nameNumber = cursor.getString(1);
                String location = cursor.getString(2);
                String size = cursor.getString(3);
                String price = cursor.getString(4);
                String propertyType = cursor.getString(5);
                String leaseType = cursor.getString(6);
                String constructionYear = cursor.getString(7);
                String floorsNumber = cursor.getString(8);
                String description = cursor.getString(9);
                String localAmenities = cursor.getString(10);
                String bedroomsNumber = cursor.getString(11);
                String bathroomsNumber = cursor.getString(12);
                storeProperty.add(new PropertyModelClass(id, nameNumber, location, size, price, propertyType, leaseType,
                        constructionYear, floorsNumber, description, localAmenities, bedroomsNumber, bathroomsNumber));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeProperty;
    }

    //Method for the simple search
    public List<PropertyModelClass> searchPropertyList(String theString) {
        String sql = " SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_NUMBER + " LIKE '%" + theString + "%' COLLATE NOCASE";
        sqLiteDatabase = this.getWritableDatabase();
        List<PropertyModelClass> storeSearch = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String nameNumber = cursor.getString(1);
                String location = cursor.getString(2);
                String size = cursor.getString(3);
                String price = cursor.getString(4);
                String propertyType = cursor.getString(5);
                String leaseType = cursor.getString(6);
                String constructionYear = cursor.getString(7);
                String floorsNumber = cursor.getString(8);
                String description = cursor.getString(9);
                String localAmenities = cursor.getString(10);
                String bedroomsNumber = cursor.getString(11);
                String bathroomsNumber = cursor.getString(12);
                storeSearch.add(new PropertyModelClass(id, nameNumber, location, size, price, propertyType, leaseType,
                        constructionYear, floorsNumber, description, localAmenities, bedroomsNumber, bathroomsNumber));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeSearch;
    }

    //Method for the advanced search
    public List<PropertyModelClass> advancedSearchPropertyList(String area, String type, String bedrooms) {
        String sql = " SELECT * FROM " + TABLE_NAME + " WHERE " + LOCATION + " LIKE '%" + area + "%' COLLATE NOCASE" +
                " AND " + PROPERTY_TYPE + " LIKE '%" + type + "%' COLLATE NOCASE " +
                " AND " + BEDROOMS_NUMBER + " LIKE '%" + bedrooms + "%'";
        sqLiteDatabase = this.getWritableDatabase();
        List<PropertyModelClass> storeSearch = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String nameNumber = cursor.getString(1);
                String location = cursor.getString(2);
                String size = cursor.getString(3);
                String price = cursor.getString(4);
                String propertyType = cursor.getString(5);
                String leaseType = cursor.getString(6);
                String constructionYear = cursor.getString(7);
                String floorsNumber = cursor.getString(8);
                String description = cursor.getString(9);
                String localAmenities = cursor.getString(10);
                String bedroomsNumber = cursor.getString(11);
                String bathroomsNumber = cursor.getString(12);
                storeSearch.add(new PropertyModelClass(id, nameNumber, location, size, price, propertyType, leaseType,
                        constructionYear, floorsNumber, description, localAmenities, bedroomsNumber, bathroomsNumber));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeSearch;
    }

    //Add report method
    public void addReport(ReportModelClass reportModelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.PROPERTY_ID, reportModelClass.getPropertyID());
        contentValues.put(DatabaseHelperClass.VIEWING_DATE, reportModelClass.getViewingDate());
        contentValues.put(DatabaseHelperClass.INTEREST, reportModelClass.getInterest());
        contentValues.put(DatabaseHelperClass.OFFER_PRICE, reportModelClass.getOfferPrice());
        contentValues.put(DatabaseHelperClass.OFFER_CONDITIONS, reportModelClass.getOfferConditions());
        contentValues.put(DatabaseHelperClass.EXPIRY_DATE, reportModelClass.getOfferExpiryDate());
        contentValues.put(DatabaseHelperClass.VIEWING_COMMENTS, reportModelClass.getViewingComments());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME_REPORT, null, contentValues);

    }

    //Get report details
    public List<ReportModelClass> getReportList(Integer id) {
        String sql = " SELECT * FROM " + TABLE_NAME_REPORT + " WHERE " + PROPERTY_ID + "=? ";
        sqLiteDatabase = this.getReadableDatabase();
        List<ReportModelClass> storeReport = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            do {
                String propertyName = cursor.getString(0);
                String viewingDate = cursor.getString(1);
                String interest = cursor.getString(2);
                String offerPrice = cursor.getString(3);
                String offerConditions = cursor.getString(4);
                String expiryDate = cursor.getString(5);
                String viewingComments = cursor.getString(6);
                storeReport.add(new ReportModelClass(propertyName, viewingDate, interest, offerPrice, offerConditions, expiryDate, viewingComments));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeReport;
    }

    //Update property method
    public void updateProperty(PropertyModelClass propertyModelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME_NUMBER, propertyModelClass.getNameNumber());
        contentValues.put(DatabaseHelperClass.LOCATION, propertyModelClass.getLocation());
        contentValues.put(DatabaseHelperClass.SIZE, propertyModelClass.getSize());
        contentValues.put(DatabaseHelperClass.PRICE, propertyModelClass.getPrice());
        contentValues.put(DatabaseHelperClass.PROPERTY_TYPE, propertyModelClass.getPropertyType());
        contentValues.put(DatabaseHelperClass.LEASE_TYPE, propertyModelClass.getLeaseType());
        contentValues.put(DatabaseHelperClass.CONSTRUCTION_YEAR, propertyModelClass.getConstructionYear());
        contentValues.put(DatabaseHelperClass.FLOORS_NUMBER, propertyModelClass.getFloorsNumber());
        contentValues.put(DatabaseHelperClass.DESCRIPTION, propertyModelClass.getDescription());
        contentValues.put(DatabaseHelperClass.LOCAL_AMENITIES, propertyModelClass.getLocalAmenities());
        contentValues.put(DatabaseHelperClass.BEDROOMS_NUMBER, propertyModelClass.getBedroomsNumber());
        contentValues.put(DatabaseHelperClass.BATHROOMS_NUMBER, propertyModelClass.getBathroomsNumber());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, _ID + " = ?", new String[]
                {String.valueOf(propertyModelClass.getId())});
    }

    //Delete property method
    public void deleteProperty(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, _ID + " =? ", new String[]
                {String.valueOf(id)});
    }

    public void deleteReport(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME_REPORT, PROPERTY_ID + " =? ", new String[]{String.valueOf(id)});
    }
}
