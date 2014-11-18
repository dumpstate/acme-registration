package com.dumpstate.acme.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.classowl.app.R;

import junit.framework.Assert;

/**
 * Class for holding references to view items of RegistrationFragment.
 */
public class RegistrationViewHolder {

    /**
     * Factory method making RegistrationViewHolder out of a rootView.
     * Knows the IDs of the fragment View items.
     */
    public static RegistrationViewHolder get(View rootView) {
        Assert.assertNotNull(rootView);

        RegistrationViewHolder holder = new RegistrationViewHolder();

        holder.mEntitiesSpinner = (Spinner)rootView.findViewById(R.id.registration_entities_spinner);
        holder.mEntitiesProgressBar = (ProgressBar)rootView.findViewById(R.id.registration_entities_progress_bar);
        holder.mUserSpinner = (Spinner)rootView.findViewById(R.id.registration_user_spinner);
        holder.mFirstNameEditText = (EditText)rootView.findViewById(R.id.registration_firstname_edittext);
        holder.mLastNameEditText = (EditText)rootView.findViewById(R.id.registration_lastname_edittext);
        holder.mEmailEditText = (EditText)rootView.findViewById(R.id.registration_email_edittext);
        holder.mPasswordEditText = (EditText)rootView.findViewById(R.id.registration_password_edittext);
        holder.mSignUpButton = (Button)rootView.findViewById(R.id.registration_signup_button);
        holder.mTermsAndCondTextView = (TextView)rootView.findViewById(R.id.registration_termsandcond_textview);

        Assert.assertNotNull(holder.mEntitiesSpinner);
        Assert.assertNotNull(holder.mEntitiesProgressBar);
        Assert.assertNotNull(holder.mUserSpinner);
        Assert.assertNotNull(holder.mFirstNameEditText);
        Assert.assertNotNull(holder.mLastNameEditText);
        Assert.assertNotNull(holder.mEmailEditText);
        Assert.assertNotNull(holder.mPasswordEditText);
        Assert.assertNotNull(holder.mSignUpButton);
        Assert.assertNotNull(holder.mTermsAndCondTextView);

        return holder;
    }

    public Spinner mEntitiesSpinner;
    public ProgressBar mEntitiesProgressBar;
    public Spinner mUserSpinner;
    public EditText mFirstNameEditText;
    public EditText mLastNameEditText;
    public EditText mEmailEditText;
    public EditText mPasswordEditText;
    public Button mSignUpButton;
    public TextView mTermsAndCondTextView;
}