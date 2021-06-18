package com.developer.projetounivesp2021_frontrnd.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.developer.projetounivesp2021_frontrnd.R;
import com.developer.projetounivesp2021_frontrnd.interfaces.Constants;

public abstract class BaseFragment extends Fragment implements Constants {

    private final FragmentManager mMamanger;
    private final Bundle mExtras;

    public BaseFragment(){
        assert getActivity() != null;
        mMamanger = getActivity().getSupportFragmentManager();
        mExtras = getActivity().getIntent().getExtras();
    }

    public BaseFragment(FragmentManager Mamanger) {
        this.mMamanger = Mamanger;
        this.mExtras = new Bundle();
    }

    public BaseFragment(FragmentManager mMamanger, Bundle mExtras) {
        this.mMamanger = mMamanger;
        this.mExtras = mExtras;
    }

    protected FragmentManager getFragmentManger() {
        return this.mMamanger;
    }

    protected Bundle getExtras() {
        return mExtras != null ? mExtras : new Bundle();
    }

    public abstract int getTitle();

    protected abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

}
