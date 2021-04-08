package com.simple.pos.modul.profiluser.profilnote.update

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.databinding.ActivityProfileStruckUpdateBinding
import com.simple.pos.modul.profiluser.profilnote.NoteStoreActivity
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.util.StoreUtil

class NoteStoreUpdateActivity : AppCompatActivity(), NoteStoreUpdateContract.View {
    private val presenter = NoteStoreUpdatePresenter(this)
    private var _binding: ActivityProfileStruckUpdateBinding? = null
    private val binding get() = _binding!!
    private val currentStore: Store = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileStruckUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.showNotes()
        onClickInitialization()
    }

    private fun onClickInitialization() {
        binding.let {
            it.btnCancelNoteUpdate.setOnClickListener {
                redirectToNoteStore()
            }
            it.btnUpdateNoteThanks.setOnClickListener {
                onClickUpdate()
            }
            it.ivBackToProfile.setOnClickListener{
                redirectToNoteStore()
            }
        }
    }

    private fun onClickUpdate() {
        binding.btnCancelNoteUpdate.isEnabled = false
        binding.btnUpdateNoteThanks.isEnabled = false
        val store: Store = Store(currentStore.name).apply {
            id = currentStore.id
            noteReceipt = binding.etThanksNote.text.toString()
        }

        presenter.updateStore(store)
    }
 
    
    override fun redirectToNoteStore() {
        val intent = Intent(this, NoteStoreActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showNotes(note: String?) {
        binding.etThanksNote.setText(note)
    }


    override fun updateSuccess(message: String) {
        enableButtonOption()
        makeToast("Berhasil update Note Receipt")
        redirectToNoteStore()
    }

    override fun updateFailed(message: String) {
        enableButtonOption()
        makeToast("Gagal melakukan update")
    }


    private fun makeToast(message: String) {
        Toast.makeText(applicationContext, message,
                Toast.LENGTH_SHORT).show()
    }

    private fun enableButtonOption(){
        binding.let {
            it.btnUpdateNoteThanks.isEnabled = true
            it.btnCancelNoteUpdate.isEnabled = true
        }
    }

}