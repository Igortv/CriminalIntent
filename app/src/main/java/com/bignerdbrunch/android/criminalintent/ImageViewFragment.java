package com.bignerdbrunch.android.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class ImageViewFragment extends DialogFragment {
    private static final String ARG_PHOTO_FILE = "photo_file";

    private File mPhotoFile;
    private ImageView mPhotoView;

    public static ImageViewFragment newInstance(File photoFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO_FILE, photoFile);

        ImageViewFragment fragment = new ImageViewFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.image_crime, null);

        mPhotoFile = (File) getArguments().getSerializable(ARG_PHOTO_FILE);

        mPhotoView = (ImageView) v.findViewById(R.id.image_dialog_crime_photo);

        Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
        mPhotoView.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.image_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

}
