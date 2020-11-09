package com.example.infinitescroll.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.infinitescroll.R
import com.igreenwood.loupe.Loupe

/**
 * Open image for zoom, there are some improvements to be done here!
 */
class ImageDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_image, null)

            builder.setView(view)

            val image = requireArguments()["image"] as String
            val imageView = view.findViewById<ImageView>(R.id.image)
            val constraint = view.findViewById<ConstraintLayout>(R.id.constraint)

            context?.let { context ->
                Glide.with(context).load(image).into(imageView)
            }

            Loupe.create(imageView, constraint) { // imageView is your ImageView
                onViewTranslateListener = object : Loupe.OnViewTranslateListener {

                    override fun onStart(view: ImageView) {
                    }

                    override fun onViewTranslate(view: ImageView, amount: Float) {
                    }

                    override fun onRestore(view: ImageView) {
                    }

                    override fun onDismiss(view: ImageView) {
                        dismiss()
                    }
                }
            }

            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")

    }

    companion object {
        fun getInstance(image: String): ImageDialog {
            val bundle = Bundle().apply { putSerializable("image", image) }
            return ImageDialog()
                .apply { arguments = bundle }
        }
    }

}