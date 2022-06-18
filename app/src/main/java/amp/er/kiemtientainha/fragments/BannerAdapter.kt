package amp.er.kiemtientainha.fragments

import amp.er.kiemtientainha.R
import amp.er.kiemtientainha.data.Data
import amp.er.kiemtientainha.databinding.ItemNavPagerBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class BannerAdapter: RecyclerView.Adapter<BannerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_nav_pager, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding){
            image.setBackgroundResource(Data.contents[position].resource)
        }
    }

    override fun getItemCount(): Int = Data.contents.size

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding by lazy {
            ItemNavPagerBinding.bind(itemView)
        }
    }
}