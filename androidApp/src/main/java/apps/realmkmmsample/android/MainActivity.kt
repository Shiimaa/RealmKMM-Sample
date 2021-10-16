package apps.realmkmmsample.android

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import apps.realmkmmsample.Calculator
import apps.realmkmmsample.Greeting
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    private lateinit var countTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.textView)
        tv.text = greet()

        val numATV: EditText = findViewById(R.id.editTextNumberDecimalA)
        val numBTV: EditText = findViewById(R.id.editTextNumberDecimalB)

        val sumTV: TextView = findViewById(R.id.textViewSum)
        countTV = findViewById(R.id.textHistoryCount)

//        val textWatcher = object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                try {
//                    val numA = Integer.parseInt(numATV.text.toString())
//                    val numB = Integer.parseInt(numBTV.text.toString())
//                    sumTV.text = "= ${Calculator.sum(numA, numB)}"
//                } catch (e: NumberFormatException) {
//                    sumTV.text = "= 🤔"
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//        }


        for (i in 0..10000) {
            GlobalScope.launch {
                Calculator.sum(i, i + 1)
            }
        }

        countTV.text = "History count: ${Calculator.history().size}"

//        numATV.addTextChangedListener(textWatcher)
//        numBTV.addTextChangedListener(textWatcher)
//
//


        lifecycleScope.launch {
            Calculator.listen().collect {
                countTV.text = "History count: ${Calculator.history().size}"
            }
        }
    }
}
