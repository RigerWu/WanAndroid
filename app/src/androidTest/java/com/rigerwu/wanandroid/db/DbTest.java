package com.rigerwu.wanandroid.db;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.rigerwu.wanandroid.Data.db.AppDatabase;

import org.junit.After;
import org.junit.Before;

/**
 * Created by RigerWu on 2018/5/28.
 */
public abstract class DbTest {

    protected AppDatabase db;

    @Before
    public void initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase.class).build();
    }

    @After
    public void closeDb() {
        db.close();
    }
}
