package com.simple.pos.modul.profiluser.notesetting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.R
import com.simple.pos.databinding.ActivityProfileStoreUpdateBinding
import com.simple.pos.databinding.ActivityProfileStruckBinding
import com.simple.pos.modul.profiluser.ProfileUserActivity
import com.simple.pos.modul.profiluser.notesetting.update.NoteStoreUpdateActivity

class NoteStoreActivity: AppCompatActivity(), NoteStoreContract.View {
    private val presenter = NoteStorePresenter(this)
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

        setContentView(R.layout.activity_profile_struck)
        onClickInit()
        presenter.showNotes()
    }

    private fun onClickInit() {
        binding.ivBackToProfile.setOnClickListener {
            redirectToUserProfile()
        }
    }
}