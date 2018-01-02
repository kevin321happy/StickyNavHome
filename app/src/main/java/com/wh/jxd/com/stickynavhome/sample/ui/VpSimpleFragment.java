package com.wh.jxd.com.stickynavhome.sample.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.wh.jxd.com.stickynavhome.BaseFragment;
import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.sample.ui.adapter.CampaignAdapter;

public class VpSimpleFragment extends BaseFragment
{
	public static final String BUNDLE_TITLE = "title";
	private String mType = "DefaultValue";
	private RecyclerView mRcv;


	@Override
	protected void initView(View view, Bundle savedInstanceState) {
		Bundle arguments = getArguments();
		if (arguments != null)
		{
			mType = arguments.getString(BUNDLE_TITLE);
		}
		mRcv= (RecyclerView) view.findViewById(R.id.rev_campaign);
		mRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
		CampaignAdapter campaignAdapter = new CampaignAdapter();
		mRcv.setAdapter(campaignAdapter);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_campaign;
	}

	public static VpSimpleFragment newInstance(String type)
	{
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_TITLE, type);
		VpSimpleFragment fragment = new VpSimpleFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
}
