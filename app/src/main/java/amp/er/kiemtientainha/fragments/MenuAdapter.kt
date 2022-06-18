package amp.er.kiemtientainha.fragments

import amp.er.kiemtientainha.R
import amp.er.kiemtientainha.data.Data
import amp.er.kiemtientainha.databinding.ItemMenuBinding
import amp.er.kiemtientainha.databinding.ItemNavPagerBinding
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(
    private val menu: Menu,
    private val listener: Listener
): RecyclerView.Adapter<MenuAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_menu, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding){
             val item = menu[position]
            menuItemView.text = item.title

            menuItemView.setCompoundDrawablesWithIntrinsicBounds(
                item.icon.apply {
                    setTint(ContextCompat.getColor(root.context, R.color.white))
                },
                null, null,null)

            root.setOnClickListener {
                listener.onItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int = Data.contents.size

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding by lazy {
            ItemMenuBinding.bind(itemView)
        }
    }

    interface Listener {
        fun onItemClick(position: Int)
    }
}