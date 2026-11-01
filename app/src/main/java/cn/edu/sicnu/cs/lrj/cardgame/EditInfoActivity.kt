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

        //拿到传过来旧数据回填输入框
        etName.setText(intent.getStringExtra("name"))
        etStuId.setText(intent.getStringExtra("stuId"))
        val oldGender = intent.getStringExtra("gender")
        if(oldGender == getString(R.string.male)) rbMale.isChecked = true
        if(oldGender == getString(R.string.female)) rbFemale.isChecked = true
        etPhone.setText(intent.getStringExtra("phone"))
        etEmail.setText(intent.getStringExtra("email"))
        etHobby.setText(intent.getStringExtra("hobby"))

        btnCancel.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            var selGender = ""
            if(rgGender.checkedRadioButtonId == R.id.rb_male){
                selGender = getString(R.string.male)
            }else if(rgGender.checkedRadioButtonId == R.id.rb_female){
                selGender = getString(R.string.female)
            }

            val resultIntent = Intent()
            resultIntent.putExtra("name",etName.text.toString())
            resultIntent.putExtra("stuId",etStuId.text.toString())
            resultIntent.putExtra("gender",selGender)
            resultIntent.putExtra("phone",etPhone.text.toString())
            resultIntent.putExtra("email",etEmail.text.toString())
            resultIntent.putExtra("hobby",etHobby.text.toString())
            setResult(RESULT_OK,resultIntent)
            finish()
        }
    }
}
