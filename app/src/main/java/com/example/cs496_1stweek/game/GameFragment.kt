package com.example.cs496_1stweek.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.GridView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.cs496_1stweek.R

class GameFragment : Fragment() {
    private var pos = 1
    private var life = 3
    private val resultHistoryList = mutableListOf<GameHistoryItem>()
    private val adapter = GameHistoryAdapter(resultHistoryList)

    private fun getRandomNum(): Int {
        val randomNum = (0..9).shuffled().take(4)
        return randomNum.joinToString(separator = "").toInt()
    }

    private fun getResult(answer: Int,ans1_t:TextView, ans2_t:TextView, ans3_t:TextView, ans4_t:TextView): Array<Int> {
        if(ans1_t.text==" "||ans2_t.text==" "||ans3_t.text==" "||ans4_t.text==" "){
            Toast.makeText(requireContext(),"4자리 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
            return arrayOf(0,0,0)
        }

        val currentAnswer : String = ans1_t.text.toString() + ans2_t.text + ans3_t.text + ans4_t.text

        var strike = 0
        var ball = 0

        if(answer == currentAnswer.toInt()) strike = 4
        else{
            var answerToString = answer.toString()
            if(answer<1000){ answerToString = "0"+answerToString }
            for (i in 0 until 4) {
                if (answerToString[i]==currentAnswer[i]) strike++
                for (j in 0 until 4){
                    if (i!=j && answerToString[j]==currentAnswer[i]) ball++
                }
            }
            if(strike==0 && ball==0) life--
        }

        val out = 3-life

        if(life == 0) {
            Toast.makeText(requireContext(), "3 out, you're dead", Toast.LENGTH_SHORT).show()
        } else {
            resultHistoryList.add(GameHistoryItem(currentAnswer, strike.toString(), ball.toString(), out.toString()))
            adapter.notifyDataSetChanged()
        }

        /*if(life==0) Toast.makeText(requireContext(), "3 out, you're dead", Toast.LENGTH_SHORT).show()
        else Toast.makeText(requireContext(),"$answer,$currentAnswer -> $strike/$ball/$out", Toast.LENGTH_SHORT).show()*/

        return arrayOf(strike, ball, out)
    }

    private fun writeOnButtons(num:Int, ans1:ImageButton, ans2:ImageButton, ans3:ImageButton, ans4:ImageButton, ans1_t:TextView, ans2_t:TextView, ans3_t:TextView, ans4_t:TextView) {
        when (pos) {
            1 -> {
                ans1_t.text=num.toString()
                if(ans2_t.text==num.toString()){ ans2_t.text=" " }
                else if(ans3_t.text==num.toString()){ ans3_t.text=" " }
                else if(ans4_t.text==num.toString()){ ans4_t.text=" " }
                ans1.setImageResource(R.drawable.button_default)
                ans2.setImageResource(R.drawable.input_text)
            }
            2 -> {
                ans2_t.text=num.toString()
                if(ans1_t.text==num.toString()){ ans1_t.text=" " }
                else if(ans3_t.text==num.toString()){ ans3_t.text=" " }
                else if(ans4_t.text==num.toString()){ ans4_t.text=" " }
                ans2.setImageResource(R.drawable.button_default)
                ans3.setImageResource(R.drawable.input_text)
            }
            3 -> {
                ans3_t.text=num.toString()
                if(ans1_t.text==num.toString()){ ans1_t.text=" " }
                else if(ans2_t.text==num.toString()){ ans2_t.text=" " }
                else if(ans4_t.text==num.toString()){ ans4_t.text=" " }
                ans3.setImageResource(R.drawable.button_default)
                ans4.setImageResource(R.drawable.input_text)
            }
            else -> {
                ans4_t.text=num.toString()
                if(ans1_t.text==num.toString()){ ans1_t.text=" " }
                else if(ans2_t.text==num.toString()){ ans2_t.text=" " }
                else if(ans3_t.text==num.toString()){ ans3_t.text=" " }
                ans4.setImageResource(R.drawable.button_default)
                ans1.setImageResource(R.drawable.input_text)
            }
        }

        if(pos++==4) pos=1
    }

    private fun changePos(to: Int, ans1: ImageButton, ans2: ImageButton, ans3: ImageButton, ans4: ImageButton){
        when (pos) {
            1 -> ans1.setImageResource(R.drawable.button_default)
            2 -> ans2.setImageResource(R.drawable.button_default)
            3 -> ans3.setImageResource(R.drawable.button_default)
            4 -> ans4.setImageResource(R.drawable.button_default)
        }
        when (to) {
            1 -> ans1.setImageResource(R.drawable.input_text)
            2 -> ans2.setImageResource(R.drawable.input_text)
            3 -> ans3.setImageResource(R.drawable.input_text)
            4 -> ans4.setImageResource(R.drawable.input_text)
        }
        pos=to
        return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gameView = inflater.inflate(R.layout.game_frag, container, false)
        val gridView : GridView = gameView.findViewById(R.id.result_table)

        gridView.adapter = adapter

        val numberA = getRandomNum()
        Log.d("printed:numberToGuess", numberA.toString())

        val writeButton1 : ImageButton = gameView.findViewById(R.id.button1)
        val writeButton2 : ImageButton = gameView.findViewById(R.id.button2)
        val writeButton3 : ImageButton = gameView.findViewById(R.id.button3)
        val writeButton4 : ImageButton = gameView.findViewById(R.id.button4)
        val writeButton5 : ImageButton = gameView.findViewById(R.id.button5)
        val writeButton6 : ImageButton = gameView.findViewById(R.id.button6)
        val writeButton7 : ImageButton = gameView.findViewById(R.id.button7)
        val writeButton8 : ImageButton = gameView.findViewById(R.id.button8)
        val writeButton9 : ImageButton = gameView.findViewById(R.id.button9)
        val writeButton0 : ImageButton = gameView.findViewById(R.id.button0)

        val answerButton1 : ImageButton = gameView.findViewById(R.id.answer1)
        val answerButton2 : ImageButton = gameView.findViewById(R.id.answer2)
        val answerButton3 : ImageButton = gameView.findViewById(R.id.answer3)
        val answerButton4 : ImageButton = gameView.findViewById(R.id.answer4)

        val ans1_text : TextView = gameView.findViewById(R.id.ans1_text)
        val ans2_text : TextView = gameView.findViewById(R.id.ans2_text)
        val ans3_text : TextView = gameView.findViewById(R.id.ans3_text)
        val ans4_text : TextView = gameView.findViewById(R.id.ans4_text)

        val enterButton : Button = gameView.findViewById(R.id.see_result_button)

        writeButton1.setOnClickListener { writeOnButtons(1, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton2.setOnClickListener { writeOnButtons(2, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton3.setOnClickListener { writeOnButtons(3, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton4.setOnClickListener { writeOnButtons(4, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton5.setOnClickListener { writeOnButtons(5, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton6.setOnClickListener { writeOnButtons(6, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton7.setOnClickListener { writeOnButtons(7, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton8.setOnClickListener { writeOnButtons(8, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton9.setOnClickListener { writeOnButtons(9, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }
        writeButton0.setOnClickListener { writeOnButtons(0, answerButton1, answerButton2, answerButton3, answerButton4, ans1_text, ans2_text, ans3_text, ans4_text) }

        answerButton1.setOnClickListener { changePos(1, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton2.setOnClickListener { changePos(2, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton3.setOnClickListener { changePos(3, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton4.setOnClickListener { changePos(4, answerButton1, answerButton2, answerButton3, answerButton4) }

        enterButton.setOnClickListener { getResult(numberA, ans1_text, ans2_text, ans3_text, ans4_text) }

        return gameView
    }
}