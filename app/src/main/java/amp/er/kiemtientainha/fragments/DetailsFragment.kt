package amp.er.kiemtientainha.fragments

import amp.er.kiemtientainha.R
import amp.er.kiemtientainha.base.BaseFragment
import amp.er.kiemtientainha.binding.viewBinding
import amp.er.kiemtientainha.data.DataModel
import amp.er.kiemtientainha.databinding.FragmentDetailsBinding
import amp.er.kiemtientainha.databinding.FragmentMainBinding
import androidx.core.content.ContextCompat

class DetailsFragment(
    private val content: DataModel
): BaseFragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    override fun setupViews() {
        with(binding){
            title.text = content.title
            description.text = content.content
            banner.setBackgroundResource(content.resource)
        }
    }

    override fun viewModelObservers() {

    }
}