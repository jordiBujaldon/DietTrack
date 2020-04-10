/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.pis2020.activities.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider

import com.example.pis2020.databinding.BarcodeFieldBinding
import com.example.pis2020.ia.barcodescanner.BarcodeField
import com.example.pis2020.ia.camera.WorkflowModel

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/** Displays the bottom sheet to present barcode fields contained in the detected barcode.  */
class BarcodeResultFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        val binding = BarcodeFieldBinding.inflate(layoutInflater, viewGroup, false)

        val arguments = arguments
        val barcodeFieldList: ArrayList<BarcodeField> =
                if (arguments?.containsKey(ARG_BARCODE_FIELD_LIST) == true) {
                    arguments.getParcelableArrayList(ARG_BARCODE_FIELD_LIST) ?: ArrayList()
                } else {
                    Log.e(TAG, "No barcode field list passed in!")
                    ArrayList()
                }

        binding.barcodeFieldValue.text = barcodeFieldList[0].value

        return binding.root
    }

    override fun onDismiss(dialogInterface: DialogInterface) {
        activity?.let {
            // Back to working state after the bottom sheet is dismissed.
            ViewModelProvider(it).get<WorkflowModel>(WorkflowModel::class.java)
                    .setWorkflowState(WorkflowModel.WorkflowState.DETECTING)
        }
        super.onDismiss(dialogInterface)
    }

    companion object {

        private const val TAG = "BarcodeResultFragment"
        private const val ARG_BARCODE_FIELD_LIST = "arg_barcode_field_list"

        fun show(fragmentManager: FragmentManager, barcodeFieldArrayList: ArrayList<BarcodeField>) {
            val barcodeResultFragment =
                BarcodeResultFragment()
            barcodeResultFragment.arguments = Bundle().apply {
                putParcelableArrayList(ARG_BARCODE_FIELD_LIST, barcodeFieldArrayList)
            }
            barcodeResultFragment.show(fragmentManager,
                TAG
            )
        }

        fun dismiss(fragmentManager: FragmentManager) {
            (fragmentManager.findFragmentByTag(TAG) as BarcodeResultFragment?)?.dismiss()
        }
    }
}
