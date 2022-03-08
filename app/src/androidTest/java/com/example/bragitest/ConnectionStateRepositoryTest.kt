package com.example.bragitest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bragitest.data.ConnectionStateRepository
import com.example.bragitest.data.ConnectionStateSource
import com.example.bragitest.data.model.ConnectionState
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ConnectionStateRepositoryTest {

    private lateinit var testScheduler: TestScheduler
    private val connectionStateSource = ConnectionStateSource()

    @Before
    fun before() {
        testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler {
            return@setComputationSchedulerHandler testScheduler
        }
    }

    @After
    fun after() {
        RxJavaPlugins.setComputationSchedulerHandler(null);
    }

    @Test
    fun testConnectionStateSource() {
        val connectionStateRepository = ConnectionStateRepository(connectionStateSource)

        val testObserver: TestObserver<ConnectionState> = TestObserver()
        connectionStateRepository.getConnectionStateObservable().subscribeOn(testScheduler)
            .subscribe(testObserver)

        testObserver.assertNoValues()
        testObserver.assertNotComplete()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(2)
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(2)
    }
}
