package mx.edu.itm.link.dadm_u3proyb.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.dadm_u3proyb.R
import mx.edu.itm.link.dadm_u3proyb.ui.GlobalViewModel

class NotificationsFragment : Fragment() {

    private val viewModel: GlobalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        return root
    }
}