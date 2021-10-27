package com.example.myapplicationdemo

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    lateinit var  titleText: TextView
    lateinit var imageView: ImageView
    lateinit var progressbar: ProgressBar
    val TAG:String=MainActivity::class.java.simpleName
    var currentPosition:Int=0
    var dataList= mutableListOf<ImageItemDataItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressbar=findViewById(R.id.progress_bar);
        imageView=findViewById(R.id.image_view);
        titleText=findViewById(R.id.title_text);

        imageView.setOnClickListener {

            Log.d("MainActivityTest","Click on ImafgeView" )
            if(currentPosition==3){
                currentPosition=-1
            }
            swapData(++currentPosition)
        }

        generateData()
    }

    override fun onStart() {
        super.onStart()
        swapData(currentPosition)
    }


    fun generateData(){
        dataList.add(ImageItemDataItem(1,"accusamus beatae ad facilis cum similique qui sunt","https://sample-videos.com/img/Sample-jpg-image-200kb.jpg"))
        dataList.add(ImageItemDataItem(1,"reprehenderit est deserunt velit ipsam","https://sample-videos.com/img/Sample-png-image-500kb.png"))
        dataList.add(ImageItemDataItem(1,"officia porro iure quia iusto qui ipsa ut modi","https://file-examples-com.github.io/uploads/2017/10/file_example_JPG_500kB.jpg"))
        dataList.add(ImageItemDataItem(1,"culpa odio esse rerum omnis laboriosam voluptate repudiandae","https://upload.wikimedia.org/wikipedia/commons/c/c4/Hemerocallis_lilio-asphodelus.jpg"))
    }


    fun swapData(position:Int){
        Log.d(TAG,dataList[position].url)
        titleText.text=dataList[position].title
        progressbar.visibility= View.VISIBLE
        Glide.with(applicationContext).load(dataList[position].url).error(R.drawable.ic_launcher_background)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    @Nullable e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // log exception
                    Log.e(TAG, "Error loading image", e)
                    progressbar.visibility= View.GONE
                    return false // important to return false so the error placeholder can be placed
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressbar.visibility= View.GONE
                    return false
                }
            })
            .into(imageView);
    }


}