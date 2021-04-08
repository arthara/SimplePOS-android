package com.simple.pos.modul.profiluser.profilnote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.R
import com.simple.pos.databinding.ActivityProfileStoreBinding
import com.simple.pos.databinding.ActivityProfileStruckBinding
import com.simple.pos.modul.profiluser.ProfileUserActivity
import com.simple.pos.modul.profiluser.profilnote.update.NoteStoreUpdateActivity

class NoteStoreActivity: AppCompatActivity(), NoteStoreContract.View {
    private val presenter = NoteStorePresenter(this)
    private var _binding: ActivityProfileStruckBinding? = null
    private val binding get() = _binding!!

    override fun redirectToUpdateNote() {
        val intent = Intent(this, NoteStoreUpdateActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun redirectToUserProfile() {
/*        val intent = Intent(this, ProfileUserActivity::class.java)
        startActivity(intent)*/
        finish()
    }

    override fun showNotes(notes: String?) {
        binding.tvThanksNote.text = notes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileStruckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickInit()
        presenter.showNotes()
    }

    private fun onClickInit() {
        binding.let {
            it.ivBackToProfile.setOnClickListener {
                redirectToUserProfile()
            }
            it.updateGoToNoteBtn.setOnClickListener {
                redirectToUpdateNote()
            }
        }
    }
}