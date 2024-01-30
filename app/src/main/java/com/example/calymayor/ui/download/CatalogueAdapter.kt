package com.example.calymayor.ui.download

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calymayor.R
import com.example.calymayor.data.remote.dto.CatalogueDTO
import com.example.calymayor.data.remote.dto.SanitAbastecimiento
import com.example.calymayor.databinding.ItemDownloadCatalogueBinding
import com.example.calymayor.ui.download.CatalogueAdapter.CatalogueHolder

class CatalogueAdapter(
    private val list: List<SanitAbastecimiento>,
    private val context: Context
): RecyclerView.Adapter<CatalogueHolder>(){

    class CatalogueHolder(
        private val binding: ItemDownloadCatalogueBinding,
        private val context: Context
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: SanitAbastecimiento){
            with(binding){
                tvIdProvision.text = item.idAbastecimiento.toString().ifEmpty { context.getString(R.string.no_info) }
                tvTypeProvision.text = item.tipoAbastecimiento.ifEmpty { context.getString(R.string.no_info) }
                tvCreateBy.text = item.usuarioCreacion.ifEmpty { context.getString(R.string.no_info) }
                tvModifyBy.text = item.usuarioModificacion.ifEmpty { context.getString(R.string.no_info) }
                tvDeleteBy.text = item.usuarioEliminacion.ifEmpty { context.getString(R.string.no_info) }
                tvDateCreate.text = item.fechaCreacion.ifEmpty { context.getString(R.string.no_info) }
                tvDateModify.text = item.fechaModificacion.ifEmpty { context.getString(R.string.no_info) }
                tvDateDelete.text = item.fechaEliminacion.ifEmpty { context.getString(R.string.no_info) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueHolder {
        return CatalogueHolder(
            ItemDownloadCatalogueBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), context)
    }

    override fun onBindViewHolder(holder: CatalogueHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}