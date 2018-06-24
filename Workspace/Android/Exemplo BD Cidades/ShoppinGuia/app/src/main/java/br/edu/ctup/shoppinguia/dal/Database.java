package br.edu.ctup.shoppinguia.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.edu.ctup.shoppinguia.model.User;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "ShoppinGuia.db";
    public static final int DB_VERSION = 1;

    public static final String TYPE_TEXT = " TEXT";
    public static final String TYPE_INTEGER = " INTEGER";
    public static final String NOT_NULL = " NOT NULL";
    public static final String UNIQUE = " UNIQUE";
    public static final String PRIMARY_KEY = " PRIMARY KEY AUTOINCREMENT";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    public static final String COMMA = ", ";

    DateFormat df = new SimpleDateFormat("dd-mm-yyyy, HH:mm:ss");

    public static final String SQL_CREATE_TABLE_CATEGORY =
            CREATE_TABLE + Contract.TableCategory.TABLE_NAME +
                    "( " + Contract.TableCategory.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableCategory.NAME + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableCategory.DESCRIPTION + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableCategory.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableCategory.UPDATED_AT + TYPE_TEXT + ");";

    public static final String SQL_CREATE_TABLE_MALL =
            CREATE_TABLE + Contract.TableMall.TABLE_NAME +
                    "( " + Contract.TableMall.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableMall.NAME + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableMall.OPERATING_HOUR + TYPE_TEXT + COMMA +
                    Contract.TableMall.DESCRIPTION + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableMall.DOCUMENT + TYPE_INTEGER + NOT_NULL + UNIQUE + COMMA +
                    Contract.TableMall.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableMall.UPDATED_AT + TYPE_TEXT + COMMA +
                    Contract.TableMall.DELETED_AT + TYPE_TEXT + ");";

    public static final String SQL_CREATE_TABLE_MALL_USER =
            CREATE_TABLE + Contract.TableMallUser.TABLE_NAME +
                    "( " + Contract.TableMallUser.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableMallUser.MALL_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableMallUser.USER_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableMallUser.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    "FOREIGN KEY(" + Contract.TableMallUser.MALL_ID + ") REFERENCES " + Contract.TableMall.TABLE_NAME + "(" + Contract.TableMall.ID + ")" + COMMA +
                    "FOREIGN KEY(" + Contract.TableMallUser.USER_ID + ") REFERENCES " + Contract.TableUser.TABLE_NAME + "(" + Contract.TableUser.ID + "));";

    public static final String SQL_CREATE_TABLE_NOTIFICATION =
            CREATE_TABLE + Contract.TableNotification.TABLE_NAME +
                    "( " + Contract.TableNotification.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableNotification.DESCRIPTION + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableNotification.SHOP_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableNotification.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableNotification.UPDATED_AT + TYPE_TEXT + COMMA +
                    Contract.TableNotification.DELETED_AT + TYPE_TEXT + COMMA +
                    "FOREIGN KEY(" + Contract.TableNotification.SHOP_ID + ") REFERENCES " + Contract.TableShop.TABLE_NAME + "(" + Contract.TableShop.ID + "));";

    public static final String SQL_CREATE_TABLE_PROMOTION =
            CREATE_TABLE + Contract.TablePromotion.TABLE_NAME +
                    "( " + Contract.TablePromotion.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TablePromotion.NAME + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TablePromotion.DESCRIPTION + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TablePromotion.WINNER_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TablePromotion.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TablePromotion.UPDATED_AT + TYPE_TEXT + COMMA +
                    Contract.TablePromotion.EXPIRY_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    "FOREIGN KEY(" + Contract.TablePromotion.WINNER_ID + ") REFERENCES " + Contract.TableUser.TABLE_NAME + "(" + Contract.TableUser.ID + "));";

    public static final String SQL_CREATE_TABLE_ROLE =
            CREATE_TABLE + Contract.TableRole.TABLE_NAME +
                    "( " + Contract.TableRole.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableRole.NAME + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableRole.CREATED_AT + TYPE_TEXT + NOT_NULL + ");";

    public static final String SQL_CREATE_TABLE_SHOP =
            CREATE_TABLE + Contract.TableShop.TABLE_NAME +
                    "( " + Contract.TableShop.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableShop.NAME + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableShop.PHONE + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableShop.OPERATING_HOUR + TYPE_TEXT + COMMA +
                    Contract.TableShop.STATUS + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableShop.DOCUMENT + TYPE_INTEGER + NOT_NULL + UNIQUE + COMMA +
                    Contract.TableShop.MALL_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableShop.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableShop.UPDATED_AT + TYPE_TEXT + COMMA +
                    Contract.TableShop.DELETED_AT + TYPE_TEXT + COMMA +
                    "FOREIGN KEY(" + Contract.TableShop.MALL_ID + ") REFERENCES " + Contract.TableMall.TABLE_NAME + "(" + Contract.TableMall.ID + "));";

    public static final String SQL_CREATE_TABLE_SHOP_CATEGORY =
            CREATE_TABLE + Contract.TableShopCategory.TABLE_NAME +
                    "( " + Contract.TableShopCategory.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableShopCategory.CATEGORY_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableShopCategory.SHOP_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableShopCategory.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    "FOREIGN KEY(" + Contract.TableShopCategory.CATEGORY_ID + ") REFERENCES " + Contract.TableCategory.TABLE_NAME + "(" + Contract.TableCategory.ID + ")" + COMMA +
                    "FOREIGN KEY(" + Contract.TableShopCategory.SHOP_ID + ") REFERENCES " + Contract.TableShop.TABLE_NAME + "(" + Contract.TableShop.ID + "));";

    public static final String SQL_CREATE_TABLE_SHOP_USER =
            CREATE_TABLE + Contract.TableShopUser.TABLE_NAME +
                    "( " + Contract.TableShopUser.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableShopUser.SHOP_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableShopUser.USER_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableShopUser.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    "FOREIGN KEY(" + Contract.TableShopUser.USER_ID + ") REFERENCES " + Contract.TableUser.TABLE_NAME + "(" + Contract.TableUser.ID + ")" + COMMA +
                    "FOREIGN KEY(" + Contract.TableShopUser.SHOP_ID + ") REFERENCES " + Contract.TableShop.TABLE_NAME + "(" + Contract.TableShop.ID + "));";

    public static final String SQL_CREATE_TABLE_USER =
            CREATE_TABLE + Contract.TableUser.TABLE_NAME +
                    "( " + Contract.TableUser.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableUser.NAME + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableUser.EMAIL + TYPE_TEXT + NOT_NULL + UNIQUE + COMMA +
                    Contract.TableUser.PASSWORD + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableUser.PHONE + TYPE_TEXT + COMMA +
                    Contract.TableUser.TOKEN + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableUser.ROLE_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableUser.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableUser.UPDATED_AT + TYPE_TEXT + COMMA +
                    Contract.TableUser.DELETED_AT + TYPE_TEXT + COMMA +
                    "FOREIGN KEY(" + Contract.TableUser.ROLE_ID + ") REFERENCES " + Contract.TableRole.TABLE_NAME + "(" + Contract.TableRole.ID + "));";

    public static final String SQL_CREATE_TABLE_USER_FAVORITE =
            CREATE_TABLE + Contract.TableUserFavorite.TABLE_NAME +
                    "( " + Contract.TableUserFavorite.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableUserFavorite.SHOP_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableUserFavorite.USER_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableUserFavorite.CREATED_AT + TYPE_INTEGER + NOT_NULL + COMMA +
                    "FOREIGN KEY(" + Contract.TableUserFavorite.USER_ID + ") REFERENCES " + Contract.TableUser.TABLE_NAME + "(" + Contract.TableUser.ID + ")" + COMMA +
                    "FOREIGN KEY(" + Contract.TableUserFavorite.SHOP_ID + ") REFERENCES " + Contract.TableShop.TABLE_NAME + "(" + Contract.TableShop.ID + "));";

    public static final String SQL_CREATE_TABLE_USER_HISTORY =
            CREATE_TABLE + Contract.TableUserHistory.TABLE_NAME +
                    "( " + Contract.TableUserHistory.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableUserHistory.TYPE + TYPE_TEXT + NOT_NULL + COMMA +
                    Contract.TableUserHistory.USER_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableUserHistory.SHOP_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableUserHistory.PROMOTION_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableUserHistory.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    "FOREIGN KEY(" + Contract.TableUserHistory.USER_ID + ") REFERENCES " + Contract.TableUser.TABLE_NAME + "(" + Contract.TableUser.ID + ")" + COMMA +
                    "FOREIGN KEY(" + Contract.TableUserHistory.PROMOTION_ID + ") REFERENCES " + Contract.TablePromotion.TABLE_NAME + "(" + Contract.TablePromotion.ID + ")" + COMMA +
                    "FOREIGN KEY(" + Contract.TableUserHistory.SHOP_ID + ") REFERENCES " + Contract.TableShop.TABLE_NAME + "(" + Contract.TableShop.ID + "));";

    public static final String SQL_CREATE_TABLE_USER_PROMOTION =
            CREATE_TABLE + Contract.TableUserPromotion.TABLE_NAME +
                    "( " + Contract.TableUserPromotion.ID + TYPE_INTEGER + PRIMARY_KEY + COMMA +
                    Contract.TableUserPromotion.PROMOTION_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableUserPromotion.USER_ID + TYPE_INTEGER + NOT_NULL + COMMA +
                    Contract.TableUserPromotion.CREATED_AT + TYPE_TEXT + NOT_NULL + COMMA +
                    "FOREIGN KEY(" + Contract.TableUserPromotion.PROMOTION_ID + ") REFERENCES " + Contract.TablePromotion.TABLE_NAME + "(" + Contract.TablePromotion.ID + ")" + COMMA +
                    "FOREIGN KEY(" + Contract.TableUserPromotion.USER_ID + ") REFERENCES " + Contract.TableUser.TABLE_NAME + "(" + Contract.TableUser.ID + "));";


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("Category: ", SQL_CREATE_TABLE_CATEGORY);
        Log.v("Mall: ", SQL_CREATE_TABLE_MALL);
        Log.v("Mall_user: ", SQL_CREATE_TABLE_MALL_USER);
        Log.v("Notification: ", SQL_CREATE_TABLE_NOTIFICATION);
        Log.v("Promotion: ", SQL_CREATE_TABLE_PROMOTION);
        Log.v("Role: ", SQL_CREATE_TABLE_ROLE);
        Log.v("Shop: ", SQL_CREATE_TABLE_SHOP);
        Log.v("Shop_category: ", SQL_CREATE_TABLE_SHOP_CATEGORY);
        Log.v("Shop_user: ", SQL_CREATE_TABLE_SHOP_USER);
        Log.v("User: ", SQL_CREATE_TABLE_USER);
        Log.v("User_favorite: ", SQL_CREATE_TABLE_USER_FAVORITE);
        Log.v("User_history: ", SQL_CREATE_TABLE_USER_HISTORY);
        Log.v("User_promotion: ", SQL_CREATE_TABLE_USER_PROMOTION);

        db.execSQL(SQL_CREATE_TABLE_CATEGORY);
        db.execSQL(SQL_CREATE_TABLE_MALL);
        db.execSQL(SQL_CREATE_TABLE_ROLE);
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_MALL_USER);
        db.execSQL(SQL_CREATE_TABLE_NOTIFICATION);
        db.execSQL(SQL_CREATE_TABLE_PROMOTION);
        db.execSQL(SQL_CREATE_TABLE_SHOP);
        db.execSQL(SQL_CREATE_TABLE_SHOP_CATEGORY);
        db.execSQL(SQL_CREATE_TABLE_SHOP_USER);
        db.execSQL(SQL_CREATE_TABLE_USER_FAVORITE);
        db.execSQL(SQL_CREATE_TABLE_USER_HISTORY);
        db.execSQL(SQL_CREATE_TABLE_USER_PROMOTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public User userLogin(String login, String password){
        User user = new User();

        SQLiteDatabase db = getWritableDatabase();

        String where = Contract.TableUser.EMAIL + " = ? AND " + Contract.TableUser.PASSWORD + " = ?";

        String[] valuesWhere = new String[]{
                login,
                password
        };

        String[] columns = new String[]{
                Contract.TableUser.ID,
                Contract.TableUser.NAME,
                Contract.TableUser.EMAIL,
                Contract.TableUser.PASSWORD,
                Contract.TableUser.PHONE,
                Contract.TableUser.TOKEN,
                Contract.TableUser.CREATED_AT,
                Contract.TableUser.ROLE_ID
        };

        Cursor cursor = db.query(
                Contract.TableUser.TABLE_NAME,
                columns,
                where,
                valuesWhere,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        if (cursor.getCount() > 0){

            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            user.setPhone(cursor.getString(4));
            user.setToken(cursor.getString(5));
//            u.setCreated_at();
//            u.setRole();
        }

        return user;
    }

    public Boolean userRegister(User u) {

        SQLiteDatabase db = getWritableDatabase();

        String where = Contract.TableUser.EMAIL + " = ?";

        String[] valuesWhere = new String[]{
                u.getEmail()
        };

        Cursor cursor = db.query(
                Contract.TableUser.TABLE_NAME,
                null,
                where,
                valuesWhere,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        if(cursor.getCount() == 0){

            ContentValues data = new ContentValues();

            data.put(Contract.TableUser.NAME, u.getName());
            data.put(Contract.TableUser.EMAIL, u.getEmail());
            data.put(Contract.TableUser.PASSWORD, u.getPassword());
            data.put(Contract.TableUser.PHONE, u.getPhone() != null ? u.getPhone() : null);
            data.put(Contract.TableUser.TOKEN, u.getToken());
            data.put(Contract.TableUser.CREATED_AT, df.format(Calendar.getInstance().getTime()));
            data.put(Contract.TableUser.ROLE_ID, u.getRole_id());

            db.insert(Contract.TableUser.TABLE_NAME, null, data);

            return true;
        }

        return false;
    }

    public ArrayList<User> returnUsers(){
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<User> users = new ArrayList<User>();
        User u = new User();

        String[] columns = new String[]{
                Contract.TableUser.ID,
                Contract.TableUser.NAME,
                Contract.TableUser.EMAIL,
                Contract.TableUser.PASSWORD,
                Contract.TableUser.PHONE,
                Contract.TableUser.TOKEN,
                Contract.TableUser.CREATED_AT,
                Contract.TableUser.ROLE_ID
        };

        Cursor cursor = db.query(
                Contract.TableUser.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                Contract.TableUser.CREATED_AT + " DESC"
        );

        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do {
                //Preecher a lista com os dados cursor
                u = new User();
                u.setName(cursor.getString(1));
                u.setEmail(cursor.getString(2));
                u.setPassword(cursor.getString(3));
                u.setPhone(cursor.getString(4));
                u.setToken(cursor.getString(5));
//                u.setCreated_at();
//                u.setRole();
                users.add(u);
            }while (cursor.moveToNext());

            return users;
        }
        return null;
    }
}
