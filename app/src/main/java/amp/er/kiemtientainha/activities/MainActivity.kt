package amp.er.kiemtientainha.activities

import amp.er.kiemtientainha.R
import amp.er.kiemtientainha.binding.viewBinding
import amp.er.kiemtientainha.databinding.ActivityMainBinding
import amp.er.kiemtientainha.fragments.BannerAdapter
import amp.er.kiemtientainha.fragments.MenuAdapter
import amp.er.kiemtientainha.fragments.ViewPagerAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity(),
MenuAdapter.Listener {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val adapter by lazy {
        ViewPagerAdapter(this)
    }

    private val bannerAdapter by lazy {
        BannerAdapter()
    }

    private val menuAdapter by lazy {
        MenuAdapter(binding.bottomNavigationView.menu, this)
    }

    val slideTimer = 3000L

    val sliderHandler = Handler(Looper.getMainLooper())

    val sliderRunnable = Runnable {
        val nextBanner = binding.navDrawer.itemPager.currentItem + 1
        binding.navDrawer.itemPager.setCurrentItem(
            if (bannerAdapter.itemCount - 1 < nextBanner) 0 else nextBanner,
            true
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        with(binding){
            fragmentViewPager.adapter = adapter
            fragmentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setFragmentPager(position)
                }
            })

            bottomNavigationView.setOnItemSelectedListener {menu ->
                when(menu.itemId){
                    R.id.main -> setCurrentItem(0)
                    R.id.money -> setCurrentItem(1)
                    R.id.about -> setCurrentItem(2)
                    R.id.tips -> setCurrentItem(3)
                    else -> {}
                }
                true
            }

            appButton.setOnClickListener {
                openDrawer()
            }

            with(navDrawer){
                itemPager.adapter = bannerAdapter
                itemPager.registerOnPageChangeCallback(object :
                    ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        sliderHandler.removeCallbacks(sliderRunnable)
                        sliderHandler.postDelayed(sliderRunnable, slideTimer)
                    }
                })

                menuRecycler.adapter = menuAdapter
                menuRecycler.layoutManager = LinearLayoutManager(this@MainActivity)


                btnExit.setOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit Application?")
            .setMessage("Do you want to exit?")
            .setPositiveButton("Ok"){ _,_ -> super.onBackPressed() }
            .setNegativeButton("Cancel"){ d, _ -> d.dismiss()}
            .show()
    }

    private fun setFragmentPager(position: Int) {
        when(position){
            0 -> setSelectedNav(R.id.main)
            1 -> setSelectedNav(R.id.money)
            2 -> setSelectedNav(R.id.about)
            3 -> setSelectedNav(R.id.tips)
        }
        closeDrawer()
    }

    fun setSelectedNav(id: Int){
        binding.bottomNavigationView.selectedItemId = id
    }

    private fun closeDrawer() {
        binding.drawer.closeDrawer(
            GravityCompat.START
        )
    }

    private fun openDrawer() {
        binding.drawer.openDrawer(
            GravityCompat.START
        )
    }

    private fun setCurrentItem(position: Int){
        binding.fragmentViewPager.setCurrentItem(position, true)
    }

    companion object {
       fun getStartIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    override fun onItemClick(position: Int) {
        setFragmentPager(position)
    }
}