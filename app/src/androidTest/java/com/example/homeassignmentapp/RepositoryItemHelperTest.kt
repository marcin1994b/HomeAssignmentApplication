package com.example.homeassignmentapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.homeassignmentapp.adapter.repository.RepositoryItem
import com.example.homeassignmentapp.adapter.repository.getAvatarUrl
import com.example.homeassignmentapp.adapter.repository.getDescription
import com.example.homeassignmentapp.adapter.repository.getOwnerName
import com.example.homeassignmentapp.adapter.repository.getRepositoryName
import com.example.homeassignmentapp.adapter.repository.getRepositoryTitle
import com.example.homeassignmentapp.adapter.repository.getUrl
import com.example.homeassignmentapp.model.Repository
import com.example.homeassignmentapp.model.RepositoryOwner

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class RepositoryItemHelperTest {

    @Test
    fun repositoryItemWithValues() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val item = RepositoryItem(
            id = 123,
            repository = Repository(
                id = 123,
                name = "testName",
                fullName = "testFullName",
                description = "testDescription",
                url = "testURL",
                owner = RepositoryOwner(
                    "testOwnerName",
                    "testOwnerAvatarURL"
                )
            )
        )

        assertEquals("Name: testName", item.getRepositoryName(appContext))
        assertEquals("Title: testFullName", item.getRepositoryTitle(appContext))
        assertEquals("Description: \ntestDescription", item.getDescription(appContext))
        assertEquals("URL: testURL", item.getUrl(appContext))
        assertEquals("Owner: testOwnerName", item.getOwnerName(appContext))
        assertEquals("testOwnerAvatarURL", item.getAvatarUrl())
    }

    @Test
    fun repositoryItemWithoutValues() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val item = RepositoryItem(
            id = 123,
            repository = Repository(
                id = 123,
                name = null,
                fullName = null,
                description = null,
                url = null,
                owner = RepositoryOwner(
                    null,
                    null
                )
            )
        )

        assertEquals("Name: Unknown", item.getRepositoryName(appContext))
        assertEquals("Title: Unknown", item.getRepositoryTitle(appContext))
        assertEquals("Description: \nUnknown", item.getDescription(appContext))
        assertEquals("URL: Unknown", item.getUrl(appContext))
        assertEquals("Owner: Unknown", item.getOwnerName(appContext))
        assertEquals(null, item.getAvatarUrl())
    }

    @Test
    fun repositoryItemWithoutOwner() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val item = RepositoryItem(
            id = 123,
            repository = Repository(
                id = 123,
                name = null,
                fullName = null,
                description = null,
                url = null,
                owner = null
            )
        )

        assertEquals("Owner: Unknown", item.getOwnerName(appContext))
        assertEquals(null, item.getAvatarUrl())
    }
}