package com.simple.pos.modul.profiluser.notesetting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.databinding.ActivityProfileStruckBinding
import com.simple.pos.modul.profiluser.ProfileUserActivity

class NoteStoreActivity: AppCompatActivity(), NoteStoreContract.View {
    private lateinit var presenter: NoteStorePresenter
    private lateinit var binding: ActivityProfileStruckBinding

    override fun redirectToUpdateProduct() {
/*        val intent = Intent(this, NoteStoreUpdateActivity::class.java)
        startActivity(intent)*/
    }

    override fun redirectToUserProfile() {
        val intent = Intent(this, ProfileUserActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileStruckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = NoteStorePresenter(this)
        onClickInit()
        presenter.showNotes()
    }

    private fun onClickInit() {
        binding.ivBackToProfile.setOnClickListener {
            redirectToUserProfile()
        }
    }
}