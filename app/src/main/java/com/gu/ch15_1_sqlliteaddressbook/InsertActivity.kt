package com.gu.ch15_1_sqlliteaddressbook

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InsertActivity : AppCompatActivity() {


    private lateinit var sqlLiteDatabase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        sqlLiteDatabase = MyDBHelper(this).writableDatabase

        //綁定元件
        val ed_number = findViewById<EditText>(R.id.ed_number)
        val ed_name = findViewById<EditText>(R.id.ed_name)
        val ed_phone = findViewById<EditText>(R.id.ed_phone)
        val ed_address = findViewById<EditText>(R.id.ed_address)
        val btn_insert = findViewById<Button>(R.id.btn_insert_confirm)

        btn_insert.setOnClickListener {
            //判斷是否輸入編號
            if (ed_number.length() < 1){
                ed_number.hint="編號不可留白且不可重複"
                return@setOnClickListener
            } else if(ed_name.length() < 1){
                ed_name.hint="姓名一定要填"
                return@setOnClickListener
            } else if(ed_phone.length() < 1){
                ed_phone.hint="手機或電話一定要填"
                return@setOnClickListener
            } else if(ed_address.length() < 1) {
                ed_address.hint = "請填入地址"
            }
            else {
                try {
                    sqlLiteDatabase.execSQL(
                        "INSERT INTO myTable(number, name, phone, address) VALUES(?, ?, ?, ?)",
                        arrayOf(ed_number.text.toString(),
                            ed_name.text.toString(), ed_phone.text.toString(), ed_address.text.toString())
                    )
                    showToast("新增:${ed_number.text},價格:${ed_name.text}, 電話號碼:${ed_phone.text}, " +
                            "地址:${ed_address.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("新增失敗:$e")
                }
            }

        }

        //綁定返回按鈕
        val btn_i2h = findViewById<Button>(R.id.btn_insert2home)
        btn_i2h.setOnClickListener{
            //返回首頁
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    private fun cleanEditText() {
        findViewById<EditText>(R.id.ed_name).setText("")
        findViewById<EditText>(R.id.ed_number).setText("")
        findViewById<EditText>(R.id.ed_phone).setText("")
        findViewById<EditText>(R.id.ed_address).setText("")
    }

}