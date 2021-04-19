package com.simple.pos.shared.modul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.simple.pos.R

class LoadingDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false

        return inflater.inflate(R.layout.dialog_loading, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //make dialog's background transparent
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}