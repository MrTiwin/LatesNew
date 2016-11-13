package pe.edu.utp.latesnew.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.utp.latesnew.R;
import pe.edu.utp.latesnew.models.Article;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder>{
    List<Article> articles;

    public void setArticles(List<Article> articles){
        this.articles = articles;
    }
    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_article, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, int position) {
        holder.titleTextView.setText(articles.get(position).getTitle());
        holder.pictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.pictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.pictureANImageView.setImageUrl(articles.get(position).getUrlToImage());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView articleCardView;
        ANImageView pictureANImageView;
        TextView titleTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            articleCardView = (CardView) itemView.findViewById(R.id.articleCardView);
            pictureANImageView = (ANImageView) itemView.findViewById(R.id.pictureANImageView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
        }
    }
}
