package com.gu.ch15_1_sqlliteaddressbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.lang.System.exit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()

    }

    private fun setListener() {
        val btn_insert = findViewById<Button>(R.id.btn_insert)
        val btn_exit = findViewById<Button>(R.id.btn_exit)
        val btn_query = findViewById<Button>(R.id.btn_query)
        val btn_update = findViewById<Button>(R.id.btn_update)
        val btn_delete = findViewById<Button>(R.id.btn_delete)

        //新增資料
        btn_insert.setOnClickListener{
            startActivity(Intent(this,InsertActivity::class.java))
        }

        //查詢
        btn_query.setOnClickListener{
            startActivity(Intent(this,QueryActivity::class.java))
        }

        btn_update.setOnClickListener {
            startActivity(Intent(this,UpdateActivity::class.java))
        }

        btn_delete.setOnClickListener {
            startActivity(Intent(this,DeleteActivity::class.java))
        }

        //離開
        btn_exit.setOnClickListener{
            exit(0)
        }
    }
}