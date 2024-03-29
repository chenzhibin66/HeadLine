package com.nuc.calvin.headline.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    private FragmentActivity fragmentActivity;

    public View getMyView() {
        return myView;
    }

    public View myView;
    //可用来Fragment与Activity之间传送数据
    protected OnFragmentInteractionListener mListener;
    protected Handler handler;


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, myView);
        fragmentActivity = getSupportActivity();
        Fresco.initialize(getContext());
        initView(myView);
        return myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initView(myView);
       /* handler = initHandle();*/
    }


   /* protected abstract Handler initHandle();*/

    /**
     * 初始化UI
     * <p>
     * //@param savedInstanceState
     */
    protected abstract void initView(View view);

    /**
     * @return
     */
    protected abstract int getContentView();

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
