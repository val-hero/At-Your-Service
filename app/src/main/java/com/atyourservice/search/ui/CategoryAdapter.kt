package com.atyourservice.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atyourservice.search.domain.model.Category
import com.example.atyourservice.R
import com.example.atyourservice.databinding.MainCategoryCardBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private val category = ArrayList<Category>()

    fun setCategory(allCategory: List<Category>) {
        category.addAll(allCategory)
    }

    class CategoryHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = MainCategoryCardBinding.bind(item)
        fun bind(model: Category) = with(binding) {
            imageCategory.setImageResource(model.id)
            textCategory.text = model.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_category_card, parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int = category.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(category[position])
    }

}
