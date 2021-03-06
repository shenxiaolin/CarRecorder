package com.mitu.carrecorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mitu.carrecorder.R;
import com.mitu.carrecorder.entiy.FileEntity;

import java.util.ArrayList;
import java.util.Map;


/**
 * 说明：照片列表显示
 * 2016/6/20 0020
 */
public class PhotoListviewAdapter extends BaseAdapter {

    private Context mContext;
    private Map<String,ArrayList<FileEntity>> mPhotoList;
    private ArrayList<String> dateList;


    public PhotoListviewAdapter(Context cx, Map<String,ArrayList<FileEntity>> mPhotoList,ArrayList<String> dateList) {
        this.mContext = cx;
//		this.ImageArrs = arr;
        this.mPhotoList = mPhotoList;
        this.dateList = dateList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (dateList == null) {
            return 0;
        } else {
            return this.dateList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        if (dateList == null) {
            return null;
        } else {
            return this.dateList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
       // final int[] resourceId = dateList.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.fragment_listview_photo_item, null, false);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_fragment_time);
            holder.gridView = (GridView) convertView.findViewById(R.id.listview_item_gridview);
            holder.more = (RelativeLayout) convertView.findViewById(R.id.more_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (this.dateList != null) {
            if (holder.tvTime != null) {
                holder.tvTime.setText(dateList.get(position));
            }
            if (holder.gridView != null) {
//
                GridviewPhotoNormalAdapter gridViewAdapter = new GridviewPhotoNormalAdapter(mContext,
                        mPhotoList.get(dateList.get(position)));
                holder.gridView.setAdapter(gridViewAdapter);


//                final String dateStr = dateList.get(position);
                final int listviewIndex = position;
                holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(mContext, BigPictureActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("photoList",mPhotoList.get(dateList.get(position)));
//                        bundle.putString("dateStr",dateStr);
//                        bundle.putInt("posi",position);
//                        intent.putExtras(bundle);
//                        mContext.startActivity(intent);
                        mlistener.OnItemClick(mContext,position,listviewIndex);
                    }
                });


            }
        }
        return convertView;
    }

    public OnGridviewItemClickListener mlistener;
    public interface OnGridviewItemClickListener{
        public void OnItemClick(Context context,int gridviewIndex,int listviewIndex);
    };

    public void setOnGridViewItemClickListener(OnGridviewItemClickListener listener){
        mlistener = listener;
    }

    private class ViewHolder {
        private TextView tvTime;
        private RelativeLayout more;
        private GridView gridView;
    }

}
