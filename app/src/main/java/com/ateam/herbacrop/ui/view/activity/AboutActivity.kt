package com.ateam.herbacrop.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.AboutDescModel
import com.ateam.herbacrop.core.domain.model.AboutModel
import com.ateam.herbacrop.databinding.ActivityAboutBinding
import com.ateam.herbacrop.ui.adapter.RecyclerAboutAdapter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AboutActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAboutBinding
    private lateinit var aboutAdapter:RecyclerAboutAdapter
    private val db = Firebase.firestore
    private val koleksi = db.collection("main_data")
    private val about = ArrayList<AboutModel>()
    private val aboutDesc = ArrayList<AboutDescModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "About"

        aboutAdapter = RecyclerAboutAdapter()

        koleksi.document("home").collection("about")
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                    val collection:List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val aboutModel:AboutModel? = document.toObject(AboutModel::class.java)
                        println("home :$aboutModel")
                        aboutModel?.let {
                            it1->about.add(it1)
                            aboutAdapter.setData(about)
                        }
                        aboutAdapter.notifyDataSetChanged()
                    }
                }
            }

        koleksi.document("home")
            .get()
            .addOnSuccessListener {
                if (it != null){

                    val collection: DocumentSnapshot? = it
                        val aboutModel: AboutDescModel? = collection?.toObject(AboutDescModel::class.java)
                        println("home :$aboutModel")
                        aboutModel?.let {
                            it1->aboutDesc.add(aboutModel)
                            binding.desctambah.text = it1.abstract_4
                            binding.textdesc1.text = it1.abstract_6
                            binding.desc2.text = it1.abstract_5
                        }

                }
            }

        binding.rvAbout.apply {
            val layoutManager = LinearLayoutManager(this@AboutActivity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter = aboutAdapter
        }
    }
}