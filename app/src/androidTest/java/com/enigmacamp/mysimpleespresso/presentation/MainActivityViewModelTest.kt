package com.enigmacamp.mysimpleespresso.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enigmacamp.mysimpleespresso.data.SpentDatabase
import com.enigmacamp.mysimpleespresso.repository.SpentRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var spentDatabase: SpentDatabase
    lateinit var spentRepository: SpentRepository
    lateinit var viewModel: MainActivityViewModel

    @Before
    fun setUp() {
        spentDatabase = SpentDatabase()
        spentRepository = SpentRepository(spentDatabase)
        viewModel = MainActivityViewModel(spentRepository)
    }

    @Test
    fun testAddNewSpent_shouldReturnLiveDataSuccessMessage_whenSuccess() {
        viewModel.addNewSpent("1", "Test New Spent")
        val result = viewModel.meessageNotificationLiveData.getOrAwaitValue()
        assertThat(result).isEqualTo("Successfully add your spent")
    }

    @Test
    fun testGetRecentSpent_shouldReturnLiveDataSpentListMessage_whenSuccess() {
        viewModel.addNewSpent("1", "Test New Spent 1")
        viewModel.addNewSpent("2", "Test New Spent 2")
        viewModel.getRecentSpent()
        val result = viewModel.spentListLiveData.getOrAwaitValue()
        assertThat(result).isEqualTo("Test New Spent 1,Test New Spent 2")
    }
}