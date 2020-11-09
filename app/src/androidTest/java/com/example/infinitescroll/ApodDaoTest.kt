package com.example.infinitescroll

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.example.infinitescroll.data.local.ApodDao
import com.example.infinitescroll.data.local.AppDatabase
import com.example.infinitescroll.data.model.Apod
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class ApodDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var apodDao: ApodDao

    private val emptyApod = Apod(
            "",
            Calendar.getInstance(),
            "",
            "",
            "",
            "",
            "",
            "",
    )

    private val apod = Apod(
            "copyright",
            Calendar.getInstance(),
            "explanation",
            "hdUrl",
            "mediaType",
            "serviceVersion",
            "title",
            "url"
    )

    private val otherApod = Apod(
            "copyright",
            Calendar.getInstance().also { it.add(Calendar.DATE, -1) },
            "explanation",
            "hdUrl",
            "mediaType",
            "serviceVersion",
            "title",
            "url"
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        apodDao = database.apodDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    private suspend fun insertApod() = apodDao.insertApod(apod)

    @Test
    fun testGetNullApod() {
        val factory = apodDao.getApods()
        val list = (factory.create() as LimitOffsetDataSource).loadRange(0, 10)
        assertThat(list.size, equalTo(0))
    }

    @Test
    fun testInsertApod() = runBlocking {
        val id = insertApod()
        assertThat(id, equalTo(apod.date.timeInMillis))
    }

    @Test
    fun testGetApod() = runBlocking {
        insertApod()
        val factory = apodDao.getApods()
        val list = (factory.create() as LimitOffsetDataSource).loadRange(0, 10)
        assertThat(list.size, equalTo(1))
    }

    @Test
    fun testInsertApodReplace() = runBlocking {
        insertApod()
        apodDao.insertApod(emptyApod)

        val factory = apodDao.getApods()
        val list = (factory.create() as LimitOffsetDataSource).loadRange(0, 10)
        assertThat(list[0].copyright, equalTo(emptyApod.copyright))
    }

    @Test
    fun testInsertApodList() = runBlocking {
        apodDao.insertApodList(listOf(apod, otherApod))
        val factory = apodDao.getApods()
        val list = (factory.create() as LimitOffsetDataSource).loadRange(0, 10)
        assertThat(list.size, equalTo(2))
    }

    @Test
    fun testGetLastApod() = runBlocking {
        insertApod()
        apodDao.insertApod(otherApod)
        val apod = apodDao.getLastApod()
        assertThat(apod, `is`(notNullValue(Apod::class.java)))
        assertThat(apod!!.date.timeInMillis, equalTo(otherApod.date.timeInMillis))
    }

    @Test
    fun testGetApodDesc() = runBlocking {
        insertApod()
        apodDao.insertApod(otherApod)
        val factory = apodDao.getApods()
        val list = (factory.create() as LimitOffsetDataSource).loadRange(0, 10)
        assertThat(list[0].date.timeInMillis, equalTo(apod.date.timeInMillis))
    }

}