package kh.edu.rupp.fe.visitme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.fe.visitme.model.api.model.Province2
import kh.edu.rupp.fe.visitme.databinding.ViewHolderProvinceBinding
import kh.edu.rupp.fe.visitme.model.api.model.Province

class ProvincesAdapter2: ListAdapter<Province, ProvincesAdapter2.ProvinceViewHolder>(

    object : DiffUtil.ItemCallback<Province>() {
        override fun areItemsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem.id == newItem.id
        }

    }

) {

    var onProvinceClickListener: ((Int, Province) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderProvinceBinding.inflate(layoutInflater, parent, false)
        return ProvinceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onProvinceClickListener?.invoke(position, item)
        }
    }

    class ProvinceViewHolder(val itemBinding: ViewHolderProvinceBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(province: Province) {
            Picasso.get().load(province.imageUrl).into(itemBinding.imgProvince)
            itemBinding.txtName.text = province.name
        }

    }

}