package br.edu.ctup.shoppinguia.dal;

import android.provider.BaseColumns;

public class Contract {

    private Contract(){}

    public static class TableCategory implements BaseColumns {
        public static final String TABLE_NAME = "Category";
        public static final String ID = "Id";
        public static final String NAME = "Name";
        public static final String DESCRIPTION = "Description";
        public static final String CREATED_AT = "Created_at";
        public static final String UPDATED_AT = "Updated_at";
    }

    public static class TableMall implements BaseColumns{
        public static final String TABLE_NAME = "Mall";
        public static final String ID = "Id";
        public static final String NAME = "Name";
        public static final String OPERATING_HOUR = "Operating_hour";
        public static final String DESCRIPTION = "Description";
        public static final String DOCUMENT = "Document";
        public static final String CREATED_AT = "Created_at";
        public static final String UPDATED_AT = "Updated_at";
        public static final String DELETED_AT = "Deleted_at";
    }

    public static class TableMallUser implements BaseColumns{
        public static final String TABLE_NAME = "Mall_user";
        public static final String ID = "Id";
        public static final String MALL_ID = "Mall_id";
        public static final String USER_ID = "User_id";
        public static final String CREATED_AT = "Created_at";
    }

    public static class TableNotification implements BaseColumns{
        public static final String TABLE_NAME = "Notification";
        public static final String ID = "Id";
        public static final String DESCRIPTION = "Description";
        public static final String SHOP_ID = "Shop_id";
        public static final String CREATED_AT = "Created_at";
        public static final String UPDATED_AT = "Updated_at";
        public static final String DELETED_AT = "Deleted_at";
    }

    public static class TablePromotion implements BaseColumns{
        public static final String TABLE_NAME = "Promotion";
        public static final String ID = "Id";
        public static final String NAME = "Name";
        public static final String DESCRIPTION = "Description";
        public static final String WINNER_ID = "Winner_id";
        public static final String CREATED_AT = "Created_at";
        public static final String UPDATED_AT = "Updated_at";
        public static final String EXPIRY_AT = "Expiry_at";
    }

    public static class TableRole implements BaseColumns{
        public static final String TABLE_NAME = "Role";
        public static final String ID = "Id";
        public static final String NAME = "Name";
        public static final String CREATED_AT = "Created_at";
    }

    public static class TableShop implements BaseColumns{
        public static final String TABLE_NAME = "Shop";
        public static final String ID = "Id";
        public static final String NAME = "Name";
        public static final String PHONE = "Phone";
        public static final String OPERATING_HOUR = "Operating_Hour";
        public static final String STATUS = "Status";
        public static final String DOCUMENT = "Document";
        public static final String MALL_ID = "Mall_id";
        public static final String CREATED_AT = "Created_at";
        public static final String UPDATED_AT = "Updated_at";
        public static final String DELETED_AT = "Deleted_at";
    }

    public static class TableShopCategory implements BaseColumns{
        public static final String TABLE_NAME = "Shop_category";
        public static final String ID = "Id";
        public static final String CATEGORY_ID = "Category_id";
        public static final String SHOP_ID = "Shop_id";
        public static final String CREATED_AT = "Created_at";
    }

    public static class TableShopUser implements BaseColumns{
        public static final String TABLE_NAME = "Shop_user";
        public static final String ID = "Id";
        public static final String SHOP_ID = "Shop_id";
        public static final String USER_ID = "User_id";
        public static final String CREATED_AT = "Created_at";
    }

    public static class TableUser implements BaseColumns{
        public static final String TABLE_NAME = "User";
        public static final String ID = "Id";
        public static final String NAME = "Name";
        public static final String EMAIL = "Email";
        public static final String PASSWORD = "Password";
        public static final String PHONE = "Phone";
        public static final String TOKEN = "Token";
        public static final String ROLE_ID = "Role_id";
        public static final String CREATED_AT = "Created_at";
        public static final String UPDATED_AT = "Updated_at";
        public static final String DELETED_AT = "Deleted_at";
    }

    public static class TableUserFavorite implements BaseColumns{
        public static final String TABLE_NAME = "User_favorite";
        public static final String ID = "Id";
        public static final String SHOP_ID = "Shop_id";
        public static final String USER_ID = "User_id";
        public static final String CREATED_AT = "Created_at";
    }

    public static class TableUserHistory implements BaseColumns{
        public static final String TABLE_NAME = "User_history";
        public static final String ID = "Id";
        public static final String TYPE = "Type";
        public static final String USER_ID = "User_id";
        public static final String SHOP_ID = "Shop_id";
        public static final String PROMOTION_ID = "Promotion_id";
        public static final String CREATED_AT = "Created_at";
    }

    public static class TableUserPromotion implements BaseColumns{
        public static final String TABLE_NAME = "User_promotion";
        public static final String ID = "Id";
        public static final String PROMOTION_ID = "Promotion_id";
        public static final String USER_ID = "User_id";
        public static final String CREATED_AT = "Created_at";
    }
}
