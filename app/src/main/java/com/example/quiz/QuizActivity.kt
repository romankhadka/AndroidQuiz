//import android.app.AlertDialog
//import android.os.Bundle
//import android.widget.Button
//import android.widget.CheckBox
//import android.widget.RadioButton
//import android.widget.RadioGroup
//import androidx.appcompat.app.AppCompatActivity
//import com.example.quiz.R
//import java.text.SimpleDateFormat
//import java.util.*
//
//class QuizActivity : AppCompatActivity() {
//
//    private lateinit var radioGroup: RadioGroup
//    private lateinit var checkBox1: CheckBox
//    private lateinit var checkBox2: CheckBox
//    private lateinit var checkBox3: CheckBox
//    private lateinit var checkBox4: CheckBox
//    private lateinit var submitButton: Button
//    private lateinit var resetButton: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        radioGroup = findViewById(R.id.radioGroup)
//        checkBox1 = findViewById(R.id.checkBox1)
//        checkBox2 = findViewById(R.id.checkBox2)
//        checkBox3 = findViewById(R.id.checkBox3)
//        checkBox4 = findViewById(R.id.checkBox4)
//        submitButton = findViewById(R.id.submitButton)
//        resetButton = findViewById(R.id.resetButton)
//
//        submitButton.setOnClickListener { onSubmitButtonClick() }
//        resetButton.setOnClickListener { onResetButtonClick() }
//    }
//
//    private fun onSubmitButtonClick() {
//        val score = calculateScore()
//        showResultDialog(score)
//    }
//
//    private fun calculateScore(): Int {
//        val correctAnswers = 2
//        return correctAnswers * 50
//    }
//
//    private fun showResultDialog(score: Int) {
//        val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
//        val message = "Congratulations! You submitted on $currentDate, and you achieved ${score}%."
//
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Quiz Result")
//            .setMessage(message)
//            .setPositiveButton("OK") { _, _ ->
//                // Handle OK button click if needed
//            }
//            .show()
//    }
//
//    private fun onResetButtonClick() {
//        // Clear selected choices and reset the UI
//        radioGroup.clearCheck()
//        checkBox1.isChecked = false
//        checkBox2.isChecked = false
//        checkBox3.isChecked = false
//        checkBox4.isChecked = false
//    }
//}

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.R
import java.text.SimpleDateFormat
import java.util.*

class QuizActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var submitButton: Button
    private lateinit var resetButton: Button
    private lateinit var questionTextView1: TextView
    private lateinit var questionTextView2: TextView

    private var correctAnswerQuestion1: Int = 0
    private var correctAnswersQuestion2: MutableSet<Int> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup)
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        submitButton = findViewById(R.id.submitButton)
        resetButton = findViewById(R.id.resetButton)
        questionTextView1 = findViewById(R.id.questionTextView1)
        questionTextView2 = findViewById(R.id.questionTextView2)

        questionTextView1.text = "Question 1: What is the capital of France?"
        questionTextView2.text = "Question 2: Which programming languages are used in Android development?"

        correctAnswerQuestion1 = (0..3).random()

        val numberOfCorrectAnswersQuestion2 = (1..4).random()
        while (correctAnswersQuestion2.size < numberOfCorrectAnswersQuestion2) {
            correctAnswersQuestion2.add((0..3).random())
        }

        submitButton.setOnClickListener { onSubmitButtonClick() }
        resetButton.setOnClickListener { onResetButtonClick() }
    }

    private fun onSubmitButtonClick() {
        val score = calculateScore()
        showResultDialog(score)
    }

    private fun calculateScore(): Int {
        var score = 0

        val selectedAnswerQuestion1 = radioGroup.indexOfChild(findViewById(radioGroup.checkedRadioButtonId))
        if (selectedAnswerQuestion1 == correctAnswerQuestion1) {
            score += 50
        }

        if (checkBox1.isChecked && correctAnswersQuestion2.contains(0)) {
            score += 12
        }
        if (checkBox2.isChecked && correctAnswersQuestion2.contains(1)) {
            score += 12
        }
        if (checkBox3.isChecked && correctAnswersQuestion2.contains(2)) {
            score += 12
        }
        if (checkBox4.isChecked && correctAnswersQuestion2.contains(3)) {
            score += 12
        }

        return score
    }

    private fun showResultDialog(score: Int) {
        val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val message = "Congratulations! You submitted on $currentDate, and you achieved ${score}%."

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quiz Result")
            .setMessage(message)
            .setPositiveButton("OK") { _, _ ->
                // Handle OK button click if needed
            }
            .show()
    }

    private fun onResetButtonClick() {
        radioGroup.clearCheck()
        checkBox1.isChecked = false
        checkBox2.isChecked = false
        checkBox3.isChecked = false
        checkBox4.isChecked = false
    }
}
