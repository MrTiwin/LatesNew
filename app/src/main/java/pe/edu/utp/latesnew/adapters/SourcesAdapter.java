package pe.edu.utp.latesnew.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.utp.latesnew.LatesNewsApp;
import pe.edu.utp.latesnew.R;
import pe.edu.utp.latesnew.activities.ArticlesActivity;
import pe.edu.utp.latesnew.models.Source;

public class SourcesAdapter
        extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {
    List<Source> sources;

    public void setSources(List<Source> sources){
        this.sources = sources;
    }

    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_source,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SourcesAdapter.ViewHolder holder, final int position) {
        holder.nameTextView.setText(sources.get(position).getName());
        holder.logoANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.logoANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.logoANImageView.setImageUrl(sources.get(position).getUrlToSmallLogo());
        holder.sourceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatesNewsApp.getInstace().setCurrentSource(sources.get(position));
                Intent articlesIntent = new Intent(view.getContext(), ArticlesActivity.class);
                view.getContext().startActivity(articlesIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView sourceCardView;
        ANImageView logoANImageView;
        TextView nameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            sourceCardView = (CardView) itemView.findViewById(R.id.sourceCardView);
            logoANImageView = (ANImageView) itemView.findViewById(R.id.logoANImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
