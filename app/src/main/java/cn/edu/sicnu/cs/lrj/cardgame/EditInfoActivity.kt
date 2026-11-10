package cn.edu.sicnu.cs.lrj.cardgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class EditInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_info)

        val etName: EditText = findViewById(R.id.et_name)
        val etStuId: EditText = findViewById(R.id.et_student_id)
        val rgGender: RadioGroup = findViewById(R.id.rg_gender)
        val rbMale: RadioButton = findViewById(R.id.rb_male)
        val rbFemale: RadioButton = findViewById(R.id.rb_female)
        val etPhone: EditText = findViewById(R.id.et_phone)
        val etEmail: EditText = findViewById(R.id.et_email)
        val etHobby: EditText = findViewById(R.id.et_hobby)
        val btnSave: Button = findViewById(R.id.btn_save)
        val btnCancel: Button = findViewById(R.id.btn_cancel)

        btnSave.setOnClickListener {
            val intent = Intent(this, CardGameActivity::class.java)
            intent.putExtra("name", etName.text.toString())
            intent.putExtra("stuId", etStuId.text.toString())
            intent.putExtra("gender", if(rgGender.checkedRadioButtonId == R.id.rb_male) "男" else "女")
            intent.putExtra("phone", etPhone.text.toString())
            intent.putExtra("email", etEmail.text.toString())
            intent.putExtra("hobby", etHobby.text.toString())
            startActivity(intent)
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}
