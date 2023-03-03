package com.jakmall.test.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jakmall.test.app.databinding.ItemDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to showing dialog, contain child list item on onclick listener result
 */
@AndroidEntryPoint
class ItemDialogBottomSheet(private val data:String) : BottomSheetDialogFragment() {
    lateinit var viewBinding:ItemDialogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = ItemDialogFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.itemDetailTv.text = data
    }
}