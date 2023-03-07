package com.example.homeassignmentapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.homeassignmentapp.adapter.repository.RepositoryItem
import com.example.homeassignmentapp.adapter.repository.getAvatarUrl
import com.example.homeassignmentapp.adapter.repository.getDescription
import com.example.homeassignmentapp.adapter.repository.getOwnerName
import com.example.homeassignmentapp.adapter.repository.getRepositoryName
import com.example.homeassignmentapp.adapter.repository.getRepositoryTitle
import com.example.homeassignmentapp.adapter.repository.getUrl
import com.example.homeassignmentapp.model.Repositories
import com.example.homeassignmentapp.model.Repository
import com.example.homeassignmentapp.model.RepositoryOwner
import com.example.homeassignmentapp.model.getTotalCount
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoriesHelperTest {

    @Test
    fun repositoriesDataWithValues() {
        val repositories = Repositories(
            totalCount = 123,
            items = listOf(Repository(null, null, null, null, null, null))
        )

        Assert.assertEquals(123, repositories.getTotalCount())
        Assert.assertEquals(1, repositories.items?.size)
    }

    @Test
    fun repositoriesDataWithoutValues() {
        val repositories = Repositories(
            totalCount = null,
            items = null
        )

        Assert.assertEquals(0, repositories.getTotalCount())
        Assert.assertEquals(null, repositories.items?.size)
    }

}