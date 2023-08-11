package com.example.projectfrontend.classUnderstanding;

import com.example.projectfrontend.R;

public class UnderstandingItem {

    static int[] icon = {
            R.drawable.ic_descr, R.drawable.ic_manage, R.drawable.ic_params,
            R.drawable.ic_principles, R.drawable.ic_contrac

    };

    static int [] title = {
            R.string.whatIs, R.string.businessManagement, R.string.systemParameter,
            R.string.inventivePrinciples, R.string.contradictionMatrix
    };

    static ItemModel.Type[] type = {
            ItemModel.Type.ABOUT, ItemModel.Type.BAM, ItemModel.Type.PARAMETER, ItemModel.Type.PRINCIPLE, ItemModel.Type.MATRIX
    };
}
