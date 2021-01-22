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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_ticket.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var contents= arrayListOf<Contents>()
        contents.add(Contents("Lecture Halls",R.drawable.lh,1))
        contents.add(Contents("Reading Hall",R.drawable.rh,0))
        contents.add(Contents("Library",R.drawable.lib,0))
        contents.add(Contents("Labs",R.drawable.lab,1))

        val adapter=ContentAdapter(this,contents)
        listview.adapter= adapter
    }

    inner class ContentAdapter: BaseAdapter {
        var context:Context?=null
        var contents= arrayListOf<Contents>()
        constructor(context: Context,contents: ArrayList<Contents>){
            this.context=context
            this.contents=contents
            Log.d("ARRAY","$contents")
        }


        override fun getCount(): Int {
            return contents!!.size
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView= inflater.inflate(R.layout.main_ticket,null)
            myView.tvText.text = contents!![position].text
            myView.ivImage.setImageResource(contents[position].image)
            if(contents[position].descp == 1){
                myView.setOnClickListener{
                    val intent=Intent(context,SubValues::class.java)
                    intent.putExtra("activityFrom",contents[position].text)
                    startActivity(intent)
                }
            }else{
                myView.setOnClickListener {
                    val intent=Intent(context,Scanner::class.java)
                    intent.putExtra("activityFrom",contents[position].text)
                    startActivity(intent)
                }
            }

            return myView
        }

    }
}