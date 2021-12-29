package com.gu.ch15_1_sqlliteaddressbook

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class UpdateActivity : AppCompatActivity() {

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase
    private lateinit var toastQueryString: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        //取得資料庫實體
        dbrw = MyDBHelper(this).writableDatabase

        setListener()

    }

    private fun setListener() {
        val ed_number = findViewById<EditText>(R.id.updateNumberEditText)
        val ed_name = findViewById<EditText>(R.id.updateNameEditText)
        val ed_phone = findViewById<EditText>(R.id.updatePhoneEditText)
        val ed_address = findViewById<EditText>(R.id.updateAddressEditText)


        findViewById<Button>(R.id.updateCheckButton).setOnClickListener {
            //判斷是否有填入書名或價格
            if (ed_number.length() < 1)
                showToast("欄位請勿留空")
            else if(ed_name.length() < 1 && ed_phone.length() < 1 && ed_address.length() < 1)
                showToast("欄位請勿留空")
            if(ed_name.length() > 1) {
                try {
                    dbrw.execSQL("UPDATE myTable SET name = ${ed_name.text} WHERE number LIKE '${ed_number.text}'")
                    showToast("更新:${ed_number.text},名字:${ed_name.text}")
                    //cleanEditText()
                } catch (e: Exception) {
                    showToast("更新失敗:$e")
                }
            }
            if(ed_phone.length() > 1) {
                try {
                    dbrw.execSQL("UPDATE myTable SET phone = ${ed_phone.text} WHERE number LIKE '${ed_number.text}'")
                    showToast("更新:${ed_number.text},名字:${ed_name.text}")
                    //cleanEditText()
                } catch (e: Exception) {
                    showToast("更新失敗:$e")
                }
            }
            if(ed_address.length() > 1) {
                try {
                    dbrw.execSQL("UPDATE myTable SET address = ${ed_address.text} WHERE number LIKE '${ed_number.text}'")
                    showToast("更新:${ed_number.text},名字:${ed_name.text}")
                    //cleanEditText()
                } catch (e: Exception) {
                    showToast("更新失敗:$e")
                }
            }

            cleanEditText()
        }

        findViewById<Button>(R.id.updateRetunrHomeButton).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    private fun cleanEditText() {
        findViewById<EditText>(R.id.updateNumberEditText).setText("")
        findViewById<EditText>(R.id.updateNameEditText).setText("")
        findViewById<EditText>(R.id.updatePhoneEditText).setText("")
        findViewById<EditText>(R.id.updateAddressEditText).setText("")
    }


}