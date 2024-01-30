package com.example.calymayor.ui.load

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calymayor.R
import com.example.calymayor.data.db.entities.SelectOptionsEntity
import com.example.calymayor.databinding.ItemSelectionsBinding
import com.example.calymayor.ui.load.LoadCatalogueAdapter.LoadCatalogueHolder

class LoadCatalogueAdapter (
    private val list: List<SelectOptionsEntity>,
    private val context: Context,
    private val listener: (SelectOptionsEntity, ActionListener) -> Unit
): RecyclerView.Adapter<LoadCatalogueHolder>(){

    class LoadCatalogueHolder(
        private val binding: ItemSelectionsBinding,
        private val context: Context,
        private val listener: (SelectOptionsEntity, ActionListener) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(selectOptionsEntity: SelectOptionsEntity){
            with(binding){
                tvSelectionNameProvision.text = when(selectOptionsEntity.idProvision){
                    1 ->{
                        context.getString(R.string.papper)
                    }
                    2 ->{
                        context.getString(R.string.towels)
                    }
                    3 ->{
                        context.getString(R.string.deodorant)
                    }
                    4 ->{
                        context.getString(R.string.soap)
                    }
                    5 ->{
                        context.getString(R.string.water)
                    }
                    else -> {
                        context.getString(R.string.no_info)
                    }
                }
                checkboxProvision1.setOnCheckedChangeListener { compoundButton, b ->
                    if(b){
                        listener(selectOptionsEntity, ActionListener.CLICK_CHECKBOX_YES)
                        checkboxProvision2.isChecked = false
                        checkboxProvision3.isChecked = false
                    }
                }

                checkboxProvision2.setOnCheckedChangeListener { compoundButton, b ->
                    if(b){
                        listener(selectOptionsEntity, ActionListener.CLICK_CHECKBOX_NO)
                        checkboxProvision1.isChecked = false
                        checkboxProvision3.isChecked = false
                    }
                }

                checkboxProvision3.setOnCheckedChangeListener { compoundButton, b ->
                    if(b){
                        listener(selectOptionsEntity, ActionListener.CLICK_CHECKBOX_NA)
                        checkboxProvision1.isChecked = false
                        checkboxProvision2.isChecked = false
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadCatalogueHolder {
        return LoadCatalogueHolder(
            ItemSelectionsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), context, listener
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LoadCatalogueHolder, position: Int) {
        holder.bind(list[position])
    }

}

enum class ActionListener {
    CLICK_CHECKBOX_YES,
    CLICK_CHECKBOX_NO,
    CLICK_CHECKBOX_NA,
}