package com.example.sherapy.activities

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sherapy.R
import com.example.sherapy.databinding.Fragment2Binding
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class Fragment2Activity : Fragment() {

    private lateinit var binding: Fragment2Binding
    lateinit var questionsList:ArrayList<QuestionModel>
    private var index:Int = 0
    lateinit var questionModel: QuestionModel
    private var soalNo:Int=0
    private var correctAnswerCount:Int=0
    private lateinit var timer: CountDownTimer

    lateinit var questions:TextView
    lateinit var option1: Button
    lateinit var option2:Button
    lateinit var option3:Button
    lateinit var option4:Button
    lateinit var soal:TextView
    lateinit var countDown:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment2Binding.inflate(layoutInflater, container, false)
        questions=binding.questions
        option1=binding.option1
        option2=binding.option2
        option3=binding.option3
        option4=binding.option4
        soal=binding.soal
        countDown=binding.countdown

        questionsList= ArrayList()
        questionsList.add(QuestionModel("Kurang berminat atau bergairah dalam melakukan apapun","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Merasa murung, sedih, atau putus asa","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Sulit tidur/mudah terbangun, atau terlalu banyak tidur","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Merasa lelah atau kurang bertenaga","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Kurang nafsu makan atau terlalu banyak makan","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Kurang percaya diri atau merasa orang yang gagal","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Sulit berkonsentrasi pada sesuatu","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Bergerak atau berbicara sangat lambat atau sebaliknya","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Merasa lebih baik mati atau ingin melukai diri sendiri","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Merasa mampu untuk mengambil keputusan","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Sulit tidur karena khawatir","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Merasa berperan pada sesuatu yang bermanfaat","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Merasa terus menerus dibawah tekanan","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Merasa tidak sanggup mengatasi kesulitan-kesulitan","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Dapat menikmati aktivitas sehari-hari","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Mampu menanggung masalah-masalah","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Merasa tidak bahagia dan tertekan","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Hilang percaya diri","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Berpikiran diri anda tidak berguna","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))
        questionsList.add(QuestionModel("Setelah mempertimbangkan segala hal, merasa bahagia","Tidak pernah","Kurang dari biasanya","Sama seperti biasanya","Lebih dari biasanya"))

        binding.option1.setOnClickListener {
            binding.progressBar.progress += 1
            timer.cancel()
            countdown()
                if (index < 19) {
                    index++
                    soalNo++
                    questionModel = questionsList[index]
                    setAllQuestions()
                } else {
                    gameResult()
                    findNavController().navigate(Fragment2ActivityDirections.actionFragment2ActivityToFragment3Activity(
                        correctAnswerCount, questionsList.size
                    ))
                }
            }
            binding.option2.setOnClickListener {
                binding.progressBar.progress += 1
                timer.cancel()
                countdown()
                if (index < 19) {
                    index++
                    soalNo++
                    questionModel = questionsList[index]
                    setAllQuestions()
                    correctAnswerCount += 1
                } else {
                    gameResult()
                    findNavController().navigate(Fragment2ActivityDirections.actionFragment2ActivityToFragment3Activity(
                        correctAnswerCount, questionsList.size
                    ))
                }
            }
            binding.option3.setOnClickListener {
                binding.progressBar.progress += 1
                timer.cancel()
                countdown()
                if (index < 19) {
                    index++
                    soalNo++
                    questionModel = questionsList[index]
                    setAllQuestions()
                    correctAnswerCount += 2
                } else {
                    gameResult()
                    findNavController().navigate(Fragment2ActivityDirections.actionFragment2ActivityToFragment3Activity(
                        correctAnswerCount, questionsList.size
                    ))
                }
            }
            binding.option4.setOnClickListener {
                binding.progressBar.progress += 1
                timer.cancel()
                countdown()
                if (index < 19) {
                    index++
                    soalNo++
                    questionModel = questionsList[index]
                    setAllQuestions()
                    correctAnswerCount += 3
                } else {
                    gameResult()
                    findNavController().navigate(Fragment2ActivityDirections.actionFragment2ActivityToFragment3Activity(
                        correctAnswerCount, questionsList.size
                    ))
                }
            }

        //questionsList.shuffle()
        questionModel= questionsList[index]
        setAllQuestions()
        countdown()
        gameResult()
        return binding.root
    }

    fun countdown(){
        var duration:Long= TimeUnit.SECONDS.toMillis(20)
        timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val sDuration:String= String.format(
                    Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                countDown.text = sDuration
            }
            override fun onFinish() {
                duration = TimeUnit.SECONDS.toMillis(0)
            }
        }.start()
    }

    private fun gameResult(){
        val bundle = Bundle()
        bundle.putString("correct",correctAnswerCount.toString())
        bundle.putString("total",questionsList.size.toString())

        val fragment = Fragment3Activity()
        fragment.arguments = bundle
    }

    private fun setAllQuestions() {
        questions.text=questionModel.question
        option1.text=questionModel.option1
        option2.text=questionModel.option2
        option3.text=questionModel.option3
        option4.text=questionModel.option4
        binding.soal.text=soalNo.toString()
    }
    private fun enableButton(){
        option1.isClickable=true
        option2.isClickable=true
        option3.isClickable=true
        option4.isClickable=true
    }
    private fun disableButton(){
        option1.isClickable=false
        option2.isClickable=false
        option3.isClickable=false
        option4.isClickable=false
    }

}