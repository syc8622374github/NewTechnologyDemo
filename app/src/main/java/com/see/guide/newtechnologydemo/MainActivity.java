package com.see.guide.newtechnologydemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(fragmentOne.newInstance("1","2"), "TabOne");//添加Fragment
        viewPagerAdapter.addFragment(fragmentOne.newInstance("1","2"), "TabTwo");
        viewPagerAdapter.addFragment(fragmentOne.newInstance("1","2"), "TabThree");
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("TabOne"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("TabTwo"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TabThree"));
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题

        /*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvToDoList);
        recyclerView.setHasFixedSize(true);//固定
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        initData();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerAdapter adapter = new RecyclerAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);*/
    }

    private void initData() {
        data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(i+"cycnice");
        }
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {
        //添加的Fragment的集合
        private final List<Fragment> mFragments = new ArrayList<>();
        //每个Fragment对应的title的集合
        private final List<String> mFragmentsTitles = new ArrayList<>();

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * @param fragment      添加Fragment
         * @param fragmentTitle Fragment的标题，即TabLayout中对应Tab的标题
         */
        public void addFragment(Fragment fragment, String fragmentTitle) {
            mFragments.add(fragment);
            mFragmentsTitles.add(fragmentTitle);
        }

        @Override
        public Fragment getItem(int position) {
            //得到对应position的Fragment
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            //返回Fragment的数量
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //得到对应position的Fragment的title
            return mFragmentsTitles.get(position);
        }
    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(MainActivity.this,"dasdas",Toast.LENGTH_SHORT).show();
    }

    class RecyclerAdapter extends RecyclerView.Adapter{
        OnItemClickListener mOnItemClickListener;
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(MainActivity.this,R.layout.item_main,null);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RecyclerHolder recyclerHolder = (RecyclerHolder) holder;
            recyclerHolder.textView.setText(data.get(position));
            recyclerHolder.setOnItemClickListener(mOnItemClickListener);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textView;
        OnItemClickListener mOnItemClickListener;
        public View view;


        public RecyclerHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            textView = (TextView) view.findViewById(R.id.tv_title);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
