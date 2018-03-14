

import android.support.v7.widget.RecyclerView
import android.view.View

class ViewWrapper<in D, V>(var view: V) : RecyclerView.ViewHolder(view) where V : View, V: ViewWrapper.Binder<D> {

    interface Binder<in D> {
        fun bind(data: D)
    }

}