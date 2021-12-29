package com.gu.ch15_1_sqlliteaddressbook

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DeleteActivity : AppCompatActivity() {

    private lateinit var dbrw: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        dbrw = MyDBHelper(this).writableDatabase

        setListener()

    }

    private fun setListener() {
        val ed_number = findViewById<EditText>(R.id.deleteNumberEditText)
        val ed_name = findViewById<EditText>(R.id.deleteNameEditText)
        val ed_phone = findViewById<EditText>(R.id.deletePhoneEditText)
        val ed_address = findViewById<EditText>(R.id.deleteAddressEditText)


        findViewById<Button>(R.id.deleteCheckButton).setOnClickListener {
            //判斷是否有填入書名或價格
            if (ed_number.length() < 1 && ed_name.length() < 1 && ed_phone.length() < 1 && ed_address.length() < 1)
                showToast("欄位請勿留空")
            if(ed_number.length() > 1) {
                try {
                    showToast("刪除:${ed_number.text}")
                    dbrw.execSQL("DELETE FROM myTable WHERE number LIKE '${ed_number.text}'")
                } catch (e: Exception) {
                    showToast("刪除失敗:$e")
                }
            }
            if(ed_name.length() > 1) {
                try {
                    showToast("刪除:${ed_name.text}")
                    dbrw.execSQL("DELETE FROM myTable WHERE name LIKE '${ed_name.text}'")
                } catch (e: Exception) {
                    showToast("刪除失敗:$e")
                }
            }
            if(ed_phone.length() > 1) {
                try {
                    showToast("刪除:${ed_phone.text}")
                    dbrw.execSQL("DELETE FROM myTable WHERE phone LIKE '${ed_phone.text}'")
                } catch (e: Exception) {
                    showToast("刪除失敗:$e")
                }
            }
            if(ed_address.length() > 1) {
                try {
                    showToast("刪除:${ed_address.text}")
                    dbrw.execSQL("DELETE FROM myTable WHERE address LIKE '${ed_address.text}'")
                } catch (e: Exception) {
                    showToast("刪除失敗:$e")
                }
            }

            cleanEditText()
        }

        findViewById<Button>(R.id.deleteReturnHomeButton).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    private fun cleanEditText() {
        findViewById<EditText>(R.id.deleteNumberEditText).setText("")
        findViewById<EditText>(R.id.deleteNameEditText).setText("")
        findViewById<EditText>(R.id.deletePhoneEditText).setText("")
        findViewById<EditText>(R.id.deleteAddressEditText).setText("")
    }


}