package com.example.navcomponent

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.navcomponent.databinding.FragmentRootBinding


/**
 * The root screen. Can launch [BoxFragment] passing background color as an argument.
 */
class RootFragment : Fragment(R.layout.fragment_root){

    private lateinit var binding: FragmentRootBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)


        binding.openYellowBox.setOnClickListener {
            openBox(Color.rgb(255, 255, 200), getString(R.string.yellow))

        }

        binding.openGreenButton.setOnClickListener {
            openBox(Color.rgb(200, 255, 200), getString(R.string.green))
        }

        val liveData =
            findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(
                BoxFragment.EXTRA_RANDOM_NUMBER
            )
        //listening for the result from BoxFragment



        listenResult<Int>(BoxFragment.EXTRA_RANDOM_NUMBER) { randomNumber ->
                Toast.makeText(
                    requireContext(),
                    getString(R.string.generated_number, randomNumber),
                    Toast.LENGTH_SHORT
                ).show()

        }
    }





    private fun openBox(color: Int, colorName: String) {

        //launch box fragment with arguments  and additional options
        val direction = RootFragmentDirections.actionRootFragmentToBoxFragment(color, colorName)
        findNavController().navigate(
            direction,
            navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit =  R.anim.pop_exit

                }
            }
        )
    }
}