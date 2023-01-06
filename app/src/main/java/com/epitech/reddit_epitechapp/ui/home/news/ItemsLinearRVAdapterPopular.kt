package com.epitech.reddit_epitechapp.ui.home.news

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epitech.reddit_epitechapp.R
import com.epitech.reddit_epitechapp.models.ChildrenPopularModelId
import com.epitech.reddit_epitechapp.utils.Constants.Companion.VIEW_TYPE_ITEM
import com.epitech.reddit_epitechapp.utils.Constants.Companion.VIEW_TYPE_LOADING
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.linear_item_row_news.view.*
import kotlinx.android.synthetic.main.progress_loading.view.*

class ItemsLinearRVAdapterPopular(private var itemsCells: ArrayList<ChildrenPopularModelId?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var mcontext: Context

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addData(dataViews: ArrayList<ChildrenPopularModelId?>) {
        this.itemsCells.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun addLoadingView() {
        Handler().post {
            itemsCells.add(null)
            notifyItemInserted(itemsCells.size - 1)
        }
    }

    fun removeLoadingView() {
        if (itemsCells.size != 0) {
            itemsCells.removeAt(itemsCells.size - 1)
            notifyItemRemoved(itemsCells.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mcontext = parent.context
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.linear_item_row_news, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(mcontext).inflate(R.layout.progress_loading, parent, false)
            view.progressbar.indeterminateDrawable.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return itemsCells.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemsCells[position] == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_ITEM) {
            var tmp: ChildrenPopularModelId? = itemsCells[position]

            if (tmp != null) {
                if (tmp.data.icon_img != null && tmp.data.icon_img.isNotEmpty()) {
                    Picasso.get().load(tmp.data.icon_img).into(holder.itemView.img_thumbnail)
                } else {
                    Picasso.get().load("https://www.redditstatic.com/avatars/avatar_default_02_A5A4A4.png").into(holder.itemView.img_thumbnail)

                }
            }
            if (tmp != null) {
                holder.itemView.display_name_prefixed.text = tmp.data.display_name_prefixed
            }
            if (tmp != null) {
                holder.itemView.display_name.text = tmp.data.display_name
            }
            if (tmp != null) {
                holder.itemView.subscribers.text = tmp.data.subscribers.toString()
            }
            if (tmp != null) {
                holder.itemView.title.text = tmp.data.title
            }
            if (tmp != null) {
                holder.itemView.public_description.text = tmp.data.public_description
            }
            if (tmp != null) {
                if (tmp.data.banner_img != null && tmp.data.banner_img.isNotEmpty()) {
                    Picasso.get().load(tmp.data.banner_img).into(holder.itemView.banner_img)
                } else {
                    holder.itemView.banner_img.setImageResource(0)
                }
            }
        }
    }

}
