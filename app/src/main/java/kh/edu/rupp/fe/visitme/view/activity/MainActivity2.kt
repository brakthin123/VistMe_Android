package kh.edu.rupp.fe.visitme.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kh.edu.rupp.fe.visitme.R
import kh.edu.rupp.fe.visitme.databinding.ActivityMainBinding
import kh.edu.rupp.fe.visitme.view.activity.fragment.HomeFragment
import kh.edu.rupp.fe.visitme.view.activity.fragment.MoreFragment
import kh.edu.rupp.fe.visitme.view.activity.fragment.ProfileFragment
import kh.edu.rupp.fe.visitme.view.activity.fragment.ProvincesFragment
import kh.edu.rupp.fe.visitme.view.activity.fragment.SearchFragment

class MainActivity2: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("This Activity is using Kotlin")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Show HomeFragment
        showFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.mnuHome -> showFragment(HomeFragment())
                R.id.mnuProvinces -> showFragment(ProvincesFragment())
                R.id.mnuSearch -> showFragment(SearchFragment())
                R.id.mnuProfile -> showFragment(ProfileFragment())
                else -> showFragment(MoreFragment())
            }

            true
        }
    }

    private fun showFragment(fragment: Fragment) {

        // FragmentTransaction
        val fragmentTransition = supportFragmentManager.beginTransaction()

        // Replace fragment in lytFragment
        fragmentTransition.replace(R.id.lytFragment, fragment)

        // Commit transaction
        fragmentTransition.commit()

    }

}