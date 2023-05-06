package com.example.srifin

import androidx.test.core.app.ActivityScenario
import com.google.firebase.database.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.util.regex.Pattern.matches

class AmountTest {

    private lateinit var amountRef: DatabaseReference

    @Before
    fun setUp() {
        amountRef = FirebaseDatabase.getInstance().getReference("test_amounts").push()
    }

    @After
    fun tearDown() {
        amountRef.removeValue()
    }

    @Test
    fun enterAmount_navigateToReportActivity() {
        ActivityScenario.launch(Amount::class.java)
       // onView(withId(R.id.editTextTextPersonName3)).perform(typeText("100"))
        //onView(withId(R.id.button20)).perform(click())
        amountRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                assertEquals("100", snapshot.getValue(String::class.java))
            }

            override fun onCancelled(error: DatabaseError) {
                fail("Database read failed: ${error.message}")
            }
        })
     //   onView(withId(R.id.report_layout)).check(matches(isDisplayed()))
    }

    private fun isDisplayed(): String {
        TODO("Not yet implemented")
    }

    private fun click() {

    }

    private fun withId(editTextTextPersonName3: Int) {

    }

    private fun onView(withId: Any) {

    }

    private fun typeText(s: String) {

    }
}
