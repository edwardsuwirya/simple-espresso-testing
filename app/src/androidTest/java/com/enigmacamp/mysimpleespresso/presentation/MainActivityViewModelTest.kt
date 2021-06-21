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
    fun shouldReturnLiveDataSuccessMessage_whenSuccessAddNewSpent() {
        //Given
        val spentAmount = "1"
        val spentDescription = "Test New Spent"

        //When
        viewModel.addNewSpent(spentAmount, spentDescription)
        val result = viewModel.meessageNotificationLiveData.getOrAwaitValue()

        //Then
        assertThat(result).isEqualTo("Successfully add your spent")
    }

    @Test
    fun shouldReturnLiveDataSpentListMessage_whenSuccessGetRecentSpent() {
        //Given
        val spentAmount1 = "1"
        val spentDescription1 = "Test New Spent 1"

        val spentAmount2 = "2"
        val spentDescription2 = "Test New Spent 2"

        viewModel.addNewSpent(spentAmount1, spentDescription1)
        viewModel.addNewSpent(spentAmount2, spentDescription2)

        //When
        viewModel.getRecentSpent()
        val result = viewModel.spentListLiveData.getOrAwaitValue()

        //Then
        assertThat(result).isEqualTo("Test New Spent 1,Test New Spent 2")
    }
}