package eclass.dogking.com.oneclass.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import eclass.dogking.com.oneclass.AdminUpdata1;
import eclass.dogking.com.oneclass.AdminUpdata4;
import eclass.dogking.com.oneclass.R;
import eclass.dogking.com.oneclass.entiry.Exam;
import eclass.dogking.com.oneclass.entiry.Exercise;
import eclass.dogking.com.oneclass.entiry.UserExerciseShow;

/**
 * Created by dog on 2018/3/10 0010.
 */


public class Manager4sAdapter extends RecyclerView.Adapter<Manager4sAdapter.ViewHolder> {

    private List<Exercise> list;
    private Context mContext;
    private LayoutInflater inflater;
    protected RvListener listener;

    public Manager4sAdapter(Context context,  List<Exercise> exercises, RvListener listener) {
        this.list =exercises;
        this.mContext = context;
        this.listener = listener;
        inflater = LayoutInflater.from(mContext);
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.admin_exam, viewGroup, false);
        ViewHolder vh = new ViewHolder(view,listener);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final Exercise exercise = list.get(position);
        viewHolder.t1.setText(exercise.getName());



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Exercise exercise=list.get(position);
                Intent intent=new Intent(v.getContext(),AdminUpdata4.class);
                intent.putExtra("Lid",exercise.getId()+"");//传递课程
                v.getContext().startActivity(intent);

                //Toast.makeText(v.getContext(),"你点击的课为："+lectureShow1.getLectureName(),Toast.LENGTH_SHORT).show();
            }

        });



    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView t1;
        public Button b1;
        protected RvListener mListener;

        public ViewHolder(View view,RvListener listener) {
            super(view);
            this.mListener=listener;
            t1 = (TextView) view.findViewById(R.id.userexercise_name);
            b1=(Button) view.findViewById(R.id.delete);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v.getId(), getAdapterPosition());
                }
            });

        }

    }
}