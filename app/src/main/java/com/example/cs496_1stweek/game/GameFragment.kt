package com.example.cs496_1stweek.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

    private fun getResult(answer: Int, ans1: Button, ans2: Button, ans3: Button, ans4: Button): Array<Int> {
        if(ans1.text==" "||ans2.text==" "||ans3.text==" "||ans4.text==" "){
            Toast.makeText(requireContext(),"4자리 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
            return arrayOf(0,0,0)
        }

        val currentAnswer : String = ans1.text.toString() + ans2.text + ans3.text + ans4.text

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

    private fun writeOnButtons(num: Int, ans1:Button, ans2: Button, ans3: Button, ans4: Button) {
        when (pos) {
            1 -> {
                ans1.text=num.toString()
                if(ans2.text==num.toString()){ ans2.text=" " }
                else if(ans3.text==num.toString()){ ans3.text=" " }
                else if(ans4.text==num.toString()){ ans4.text=" " }
                ans1.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.purple_500)
                ans2.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.light_blue)
            }
            2 -> {
                ans2.text=num.toString()
                if(ans1.text==num.toString()){ ans1.text=" " }
                else if(ans3.text==num.toString()){ ans3.text=" " }
                else if(ans4.text==num.toString()){ ans4.text=" " }
                ans2.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.purple_500)
                ans3.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.light_blue)
            }
            3 -> {
                ans3.text=num.toString()
                if(ans1.text==num.toString()){ ans1.text=" " }
                else if(ans2.text==num.toString()){ ans2.text=" " }
                else if(ans4.text==num.toString()){ ans4.text=" " }
                ans3.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.purple_500)
                ans4.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.light_blue)
            }
            else -> {
                ans4.text=num.toString()
                if(ans1.text==num.toString()){ ans1.text=" " }
                else if(ans2.text==num.toString()){ ans2.text=" " }
                else if(ans3.text==num.toString()){ ans3.text=" " }
                ans4.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.purple_500)
                ans1.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.light_blue)
            }
        }

        if(pos++==4) pos=1
    }

    private fun changePos(to: Int, ans1: Button, ans2: Button, ans3: Button, ans4: Button){
        when (pos) {
            1 -> ans1.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.purple_500)
            2 -> ans2.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.purple_500)
            3 -> ans3.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.purple_500)
            4 -> ans4.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.purple_500)
        }
        when (to) {
            1 -> ans1.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.light_blue)
            2 -> ans2.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.light_blue)
            3 -> ans3.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.light_blue)
            4 -> ans4.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.light_blue)
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

        val writeButton1 : Button = gameView.findViewById(R.id.button1)
        val writeButton2 : Button = gameView.findViewById(R.id.button2)
        val writeButton3 : Button = gameView.findViewById(R.id.button3)
        val writeButton4 : Button = gameView.findViewById(R.id.button4)
        val writeButton5 : Button = gameView.findViewById(R.id.button5)
        val writeButton6 : Button = gameView.findViewById(R.id.button6)
        val writeButton7 : Button = gameView.findViewById(R.id.button7)
        val writeButton8 : Button = gameView.findViewById(R.id.button8)
        val writeButton9 : Button = gameView.findViewById(R.id.button9)
        val writeButton0 : Button = gameView.findViewById(R.id.button0)

        val answerButton1 : Button = gameView.findViewById(R.id.answer1)
        val answerButton2 : Button = gameView.findViewById(R.id.answer2)
        val answerButton3 : Button = gameView.findViewById(R.id.answer3)
        val answerButton4 : Button = gameView.findViewById(R.id.answer4)

        val enterButton : Button = gameView.findViewById(R.id.see_result_button)

        writeButton1.setOnClickListener { writeOnButtons(1, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton2.setOnClickListener { writeOnButtons(2, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton3.setOnClickListener { writeOnButtons(3, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton4.setOnClickListener { writeOnButtons(4, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton5.setOnClickListener { writeOnButtons(5, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton6.setOnClickListener { writeOnButtons(6, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton7.setOnClickListener { writeOnButtons(7, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton8.setOnClickListener { writeOnButtons(8, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton9.setOnClickListener { writeOnButtons(9, answerButton1, answerButton2, answerButton3, answerButton4) }
        writeButton0.setOnClickListener { writeOnButtons(0, answerButton1, answerButton2, answerButton3, answerButton4) }

        answerButton1.setOnClickListener { changePos(1, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton2.setOnClickListener { changePos(2, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton3.setOnClickListener { changePos(3, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton4.setOnClickListener { changePos(4, answerButton1, answerButton2, answerButton3, answerButton4) }

        enterButton.setOnClickListener { getResult(numberA, answerButton1, answerButton2, answerButton3, answerButton4) }

        return gameView
    }
}