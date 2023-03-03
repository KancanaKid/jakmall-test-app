package com.jakmall.test.app.ui.adapter.categories

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jakmall.test.app.databinding.CategoryItemsBinding

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to handle the list item presentation
 */

class CategoriesViewHolder(val viewBinding:CategoryItemsBinding)
    :RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(data:String, position:Int
    ){
        /**
         * the top item will be set as TOP label
         * */
        if(position == 0) {
            viewBinding.itemCategoryGotopTv.visibility = View.GONE
            viewBinding.itemCategoryTopTv.visibility = View.VISIBLE
        }else{
            viewBinding.itemCategoryGotopTv.visibility = View.VISIBLE
            viewBinding.itemCategoryTopTv.visibility = View.GONE
        }

        viewBinding.itemNumberTv.text = "${position+1}"
        viewBinding.itemCategoryTv.text = data
    }
}