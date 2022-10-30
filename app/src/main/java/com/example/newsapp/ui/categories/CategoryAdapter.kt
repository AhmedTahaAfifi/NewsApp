package com.example.newsapp.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.Category

//class CategoryAdapter(val category: List<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//       val view = LayoutInflater.from(parent.context)
//           .inflate(if(viewType == LEFT_SIDED_CATEGORY_TYPE)R.layout.left_sided_category
//               else R.layout.right_sided_category
//               ,parent,false)
//
//        return ViewHolder(view)
//    }
//
//    val LEFT_SIDED_CATEGORY_TYPE = 10
//    val RIGHT_SIDED_CATEGORY_TYPE = 20
//    override fun getItemViewType(position: Int): Int {
//        return if (position%2==0) LEFT_SIDED_CATEGORY_TYPE
//        else RIGHT_SIDED_CATEGORY_TYPE
//    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = category[position]
//        holder.title.setText(item.titleId)
//        holder.image.setImageResource(item.imageResId)
//    }
//
//    override fun getItemCount(): Int {
//        return category.size
//    }
//
//    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
//        val title: TextView = itemView.findViewById(R.id.title)
//        val image: ImageView = itemView.findViewById(R.id.image)
//
//    }
//}

class CategoryAdapter(private val category: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                if (viewType == LEFT_SIDED_CATEGORY_TYPE) R.layout.left_sided_category
                else R.layout.right_sided_category, parent, false
            )
        return ViewHolder(view)
    }

    val LEFT_SIDED_CATEGORY_TYPE = 10
    val RIGTH_SIDED_CATEGORY_TYPE = 20
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) LEFT_SIDED_CATEGORY_TYPE
        else RIGTH_SIDED_CATEGORY_TYPE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = category[position]
        holder.title.setText(item.titleId)
        holder.image.setImageResource(item.imageResId)
        onItemClickListener.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, item)
            }
        }
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(pos: Int, category: Category)
    }

    override fun getItemCount(): Int {
        return category.size
    }
}