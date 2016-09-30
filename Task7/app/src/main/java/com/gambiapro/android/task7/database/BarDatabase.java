package com.gambiapro.android.task7.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.gambiapro.android.task7.R;
import com.gambiapro.android.task7.domain.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BarDatabase extends SQLiteOpenHelper {
    public static final String DB_FILE_NAME = "bar.sqlite";
    public static final int DB_VERSION = 1;

    public static final String TABLE_PRODUCT = "product";

    private final Context context;

    public BarDatabase(final Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);

        this.context = context;
    }

    public static String readRawTextFile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = readRawTextFile(context, R.raw.create_db);

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("Not implemented.");
    }

    @NonNull
    private ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("stock", product.getStock());

        return values;
    }

    @NonNull
    private Product getProduct(Cursor cursor) {
        return new Product(
                cursor.getLong(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getDouble(cursor.getColumnIndex("price")),
                cursor.getInt(cursor.getColumnIndex("stock"))
        );
    }

    public void create(Product product) {
        ContentValues values = getContentValues(product);

        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(TABLE_PRODUCT, null, values);
        product.setId(id);
        database.close();
    }

    public Product read(long id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_PRODUCT, null, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);

        return getProduct(cursor);
    }

    public List<Product> readAll() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_PRODUCT, null, null, null, null, null, null, null);

        List<Product> products = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            products.add(getProduct(cursor));
        }

        return products;
    }

    public int update(Product product) {
        ContentValues values = getContentValues(product);

        SQLiteDatabase database = getWritableDatabase();
        int affectedRows = database.update(TABLE_PRODUCT, values, "id=?", new String[]{
            String.valueOf(product.getId())
        });
        database.close();

        return affectedRows;
    }

    public int delete(Product product) {
        SQLiteDatabase database = getWritableDatabase();
        int affectedRows = database.delete(TABLE_PRODUCT, "id=?", new String[]{
                String.valueOf(product.getId())
        });
        database.close();

        return affectedRows;
    }

}
