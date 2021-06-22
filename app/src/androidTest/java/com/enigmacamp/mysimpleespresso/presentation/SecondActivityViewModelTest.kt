package com.enigmacamp.mysimpleespresso.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.data.SpentDatabase
import com.enigmacamp.mysimpleespresso.repository.SpentRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class SecondActivityViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var spentDatabase: SpentDatabase
    lateinit var spentRepository: SpentRepository
    lateinit var viewModel: SecondActivityViewModel

    @Before
    fun setUp() {
        spentDatabase = SpentDatabase()
        spentRepository = SpentRepository(spentDatabase)
        viewModel = SecondActivityViewModel(spentRepository)
    }

    @Test
    fun shouldReturnLiveDataSpentListMessage_whenSuccessGetRecentSpent() = runBlocking {
        //Given
        spentDatabase.add(Spent(spentAmount = 1.0, spentDescription = "Test 1", spentDate = Date()))
        spentDatabase.add(Spent(spentAmount = 2.0, spentDescription = "Test 2", spentDate = Date()))

        //When
        viewModel.getRecentSpent()
        val result = viewModel.spentListLiveData.getOrAwaitValue()

        //Then
        Truth.assertThat(result.size).isEqualTo(2)
    }
}