package amp.er.kiemtientainha.fragments

import amp.er.kiemtientainha.data.Data
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
    ) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return DetailsFragment(Data.contents[position])
    }

    override fun getItemCount(): Int {
        return Data.contents.size
    }
}