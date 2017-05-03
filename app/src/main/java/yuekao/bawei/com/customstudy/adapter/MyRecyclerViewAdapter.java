package yuekao.bawei.com.customstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yuekao.bawei.com.customstudy.R;
import yuekao.bawei.com.customstudy.bean.Data;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/5/2 10:11
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
  private   List<Data.DataBean> list;
    private Context context;

    public MyRecyclerViewAdapter(List<Data.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item,null);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
             holder.jieshao.setText(list.get(position).getIntroduction());
        holder.name.setText(list.get(position).getTitle());
        holder.year.setText(list.get(position).getUserAge()+"");
        holder.zhiye.setText(list.get(position).getOccupation());
        Glide.with(context).load(list.get(position).getUserImg()).into(holder.iv);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private  ImageView iv;
        private  TextView jieshao;
        private  TextView name;
        private  TextView year;
        private  TextView zhiye;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_iv);
            jieshao = (TextView) itemView.findViewById(R.id.item_tv_jieshao);
            name = (TextView) itemView.findViewById(R.id.item_tv_name);
            year = (TextView) itemView.findViewById(R.id.item_tv_year);
            zhiye = (TextView) itemView.findViewById(R.id.item_tv_zhi);

        }
    }

}
