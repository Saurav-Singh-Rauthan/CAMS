package com.example.cams

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_sub_values.*
import kotlinx.android.synthetic.main.lab_ticket.view.*
import kotlinx.android.synthetic.main.subvalues_ticket.view.*

class SubValues : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_values)

        val bundle=intent.extras
        val type:String = bundle!!.getString("activityFrom").toString()

        var values= arrayListOf<String>()
        var labs= arrayListOf<String>()
        values.add("LH1")
        values.add("LH2")
        values.add("LH3")
        values.add("LH4")
        values.add("LH5")
        values.add("LH6")
        values.add("LH7")
        values.add("LH8")
        values.add("LH9")
        values.add("LH10")
        values.add("LH11")
        values.add("LH12")

        labs.add("Chemistry lab")
        labs.add("Physics lab")
        labs.add("DBMS lab")
        labs.add("FPL lab")
        labs.add("PBL lab")
        labs.add("CG lab")
        labs.add("PA lab")
        labs.add("LDCO lab")
        labs.add("DSA lab")
        labs.add("OOP lab")
        Log.d("BUNDLE","$labs")

        val adapter=ValuesAdapter(this,values,labs,type)
        gridView.adapter = adapter

    }

    inner class ValuesAdapter:BaseAdapter{
        var context:Context?=null
        var values= arrayListOf<String>()
        var labs= arrayListOf<String>()
        var type=""
        constructor(context: Context, values:ArrayList<String>,labs:ArrayList<String>,type:String){
            this.context=context
            this.values=values
            this.labs=labs
            this.type=type
        }

        override fun getCount(): Int {
            if(type=="Lecture Halls"){
                return values.size
            }else {
                return labs.size
            }
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return  position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater= context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var view= inflater.inflate(R.layout.subvalues_ticket,null)
            if(type=="Lecture Halls"){
                var myView= inflater.inflate(R.layout.subvalues_ticket,null)
                myView.tvSubText.text = values[position]
                myView.setOnClickListener{
                    val intent=Intent(context,Scanner::class.java)
                    intent.putExtra("activityFrom",values[position])
                    startActivity(intent)
                }
                return myView
            }else if(type=="Labs"){
                var myView= inflater.inflate(R.layout.lab_ticket,null)
                gridView.numColumns = 1
                myView.tvLabsText.text = labs[position]
                myView.setOnClickListener{
                    val intent=Intent(context,Scanner::class.java)
                    intent.putExtra("activityFrom",labs[position])
                    startActivity(intent)
                }
                return myView
            }
            return view
        }

    }
}