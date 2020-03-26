package demo.com.groupchildstestdemo.adapter;

import android.content.Context;
import android.content.res.Resources;


import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import demo.com.groupchildstestdemo.R;
import demo.com.groupchildstestdemo.model.GroupChildsModel;
import java.util.ArrayList;

public class GroupChildAdapter extends RecyclerView.Adapter<GroupChildAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GroupChildsModel> modelsAll = new ArrayList<>();

    public GroupChildAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final GroupChildsModel groupChildsModel = modelsAll.get(position);
        holder.textView.setText(groupChildsModel.name);
        holder.tvDesignation.setText(groupChildsModel.designation);

        holder.itemView.setTag(R.string.MODEL, groupChildsModel);
        holder.itemView.setTag(R.string.position, position);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.rlContent.getLayoutParams();
        layoutParams.setMargins(((int) convertDpToPixel(20, context)) * groupChildsModel.level, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);

        switch (groupChildsModel.state) {

            case CLOSED:
                holder.imgArrow.setImageResource(R.drawable.svg_arrow_right_filled);
                break;
            case OPENED:
                holder.imgArrow.setImageResource(R.drawable.svg_arrow_down_filled);
                break;
        }

        if (groupChildsModel.childsModels.isEmpty()) {
            holder.imgArrow.setVisibility(View.INVISIBLE);
            holder.viewDashed.setVisibility(View.VISIBLE);
        } else {
            holder.imgArrow.setVisibility(View.VISIBLE);
            holder.viewDashed.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (int) v.getTag(R.string.position);
                GroupChildsModel rootModel = (GroupChildsModel) v.getTag(R.string.MODEL);
                if (rootModel.childsModels.isEmpty()) {
                    return;
                }
                switch (rootModel.state) {

                    case CLOSED:
                        modelsAll.addAll(position + 1, rootModel.childsModels);
                        notifyItemRangeInserted(position + 1, rootModel.childsModels.size());
                        notifyItemRangeChanged(position + rootModel.childsModels.size(), modelsAll.size() - (position + rootModel.childsModels.size()));
                        notifyItemRangeChanged(position, modelsAll.size() - position);
                        rootModel.state = GroupChildsModel.STATE.OPENED;
                        break;

                    case OPENED:

                        int start = position + 1;
                        int end = modelsAll.size();
                        for (int i = start; i < modelsAll.size(); i++) {
                            GroupChildsModel model1 = modelsAll.get(i);
                            if (model1.level <= rootModel.level) {
                                end = i;
                                break;
                            } else {
                                if (model1.state == GroupChildsModel.STATE.OPENED) {
                                    model1.state = GroupChildsModel.STATE.CLOSED;
                                }
                            }
                        }

                        if (end != -1) {
                            modelsAll.subList(start, end).clear();
                            notifyItemRangeRemoved(start, end - start);
                            notifyItemRangeChanged(start, end - start);
                            notifyItemRangeChanged(position, modelsAll.size() - position);
                        }

                        rootModel.state = GroupChildsModel.STATE.CLOSED;
                        break;
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return modelsAll.size();
    }

    public void setData(ArrayList<GroupChildsModel> list) {
        modelsAll = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlContent;
        TextView textView;
        TextView tvDesignation;
        ImageView imgArrow;
        View viewDashed;

        public ViewHolder(View itemView) {

            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvName);
            imgArrow = (ImageView) itemView.findViewById(R.id.imgArrow);
            rlContent = (RelativeLayout) itemView.findViewById(R.id.rlContent);
            viewDashed = itemView.findViewById(R.id.viewDashed);
            tvDesignation = (TextView) itemView.findViewById(R.id.tvDesignation);
        }
    }


    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


}
