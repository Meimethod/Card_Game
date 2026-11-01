package cn.edu.sicnu.cs.lrj.cardgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //保存用户信息
    private var userName = ""
    private var stuId = ""
    private var gender = ""
    private var phone = ""
    private var email = ""
    private var hobby = ""
    private var maxScore = 0

    private lateinit var tvName: TextView
    private lateinit var tvStuId: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvHobby: TextView
    private lateinit var tvMaxScore: TextView

    companion object {
        const val REQUEST_EDIT = 1001
        const val REQUEST_GAME = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvName = findViewById(R.id.tv_name)
        tvStuId = findViewById(R.id.tv_student_id)
        tvGender = findViewById(R.id.tv_gender)
        tvPhone = findViewById(R.id.tv_phone)
        tvEmail = findViewById(R.id.tv_email)
        tvHobby = findViewById(R.id.tv_hobby)
        tvMaxScore = findViewById(R.id.tv_max_score)

        val btnEdit: Button = findViewById(R.id.btn_edit_info)
        val btnStartGame: Button = findViewById(R.id.btn_start_game)

        refreshUi()

        //跳转修改信息
        btnEdit.setOnClickListener {
            val intent = Intent(this, EditInfoActivity::class.java)
            intent.putExtra("name", userName)
            intent.putExtra("stuId", stuId)
            intent.putExtra("gender", gender)
            intent.putExtra("phone", phone)
            intent.putExtra("email", email)
            intent.putExtra("hobby", hobby)
            startActivityForResult(intent, REQUEST_EDIT)
        }

        //跳转游戏页面
        btnStartGame.setOnClickListener {
            val intent = Intent(this, CardGameActivity::class.java)
            intent.putExtra("currentMax", maxScore)
            startActivityForResult(intent, REQUEST_GAME)
        }
    }

    //刷新主页文本显示
    //刷新主页文本显示
    private fun refreshUi() {
        tvName.text = getString(R.string.fmt_name, userName)
        tvStuId.text = getString(R.string.fmt_student_id, stuId)
        tvGender.text = getString(R.string.fmt_gender, gender)
        tvPhone.text = getString(R.string.fmt_phone, phone)
        tvEmail.text = getString(R.string.fmt_email, email)
        tvHobby.text = getString(R.string.fmt_hobby, hobby)
        tvMaxScore.text = getString(R.string.fmt_max_score, maxScore)
    }


    //接收回传数据
    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK) {
            userName = data?.getStringExtra("name") ?: ""
            stuId = data?.getStringExtra("stuId") ?: ""
            gender = data?.getStringExtra("gender") ?: ""
            phone = data?.getStringExtra("phone") ?: ""
            email = data?.getStringExtra("email") ?: ""
            hobby = data?.getStringExtra("hobby") ?: ""
            refreshUi()
        }
        if(requestCode == REQUEST_GAME && resultCode == RESULT_OK){
            val gameScore = data?.getIntExtra("gameScore",0)?:0
            if(gameScore>maxScore){
                maxScore = gameScore
                refreshUi()
            }
        }
    }
}
