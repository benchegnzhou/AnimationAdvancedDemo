package com.ztsc.house.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.ztsc.house.entity.SystemMessageEntity;

import java.sql.SQLException;


/**
 * Created by benchengzhou on 2017/6/28  12:26 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： SystemMessagerDatabaseHelper
 * 备    注：
 * 这里我们需要继承OrmLiteSqliteOpenHelper，其实就是间接继承了SQLiteOpenHelper
 * 然后需要实现两个方法：
 * 1、onCreate(SQLiteDatabase database,ConnectionSource connectionSource)
 * 创建表，我们直接使用ormlite提供的TableUtils.createTable(connectionSource, User.class);进行创建~
 * 2、onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
 * 更新表，使用ormlite提供的TableUtils.dropTable(connectionSource, User.class, true);进行删除操作~
 * 删除完成后，别忘了，创建操作：onCreate(database, connectionSource);
 */

public class SystemMessagerDatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String TABLE_NAME = "sqlite-test.db";
    /**
     * systemMessageDao ，每张表对于一个
     */
    private Dao<SystemMessageEntity, Integer> systemMessageDao;

    private SystemMessagerDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, SystemMessageEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, SystemMessageEntity.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SystemMessagerDatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized SystemMessagerDatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (SystemMessagerDatabaseHelper.class) {
                if (instance == null)
                    instance = new SystemMessagerDatabaseHelper(context);
            }
        }

        return instance;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<SystemMessageEntity, Integer> getSystemMessageDao() throws SQLException {
        if (systemMessageDao == null) {
            systemMessageDao = getDao(SystemMessageEntity.class);
        }
        return systemMessageDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        systemMessageDao = null;
    }


}
