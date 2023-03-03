package com.jakmall.test.app.ui.adapter.categories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jakmall.test.app.R
import com.jakmall.test.app.data.model.CategoryMapResult
import com.jakmall.test.app.databinding.CategoryItemsBinding
import com.jakmall.test.app.ui.utils.Util.dp

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to handle list adapter
 */
class CategoryAdapter(
    private val onCategoriesListener: OnCategoriesListener
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var categoriesListMap:ArrayList<CategoryMapResult> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriesViewHolder(CategoryItemsBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CategoriesViewHolder){
            /**
             * binding list item
             * */
            holder.bind(categoriesListMap[position].category, position)

            /**
             * handling go to top function. The goTop function implemented in MainActivity class
             * */
            holder.viewBinding.itemCategoryGotopTv.setOnClickListener {
                onCategoriesListener.goTop(categoriesListMap[position])
            }

            /**
             * handling list item drop-down behaviour
             * just using TextView manipulation to add child list item
             * it would be perfect if using recyclerview as list container
             * */
            holder.viewBinding.itemDropdownIv.setOnClickListener {
                if (holder.viewBinding.contentLayout.visibility == View.GONE) {
                    holder.viewBinding.contentLayout.visibility = View.VISIBLE
                    holder.viewBinding.itemDropdownIv.apply {
                        setImageResource(R.drawable.ic_chevron_up)
                    }
                    val layoutParam = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParam.setMargins(16.dp,8.dp,0,8.dp)

                    val lineLayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.dp
                    )
                    lineLayoutParams.marginStart = 16.dp

                    holder.viewBinding.contentLayout.removeAllViews()
                    categoriesListMap[position].jokes.forEach { joke ->
                        var jokeItemTv = TextView(holder.viewBinding.contentLayout.context)
                        jokeItemTv.layoutParams = layoutParam
                        jokeItemTv.text = joke.joke
                        jokeItemTv.setOnClickListener {
                            onCategoriesListener.showDialog((it as TextView).text.toString())
                        }
                        holder.viewBinding.contentLayout.addView(jokeItemTv)

                        val line = View(holder.viewBinding.contentLayout.context)
                        line.layoutParams = lineLayoutParams
                        line.setBackgroundColor(
                            ContextCompat.getColor(
                                holder.viewBinding.contentLayout.context,
                                R.color.grey
                            )
                        )
                        holder.viewBinding.contentLayout.addView(line)
                    }
                } else {
                    holder.viewBinding.itemDropdownIv.apply {
                        setImageResource(R.drawable.ic_chevron_down)
                    }
                    holder.viewBinding.contentLayout.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int = categoriesListMap.size

    /**
     * abstraction for list item behaviour. The implementation are in MainActivity class
     * */
    interface OnCategoriesListener{
        fun goTop(value:CategoryMapResult)
        fun showDialog(data:String)
    }
}