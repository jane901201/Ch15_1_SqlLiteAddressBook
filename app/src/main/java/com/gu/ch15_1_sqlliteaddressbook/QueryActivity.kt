package com.gu.ch15_1_sqlliteaddressbook

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class QueryActivity : AppCompatActivity() {

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase
    private lateinit var toastQueryString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query)

        //取得資料庫實體
        dbrw = MyDBHelper(this).writableDatabase
        //宣告 Adapter 並連結 ListView
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, items)
        findViewById<ListView>(R.id.result_list).adapter = adapter

        setListener()
    }

    private fun setListener() {
        //綁定元件
        val btn_query = findViewById<Button>(R.id.btn_query_query)
        val btn_clean = findViewById<Button>(R.id.btn_clean)
        val btn_home = findViewById<Button>(R.id.btn_requery)
        val ed_number_query = findViewById<EditText>(R.id.ed_number_query)
        val ed_name_query = findViewById<EditText>(R.id.ed_name_query)
        val ed_phone_query = findViewById<EditText>(R.id.ed_phone_query)
        val ed_address_query = findViewById<EditText>(R.id.ed_address_query)

        //依編號查詢
        btn_query.setOnClickListener {

            if (ed_number_query.length() < 1 &&
                ed_name_query.length() < 1 &&
                ed_phone_query.length() < 1 &&
                ed_address_query.length() < 1
            ) {
                //未輸入條件，查詢全部資料，這邊先把所有的addressBooks替換成myTable
                toastQueryString = "select * from myTable"
                showToast("queryString:\n${toastQueryString}")
                getSQLData()

            } else {
                //輸入編號，依編號查詢
                if (ed_number_query.length() > 0) {
                    toastQueryString="select * from myTable where id='${ed_number_query.text}'"
                    showToast("queryString:\n${toastQueryString}")
                    //getSQLData()

                } else {
                    //依3欄位組合查詢
                    if (ed_name_query.length() > 0 &&
                        ed_phone_query.length()>0 &&
                        ed_address_query.length()>0) {
                        toastQueryString = "select * from myTable where name like '${ed_name_query.text}' and " +
                                "phone like '${ed_phone_query.text}' and address like '${ed_address_query.text}'"
                        showToast("queryString:\n${toastQueryString}")
                        getSQLData()

                    } else if(
                        ed_name_query.length() > 0 &&
                        ed_phone_query.length()>0
                    ){
                        //依name,phone欄位組合查詢
                        toastQueryString = "select * from myTable where name like '${ed_name_query.text}' and " +
                                "phone like '${ed_phone_query.text}'"
                        showToast("queryString:\n${toastQueryString}")
                        getSQLData()

                    } else if( ed_name_query.length() > 0 &&
                        ed_address_query.length()>0){
                        //依名字地址查詢
                        toastQueryString = "select * from myTable where name like '${ed_name_query.text}' and " +
                                "address like '${ed_address_query.text}'"
                        showToast("queryString:\n${toastQueryString}")
                        getSQLData()

                    } else if( ed_phone_query.length() > 0 &&
                        ed_address_query.length()>0){
                        //依電話地址查詢
                        toastQueryString = "select * from myTable where phone like '${ed_phone_query.text}' and " +
                                "address like '${ed_address_query.text}'"
                        showToast("queryString:\n${toastQueryString}")
                        getSQLData()

                    }else if(ed_name_query.length() > 0){
                        //依name欄位查詢
                        toastQueryString = "select * from myTable where name like '${ed_name_query.text}'"
                        showToast("queryString:\n${toastQueryString}")
                        getSQLData()

                    } else if(ed_phone_query.length()>0){
                        //依phone欄位查詢
                        toastQueryString = "select * from myTable where phone like '${ed_phone_query.text}'"
                        showToast("queryString:\n${toastQueryString}")
                        getSQLData()
                    } else {
                        toastQueryString = "select * from myTable where address like '${ed_address_query.text}'"
                        showToast("queryString:\n${toastQueryString}")
                        //依address欄位查詢
                        getSQLData()
                    }
                }
            }
        }

        //返回主頁
        btn_home.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        //清除查詢資料
        btn_clean.setOnClickListener{
            ed_number_query.text=null
            ed_name_query.text=null
            ed_phone_query.text=null
            ed_address_query.text=null
        }

    }

    private fun getSQLData() {
        val c = dbrw.rawQuery(toastQueryString, null)
        c.moveToFirst() //從第一筆開始輸出
        items.clear() //清空舊資料
        showToast("共有${c.count}筆資料")
        for (i in 0 until c.count) {
            //加入新資料
            items.add("編號:${c.getInt(0)}\t\t\t\t 姓名:${c.getString(1)}\t\t\t\t" +
                    " 手機:${c.getInt(2)}\t\t\t\t 地址:${c.getString(3)}\t\t\t\t")
            c.moveToNext() //移動到下一筆
        }
        adapter.notifyDataSetChanged() //更新列表資料
        c.close() //關閉 Cursor
    }

    //建立 showToast 方法顯示 Toast 訊息
    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}